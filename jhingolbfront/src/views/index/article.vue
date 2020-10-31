<template>
  <div class="block">
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="modifiedTime"
        label="时间"
        width="250"
        >
      </el-table-column>
      <el-table-column
        prop="title"
        label="标题"

        >
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
  </div>
</template>

<script>
export default {
  name: "article.vue",
  data() {
    return {
      tableData:[],
      currentPage: 1,
      pageSize: 12,
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
  }
}
</script>

<style scoped>

</style>
