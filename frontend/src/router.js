import Vue from "vue";
import Router from "vue-router";
import Home from './components/Home.vue';
import Login from './components/auth/Login.vue';
import Register from './components/auth/Register.vue';

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/profile',
            name: 'profile',
            // lazy-loaded
            component: () => import('./components/auth/Profile.vue')
        },
        {
            path: '/403',
            name: '403',
            // lazy-loaded
            component: () => import('./components/error/403.vue')
        },
        {
            path: "/equipments",
            name: "equipments",
            component: () => import("./components/equipment/EquipmentsList")
        },
        {
            path: "/equipments/:id",
            name: "equipments-edit",
            component: () => import("./components/equipment/EquipmentEdit")
        },
        {
            path: "/equipmentAdd",
            name: "equipment-add",
            component: () => import("./components/equipment/EquipmentAdd")
        },
        {
            path: "/requests",
            name: "requests",
            component: () => import("./components/request/RequestsList")
        },
        {
            path: "/requests/:id",
            name: "requests-edit",
            component: () => import("./components/request/RequestEdit")
        },
        {
            path: "/requestAdd",
            name: "request-add",
            component: () => import("./components/request/RequestAdd")
        }
    ]
});