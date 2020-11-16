<template>
  <div>
    <el-tag
      :key="tag"
      v-for="tag in dynamicTags"
      closable
      :disable-transitions="false"
      @close="handleClose(tag)">
      {{tag}}
    </el-tag>
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
      <el-tag :key="tag"
              style="cursor: pointer"
              type="info"
              v-for="tag in allTags"
              :disable-transitions="true"
              @click="addTags(tag)"
              size="mini"
      >
        {{tag}}
      </el-tag>
      <el-button class="button-new-tag" slot="reference" size="medium" icon="el-icon-plus">添加标签</el-button>
    </el-popover>
  </div>
</template>

<script>
export default {
  name: "ChooseTags",
  data() {
    return {
      dynamicTags: [],
      inputVisible: false,
      inputValue: '',
      allTags:[],
      searchTags:"",
    }
  },
  methods:{
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },
    loadTags:function() {
      this.$axios({
        methods: "get",
        url: "tags/getTagsList"
      }).then((response) => {
        var list = response.data.data;
        for(var i = 0;i < list.length;i++) {
          var name = list[i].name;
          this.allTags.push(name);
        }
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
        if(this.dynamicTags[i] == tag) {
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
