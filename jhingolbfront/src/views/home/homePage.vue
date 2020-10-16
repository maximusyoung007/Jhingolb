<template>
  <div>
    {{ message }}
  </div>
</template>

<script>
import BlogHeader from "../../components/common/Header";
import BlogFooter from "../../components/common/Footer";
export default {
  name: "index",
  components: {BlogFooter, BlogHeader},
  data() {
    return {
      message: "登录成功"
    }
  },
  methods: {

  }
}
</script>

<style scoped>

</style>
