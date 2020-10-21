<template>
  <div class="home">
    <el-form class="inputItem">
    <el-input class="inputItem" v-model="article.title" placeholder="请输入文章标题" style="padding-bottom: 10px"></el-input>
    <el-button class="inputItem" icon="el-icon-plus" @click="addTags">添加标签</el-button>
    </el-form>
    <div id="demo1"></div>
    <h3>内容预览</h3>
    <textarea name="" id="" cols="170" rows="20" readonly v-model="editorData"></textarea>
  </div>
</template>

<script>
import wangEditor from "wangeditor"
export default {
  name: "editArticle.vue",
  data() {
    return {
      editor:null,
      editorData:'',
      article: {
        title: ""
      }
    }
  },
  mounted() {
    const editor = new wangEditor(`#demo1`)
    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.editorData = newHtml
    }
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
    addTags() {

    }
  },
  beforeDestroy() {
    // 调用销毁 API 对当前编辑器实例进行销毁
    this.editor.destroy()
    this.editor = null
  }
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
