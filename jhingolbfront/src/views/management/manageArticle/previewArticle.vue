<template>
  <div>
    <el-row>
      <el-col :span="4"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
      <el-col :span="16">
        <el-card shadow="never" v-model="article">
          <div slot="header" class="clearfix">
            <div style="font-size: 24px">{{ article.title }}</div>
            <div class="time">
              <i class="el-icon-time"></i>
              <time>{{ article.modifiedTime }}</time>
              &nbsp;
              <i class="el-icon-folder-opened"></i>
              <span>{{ article.category }}</span>

              <i class="el-icon-view"></i>
              <span>{{article.views}}</span>
            </div>
            <div>
              <el-tag :key="tag.id"
                      v-for="tag in tags"
                      :disable-transitions="true"
                      size="mini"
              >{{tag.name}}</el-tag>
            </div>
          </div>
          <div v-html="article.articleBody">
          </div>
          <el-divider></el-divider>
          <div style="text-align: center">
            <el-button type="primary" @click="toEditPage(article.id)">编辑</el-button>
            <el-button type="success" @click="goBack()">返回</el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="4"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
    </el-row>
    <br/>
  </div>
</template>

<script>
import 'highlight.js/styles/vs.css'
export default {
  name: "previewArticle.vue",
  data() {
    return {
      article:"",
      tags:""
    }
  },
  mounted:function() {
    this.getArticleById();
  },
  methods: {
    getArticleById:function () {
      if(this.$route.params.previewArticleId != null) {
        localStorage.setItem("previewArticleId", this.$route.params.previewArticleId);
      }
      this.$axios({
        method: "post",
        url:"article/getArticleById",
        data: {
          id: localStorage.getItem("previewArticleId")
        }
      }).then((response) => {
        this.article = response.data.data;
        this.tags = this.article.tags;
      })
    },
    getElementToTop: function(el) {
      if(el.parentElement) {
        return this.getElementToTop(el.parentElement) + el.offsetTop
      }
      return el.offsetTop
    },
    toEditPage: function (id) {
      this.$router.push({name:"editArticle",params:{articleId:id}})
    },
    goBack: function () {
      this.$router.go(-1);
    }
  }
}
</script>

<style>
.el-row {
  background: white;
}
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
p {
  margin-left: 0;
}
.el-tag {
  margin-top:3px;
  margin-right: 3px
}
</style>
