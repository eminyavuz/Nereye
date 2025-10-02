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

// Token gerektirmeyen API instance (public endpoints için)
const publicApi = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  },
  withCredentials: false
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
  getAll: () => api.get('/user/getAll'),
  update: (id, data) => api.put(`/user/update/${id}`, data),
  delete: (id) => api.delete(`/user/delete/${id}`),
  getCurrentUser: () => api.get('/auth/me')
};

export const advertisementService = {
  create: (advertisementData) => api.post('/advertisement/save', advertisementData),
  getAll: () => api.get('/advertisement/getAll'),
  getById: (id) => api.get(`/advertisement/get/${id}`),
  update: (id, data) => api.put(`/advertisement/update/${id}`, data),
  delete: (id) => api.delete(`/advertisement/delete/${id}`),
  rent: (advertisementData, tenantId) => api.put('/advertisement/rent', advertisementData, { params: { id: tenantId } }),
  cancelRent: (adId) => api.put(`/advertisement/cancel-rent/${adId}`),
  getMyAds: () => api.get('/advertisement/get-my-ads'),
  getMyRentedAds: () => api.get('/advertisement/get-my-rented-ads')
};

export const carService = {
  getAll: () => api.get('/car/getAll'),
  getById: (id) => api.get(`/car/get/${id}`),
  create: (carData) => api.post('/car/save', carData),
  update: (data) => api.put('/car/update', data),
  delete: (id) => api.delete(`/car/delete/${id}`)
};

export const brandService = {
  getAll: () => publicApi.get('/brand/getAll'),
  getById: (id) => publicApi.get(`/brand/get/${id}`),
  create: (data) => api.post('/brand/save', data),
  update: (id, data) => api.put(`/brand/update/${id}`, data),
  delete: (id) => api.delete(`/brand/delete/${id}`)
};

export const colorService = {
  getAll: () => publicApi.get('/color/getAll'),
  getById: (id) => publicApi.get(`/color/get/${id}`),
  create: (data) => api.post('/color/save', data),
  update: (id, data) => api.put(`/color/update/${id}`, data),
  delete: (id) => api.delete(`/color/delete/${id}`)
};

export default api;