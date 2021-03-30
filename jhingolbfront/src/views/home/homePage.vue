<template>
  <el-container>
    <el-header style="background-color: gray;height: auto;position: fixed; top: 0;width: 100%;z-index: 1">
      <el-menu
        :default-active="'index'"
        router
        mode="horizontal"
        text-color="white"
        active-text-color="white"
        background-color="gray"
      >
        <el-menu-item style="float: right;border-bottom-color: transparent;" v-for="(item,i) in navList" :key="i" :index="item.name">
          {{ item.navItem }}
        </el-menu-item>
        <div id="title" class="title" @click="backToIndex()" style="color: white">云溢清寒</div>
      </el-menu>
    </el-header>
    <div class="scrollBox">
      <div style="width: 100%; background-color: gray">
        <div class="contain">
          <div class="row">
            <div class="site-heading">
              <h1 style="color: white">云溢清寒</h1>
              <span class="subheading">放弃幻想，准备战斗</span>
            </div>
          </div>
        </div>
      </div>
    <el-main style="background-color: white">
        <el-row>
          <el-col :span="24" style="padding-right: 10px">
            <router-view v-if="showRouterView"/>
          </el-col>
        </el-row>
    </el-main>
    </div>
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
    },
    backToIndex: function() {
      this.$router.push({name: "index"})
    }
  },
  mounted() {

  }
}
</script>

<style lang="scss">
#title{
  position: absolute;
  font-size: 20px;
  font-weight: bold;
  color: #222;
  float: left;
  padding: 20px;
  text-decoration: none;
  line-hieght:1;
  cursor: pointer;
  /**点击div出现蓝框，是因为出现点击div时它获取了焦点，使用outline:none去除**/
  outline:none;
}
el-header{
  padding:0px;
  margin:0px;
  border:0px;
  line-height: 500px;
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
@media (min-width: 768px) {
  .contain {
    width: 750px;
  }
}
@media (min-width: 992px) {
  .contain {
    width: 970px;
  }
}
@media (min-width: 1200px) {
  .contain {
    width: 1170px;
  }
}
.contain {
  padding-left: 15px;
  padding-right: 15px;
  margin-left: auto;
  margin-right: auto;
}
.contain:before {
  display: table;
}
.site-heading {
  text-align: center;
}
@media only screen and (min-width: 768px) {
  .site-heading {
    padding: 150px 0;
  }
  h1 {
    font-size: 80px;
  }
}
.site-heading {
  padding: 95px 0 70px;
}
.subheading {
  line-height: 1.7;
  font-size: 18px;
  display: block;
  font-weight: 300;
  margin: 10px 0 0;
  color: white;
}
h1 {
  font-family: -apple-system,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
  line-height: 1.1;
  font-weight: 700;
}
.scrollBox {
  position: absolute;
  padding: 0!important;
  left: 0;
  right: 0;
  top: 60px;
}
</style>
