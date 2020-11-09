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
import store from "./store"

var axios = require('axios');
//axios.defaults.baseURL = "http://106.14.44.210:8090";
axios.defaults.baseURL = "http://127.0.0.1:8090"
Vue.prototype.$axios = axios;
Vue.prototype.$store = store;
Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(Antd);
Vue.use(AMap);

router.beforeEach((to,from,next) => {
  if(to.meta.requireAuth) {
    console.log("跳转前" + store.state.user.username);
    if(store.state.user.username) {
      next();
    } else {
      next({
        path:'/login',
        query:{redirect: to.fullPath}
      })
    }
  } else {
    next();
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
