import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueAxios from "vue-axios"
import axios from "axios";

const app = createApp(App).use(router)
app.use(VueAxios, axios)
app.provide("axios", app.config.globalProperties.axios)
app.mount("#app")
