<template>
  <div id="app">
      <h2 class="result" v-if="this.$route.params.state == 0">
        文章保存成功!
      </h2>
      <h2 class="result" v-else>
        文章发布成功!
      </h2>
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

  }
}
</script>

<style lang="scss">
.result {
  text-align: center;
}
</style>
