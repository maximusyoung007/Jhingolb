import Vue from 'Vue';
import Vuex from 'Vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  namespace: true,
  state: {
    //localStorage保存登录状态
    user: {
      username: window.localStorage.getItem('user' || '[]') == null ? ''
        : JSON.parse(window.localStorage.getItem('user' || '[]')).username
    },
  },
  getters: {
    getUser(state) {
      return state.user;
    }
  },
  mutations: {
    login : (state,user) => {
      state.user = user;
      window.localStorage.setItem('user',JSON.stringify(user))
    }
  }
})
