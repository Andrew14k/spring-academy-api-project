# build react app with node.js
FROM node:24-alpine as build

# set work directory
WORKDIR /app

# copy packages into the working directory
COPY package.json package-lock.json ./

# install npm dependencies into container
RUN npm install

# copy all other source files into container
COPY . .

# port 3000
EXPOSE 3000

# default run command
CMD ["npm", "run", "dev"]