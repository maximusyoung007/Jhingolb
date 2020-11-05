<template>
  <div>
    <el-divider></el-divider>
    <el-row>
      <el-col :span="2">
        <div>
          <el-avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"></el-avatar>
        </div>
      </el-col>
      <el-col :span="20">
        <div>
            <div class="commentsAuthor">
              {{parentComments.username}}
            </div>
            <br>
            <p>
              {{parentComments.content}}
            </p>
        </div>
        <div>
            <!--点赞按钮-->
<!--            <a-icon v-show="this.comments.likes == 0" class="commentsSame" type="like" @click="like(parentComments.id)"></a-icon>-->
<!--            <a-icon v-show="this.comments.likes > 0" class="commentsSame" type="like" @click="like(parentComments.id)" theme="filled"></a-icon>-->
<!--            <span v-show="this.comments.likes > 0" style="color:#67c23a">{{this.comments.likes}}</span>-->

<!--            &lt;!&ndash;点踩按钮&ndash;&gt;-->
<!--            <a-icon v-show="this.comments.dislikes == 0" class="commentsSame" type="dislike" @click="dislike(parentComments.id)"></a-icon>-->
<!--            <a-icon v-show="this.comments.dislikes > 0" class="commentsSame" type="dislike" @click="dislike(parentComments.id)" theme="filled"></a-icon>-->
<!--            <span v-show="this.comments.dislikes > 0" style="color: #ff4d4f">{{this.comments.dislikes}}</span>-->

            <span class="commentsSame" @click="openReplay()">回复</span>
            <time class="commentsDate">{{this.comments.updateTime}}</time>
        </div>
        <div>
          <el-card shadow="never" style="background-color:#f4f4f4;" v-show="ifShowReplayTextarea">
            <el-form :inline="true" :model="formInline" :rules="rules" ref="formInline">
              <el-form-item label="昵称" prop="username" size="small">
                <el-input v-model="formInline.username"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email" size="small">
                <el-input v-model="formInline.email"></el-input>
              </el-form-item>
              <el-form-item size="small">
                <el-button type="primary" @click="addReplay('formInline')">回复</el-button>
              </el-form-item>
            </el-form>
            <div>
                <el-input type="textarea" :rows="3" v-model="replayTextarea" autosize></el-input>
            </div>
          </el-card>
        </div>
        <div>
          <div>
          <el-card shadow="never" style="background-color: #f4f4f4" v-for="item in this.comments2.childrenComments" :key="item.id">
            <div class="commentsAuthor">
              <div v-if="item.replayTo != null && item.replayTo != 0" class="commentsAuthor">
                {{item.username}}：回复：{{item.replayTo}}:
              </div>
              <div v-if="item.replayTo == null || item.replayTo == 0" class="commentsAuthor">
                {{item.username}}：
              </div>
              <p>
                {{item.content}}
              </p>
              <time class="commentsDate">{{item.updateTime}}</time> <span style="float: right;cursor: pointer" @click="replayToChildren(item.username)">回复</span>
            </div>
          </el-card>
          </div>
          <div id="formInline2">
          <el-card shadow="never" style="background-color:#f4f4f4;" v-show="ifShowReplayToChildren">
            <el-form :inline="true" :model="formInline2" :rules="rules2" ref="formInline2">
              <el-form-item label="昵称" prop="username2" size="small">
                <el-input v-model="formInline2.username2"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email2" size="small">
                <el-input v-model="formInline2.email2"></el-input>
              </el-form-item>
              <el-form-item size="small">
                <el-button type="primary" @click="addReplayToChildren('formInline2')">回复</el-button>
              </el-form-item>
            </el-form>
            <div>
              <el-input type="textarea" :rows="3" v-model="replayTextarea2" autosize :placeholder="myPlaceholder"></el-input>
            </div>
          </el-card>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {Message} from "element-ui";
