<template>
  <div>
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
        <div class="bottom clearfix">
          <el-button @click="readMore(item.id)" type="text" class="button" style="float: right">阅读更多<i class="el-icon-d-arrow-right"></i></el-button>
        </div>
      </el-card>
  </div>
</template>

<script>
export default {
  name: "index.vue",
  data() {
    return {
      firstPageArticle:[],
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
      })
    },
    readMore: function (id) {
      this.$router.push({name: "articleDetail",params: {id: id}})
    },
    showArticleListByTags : function(id) {
      this.$router.push({name: "article",params: {tagId: id}})
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
.item-content {
  max-width: 100%;
  word-break: break-all;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 5;
  color: dimgrey;
}

.el-card{
  margin-bottom: 10px;
}
.el-tag {
  margin-top:3px;
  margin-right: 3px
}
</style>
