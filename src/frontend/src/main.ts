import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

// import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';
import "bootstrap/dist/css/bootstrap.min.css"
import VTooltip from 'v-tooltip'

// Vue.use(VTooltip);

const app = createApp(App)
app.use(VTooltip)
app.mount('#app')
