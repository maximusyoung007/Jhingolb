<template>
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
        <div v-if="category.useCounts <= 10" style="font-size: 10px">{{category.name}}</div>
        <div v-if="category.useCounts >= 10 && tag.useCounts <= 20" style="font-size: 20px">{{category.name}}</div>
        <div v-if="category.useCounts >= 20" style="font-size: 25px">{{category.name}}</div>
      </el-tag>
    </div>
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

  },
  methods: {
    getCategories: function () {
      $.axios({
        method:"get",
        url:"category/getCategories"
      }).then((response) => {
        
      })
    }
  }
}
</script>

<style scoped>

</style>
