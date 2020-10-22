<template>
  <div class="home">
    <div class="addBtn">
      <el-button type="primary" @click="editArticle()">写文章</el-button>
    </div>
    <br>
    <div>
      <el-table
        class="articleTable"
        :data="articleTable"
        border
        style="width: 100%">
        <el-table-column align="center"
          label="文章标题"
          width="515">
          <template slot-scope="scope">
            <span>{{scope.row.title}}({{scope.row.modifiedTime}})</span>
          </template>
        </el-table-column>
        <el-table-column
          label="发布状态"

          width="100">
          <template slot-scope="scope">
  <!--          <span v-if="scope.row.releaseState == 1">已发布</span>-->
  <!--          <span v-if="scope.row.releaseState == 0">未发布</span>-->
            <span>{{scope.row.releaseState == 1 ? '已发布' : '未发布'}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="views"
          label="阅读数"
          width="100">
        </el-table-column>
        <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button size="mini" @click="EditArticle(scope.$index,scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="EditArticle(scope.$index,scope.row)">删除</el-button>
            </template>
        </el-table-column>
      </el-table>
    </div>
    <br>
    <div class="page" style="text-align: center">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "editArticle.vue",
  data() {
    return {
      articleTable: [],
      currentPage:1,
      pageSize: 10,
      total:10
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
          pageSize: this.pageSize
        }
      }).then((response) => {
        this.articleTable = response.data.data.list;
        this.currentPage = response.data.data.pageNum;
        this.pageSize = response.data.data.pageSize;
        this.total = response.data.data.total;
      })
    },
    handleSizeChange:function (val) {

    },
    handleCurrentChange:function (val) {
      this.getArticleList();
    },
    editArticle: function (val) {
      this.$router.push({path:'/editArticle'});
    }
  }
}
</script>

<style lang="scss">

</style>
