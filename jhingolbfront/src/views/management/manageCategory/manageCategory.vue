<template>
  <div>
    <div>
      <div>
        <el-button type="primary" size="small" @click="addCategory()">
          新建分类
        </el-button>
      </div>
      <br>
      <el-table
        class="categoryTable"
        :data="categoryTable"
        border
        fit
      >
        <el-table-column align="center"
                         label="分类名称"
        >
          <template slot-scope="scope">
            <template v-if="scope.row.edit == 1">
              <el-input v-model="scope.row.inputName" class="edit-input" size="small" />
              <el-button
                class="cancel-btn"
                size="small"
                icon="el-icon-refresh"
                type="warning"
                @click="cancelEdit(scope.row)"
              >取消</el-button>
            </template>
            <span v-else>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="分类使用次数"
          align="center"
        >
          <template slot-scope="scope">
            <span>{{scope.row.useCounts}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.edit == 1"
              type="success"
              size="mini"
              @click="confirmEdit(scope.row)"
            >
              Ok
            </el-button>
            <el-button
              v-else
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="editCategory(scope.row)"
            >
              编辑
            </el-button>
            <el-button size="mini" type="danger" @click="deleteCategory(scope.row)">删除</el-button>
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
  mounted() {
    this.getCategoryList();
  },
  data() {
    return {
      categoryTable: [],
      currentPage:1,
      pageSize: 10,
      total:10,
    }
  },
  methods: {
    handleCurrentChange:function (val) {
      this.getArticleList();
    },
    handleSizeChange:function (val) {

    },
    getCategoryList: function () {
      this.$axios({
        method: "post",
        url: "category/getCategoryList",
        data: {
          currentPage:this.currentPage,
          pageSize: this.pageSize,
        }
      }).then(response => {
        this.categoryTable = response.data.data.list;
        this.currentPage = response.data.data.pageNum;
        this.total = response.data.data.total;
      })
    },
    confirmEdit: function(row) {
      row.edit = 0;
      this.$axios({
        method:"post",
        url:"category/updateCategory",
        data: {
          name:row.inputName,
          id: row.id
        }
      }).then(response => {
        if(response.data.type == "success") {
          row.name = row.inputName;
          Message({
            message:"分类名称修改成功",
            type:"success"
          })
        }
      })
    },
    cancelEdit: function(row) {
      row.edit = 0;
      Message({
        message: "分类名称没有更改",
        type:"warning"
      })
    },
    editCategory: function (row) {
      row.inputName = row.name;
      row.edit = 1;
    },
    deleteCategory: function(row) {
      if(row.useCounts == 0) {
        MessageBox.confirm('确定要删除该分类吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios({
            method:"post",
            url:"category/deleteCategory",
            data:{
              id:row.id
            }
          }).then((response) => {
            if(response.data.type == "success") {
              Message.success({
                message:"删除成功",
                type:"success"
              })
              this.getCategoryList();
            }
          })
        })
      } else {
        Message({
          message: "该分类在使用中，无法删除",
          type:"error"
        })
      }
    },
    addCategory: function() {
      this.$prompt('请输入分类名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$axios({
          method:"post",
          url:"category/addCategory",
          data: {
            name: value
          }
        }).then(response => {
          if(response.data.type == "success") {
            Message.success({
              message:"成功添加分类",
              type:"success"
            })
            this.getCategoryList();
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
