// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import Antd from 'ant-design-vue'
import 'element-ui/lib/theme-chalk/index.css'
import 'ant-design-vue/dist/antd.css'
import AMap from "vue-amap"

var axios = require('axios');
//axios.defaults.baseURL = "http://106.14.44.210:8090";
axios.defaults.baseURL = "http://127.0.0.1:8090"
Vue.prototype.$axios = axios;
Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(Antd);
Vue.use(AMap);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
