import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';
import './components';

import '@/utils/flexible'
import "@/assets/css/teamicon.css";
import "@/assets/css/icon.css";
import VueAnimateNumber from "vue-animate-number";
Vue.use(VueAnimateNumber);


Vue.use(ElementUI);
import './assets/css/animate.css'
import './assets/css/hover.css'

import 'fullpage.js/vendors/scrolloverflow';
import VueFullPage from 'vue-fullpage.js';

Vue.use(VueFullPage)

import VueLazyLoad from 'vue-lazyload';
Vue.use(VueLazyLoad,{
  loading:require('../src/assets/image/paiyun.gif')
})
Vue.use(VueLazyLoad)


// 首页
import VueSeamlessScroll from 'vue-seamless-scroll'
Vue.use(VueSeamlessScroll)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')



