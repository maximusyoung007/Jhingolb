<template>
  <div class="home">
    <el-form class="inputItem">
      <el-input class="inputItem" v-model="article.title" placeholder="请输入文章标题" style="padding-bottom: 10px"></el-input>
    </el-form>
<!--    <div>-->
<!--      <choose-tags></choose-tags>-->
<!--    </div>-->
    <div id="demo1"></div>
    <br>
    <div>
      <choose-tags v-on:tagsChange="updateTags"></choose-tags>
    </div>
    <textarea name="" id="" cols="170" rows="20" readonly v-model="editorData"></textarea>
    <div>
      <el-button type="primary" @click="addArticle(0)">保存</el-button>
      <el-button type="success" @click="addArticle(1)">发布</el-button>
    </div>
  </div>
</template>

<script>
import wangEditor from "wangeditor"
import chooseTags from "../manageTags/chooseTags"
import hljs from "highlight.js"
import 'highlight.js/styles/monokai-sublime.css'
import tags from "../../index/tags";
export default {
  name: "editArticle.vue",
  components: {chooseTags},
  data() {
    return {
      editor:null,
      editorData:'',
      article: {
        title: ""
      },
      tagList: []
    }
  },
  mounted() {
    const editor = new wangEditor('#demo1');
    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.editorData = newHtml
    }
    //代码语法高亮
    //editor.highlight = hljs;
    // 创建编辑器
    editor.create()
    this.editor = editor
  },
  methods: {
    getEditorData() {
      // 通过代码获取编辑器内容
      let data = this.editor.txt.html()
      alert(data)
    },
    addArticle: function (releaseState) {
      this.$axios({
        method: "post",
        url:"article/addArticle",
        data: {
          title:this.article.title,
          articleBody:this.editorData,
          releaseState: releaseState,
        }
      })
    },
    updateTags: function (e) {
      this.tagList = e;
      console.log(this.tagList);
    },
    addTags() {

    }
  },
  beforeDestroy() {
    // 调用销毁 API 对当前编辑器实例进行销毁
    this.editor.destroy()
    this.editor = null
  },
}
</script>

<style lang="scss">
.home {
  margin: auto;
  position: relative;
  .btn {
    position: absolute;
    right: 0;
    top: 0;
    padding: 5px 10px;
    cursor: pointer;
  }
}
.inputItem {
  padding-bottom: 10px;
}
.html {
  height: 100%;
}
</style>
