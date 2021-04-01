<template>
  <div>
    <div class="head2" style="width: 100%;">
      <div class="contain">
        <div class="row">
          <div class="site-heading">
            <h1 class='titleH1' style="color: white">关于</h1>
            <span class="subheading">放弃幻想，准备战斗</span>
          </div>
        </div>
      </div>
    </div>
    <el-row>
      <el-col style="text-align: center">
        <h1>關於</h1>
        <h3>
          也想做一些有意思的事情呢
        </h3>
        <h3>
          直到我聊以忘卻那無法形容的疲勞和倦怠
        </h3>
        <h3>
          以及那不可思議的
        </h3>
        <h3>
          庸碌而無聊的人生
        </h3>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "about.vue"
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
  background: url('/static/image/about.jpg');
  background-repeat: no-repeat;
  background-size: cover;
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
