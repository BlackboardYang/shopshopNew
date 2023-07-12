const { fileURLToPath, URL } = require('node:url');
const { defineConfig } = require('vite');
const vue = require('@vitejs/plugin-vue');

const AutoImport = require('unplugin-auto-import/vite');
const Components = require('unplugin-vue-components/vite');
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers');

const { useDark, useToggle } = require('@vueuse/core');

const isDark = useDark();
const toggleDark = useToggle(isDark);

module.exports = defineConfig({
    server: {
        host: true,
        port: 80,
    },
    plugins: [
        vue(),
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver({ importStyle: "sass" })],
        }),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
        },
    },
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `
          @use "@/styles/element/index.scss" as *;
          @use "@/styles/var.scss" as *;
        `,
            },
        },
    },
    // https://cli.vuejs.org/config/#devserver-proxy
    server: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8081',
                ws: true,
                changeOrigin: true,
            },
        },
    },
});
