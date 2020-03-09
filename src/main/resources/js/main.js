import Vue from 'vue'
import App from './App.vue'
import router from "./router/router"
import store from "./store/store"
import vuetify from "./plugins/vuetify";
import '@babel/polyfill'

new Vue({
    el: '#app',
    router,
    store,
    vuetify,
    template: '<App/>',
    render: x => x(App)
})