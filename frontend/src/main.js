import Vue from 'vue';
import App from './App.vue';
// import { router } from './router';
import Axios from 'axios'
import router from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import {library} from '@fortawesome/fontawesome-svg-core';
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome';
import {faEdit, faHome, faSignInAlt, faSignOutAlt, faUser, faUserPlus} from '@fortawesome/free-solid-svg-icons';
// import '@fortawesome/fontawesome-free/css/all.css'

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt, faEdit);

Vue.config.productionTip = false;

Vue.prototype.$http = Axios;

Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.use(require('vue-moment'));

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');