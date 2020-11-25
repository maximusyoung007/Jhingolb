<template>
  <div>
    <el-row>
      <el-col :span="3"><span style="color: white">hello</span></el-col>
      <el-col :span="18">
        <div class="mainDiv" style="margin:0 auto;width: 500px">
          <h1 style="text-align: center">这里是所有的分类</h1>
          <h2 style="text-align: center">一共有{{count}}个</h2>
          <div style="text-align: center">
              <el-tag :key="category.id"
                      style="cursor: pointer"
                      v-for="category in allCategories"
                      :disable-transitions="true"
                      effect="plain"
              >
                  <div style="font-size: 10px">{{category.name}}</div>
              </el-tag>
          </div>
        </div>
      </el-col>
      <el-col :span="3"><span style="color: white"></span></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "category.vue",
  data() {
    return {
      count:"",
      allCategories:[]
    }
  },
  mounted() {
    this.getCategories();
  },
  methods: {
    getCategories: function () {
      this.$axios({
        method:"get",
        url:"category/getCategoryShow"
      }).then((response) => {
          this.allCategories = response.data.data;
          this.count = this.allCategories.length;
      })
    }
  }
}
</script>

<style scoped>
.el-tag--plain {
  margin:5px
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
