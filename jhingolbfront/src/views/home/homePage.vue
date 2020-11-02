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
          <el-col :span="15" style="padding-right: 10px">
            <router-view v-if="showRouterView"/>
          </el-col>
          <el-col :span="5">
            <div>
              <el-input placeholder="..." v-model="searchArticle">
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </div>
            <el-divider></el-divider>
            <div class="weather">
              <span class="weatherInfo">{{temp}}</span> &nbsp;
              <span class="weatherInfo">{{city}}</span>&nbsp;
              <span class="weatherInfo">{{situation}}</span>
              <img :src="image">
              <span v-if="airCondition == '优'" style="color: limegreen" class="weatherInfo">{{airCondition}}</span>
              <span v-if="airCondition == '良'" style="color: orange" class="weatherInfo">{{airCondition}}</span>
              <span v-if="airCondition == '轻度污染'" style="color: darkorange" class="weatherInfo">{{airCondition}}</span>
              <span v-if="airCondition == '中度污染'" style="color: red" class="weatherInfo">{{airCondition}}</span>
              <span v-if="airCondition == '重度污染'" style="color: purple" class="weatherInfo">{{airCondition}}</span>
              <span v-if="airCondition == '严重污染'" style="color: darkred" class="weatherInfo">{{airCondition}}</span>
            </div>
            <el-divider></el-divider>
            <div class="mainBox">
              <Calendar></Calendar>
            </div>
            <el-divider></el-divider>
            <div class="tags">
                <h3 style="border: #222222;padding-left: 5px">标签</h3>
              <el-tag :key="tag.id"
                      style="cursor: pointer;padding-bottom: 10px"
                      type="success"
                      v-for="tag in allTags"
                      :disable-transitions="true"
                      @click="showArticleList(tag)"
                      size="small"
              >
                {{tag.name}}
              </el-tag>
            </div>
            <el-divider></el-divider>
            <div class="archive">
              <h3 style="border: #222222;padding-left: 5px">文章归档</h3>
              <ul>
                <li
                    v-for="item in articleArchive" :key="item.id" :index="item.id">
                  <el-button size="small" type="text" @click="getArticleByArchive(item.modifiedTime)">{{item.archive}}</el-button>
                </li>
              </ul>
            </div>
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
    loadTags:function() {
      this.$axios({
        methods: "get",
        url: "tags/getTagsListInUse"
      }).then((response) => {
        this.allTags = response.data.data;
      })
    },
    loadArticleDate: function () {
      this.$axios({
        methods: "get",
        url: "article/getArticleDate",
      }).then((response) => {
        this.articleArchive = response.data.data;
      })
    },
    mouseOverColor: function () {
      this.activeColor = "blue";
    },
    mouseDownColor: function () {
      this.activeColor = "dodgerblue"
    },
    getWhether: function() {
      var locationId = this.locationId;
      this.$axios({
        methods: "get",
        url:"https://devapi.qweather.com/v7/weather/now?location=" + locationId + "&key=c777ad6141464ba4bee5675ed13ed0ed"
      }).then((response) => {
          var data = response.data.now;
          this.temp = data.temp + "℃";
          this.situation = data.text;
          this.icon = data.icon;
          this.image = require("../../common/static/img/weatherIcon/" + this.icon + ".png");
          this.getAirCondition();
      })
    },
    getAirCondition: function() {
      var locationId = this.locationId;
      this.$axios({
        methods: "get",
        url: "https://devapi.qweather.com/v7/air/now?location=" + locationId + "&key=c777ad6141464ba4bee5675ed13ed0ed"
      }).then((response) => {
          this.airCondition = response.data.now.category;
      })
    },
    getLocationID: function () {
      var city = this.city;
      if(city.charAt(city.length) == '市') {
        city = city.substring(0,city.length - 1);
      }
      this.$axios({
        methods:"get",
        url:"https://geoapi.qweather.com/v2/city/lookup?location=" + city + "&key=c777ad6141464ba4bee5675ed13ed0ed"
      }).then((response) => {
        this.locationId = response.data.location[0].id;
        this.getWhether();
      })
    },
    getLocation: function() {
      this.$axios({
        methods:"get",
        url:"https://restapi.amap.com/v3/ip?key=779959b6cd7d724db94bbfbef33d15a8"
      }).then((response) => {
        this.city = response.data.city;
        this.getLocationID();
      })
    },
    showArticleList: function (tag) {
      this.$router.push({name: "article", params: {tagId: tag.id,type:tag.id}})
      this.reload();
    },
    getArticleByArchive: function (modifiedTime) {
      this.$router.push({name: "article",params: {modifiedTime: modifiedTime,type:modifiedTime}});
      this.reload();
    },
    //重新加载页面
    reload: function() {
      this.showRouterView = false;
      this.$nextTick(() => (this.showRouterView = true))
    }
  },
  mounted() {
    this.loadTags();
    this.loadArticleDate();
    this.getLocation();
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
  font-size:20px;
  font-weight: lighter;
  padding: 5px;
}
.weather {
  background-color: #f1f1f1;
}


</style>
