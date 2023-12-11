FROM node:16

WORKDIR /app

# First, copy the package.json (and package-lock.json if available) into the image
COPY package*.json ./

# Then, run npm install to install dependencies
RUN npm install

# After installing dependencies, copy the rest of your project files into the image
COPY . .

EXPOSE 5173

CMD ["npm", "run", "dev"]
