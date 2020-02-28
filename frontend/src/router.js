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
            path: '/admin',
            name: 'admin',
            // lazy-loaded
            component: () => import('./components/board/BoardAdmin.vue')
        },
        {
            path: '/mod',
            name: 'moderator',
            // lazy-loaded
            component: () => import('./components/board/BoardModerator.vue')
        },
        {
            path: '/user',
            name: 'user',
            // lazy-loaded
            component: () => import('./components/board/BoardUser.vue')
        },
        {
            path: "/equipments",
            name: "equipments",
            component: () => import("./components/equipment/EquipmentsList")
        },
        {
            path: "/equipments/:id",
            name: "equipments-details",
            component: () => import("./components/equipment/Equipment")
        },
        {
            path: "/addEquipment",
            name: "add-equipment",
            component: () => import("./components/equipment/AddEquipment")
        },
        {
            path: "/requests",
            name: "requests",
            component: () => import("./components/request/RequestsList")
        },
        {
            path: "/requests/:id",
            name: "requests-details",
            component: () => import("./components/request/Request")
        },
        {
            path: "/addRequest",
            name: "add-request",
            component: () => import("./components/request/AddRequest")
        }
    ]
});