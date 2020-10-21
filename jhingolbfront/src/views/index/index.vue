<template>
  <div>
    <el-card shadow="always" v-for="(item,i) in firstPageArticle" :key="i" :index="item.id">
      <div slot="header" class="clearfix">
        <div style="font-size: 24px">{{ item.title }}</div>
        <div class="time">
          <i class="el-icon-time"></i>
          <time>{{ item.modifiedTime }}</time>
          &nbsp;
          <i class="el-icon-folder-opened"></i>
          <span>分类</span>
        </div>
      </div>
      <div v-html="item.articleBody">
      </div>
      <div class="bottom clearfix">
        <el-button type="text" class="button" style="float: right">阅读更多<i class="el-icon-d-arrow-right"></i></el-button>
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
      firstPageArticle:[
        {title:"wenzhangmingzi",modifiedTime:"2020-02-01"}
      ]
    }
  },
  mounted:function() {
    this.getFirstPageArticleList();
  },
  methods: {
    getFirstPageArticleList:function () {
      this.$axios({
        method: "get",
        url:"article/getFirstPageArticleList"
      }).then((response) => {
        this.firstPageArticle = response.data.data.list;
        console.log(this.firstPageArticle);
      })
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
</style>
