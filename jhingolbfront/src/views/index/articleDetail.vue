<template>
  <div>
    <div class="head2" style="width: 100%;">
      <div class="contain">
        <div class="row">
          <div class="site-heading">
            <h1 class='titleH1' style="color: white"></h1>
            <span class="subheading"></span>
          </div>
        </div>
      </div>
    </div>
    <el-col :lg="16" :md="16" :sm="12" class="articleList" style="padding-top:25px;width: 100%">
      <div class="indexContainer articleHead">
        <div class="articleTitle">
          <h1 style="font-weight: 400; font-size: 1.5em; color: #795548">{{article.title}}</h1>
        </div>
        <div class="articleInfo">
          <i class="el-icon-time"></i>
          <time>{{ article.modifiedTime }}</time>
          &nbsp;
          <i class="el-icon-folder-opened"></i>
          <span>{{ article.category }}</span>
          &nbsp;
          <i class="el-icon-view"></i>
          <span>{{views}}</span>
        </div>
      </div>
      <article class="indexContainer">
        <div>
          <div v-html="article.articleBody"></div>
        </div>
      </article>
    </el-col>
  </div>
</template>

<script>
import {Message} from 'element-ui'
import Comments from '../common/comments'
import 'highlight.js/styles/vs.css'
export default {
  name: "index.vue",
  components:{Comments},
  data() {
    return {
      article:"",
      comments: {
        petName:"",
        email:"",
        textarea:"",
        thumbsUp:"",
        oppose:"",
        commentCounts: "",
      },
      rules: {
        petName: [
          {required: true,message:"请填写昵称",trigger:'blur'},
        ],
        email:[
          {required:true,message:"请填写邮箱",trigger:'blur'},
          {type: 'email',message: "请输入正确的邮箱",trigger: ['blur','change']}
        ]
      },
      commentList:[
        {username:'username1',content:'it is a test',haveLike:true,haveDisLike:true,date:'2020-01-01'},
        {username:'username2',content: 'it is a test',haveLike: true,haveDisLike: true,date:'2020-01-01'}
      ],
      views: ""
    }
  },
  mounted:function() {
    this.getArticleById();
    this.getCommentList();
    this.getViews();
  },
  methods: {
    getArticleById:function () {
      if(this.$route.params.id != null) {
        localStorage.setItem("id", this.$route.params.id);
      }
      this.$axios({
        method: "post",
        url:"article/getArticleById",
        data: {
          id: localStorage.getItem("id")
        }
      }).then((response) => {
        this.article = response.data.data;
        this.comments.oppose = response.data.data.oppose;
        this.comments.commentCounts = response.data.data.commentCounts;
      })
    },
    addComments: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            method: "post",
            url:"comments/addComments",
            data: {
              articleId: this.article.id,
              username: this.comments.petName,
              email: this.comments.email,
              content: this.comments.textarea
            }
          }).then((response) => {
            if(response.data.type == "success") {
              Message({
                message: response.data.msg,
                type: "success",
                center:true,
                offset:50
              })
              //重新加载评论列表
              this.getCommentList();
              this.comments.commentCounts = this.commentList.length;
              var page = document.querySelector("#elementAfterComment");
              var top = this.getElementToTop(page);
              window.scrollTo({
                'top': top,
                'behavior': 'smooth'
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    addThumbsUp: function () {
      this.$axios({
        method: "post",
        url:"article/addThumbsUp",
        data: {
          id: this.article.id
        }
      }).then((response) => {
        this.comments.thumbsUp = response.data.data;
      })
    },
    addOppose: function() {
      this.$axios({
        method:"post",
        url:"article/addOppose",
        data: {
          id: this.article.id
        }
      }).then((response) => {
        this.comments.oppose = response.data.data;
      })
    },
    getCommentList: function () {
      this.$axios({
        method:"post",
        url:"comments/getCommentList",
        data: {
          articleId: localStorage.getItem("id")
        }
      }).then((response) => {
        this.commentList = response.data.data;
      })
    },
    getElementToTop: function(el) {
      if(el.parentElement) {
        return this.getElementToTop(el.parentElement) + el.offsetTop
      }
      return el.offsetTop
    },
    //获取阅读量
    getViews: function() {
      if(this.$route.params.id != null) {
        localStorage.setItem("id", this.$route.params.id);
      }
      this.$axios({
        method: "post",
        url: "article/getViewsById",
        data: {
          id: localStorage.getItem("id")
        }
      }).then((response) => {
        this.views = response.data.data;
      })
      // setTimeout(() => {
      //   this.addViews();
      // },5000)
    },
    //在页面停留五秒钟以上，算作阅读了一次
    addViews: function() {
      this.$axios({
        method:"post",
        url: "article/addViews",
        data: {
          id: localStorage.getItem("id")
        }
      }).then((response) => {

      })
    }
  },

}
</script>

<style scoped>
.time{
  font-size: 13px;
  color:#999;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}
>>>blockquote {
  display: block;
  border-left: 8px solid #d0e5f2;
  padding: 5px 10px;
  margin: 10px 0;
  line-height: 1.4;
  font-size: 100%;
  background-color: #f1f1f1;
}

/* code 样式 */
>>>code {
  display: inline-block;
  *display: inline;
  *zoom: 1;
  background-color: #f1f1f1;
  border-radius: 3px;
  padding: 3px 5px;
  margin: 0 3px;
}
>>>pre code {
  display: block;
}
>>>p font{
  line-height: 1.8;
  text-align: justify;
  font-size: 15px;
  font-family: Fira Mono,"Noto Serif SC",-apple-system,Roboto,Helvetica Neue,sans-serif;
  font-weight: 400;
  color: #444;
}
@media (min-width: 768px) {
  .indexContainer {
    width: 750px;
  }
}
@media (min-width: 992px) {
  .indexContainer {
    width: 900px;
  }
}
@media (min-width: 1200px) {
  .indexContainer {
    width: 900px;
  }
}
.indexContainer {
  padding: 25px 15px 0 15px;
  margin-left: auto;
  margin-right: auto;
  background-color: hsla(0,0%,100%,.8);
  box-shadow: 0 1.6px 3.6px 0 rgba(0,0,0,0.132),0 0.3px 0.9px 0 rgba(0,0,0,0.108);;
}
.head2 {
  background: url('/static/image/articleDetail.jpeg');
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
.articleInfo {
  color: #795548;

}
</style>
