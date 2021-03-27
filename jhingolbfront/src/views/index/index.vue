<template>
  <div>
    <el-row class="indexContainer">
      <el-col :lg="16" :offset="2" :md="15" :sm="12" class="articleList" style="padding-right: 5%">
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
      <el-col :lg="6" :md="6" :sm="12" style="padding-right: 10px">
        <hr style="padding-top: 20px">
        <div>
          <el-input placeholder="..." v-model="searchArticle">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </div>
        <el-divider></el-divider>
        <div class="tags">
          <h5>标签</h5>
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
}
h5 {
  color: gray;
  padding-bottom: 1em;
  font-family: -apple-system,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
  font-size: 14px;
  font-weight: 700;
}
</style>
