FROM node:alpine
WORKDIR /app
COPY ../../frontend/horus/package.json ./
COPY ../../frontend/horus/package-lock.json ./
COPY ../../frontend/horus ./
RUN npm i
CMD ["npm", "run", "start"]