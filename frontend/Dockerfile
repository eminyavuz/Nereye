# React build aşaması
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm cache clean --force
RUN npm install --legacy-peer-deps
RUN npm install -g react-scripts@5.0.1
COPY . .
ENV PATH /app/node_modules/.bin:$PATH
RUN npm run build

# Nginx ile serve et
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
