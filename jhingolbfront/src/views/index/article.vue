<template>
  <div class="block">
    <div class="head2" style="width: 100%;">
      <div class="contain">
        <div class="row">
          <div class="site-heading">
            <h1 class='titleH1' style="color: white">文章</h1>
            <span class="subheading">放弃幻想，准备战斗</span>
          </div>
        </div>
      </div>
    </div>
    <el-row class="indexContainer">
      <el-table
        :data="tableData"
        :show-header="false"
        style="width: 100%">
        <el-table-column
          prop="title"
          label="标题">
          <template slot-scope="scope">
            <div>
              <time style="font-size: 13px;color:#999 ">{{ scope.row.modifiedTime }}</time>
            </div>
            <div>
              <span style="font-size: 20px;cursor:pointer;font-family: 'Hiragino Sans GB'" @click="getArticleDetail(scope.row.id)">{{scope.row.title}}</span>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <div class="page" style="text-align: center">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
        </el-pagination>
      </div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "article.vue",
  data() {
    return {
      tableData:[],
      currentPage: 1,
      pageSize: 10,
      total: 10
    }
  },
  mounted() {
    this.getArticleList();
  },
  methods: {
    getArticleList:function () {
      this.$axios({
        method: "post",
        url:"article/getArticleList",
        data: {
          currentPage:this.currentPage,
          pageSize: this.pageSize,
          tagId: this.$route.params.tagId,
          categoryId: this.$route.params.categoryId,
          modifiedTime: this.$route.params.modifiedTime
        }
      }).then((response) => {
        this.tableData = response.data.data.list;
        this.currentPage = response.data.data.pageNum;
        this.pageSize = response.data.data.pageSize;
        this.total = response.data.data.total;
      })
    },
    handleCurrentChange:function (val) {
      this.getArticleList();
    },
    getArticleDetail:function(id) {
      this.$router.push({name:"articleDetail",params: {id:id}})
    }
  },
}
</script>

<style scoped>
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
.head2 {
  background: url('/static/image/article.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
  height: 400px;
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
  font-weight: 300;
  font-size: 60px;
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
</style>
