<template>
  <div>
    <div class="head2" style="width: 100%;">
      <div class="contain">
        <div class="row">
          <div class="site-heading">
            <h1 class='titleH1' style="color: white">云溢清寒</h1>
            <span class="subheading">放弃幻想，准备战斗</span>
          </div>
        </div>
      </div>
    </div>
    <el-row class="indexContainer">
      <el-col :lg="16" :offset="2" :md="16" :sm="12" class="articleList" style="padding-right: 3%">
        <div v-for="(item,i) in firstPageArticle" :key="i"
             :index="item.id">
          <h2 class="post-title">{{ item.title }}</h2>
          <div class="post-content-preview">
            这里是这篇文章的摘要
          </div>
          <p class="post-meta">
            Posted by maximus <time>{{ item.modifiedTime }}</time>
          </p>
          <div>
            <el-tag :key="tag.id"
                    style="cursor: pointer"
                    v-for="tag in item.tags"
                    :disable-transitions="true"
                    size="mini"
                    @click="showArticleListByTags(tag.id)"
            >{{tag.name}}</el-tag>
          </div>
          <hr>
        </div>
        <div class="bottom clearfix">
          <el-button v-show="firstPageArticle.length > 5" @click="readMore()" class="button" style="float: right;margin-right: 20px">more<i class="el-icon-d-arrow-right"></i></el-button>
        </div>
      </el-col>
      <el-col :lg="6" :md="6" :sm="12" style="padding-right: 3%">
        <el-divider></el-divider>
        <div class="personInfo">
          <h5>关于我</h5>
          <a>
            <img class="avatar" src="/static/image/avatar.jpg">
          </a>
          <p style="color: #bfbfbf; font-size: 14px;">一只逐渐开始喜欢写代码的菜鸡</p>
          <nav class="state">
            <div class="item posts">
              <a>
                <span class="count">47</span>
                <span class="name">文章</span>
              </a>
            </div>
            <div class="item categories">
              <a>
                <span class="count">47</span>
                <span class="name">分类</span>
              </a>
            </div>
            <div class="item tags">
            <a>
              <span class="count">15</span>
              <span class="name">标签</span>
            </a>
            </div>
          </nav>
          <div class="socialList">
            <a class="social zhihu" style="padding: 0" target="_blank" href="https://www.zhihu.com/people/vincent-young-82">ZHIHU</a>
            <a class="social github" target="blank" href="https://github.com/maximusyoung007">GITHUB</a>
            <a class="social email" target="_blank" href="mailto:yangwenjun1996@foxmail.com">EMAIL</a>
          </div>
        </div>
        <el-divider></el-divider>
        <div class="tags">
          <h5>标签</h5>
          <el-tag :key="tag.id"
                  style="cursor: pointer;padding-bottom: 10px"
                  type="info"
                  v-for="tag in allTags"
                  :disable-transitions="true"
                  @click="showArticleList(tag)"
                  size="large"
          >
            {{tag.name}}
          </el-tag>
        </div>
        <el-divider></el-divider>
        <div class="archive">
          <h5>文章归档</h5>
          <ul>
            <li
              v-for="item in articleArchive" :key="item.id" :index="item.id">
              <el-button size="small" type="text" @click="getArticleByArchive(item.modifiedTime)">{{item.archive}}</el-button>
            </li>
          </ul>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Calendar from "vue-calendar-component"
import 'highlight.js/styles/vs.css'
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

.el-card{
  margin-bottom: 10px;
}
.el-tag {
  margin-top:3px;
  margin-right: 3px
}
.post-title {
  font-size: 21px;
  line-height: 1.3;
  margin-top: 30px;
  margin-bottom: 8px;
}
@media only screen and (min-width: 768px) {
  .post-title {
    font-size: 26px;
    line-height: 1.3;
    margin-bottom: 10px;
  }
}
h2 {
  font-family: -apple-system,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
  font-weight: 700;
  margin: 30px 0 10px;
}
.post-content-preview {
  font-size: 13px;
  font-style: italic;
  color: #a3a3a3;
}
@media only screen and (min-width: 768px) {
  .post-content-preview {
    font-size: 14px;
  }
}
.post-meta {
  font-family: Lora,'Times New Roman',serif;
  color: gray;
  font-size: 16px;
  font-style: italic;
  margin-top: 0;
}
@media only screen and (min-width: 768px) {
  .post-meta {
    font-size: 18px;
  }
}
p {
  margin: 30px 0;
}
hr {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 0;
  border-top: 1px solid #eee
}
@media (min-width: 768px) {
  .indexContainer {
    width: 750px;
  }
}
@media (min-width: 992px) {
  .indexContainer {
    width: 970px;
  }
}
@media (min-width: 1200px) {
  .indexContainer {
    width: 1170px;
  }
}
.indexContainer {
  padding-right: 15px;
  padding-left: 15px;
  margin-left: auto;
  margin-right: auto;
  /*width: 100%;*/
}
h5 {
  color: gray;
  padding-bottom: 1em;
  font-family: -apple-system,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
  font-size: 14px;
  font-weight: 700;
}
.head2 {
  background: url('https://i.loli.net/2017/02/08/589ac29fe230a.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  height: 400px;
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
@media (max-width: 800px) {
  .head2 {
    height: 240px;
  }
}
.contain {
  padding-left: 15px;
  padding-right: 15px;
  margin-left: auto;
  margin-right: auto;
}
.titleH1 {
  font-family: -apple-system,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
  line-height: 1.1;
  font-weight: 700;
  font-size: 80px;
}
.site-heading {
  text-align: center;
}
@media only screen and (min-width: 768px) {
  .site-heading {
    padding: 150px 0;
  }
}
.site-heading {
  padding: 150px 0 70px;
}
.subheading {
  line-height: 1.7;
  font-size: 18px;
  display: block;
  font-weight: 300;
  margin: 10px 0 0;
  color: white;
}
.avatar {
  width: 80%;
  display: block;
  border-radius: 5px;
  margin-bottom: 20px;
}
.state {
  display: flex;
  /*justify-content: center;*/
  line-height: 1.4;
  color: rgb(102, 102, 102);
}
.item {
  padding: 0 .9375em;
}
.state .item:not(:first-child) {
  border-left: .0625rem solid rgb(204,204,204);
}
a {
  border: none;
  color: currentColor;
  outline: 0;
}
.state .item .count {
  display: block;
  font-size: 1.5em;
  font-weight: 600;
  text-align: center;
}
.state .item .name {
  color: inherit;
  font-size: 1.2em;
}
.socialList {
  padding: 20px .9375em 0px .9375em
}
.socialList a {
  font-size: 13px;
  padding: 0 10px;
  text-decoration: none;
  color: #5f5f5f;
  outline: 0;
}

</style>
