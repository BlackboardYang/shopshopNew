import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/welcome',
            name: 'welcome',
            component: () => import("@/views/WelcomeView.vue"),
            children: [
                {
                    path: 'login',
                    name: 'welcome-login',
                    component: () => import("@/components/welcome/LoginPage.vue")
                },{
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import("@/components/welcome/RegisterPage.vue")
                },{
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import("@/components/welcome/ForgetPage.vue")
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component:()=> import("@/components/welcome/logout.vue")
        }, {
            path: '/',
            component:()=> import("@/views/LayoutView.vue"),
            children: [
                {
                    path: '',
                    component:()=> import("@/views/HomeView.vue")
                }]
        }

    ]
})

export default router
