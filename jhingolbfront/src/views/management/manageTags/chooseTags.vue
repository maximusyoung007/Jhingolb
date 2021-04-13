<template>
  <div>
    <el-popover
      width="300"
      trigger="click"
      @show="loadTags()"
      @hide="clearTags()"
      x-placement="bottom"
      modal-append-to-body="false"
    >
      <p>请选择标签(最多可选择五个标签)</p>
      <div>
        <el-input placeolder="可选择标签" id="searchTags" v-model="searchTags" clearable/>
      </div>
      <br>
      <el-tag :key="tag.id"
              style="cursor: pointer"
              type="info"
              v-for="tag in allTags"
              :disable-transitions="true"
              @click="addTags(tag)"
              size="mini"
      >
        {{tag.name}}
      </el-tag>
      <el-button class="button-new-tag" slot="reference" size="medium" icon="el-icon-plus">添加标签</el-button>
    </el-popover>
    <el-tag
      :key="tag.id"
      v-for="tag in dynamicTags"
      closable
      :disable-transitions="false"
      @close="handleClose(tag)">
      {{tag.name}}
    </el-tag>
  </div>
</template>

<script>
export default {
  name: "ChooseTags",
  data() {
    return {
      dynamicTags: this.chosenTag,
      inputVisible: false,
      inputValue: '',
      allTags:[],
      searchTags:"",
    }
  },
  props:['chosenTag'],
  //监听异步传过来的数据
  watch:{
    chosenTag: function (newVal,oldVal) {
      this.dynamicTags = newVal;
    }
  },
  methods:{
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      this.$emit("tagsChange", this.dynamicTags);
    },
    loadTags:function() {
      this.$axios({
        method: "post",
        url: "tags/getTagsList",
        data: {
          useless:0
        }
      }).then((response) => {
        this.allTags = response.data.data.list;
      })
    },
    clearTags: function () {
      this.allTags.length = 0;
    },
    addTags: function(tag) {
      var i = 0,len = this.dynamicTags.length;
      if(len == 5) {
        return;
      }
      for(i = 0;i < len;i++) {
        if(this.dynamicTags[i].name == tag.name) {
          return;
        }
      }
      if(i == len) {
        this.dynamicTags.push(tag);
      }
      this.$emit("tagsChange",this.dynamicTags);
    },
  }
}
</script>

<style scoped>
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
