FROM node:16.16.0 as build-step
RUN mkdir -p /usr/local/app
WORKDIR /usr/local/app
COPY ./ /usr/local/app
RUN npm install
RUN npm run build --prod

FROM nginx:1.23.1
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build-step /usr/local/app/dist/frontend /usr/share/nginx/html

EXPOSE 80

# docker build -t frontend:1.0 .
# docker image list
# docker tag frontend:1.0 alansastre/frontend:1.0
# docker login
# hub.docker.com
# docker push alansastre/frontend:1.0
