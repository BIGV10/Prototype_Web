import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            alias: "/equipments",
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
        }
    ]
});