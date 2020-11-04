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
            <a-icon v-show="this.comments.likes == 0" class="commentsSame" type="like" @click="like(parentComments.id)"></a-icon>
            <a-icon v-show="this.comments.likes > 0" class="commentsSame" type="like" @click="like(parentComments.id)" theme="filled"></a-icon>
            <span v-show="this.comments.likes > 0" style="color:#67c23a">{{this.comments.likes}}</span>

            <!--点踩按钮-->
            <a-icon v-show="this.comments.dislikes == 0" class="commentsSame" type="dislike" @click="dislike(parentComments.id)"></a-icon>
            <a-icon v-show="this.comments.dislikes > 0" class="commentsSame" type="dislike" @click="dislike(parentComments.id)" theme="filled"></a-icon>
            <span v-show="this.comments.dislikes > 0" style="color: #ff4d4f">{{this.comments.dislikes}}</span>

            <span class="commentsSame" @click="openReplay()">回复</span>
            <time class="commentsDate">{{this.comments.updateTime}}</time>
        </div>
        <div>
          <el-card shadow="never" style="background-color:#f4f4f4;" v-show="ifShowReplayTextarea">
            <div>
              <el-input type="textarea" :rows="2" placeholder="回复..." v-model="replayTextarea"></el-input>
            </div>
            <div>
                <a-button type="primary" @click="addReplay()" style="float: right">回复</a-button>
            </div>
          </el-card>
        </div>
        <div>
          <el-card shadow="never" style="background-color: #f4f4f4" v-for="item in this.comments2.childrenComments" :key="item.id">
            <div class="commentsAuthor">
              {{item.username}}:
              <p>
                {{item.content}}
              </p>
              <time class="commentsDate">{{item.updateTime}}</time>
            </div>
          </el-card>
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
      replayNotNull:false,
      ifShowReplayTextarea: false
    }
  },
  props: ['parentComments'],

  mounted:function() {

  },
  methods: {
    like: function (id){
      this.comments.likes++;
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
      this.comments.dislikes++;
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
    addReplay: function () {
      var replayContent = this.replayTextarea.trim();
      if(replayContent != null && replayContent != "") {
        this.$axios({
          method:"post",
          url:"comments/addComments",
          data:{
            fatherId: this.comments.id,
            username: '游客测试',
            content: replayContent
          }
        }).then((response) => {
          if(response.data.type == "success") {
            this.refreshChildrenComment();
          }
        })
      } else {
        Message('回复不能为空');
      }
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
