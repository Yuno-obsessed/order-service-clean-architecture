FROM node:16

WORKDIR /app

COPY . .

RUN npm install

# After installing dependencies, copy the rest of your project files into the image
EXPOSE 5173

CMD ["npm", "run", "dev"]
