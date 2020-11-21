<template>
  <el-container>
    <el-main >
      <el-header>
        <el-menu :default-active="'index'"
                 router
                 mode="horizontal"
                 text-color="#222"
                 active-text-color="#409EFF"
                 >
          <el-menu-item>
            <span id="title" style="color: #222;float: left;text-decoration: none;line-hieght:1">云溢清寒</span>
          </el-menu-item>
          <el-menu-item style="float: right">
            <el-button size="small" type="primary" @click="logout()" style="float: right">退出系统</el-button>
          </el-menu-item>
        </el-menu>
      </el-header>
      <br>
      <el-container>
        <el-aside>
          <el-row class="tac">
            <el-col>
              <el-menu router
                       default-active="'editArticle'"
                       class="aside-menu">
                <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
                  <i :class="item.activeClass"></i>
                  {{item.navItem}}
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "management.vue",
  data() {
    return {
      navList: [
        {name:'articleList',navItem:'文章管理',activeClass: "el-icon-edit"},
        {name:'manageTags',navItem:'标签管理',activeClass: "el-icon-collection-tag"},
        {name:'manageCategory',navItem: '分类管理',activeClass: 'el-icon-folder-opened'}
      ]
    }
  },
  methods: {
    logout: function () {
      this.$axios({
        methods:"get",
        url:"login/logout"
      }).then(response => {
        if(response.data.type == "success") {
          this.$store.commit('logout');
          this.$router.replace('/login');
        }
      })
    }
  }
}
</script>

<style scoped>
#title{
  position: absolute;
  font-size: 20px;
  font-weight: bold
}
</style>
