<template>
  <div>
    <el-row>
      <el-col :span="3"><span style="color: white">hello</span></el-col>
      <el-col :span="18">
        <el-card shadow="never" v-model="article">
          <div slot="header" class="clearfix">
            <div style="font-size: 24px">{{ article.title }}</div>
            <div class="time">
              <i class="el-icon-time"></i>
              <time>{{ article.modifiedTime }}</time>
              &nbsp;
              <i class="el-icon-folder-opened"></i>
              <span>分类</span>
            </div>
          </div>
          <div v-html="article.articleBody">
          </div>
          <!--      <div button="buttonGroup" style="text-align: center">-->
          <!--        <a-button type="primary" icon="like" @click="addThumbsUp()">赞成({{this.comments.thumbsUp}})</a-button>-->
          <!--        <a-button type="danger" icon="dislike" @click="addOppose()">反对({{this.comments.oppose}})</a-button>-->
          <!--      </div>-->
          <el-divider></el-divider>
          <div>
            <el-form :rules="rules" class="demo-ruleForm"
                     :model="comments" ref="comments" label-width="70px">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="昵称：" prop="petName">
                    <el-input placeholder="请输入一个昵称" v-model="comments.petName"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="邮箱：" prop="email">
                    <el-input placeholder="请输入邮箱" v-model="comments.email"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item>
                <el-input type="textarea" :rows="10" placeholder="请输入评论" v-model="comments.textarea"></el-input>
              </el-form-item>
              <el-form-item>
                <a-button type="primary" @click="addComments('comments')">发表</a-button>
              </el-form-item>
            </el-form>
          </div>
          {{this.comments.commentCounts}}条评论
          <comments v-for="item in commentList" :key="item.id" v-bind:parent-comments="item"></comments>
          <div id="elementAfterComment"></div>
        </el-card>
      </el-col>
      <el-col :span="3"><span style="color: white">hello</span></el-col>
    </el-row>
    <br/>
  </div>
</template>

<script>
import {Message} from 'element-ui'
import Comments from '../common/comments'
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
    }
  },
  mounted:function() {
    this.getArticleById();
    this.getCommentList();
  },
  methods: {
    getArticleById:function () {
      if(this.$route.params.id != null) {
        localStorage.setItem("id", this.$route.params.id);
      }
      console.log("id" + localStorage.getItem("id"));
      this.$axios({
        method: "post",
        url:"article/getArticleById",
        data: {
          id: localStorage.getItem("id")
        }
      }).then((response) => {
        this.article = response.data.data;
        this.comments.thumbsUp = response.data.data.thumbsUp;
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
    }
  }
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
p {
  margin-left: 0;

}
</style>
