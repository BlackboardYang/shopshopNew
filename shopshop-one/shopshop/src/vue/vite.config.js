import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

import { useDark, useToggle } from '@vueuse/core'

const isDark = useDark()
const toggleDark = useToggle(isDark)

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        host: true,
        port: 80,
    },
  plugins: [vue(),
  AutoImport({
               resolvers: [ElementPlusResolver()],
             }),
  Components({
               resolvers: [ElementPlusResolver({importStyle: "sass"})],
             }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `
                    @use "@/styles/element/index.scss" as *;
                    @use "@/styles/var.scss" as *;
                    `,
            }
        }
    }
})