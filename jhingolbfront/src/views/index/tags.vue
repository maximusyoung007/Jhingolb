<template>
  <div>
    <el-row>
      <el-col :span="3"><span style="color: white">hello</span></el-col>
      <el-col :span="18">
        <div class="mainDiv" style="margin:0 auto;">
          <h1 style="text-align: center">这里是所有的标签</h1>
          <h2 style="text-align: center">一共有{{count}}个</h2>
          <div style="text-align: center">
            <el-tag :key="tag.id"
                    style="cursor: pointer"
                    v-for="tag in allTags"
                    :disable-transitions="true"
                    effect="plain"
            >
              <div style="font-size: 10px">{{tag.name}}</div>
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
  name: "tags.vue",
  data() {
    return {
      count:"",
      allTags:[]
    }
  },
  methods: {
    loadTags:function() {
      this.$axios({
        methods: "get",
        url: "tags/getTagsListShow"
      }).then((response) => {
        this.allTags = response.data.data;
        this.count = this.allTags.length;
      })
    }
  },
  mounted() {
    this.loadTags();
  }
}
</script>

<style scoped>
.el-tag--plain {
  margin:5px
}
</style>
