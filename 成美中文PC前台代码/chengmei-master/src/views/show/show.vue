<template>
  <div class="show_container">
    <!-- 头部区域 -->
    <hea-der class="header"></hea-der>
    <!-- 大图 -->
    <div class="cycle_image">
      <img width="100%" :src="link + ImageUrl" />
    </div>
    <!-- 步骤菜单 -->
    <div
      :class="['steps', flexd ? 'flexd' : '', active === 4 ? 'sb_yh' : '']"
      ref="setepe"
    >
      <el-steps
        direction="vertical"
        process-status="process"
        :active="active"
        finish-status="process"
        :space="100"
      >
        <el-step
          v-for="(item, index) in navList"
          :key="item.menuName"
          :class="index + 1 === active ? 'activeChange' : ''"
          @click.native="goto(item.menuId)"
          :title="item.menuName"
        />
      </el-steps>
    </div>
    <!-- 搜索导航栏 -->
    <div class="nav_content">
      <div class="nav">
        <el-input
          placeholder="输入项目名称或关键词查找"
          class="input-with-select"
          v-model="seekText"
          clearable
          @input="getInput"
        >
        </el-input>
        <div class="div_btn" @click="seek()">
          <el-button type="warning" class="btn"></el-button>
          <i class="iconfont icon-Teamsousuo"></i>
        </div>
      </div>
    </div>
    <!-- 自有项目 -->
    <Module
      id="8"
      ref="own"
      :title="navList[0] && navList[0].menuName"
      sub-title="THEIR OWN PROJECTS"
      :list="ownList"
      :more-route="{ path: '/more', query: { index: 8 } }"
      @click-item="setpop"
    ></Module>
    <!-- 联合劝募 -->
    <Module
      ref="unite"
      id="9"
      :title="navList[1] && navList[1].menuName"
      sub-title="THE JOINT FUNDRAISING"
      :list="uniteList"
      :more-route="{ path: '/more', query: { index: 9 } }"
      @click-item="setpop"
    ></Module>
    <!-- 专项基金-->
    <Module
      id="10"
      ref="specia"
      :title="navList[2] && navList[2].menuName"
      sub-title="SPECIAL FUND"
      :list="specialList"
      :more-route="{ path: '/more', query: { index: 10 } }"
      @click-item="setpop"
    ></Module>
    <!-- 视频专区-->
    <Module
      id="11"
      ref="video"
      :title="navList[3] && navList[3].menuName"
      sub-title="VIDEO ZONE"
      :list="videoList"
      :more-route="{ path: '/more', query: { index: 11 } }"
      @click-item="setpop"
    ></Module>
    <!-- 弹窗 -->
    <el-dialog @close="closeDialog" :visible.sync="dialogVisible" width="60%">
      <div class="el-dialog-div">
        <span v-html="itemPopup"></span>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
import HeaDer from "@/components/header/header.vue";
import {
  getAPPLY,
  getDETAILS,
  getMENU,
  addVISIT,
  getMUNt,
} from "@/api/methods.ts";
import Module from "./components/module";
import { link } from "@/utils/common.ts";
export default {
  components: {
    HeaDer,
    Module,
  },
  watch: {
    $route: {
      //深度监听，可监听到对象、数组的变化
      handler(val, oldVal) {
        this.goto(val.query.menuId);
      },
      deep: true, //true 深度监听
    },
  },
  data() {
    return {
      link,
      flexd: false,
      active: 1,
      //项目模块数据
      ownList: [],
      // uniteList
      uniteList: [],
      // specialList
      specialList: [],
      // videoList
      videoList: [],
      //弹窗
      dialogVisible: false,
      itemPopup: "",
      titlePopup: "",
      //菜单
      navList: [],
      seekText: "",
      //大图
      ImageUrl: "",
    };
  },

  methods: {
    getModulesItem(keyword) {
      getAPPLY({ menuId: 8, key: keyword }).then(({ rows }) => {
        this.ownList = [];
        this.$nextTick(() => {
          this.ownList = rows;
        });
      });

      getAPPLY({ menuId: 9, key: keyword }).then(({ rows }) => {
        this.uniteList = [];
        this.$nextTick(() => {
          this.uniteList = rows;
        });
      });

      getAPPLY({ menuId: 10, key: keyword }).then(({ rows }) => {
        this.specialList = [];
        this.$nextTick(() => {
          this.specialList = rows;
        });
      });

      getAPPLY({ menuId: 11, key: keyword }).then(({ rows }) => {
        this.videoList = [];
        this.$nextTick(() => {
          this.videoList = rows;
        });
      });
    },
    goto(id) {
      setTimeout(() => {
        document.documentElement.scrollTop =
          document.getElementById(id).offsetTop;
      }, 1000);
    },
    //输入框事件
    getInput(val) {
      if (val === "") {
        this.getModulesItem();
      }
    },
    //搜索内容
    async seek() {
      this.getModulesItem(this.seekText);
    },
    //弹出弹窗
    async setpop(t) {
      this.dialogVisible = true;
      this.titlePopup = t.title;
      //将当前点击的数组数据返回
      let res = await getDETAILS({ articleId: t.articleId });
      this.itemPopup = res.msg;
      //添加访问量
      await addVISIT({ articleId: t.articleId });
    },
    //关闭弹窗
    closeDialog() {
      this.itemPopup = "";
    },
    //  屏幕滚动多利用
    scrollTop() {
      return new Promise((resolve, reject) => {
        //  获取屏幕滚动的高度
        let scrollTop =
          window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop;
        resolve(scrollTop);
      });
    },
    //  侦听屏幕滚动
    async handleScroll() {
      let scrollTop = await this.scrollTop();
      let uniteNum = this.$refs.unite.getScroll();
      let speciaNum = this.$refs.specia.getScroll();
      let videoNum = this.$refs.video.getScroll();
      if (scrollTop >= 260) {
        this.flexd = true;
      }
      if (scrollTop <= 480) {
        this.flexd = false;
        this.active = 1;
      }
      if (scrollTop >= uniteNum - 140) {
        this.active = 2;
      }
      if (scrollTop >= speciaNum - 140) {
        this.active = 3;
      }
      if (scrollTop >= videoNum - 140) {
        this.active = 4;
      }
    },
  },
  async created() {
    //获取导航栏菜单
    let menures = await getMENU({ parentId: 7 });
    this.navList = menures.data;
    this.getModulesItem();
    console.log(this.navList);
    //获取图片
    let resImage = await getMUNt({ menuId: 7 });
    this.ImageUrl = resImage.data.menuImgUrl;
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    if (sessionStorage.getItem("menuId")) {
      this.goto(sessionStorage.getItem("menuId"));
    }
  },
};
</script>
<style scoped lang='scss'>
::v-deep .swiper-container {
  cursor: pointer !important;
}
.activeChange ::v-deep .el-step__icon.is-text {
  color: #ffa631 !important;
}
.activeChange ::v-deep .el-step__title.is-process {
  color: #ffa631 !important;
  font-weight: bold !important;
}
.activeChange ::v-deep .el-step__icon.is-text {
  border: 1px solid #ffa631;
}
::v-deep .el-step__title.is-process {
  font-weight: bold !important;
}
::v-deep .el-step__main {
  font-weight: bold !important;
}

