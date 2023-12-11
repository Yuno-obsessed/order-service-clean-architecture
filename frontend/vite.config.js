import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import svgrPlugin from "vite-plugin-svgr"; // Для імпорту SVG як React компонентів

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), svgrPlugin({})],
  assetsInclude: ["**/*.svg"],
  resolve: {},
});
