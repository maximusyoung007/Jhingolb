<template>
  <div class="home">
    <div class="addBtn">
      <el-button type="primary" @click="addArticle()">写文章</el-button>
    </div>
    <br>
    <div>
      <el-table
        class="articleTable"
        :data="articleTable"
        border
        fit
      >
        <el-table-column align="center"
          label="文章标题"
          >
          <template slot-scope="scope">
            <span>{{scope.row.title}}({{scope.row.modifiedTime}})</span>
          </template>
        </el-table-column>
        <el-table-column
          label="发布状态"
          align="center"
          width="100">
          <template slot-scope="scope">
            <span>{{scope.row.releaseState == 1 ? '已发布' : '未发布'}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="views"
          label="阅读数"
          align="center"
          width="100">
        </el-table-column>
        <el-table-column label="操作" width="250" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="success" @click="previewArticle(scope.$index,scope.row)">预览</el-button>
              <el-button size="mini" type="primary" @click="editArticle(scope.$index,scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="deleteArticle(scope.$index,scope.row)">删除</el-button>
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
import {Message} from 'element-ui'
import {MessageBox} from 'element-ui'
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
          pageSize: this.pageSize,
          isManage:1
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
    addArticle: function (val) {
      this.$router.push({path:'/addArticle'});
    },
    previewArticle: function (index,row) {
      var previewArticleId = row.id;
      this.$router.push({name:'previewArticle',params:{previewArticleId:previewArticleId}});
    },
    editArticle: function (index,row) {
      var articleId = row.id;
      this.$router.push({name: 'editArticle',params:{articleId:articleId}});
    },
    deleteArticle: function(index,row) {
      MessageBox.confirm('确定要删除该文章吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios({
          method:"post",
          url:"article/deleteArticle",
          data:{
            id: row.id
          }
        }).then(response => {
          if(response.data.type == "success") {
            Message({
              type: 'success',
              message: '删除成功!'
            });
            this.getArticleList();
          }
        })
      }).catch(() => {
        Message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
}
</script>

<style lang="scss">

</style>
