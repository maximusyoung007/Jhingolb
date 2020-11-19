<template>
  <div id="app">
    <div>
      <h2 class="result" v-if="this.$route.params.state == 0">
        文章保存成功!
      </h2>
      <h2 class="result" v-if="this.$route.params.state == 1">
        文章发布成功！
      </h2>
      <h2 class="result" v-if="this.$route.params.state == 2">
        文章编辑成功!
      </h2>
      <h2 class="result" v-if="this.$route.params.state == 3">
        文章保存失败
      </h2>
    </div>
    <div style="text-align: center">
<!--      <button type="primary" @click="continueEdit()">继续编辑</button>-->
      <button type="success" @click="toArticleList()">返回文章列表</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "addArticleResult.vue",
  components: {},
  data() {
    return {

    }
  },
  mounted() {

  },
  methods: {
    toArticleList: function () {
      this.$router.push({name: 'articleList'})
    },
  }
}
</script>

<style lang="scss">
.result {
  text-align: center;
}
</style>
