<template>
  <div>
    <el-card shadow="always" v-model="article">
      <div slot="header" class="clearfix">
        <div style="font-size: 24px">{{ article.title }}</div>
        <div class="time">
          <i class="el-icon-time"></i>
          <time>{{ article.modifiedTime }}</time>
          &nbsp;
          <i class="el-icon-folder-opened"></i>
          <span>分类</span>
        </div>
      </div>
      <div v-html="article.articleBody">
      </div>
    </el-card>
    <br/>
  </div>
</template>

<script>
export default {
  name: "index.vue",
  data() {
    return {
      article:"",
    }
  },
  mounted:function() {
    this.getArticleById();
  },
  methods: {
    getArticleById:function () {
      this.$axios({
        method: "post",
        url:"article/getArticleById",
        data: {
          id: this.$route.params.id
        }
      }).then((response) => {
        this.article = response.data.data;
      })
    },
    readMore: function () {
      this.$router.push("")
    }
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
</style>
