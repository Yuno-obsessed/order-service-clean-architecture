import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import svgrPlugin from "vite-plugin-svgr"; // Для імпорту SVG як React компонентів

function viteSassGlob() {}

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [viteSassGlob(), react(), svgrPlugin({})],
  assetsInclude: ["**/*.svg"],
  resolve: {},
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@import "./src/sass/_variables.scss";`,
      },
    },
  },
});
