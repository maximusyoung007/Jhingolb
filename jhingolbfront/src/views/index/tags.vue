<template>
  <div class="mainDiv" style="margin:0 auto;width: 500px">
    <h1 style="text-align: center">这里是所有的标签</h1>
    <h2 style="text-align: center">一共有{{count}}个</h2>
    <div style="text-align: center">
      <el-tag :key="tag.id"
              style="cursor: pointer"
              v-for="tag in allTags"
              :disable-transitions="true"
              effect="plain"
      >
        <div v-if="tag.useCounts <= 10" style="font-size: 10px">{{tag.name}}</div>
        <div v-if="tag.useCounts >= 10 && tag.useCounts <= 20" style="font-size: 20px">{{tag.name}}</div>
        <div v-if="tag.useCounts >= 20" style="font-size: 25px">{{tag.name}}</div>
      </el-tag>
    </div>
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
        url: "tags/getTagsList"
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
