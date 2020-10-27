<template>
  <el-container>
    <el-header style="background-color: #f1f1f1">
      <el-menu
        :default-active="'index'"
        router
        mode="horizontal"
        text-color="#222"
        active-text-color="#409EFF"
        background-color="#f1f1f1"
      >
        <el-menu-item style="float: right" v-for="(item,i) in navList" :key="i" :index="item.name">
          {{ item.navItem }}
        </el-menu-item>
        <span id="title" style="color: #222;float: left;padding: 20px;text-decoration: none;line-hieght:1">云溢清寒</span>
      </el-menu>
    </el-header>
    <el-main style="background-color: white">

        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
          <el-col :span="15" style="padding-right: 10px">
            <router-view/>
          </el-col>
          <el-col :span="5">
            <div>
              <el-input placeholder="..." v-model="searchArticle">
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </div>
            <el-divider></el-divider>
            <div>
                <h3 style="border: #222222">标签</h3>
              <el-tag :key="tag"
                      style="cursor: pointer;padding-bottom: 10px"
                      type="success"
                      v-for="tag in allTags"
                      :disable-transitions="true"
                      @click="addTags(tag)"
                      size="small"
              >
                {{tag}}
              </el-tag>
            </div>
            <el-divider></el-divider>
            <div>
              <h3 style="border: #222222">文章归档</h3>
              <ul>
                <li
                    v-for="(item,i) in articleDate" :key="i" :index="item">
                  <el-button size="small" type="text">{{item}}</el-button>
                </li>
              </ul>
            </div>
          </el-col>
          <el-col :span="2"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
        </el-row>
      </el-main>
  </el-container>

</template>

<script>
export default {
  name: "head.vue",
  data() {
    return {
      navList: [
        {name:'about',navItem:'关于'},
        {name:'tags',navItem:'标签'},
        {name:'article',navItem:'文章'},
        {name:'index',navItem:'首页'},
      ],
      allTags:[],
      articleDate:[],
      searchArticle: "",
      activeColor:"dodgerblue",
      pointer:"pointer"
    }
  },
  methods: {
    loadTags:function() {
      this.$axios({
        methods: "get",
        url: "tags/getTagsList"
      }).then((response) => {
        var list = response.data.data;
        for(var i = 0;i < list.length;i++) {
          var name = list[i].name;
          this.allTags.push(name);
        }
      })
    },
    loadArticleDate: function () {
      this.$axios({
        methods: "get",
        url: "article/getArticleDate",
      }).then((response) => {
        this.articleDate = response.data.data;
      })
    },
    mouseOverColor: function () {
      this.activeColor = "blue";
    },
    mouseDownColor: function () {
      this.activeColor = "dodgerblue"
    }
  },
  mounted() {
    this.loadTags();
    this.loadArticleDate();
  }
}
</script>

<style scoped>
#title{
  position: absolute;
  padding-top: 20px;
  font-size: 20px;
  font-weight: bold
}
el-header{
  padding:0px;
  margin:0px;
  border:0px;
}
.el-tag.el-tag--success {
  margin:3px
}
.el-menu.el-menu--horizontal{
  border-bottom: solid 0px
}
</style>
