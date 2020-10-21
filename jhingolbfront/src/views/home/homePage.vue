<template>
  <el-container>
      <el-aside style="width: 10%"></el-aside>
      <el-main>
              <el-menu
              :default-active="'index'"
              router
              mode="horizontal"
              background-color="white"
              text-color="#222"
              active-text-color="#409EFF"
            >
              <el-menu-item style="float: right" v-for="(item,i) in navList" :key="i" :index="item.name">
                {{ item.navItem }}
              </el-menu-item>
              <span id="title" style="color: #222;float: left;padding: 20px;text-decoration: none;line-hieght:1">云溢清寒</span>
            </el-menu>
        <el-row style="padding-top: 20px">
          <el-col :span="18" style="padding-right: 20px">
            <router-view/>
          </el-col>

          <el-col :span="6">
            <div>
              <el-card shadow="always">
                <div slot="header" class="clearfix">
                  <span>tags</span>
                </div>
                <span v-for="o in 4" :key="o" class="text item">
                  {{'标签' + o}}
                </span>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </el-main>
    <el-aside style="width: 10%"></el-aside>
  </el-container>

</template>

<script>
export default {
  name: "head.vue",
  data() {
    return {
      navList: [
        {name:'about',navItem:'关于'},
        {name:'tags',navItem:'标签'},
        {name:'article',navItem:'文章'},
        {name:'index',navItem:'首页'},
      ],
    }
  },
  methods: {

  }
}
</script>

<style scoped>
#title{
  position: absolute;
  padding-top: 20px;
  font-size: 20px;
  font-weight: bold
}
</style>
