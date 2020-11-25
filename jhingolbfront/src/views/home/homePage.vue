<template>
  <el-container>
    <el-header style="background-color: #f1f1f1">
      <el-menu
        :default-active="'index'"
        router
        mode="horizontal"
        text-color="#222"
        active-text-color="#409EFF"
        background-color="#f1f1f1"
      >
        <el-menu-item style="float: right" v-for="(item,i) in navList" :key="i" :index="item.name">
          {{ item.navItem }}
        </el-menu-item>
        <span id="title" style="color: #222;float: left;padding: 20px;text-decoration: none;line-hieght:1">云溢清寒</span>
      </el-menu>
    </el-header>
    <el-main style="background-color: white">

        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
          <el-col :span="20" style="padding-right: 10px">
            <router-view v-if="showRouterView"/>
          </el-col>
          <el-col :span="2"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
        </el-row>
      </el-main>
  </el-container>

</template>

<script>
import Calendar from "vue-calendar-component"
import article from "../index/article";
export default {
  name: "head.vue",
  components: {
    Calendar
  },
  data() {
    return {
      navList: [
        {name:'about',navItem:'关于'},
        {name:'tags',navItem:'标签'},
        {name: 'category',navItem: '分类'},
        //{name:'article',navItem:'文章'},
        {name:'index',navItem:'首页'},
      ],
      allTags:[],
      articleArchive:[],
      searchArticle: "",
      activeColor:"dodgerblue",
      pointer:"pointer",
      city:"",
      locationId:"",
      //温度
      temp:"",
      //天气状况
      situation:"",
      //空气质量
      airCondition:"",
      //天气图标编号
      icon:"",
      image:"",
      ip:"",
      showRouterView:true
    }
  },
  methods: {
    mouseOverColor: function () {
      this.activeColor = "blue";
    },
    mouseDownColor: function () {
      this.activeColor = "dodgerblue"
    },
    //重新加载页面
    reload: function() {
      this.showRouterView = false;
      this.$nextTick(() => (this.showRouterView = true))
    }
  },
  mounted() {

  }
}
</script>

<style lang="scss">
#title{
  position: absolute;
  padding-top: 20px;
  font-size: 20px;
  font-weight: bold
}
el-header{
  padding:0px;
  margin:0px;
  border:0px;
}
.el-tag.el-tag--success {
  margin:3px
}
.el-menu.el-menu--horizontal{
  border-bottom: solid 0px
}
.mainBox {
  width:100%;
  width:100%;
  .wh_content_all {
    background-color: #ffffff;
    .wh_top_changge {
      li {
        color: #1fb331;
        .wh_jiantou2 {
          border-top-color: #0d0b0b;
          border-right-color: #0d0b0b;
        }
        .wh_jiantou1{
          border-top-color: #0d0b0b;
          border-left-color: #0d0b0b;
        }
      }
    }
    .wh_content_item {
      .wh_item_date {
        color:#1ae558;
      }
      .wh_other_dayhide{
        color:#bfbfbf;
      }
      .wh_chose_day {
        background: #d1ff19;
        color: #0d0b0b;
      }
      .wh_isToday{
        background: #33ad53;
        color: #0d0b0b;
      }
      .wh_top_tag{
        color: #0d0b0b;
      }
    }
  }
}
.weatherInfo {
  font-size:17px;
  font-weight: lighter;
  padding: 5px;
}
.weather {
  background-color: #f1f1f1;
}
.weatherImg {
  height: 20px;
  width: 20px
}


</style>
