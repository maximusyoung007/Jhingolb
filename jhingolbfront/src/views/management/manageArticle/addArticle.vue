<template>
  <div>
    <el-row>
      <el-col :span="4"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
      <el-col :span="16">
        <div class="home">
          <el-form class="inputItem">
            <el-input class="inputItem" v-model="article.title" placeholder="请输入文章标题" style="padding-bottom: 10px"></el-input>
          </el-form>
          <div>
            <el-select  v-model="categoryValue" placeholder="选择分类" size="small" style="display: inline;float: left;padding-right: 5px">
              <el-option
                v-for="item in categoryList"
                :key="item.id"
                :label="item.label"
                :value="item.id"
              >
              </el-option>
            </el-select>
          <choose-tags v-on:tagsChange="updateTags" :chosenTag="chosenTag"></choose-tags>
          </div>
          <br>
          <div id="demo1"></div>
          <textarea hidden name="" id="" cols="170" rows="20" readonly v-model="editorData"></textarea>
        </div>
        <div>
          <div style="text-align: center">
            <el-button type="primary" @click="addArticle(0)">保存</el-button>
            <el-button type="success" @click="addArticle(1)">发布</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="4"><div class="grid-content bg-purple" style="color: white">hello world</div></el-col>
    </el-row>
  </div>
</template>

<script>
import wangEditor from "wangeditor"
import chooseTags from "../manageTags/chooseTags"
import hljs from "highlight.js"
import 'highlight.js/styles/monokai-sublime.css'
import category from "../../index/category";
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
      tagList: [],
      categoryList:[],
      categoryValue:"",
      chosenTag: []
    }
  },
  mounted() {
    const editor = new wangEditor('#demo1');
    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.editorData = newHtml
    }
    //代码语法高亮
    editor.highlight = hljs;
    editor.config.height = 500;
    editor.config.zIndex = 500;
    editor.config.onchangeTimeout = 500;
    editor.config.historyMaxSize = 500;
    editor.config.emotions = [
      {
        title:'emoji',
        type: 'emoji',
        content: '😀 😃 😄 😁 😆 😅 😂 😊 😇 🙂 🙃 😉 😓 😪 😴 🙄 🤔 😬 🤐'.split(/\s/),
      }
    ];
    //editor.config.uploadImgServer = 'article/uploadImage';
    editor.config.uploadImgShowBase64 = true;
    editor.config.uploadImgMaxSize = 2 * 1024 * 1024;
    editor.config.withCredentials = true;
    // 创建编辑器
    editor.create();
    this.editor = editor;
    this.getEditorData();
    this.initSelect();
  },
  methods: {
    getEditorData() {
      // 通过代码获取编辑器内容
      let data = this.editor.txt.html()
      this.editorData = data;
    },
    addArticle: function (releaseState) {
      this.$axios({
        method: "post",
        url:"article/addArticle",
        data: {
          title:this.article.title,
          articleBody:this.editorData,
          releaseState: releaseState,
          allTags:this.tagList,
          category: this.categoryValue,
          views:0,
        }
      }).then((response) => {
        var type = response.data.type;
        var articleId = response.data.data;
        if(type == "success") {
          this.$router.push({name: "addArticleResult",params: {state: releaseState,articleId: articleId}});
        } else {
          this.$router.push({name: "/addArticleResult",params: {state: '3',articleId: articleId}});
        }
      })
    },
    updateTags: function (e) {
      this.tagList = e;
    },
    initSelect() {
      this.$axios({
        method:"get",
        url:"category/getCategoryList"
      }).then(response => {
        this.categoryList = response.data.data;
        for(var i = 0;i < this.categoryList.length;i++) {
          this.categoryList[i].label = this.categoryList[i].name;
        }
      })
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
