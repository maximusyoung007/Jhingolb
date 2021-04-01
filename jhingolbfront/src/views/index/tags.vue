<template>
  <div>
    <div class="head2" style="width: 100%;">
      <div class="contain">
        <div class="row">
          <div class="site-heading">
            <h1 class='titleH1' style="color: white">分类</h1>
            <span class="subheading">放弃幻想，准备战斗</span>
          </div>
        </div>
      </div>
    </div>
      <el-col>
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
@media (min-width: 768px) {
  .indexContainer {
    width: 750px;
  }
}
@media (min-width: 992px) {
  .indexContainer {
    width: 970px;
  }
}
@media (min-width: 1200px) {
  .indexContainer {
    width: 1170px;
  }
}
.indexContainer {
  padding-right: 15px;
  padding-left: 15px;
  margin-left: auto;
  margin-right: auto;
}
.head2 {
  background: url('/static/image/tags.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
  height: 400px;
}
@media (max-width: 800px) {
  .head2 {
    height: 240px;
  }
}
.contain {
  padding-left: 15px;
  padding-right: 15px;
  margin-left: auto;
  margin-right: auto;
}
.titleH1 {
  font-family: -apple-system,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB",STHeiti,"Microsoft YaHei","Microsoft JhengHei","Source Han Sans SC","Noto Sans CJK SC","Source Han Sans CN","Noto Sans SC","Source Han Sans TC","Noto Sans CJK TC","WenQuanYi Micro Hei",SimSun,sans-serif;
  line-height: 1.1;
  font-weight: 300;
  font-size: 60px;
}
.site-heading {
  text-align: center;
}
@media only screen and (min-width: 768px) {
  .site-heading {
    padding: 150px 0;
  }
}
.site-heading {
  padding: 150px 0 70px;
}
.subheading {
  line-height: 1.7;
  font-size: 18px;
  display: block;
  font-weight: 300;
  margin: 10px 0 0;
  color: white;
}
</style>