export default {
  name: "Comments",
  data() {
    return {
      comments: this.parentComments,
      comments2: this.parentComments,
      replayTextarea: "",
      replayTextarea2: "",
      replayNotNull:false,
      ifShowReplayTextarea: false,
      ifShowReplayToChildren: false,
      replayUsername:"",
      replayEmail:"",
      replayTo: "",
      formInline: {
        username: "",
        email: ""
      },
      rules:{
        username:[{required: true,message:"请输入昵称",trigger:"blur"}],
        email:[{required: true,message:"请出入邮箱",trigger:"blur"},
          {type: 'email',message: "请输入正确的邮箱",trigger: ['blur','change']}
        ]
      },
      formInline2: {
        username2: "",
        email2: ""
      },
      rules2:{
        username2:[{required: true,message:"请输入昵称",trigger:"blur"}],
        email2:[{required: true,message:"请出入邮箱",trigger:"blur"},
          {type: 'email',message: "请输入正确的邮箱",trigger: ['blur','change']}
        ]
      },
      myPlaceholder:"回复给..."
    }
  },
  props: ['parentComments'],

  mounted:function() {

  },
  methods: {
    like: function (id){
      this.$axios({
        method: "post",
        url:"comments/updateComments",
        data: {
          id: id,
          likes: this.comments.likes
        }
      }).then((response) => {

      })
    },
    dislike: function (id) {
      this.$axios({
        method: "post",
        url:"comments/updateComments",
        data: {
          id: id,
          likes: this.comments.dislikes
        }
      }).then((response) => {

      })
    },
    openReplay: function() {
      this.ifShowReplayTextarea = true;
    },
    refreshChildrenComment: function () {
      this.$axios({
        method:"post",
        url:"comments/getCommentList",
        data: {
          id: this.comments.id,
          articleId:this.comments.articleId
        }
      }).then((response) => {
        this.comments2 = response.data.data[0];
        this.ifShowReplayTextarea = false;
      })
    },
    addReplay: function (formName) {
      this.$refs[formName].validate((valid) => {
        if(valid) {
          var replayContent = this.replayTextarea.trim();
          if(replayContent != null && replayContent != "") {
            this.$axios({
              method:"post",
              url:"comments/addComments",
              data:{
                fatherId: this.comments.id,
                username: this.formInline.username,
                content: replayContent,
                email: this.formInline.email
              }
            }).then((response) => {
              if(response.data.type == "success") {
                this.refreshChildrenComment();
              }
              Message.success("评论成功");
            })
          } else {
            Message('回复不能为空');
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },
    replayToChildren: function (name) {
      this.ifShowReplayToChildren = true;
      this.replayTo = name;
      this.myPlaceholder = "回复给" + name + "...";
      var page = document.querySelector("#formInline2");
      var top = this.getElementToTop(page);
      window.scrollTo({
        'top': top,
        'behavior': 'smooth'
      })
    },
    addReplayToChildren: function(formName) {
      this.$refs[formName].validate((valid) => {
        if(valid) {
          var replayContent = this.replayTextarea2.trim();
          if(replayContent != null && replayContent != "") {
            this.$axios({
              method:"post",
              url:"comments/addComments",
              data:{
                fatherId: this.comments.id,
                username: this.formInline2.username2,
                content: replayContent,
                email: this.formInline2.email2,
                replayTo: this.replayTo
              }
            }).then((response) => {
              if(response.data.type == "success") {
                this.refreshChildrenComment();
              }
              Message.success("评论成功");
              this.ifShowReplayToChildren = false;
              this.formInline2.username2 = "";
              this.formInline2.email2 = "";
              this.replayTextarea2 = "";

            })
          } else {
            Message('回复不能为空');
          }
        } else {
          console.log('error submit!!');
          return false;
        }
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
p {
  text-indent: 2em;
  color: rgba(0,0,0,0.65);
}
.commentsSame {
  margin-left: 3px;
  color: rgba(0,0,0,0.45);
  cursor: pointer;
}
.commentsDate {
  margin: 2px;
  color: #ccc;
}
.commentsAuthor {
  margin: 3px;
  color: rgba(0,0,0,0.45);
}
</style>
