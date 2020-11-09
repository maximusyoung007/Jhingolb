<template>
  <el-container>
    <el-header></el-header>
    <el-main>
      <el-row type="flex" class="row-bg">
        <el-col :span="8"></el-col>
          <el-col :span="8">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="用户名">
                <el-input v-model="form.username"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input type="password" v-model="form.password"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="loginSys">登录</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        <el-col :span="8"></el-col>
      </el-row>
    </el-main>
    <p>状态：{{$store.state.username}}</p>
  </el-container>
</template>

<script>
import BlogHeader from "../../components/common/Header";
import BlogFooter from "../../components/common/Footer";

export default {
  name: "blogAdmin",
  components: {BlogFooter, BlogHeader},
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    loginSys() {
      const self = this
      console.log("login username" + this.$store.state.user.username)
      this.$axios({
        method: "post",
        url:"login/login",
        data: {
          username: this.form.username,
          password: this.form.password
        }
      }).then(response => {
        if(response.data.type == "success") {
          this.$store.commit("login",this.form);
          var path = this.$route.query.redirect;
          this.$router.replace({path: path === '/' || path === undefined ? '/management' : path})
        }
      }).catch(function (response) {

      })
    },

  }
}
</script>

<style scoped>

</style>