.show_container {
  position: relative;
}

.side-nav {
  position: fixed;
  left: 6%;
}

// 屏幕滚动到哪里显示的位置
.flexd {
  position: fixed !important;
  left: 6%;
  top: 500px !important;
}
.sb_yh {
  position: fixed !important;
  top: -6% !important;
}
//滚动顶部的样式
.steps {
  position: absolute;
  left: 6%;
  top:650px;
  cursor: pointer;
  z-index: 990;
}

// 选中的
::v-deep .el-step__head.is-finish {
  color: #ffa631 !important;
}
::v-deep .el-step__line-inner {
  border-color: #ffa631 !important;
  background: #ffa631 !important;
}
::v-deep .el-step__head.is-finish > .el-step__icon.is-text {
  border-color: #ffa631;
}
::v-deep .el-step__title.is-finish {
  color: #ffa631 !important;
  font-size: 18px !important;
  // font-weight: bold !important;
}
// 未选中的
::v-deep .el-step__head.is-process {
  color: #999 !important;
  border-color: #999 !important;
  // font-weight: bold !important;
}
::v-deep .el-step__title.is-wait {
  color: #999 !important;
}
// 未选中的线条颜色
::v-deep .el-step__line {
  background: #999 !important;
}
// ::v-deep .el-step__title.is-process {
//   font-weight: bold !important;
// }
::v-deep .el-step__head.is-wait {
  color: #999 !important;
}
::v-deep .el-step__head.is-wait > .el-step__icon.is-text {
  border-color: #999;
}
//预选文字
::v-deep .el-step__title.is-process {
  color: #999 !important;
}

//弹窗
.el-dialog-div {
  height: 60vh;
  overflow: auto;
}
// 修改滚动条的样式
.el-dialog-div::-webkit-scrollbar {
  border-radius: 10px;
}
.el-dialog-div::-webkit-scrollbar {
  width: 6px !important;
  height: 8px;
  background: #fff;
}
.el-dialog-div::-webkit-scrollbar-thumb {
  width: 6px;
  background: #ffa631;
  border-radius: 5px;
}
.show_container {
  font-family: "Microsoft YaHei";
}
.header {
  position: fixed;
  z-index: 99;
  top: 0;
  left: 0;
  right: 0;
}
.cycle_image {
  margin-top: 95px;
  font-size: 0px;
}
.nav_content {
  position: relative;
  width: 100%;
  height: 64px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fafafc;
  .nav {
    text-align: right;
    width: 72%;
    position: relative;
    // max-width: 1400px;
    .input-with-select {
      width: 22em;
      position: relative;
      right: 3.8em;
    }
    .div_btn {
      display: inline-block;
      position: absolute;
      top: -0.08em;
      right: -0.1em;
      .btn {
        position: relative;
        top: 1.2px;
        right: 5px;
        height: 40px;
        border-radius: 2px;
        width: 4em;
        background: #ffa632;
      }
      i {
        position: absolute;
        left: 12px;
        font-size: 22px;
        top: 8px;
        color: white;
      }
    }
  }
}
</style>
