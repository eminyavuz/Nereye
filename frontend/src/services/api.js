import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  },
  withCredentials: true
});
axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (typeof error.response === "undefined") {
      console.log("network error");
      window.location.href = "/error-page";
    }
    if (error.response.status === 401) {
      // Authorization error
      window.location.href = "/signin";
    } else if (error.response.status === 500) {
      // Server error
      window.location.href = "/500-error";
    } else {
      return Promise.reject(error);
    }
  }
);

api.interceptors.request.use(
    config => {
      const token = localStorage.getItem('token');
      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
      }
      return config;
    },
    error => Promise.reject(error)
  );

api.interceptors.response.use(
    response => response,
    error => {
      if (error.response?.status === 401) {
        localStorage.removeItem('token');
        window.location.href = '/login';
      }
      return Promise.reject(error);
    }
  );

export const userService = {
  login: (credentials) => api.post('/user/login', credentials),
  register: (userData) => api.post('/user/save', userData),
  getById: (id) => api.get(`/user/get/${id}`),
  update: (id, data) => api.put(`/user/update/${id}`, data),
  delete: (id) => api.delete(`/user/delete/${id}`)
};



export default api;