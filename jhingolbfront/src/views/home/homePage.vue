<template>
  <el-container>
    <el-header class="mainHeading">
      <el-menu
        :default-active="'index'"
        router
        mode="horizontal"
        text-color="rgb(102,102,102)"
        active-text-color="rgb(102,102,102)"
        background-color="white"
        style="width: 100%;height: auto"
      >
        <el-menu-item style="float: right;border-bottom-color: transparent;" v-for="(item,i) in navList" :key="i" :index="item.name">
          {{ item.navItem }}
        </el-menu-item>
        <div id="title" class="title" @click="backToIndex()" style="rgb(70,70,70)">云溢清寒</div>
      </el-menu>
    </el-header>
    <el-row>
      <el-col :span="24">
        <router-view v-if="showRouterView"/>
      </el-col>
    </el-row>
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
.mainHeading {
  background-color: white;height: auto;position: fixed; top: 0;width: 100%;z-index: 1;padding: 0 0 0 0;opacity: 0.9
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
//.head2 {
//  background: url('https://i.loli.net/2017/02/08/589ac29fe230a.jpg');
//  background-repeat: no-repeat;
//  background-size: cover;
//  height: 400px;
//}
//@media (min-width: 768px) {
//  .contain {
//    width: 750px;
//  }
//}
//@media (min-width: 992px) {
//  .contain {
//    width: 970px;
//  }
//}
//@media (min-width: 1200px) {
//  .contain {
//    width: 1170px;
//  }
//}
//.contain {
//  padding-left: 15px;
//  padding-right: 15px;
//  margin-left: auto;
//  margin-right: auto;
//}

</style>
