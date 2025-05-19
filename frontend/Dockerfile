 # React build aşaması
FROM node:18-alpine as build
WORKDIR /app
COPY . .
RUN npm install && npm run build

# Nginx ile serve et
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
