import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // listen on all interfaces (0.0.0.0) - ensure docker port mapping 
    port: 3000
  }
})
