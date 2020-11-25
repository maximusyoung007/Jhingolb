<template>
  <div>
    <el-row>
      <el-col :span="6" style="padding-right: 10px">
        <div>
          <el-input placeholder="..." v-model="searchArticle">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </div>
        <el-divider></el-divider>
        <div class="weather">
          <span class="weatherInfo">{{temp}}</span> &nbsp
          <span class="weatherInfo">{{city}}</span>&nbsp
          <span class="weatherInfo">{{situation}}</span>
          <img :src="image" class="weatherImg">
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
      <el-col :span="18">
      <el-card shadow="never" v-for="(item,i) in firstPageArticle" :key="i"
                 :index="item.id">
          <div slot="header" class="clearfix">
            <div style="font-size: 24px">{{ item.title }}</div>
            <div class="time">
              <i class="el-icon-time"></i>
              <time>{{ item.modifiedTime }}</time>
              &nbsp;
              <i class="el-icon-folder-opened"></i>
              <span>{{ item.category }}</span>
            </div>
            <div>
              <el-tag :key="tag.id"
                      style="cursor: pointer"
                      v-for="tag in item.tags"
                      :disable-transitions="true"
                      size="mini"
                      @click="showArticleListByTags(tag.id)"
              >{{tag.name}}</el-tag>
            </div>
          </div>
          <div class="item-content" v-html="item.articleBody">
          </div>
  <!--        <div class="bottom clearfix">-->
  <!--          <el-button @click="readMore(item.id)" type="text" class="button" style="float: right">阅读更多<i class="el-icon-d-arrow-right"></i></el-button>-->
  <!--        </div>-->
        </el-card>
      <div class="bottom clearfix">
        <el-button @click="readMore()" class="button" style="float: right">更多文章<i class="el-icon-d-arrow-right"></i></el-button>
      </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Calendar from "vue-calendar-component"
export default {
  name: "index.vue",
  components: {
    Calendar
  },
  data() {
    return {
      firstPageArticle:[],
      searchArticle: "",
      temp:"",
      city:"",
      situation:"",
      airCondition:"",
      image:"",
      allTags:[],
      articleArchive:[]
    }
  },
  mounted:function() {
    this.getFirstPageArticleList();
    this.loadTags();
    this.loadArticleDate();
    this.getLocation();
  },
  methods: {
    getFirstPageArticleList:function () {
      this.$axios({
        method: "get",
        url:"article/getFirstPageArticleList"
      }).then((response) => {
        this.firstPageArticle = response.data.data;
      })
    },
    readMore: function () {
      this.$router.push({name: "article"})
    },
    showArticleListByTags : function(id) {
      this.$router.push({name: "article",params: {tagId: id}})
    },
    showArticleList: function (tag) {
      this.$router.push({name: "article", params: {tagId: tag.id,type:tag.id}})

    },
    getArticleByArchive: function (modifiedTime) {
      this.$router.push({name: "article",params: {modifiedTime: modifiedTime,type:modifiedTime}});
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
  }
}
</script>

<style scoped>
.time{
  font-size: 13px;
  color:#999;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}
>>>blockquote {
  display: block;
  border-left: 8px solid #d0e5f2;
  padding: 5px 10px;
  margin: 10px 0;
  line-height: 1.4;
  font-size: 100%;
  background-color: #f1f1f1;
}

/* code 样式 */
>>>code {
  display: inline-block;
  *display: inline;
  *zoom: 1;
  background-color: #f1f1f1;
  border-radius: 3px;
  padding: 3px 5px;
  margin: 0 3px;
}
>>>pre code {
  display: block;
}
/*.item-content {*/
/*  max-width: 100%;*/
/*  word-break: break-all;*/
/*  overflow: hidden;*/
/*  display: -webkit-box;*/
/*  -webkit-box-orient: vertical;*/
/*  -webkit-line-clamp: 5;*/
/*  color: dimgrey;*/
/*}*/

.el-card{
  margin-bottom: 10px;
}
.el-tag {
  margin-top:3px;
  margin-right: 3px
}
</style>
