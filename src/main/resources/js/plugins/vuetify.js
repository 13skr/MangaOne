import Vue from 'vue'
// import Vuetify from "js/plugins/vuetify"
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify)

const opts = {
    theme: {
        dark: true
    }
}

export default new Vuetify(opts)