<template>
  <el-container>
    <el-aside style="width: 10%"></el-aside>
    <el-main >
      <el-header>
        <el-menu class="el-menu-demo" mode="horizontal">
          <h2>后台管理</h2>
        </el-menu>
      </el-header>
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
    <el-aside style="width: 10%"></el-aside>
  </el-container>
</template>

<script>
export default {
  name: "management.vue",
  data() {
    return {
      navList: [
        {name:'editArticle',navItem:'文章编辑',activeClass: "el-icon-edit"},
        {name:'manageTags',navItem:'标签管理',activeClass: "el-icon-collection-tag"}
      ]
    }
  }
}
</script>

<style scoped>
</style>
