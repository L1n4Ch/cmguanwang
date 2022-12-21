<template>
  <div>
    <!-- 头部区域 -->
    <hea-der class="header"></hea-der>
    <!-- 轮图 -->
    <div class="cycle_image">
      <img width="100%" :src="link + ImageUrl" />
    </div>
    <!-- 标题栏 -->
    <div class="nav">
      <ul class="title_container">
        <li
          v-for="(t, i) of menuList"
          :key="i"
          :class="isActive === i ? 'active' : ''"
          @click="setActive($event, i)"
        >
          {{ t.menuName }}
        </li>
        <li v-show="false">
          {{ (isActiveMenuId = this.$route.query.menuId) }}
        </li>
      </ul>
    </div>
    <!-- 最新动态 -->
    <div
      class="dynamic"
      v-show="isActive == 2 || isActive == 3 || isActive == 4 ? false : true"
    >
      <p class="dynamic_title">最新动态</p>
      <el-card class="box-card">
        <div class="box-card-1">
          <div class="box-1">
            <p>{{ newestList[0] && newestList[0].title }}</p>
            <p v-show="isActive == 5 ? false : true">
              {{ newestList[0] && newestList[0].introduce }}
            </p>
            <div class="box-time">
              <span
                >时间：{{
                  newestList[0] && newestList[0].updateTime.split(" ")[0]
                }}</span
              >
              <span class="yuedu">
                <i class="iconfont icon-shijian"></i>
                <span>阅读：{{ newestList[0] && newestList[0].readNum }}</span>
              </span>
            </div>
            <el-button
              class="box-btn"
              type="warning"
              round
              @click="setpop(newestList[0])"
              >了解更多</el-button
            >
          </div>
        </div>
        <div class="box-card-2">
          <img
            :src="link + (this.newestList[0] && this.newestList[0].bgImageUrl)"
            alt=""
            @click="setpop(newestList[0])"
          />
          <div class="box-dot"><span></span><span></span></div>
        </div>
        <div class="box-card-3">
          <img
            @click="setpop(newestList[1])"
            :src="link + (this.newestList[1] && this.newestList[1].bgImageUrl)"
          />
          <p @click="setpop(newestList[1])">
            {{ newestList[1] && newestList[1].title }}
          </p>
          <p
            @click="setpop(newestList[1])"
            v-show="isActive == 5 ? false : true"
          >
            {{ newestList[1] && newestList[1].introduce }}
          </p>
        </div>
      </el-card>
    </div>
    <!-- 基金会动态 -->
    <div class="funds">
      <div class="row">
        <div class="tp">
          {{ isTitle ? this.menuList[this.isActive].menuName : "" }}
        </div>
        <div class="box1">
          <div class="icon">
            <i class="iconfont icon-wannengsousuo"></i>
            <h5>关键词</h5>
          </div>
          <!-- 搜索导航栏 -->
          <div class="box2_content">
            <div class="box2">
              <el-input
                placeholder="输入项目名称或关键词查找"
                class="input-with-select"
                v-model="seekText"
                clearable
                @input="getInput"
              >
              </el-input>
              <div class="div_btn" @click="seek">
                <el-button type="warning" class="btn"></el-button>
                <i class="iconfont icon-Teamsousuo"></i>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 基金动态列表 -->
    <div class="funds-list">
      <div class="list-row" v-show="isActive != 4 ? true : false">
        <el-col v-for="(t, i) of showList" :key="i" class="list-el-col">
          <div @click="setpop(t)">
            <el-card :body-style="{ padding: '0px' }" class="el-card-item">
              <img
                :src="link + t.bgImageUrl"
                style="width: 100%; height: 13.6vw; object-fit: contain"
              />
              <div style="padding: 14px" class="box-list">
                <h5>{{ t.title }}</h5>
                <p
                  v-show="
                    isActive == 2 || isActive == 3 || isActive == 5
                      ? false
                      : true
                  "
                >
                  {{ t.introduce }}
                </p>
                <div class="clearfix">
                  <i class="iconfont icon-shijian"></i>
                  <span v-if="t.updateTime.length===0">{{ t.createTime.split(" ")[0] }}</span>
                  <span v-else>{{ t.updateTime.split(" ")[0] }}</span>
<!--                  <span>{{ t.createTime.split(" ")[0] }}</span>-->
                  <i class="iconfont icon-tubiaozhizuomoban"></i>
                  <span>阅读：{{ t.readNum }}</span>
                </div>
                <div class="details">
                  <span>查看详情</span>
                </div>
              </div>
            </el-card>
          </div>
        </el-col>
      </div>
      <div class="content" v-show="isActive == 4 ? true : false">
        <div
          v-for="(item, index) in showList"
          :key="index"
          @mouseleave="isShowFalse(item)"
        >
          <div
            class="img_div"
            @mouseenter="isShowTrue(item)"
            @click="setpopSH(item)"
          >
            <img :src="link + item.bgImageUrl" />
          </div>
          <p>{{ item.title }}</p>
          <el-button
            round
            :class="['hover', item.isShow ? 'crate' : '']"
            @click="setpopSH(item)"
            >查看详情</el-button
          >
        </div>
      </div>
    </div>
    <!-- 分页 -->
    <div class="paging">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.currentPage"
        :page-sizes="[9, 15, 30]"
        :page-size="6"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total"
      >
      </el-pagination>
    </div>
    <!-- 弹窗 -->
    <el-dialog
      top="12vh"
      @close="closeDialog"
      :title="titlePopup"
      :visible.sync="dialogVisible"
      width="60%"
    >
      <div class="el-dialog-div">
        <span v-html="this.itemPopup" v-show="!isDialog"></span>
        <embed
          :src="link + itemPopup"
          type="application/pdf"
          width="100%"
          height="100%"
          internalinstanceid="81"
          v-show="isDialog"
        />
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
import HeaDer from "@/components/header/header";
import {
  getAPPLY,
  getDETAILS,
  getNEWEST,
  getMENU,
  addVISIT,
  getMUNt,
} from "@/api/methods.ts";
import { link } from "@/utils/common.ts";
export default {
  data() {
    return {
      isDialog: false, //弹窗样式
      link,
      isShow: false,
      currentPage: 1, //当前页
      dialogVisible: false, //弹窗开关
      total: 0, //总条数
      numberPage: 9, //每页的条数
      showList: [], //当前显示的数据
      newestList: [], //最新动态数组
      menuList: [
        "基金会动态",
        "媒体动态",
        "信息公布",
        "退款公示",
        "审计报告",
        "项目展示",
      ], //导航菜单
      //当前到导航栏
      isActive: 0,
      isActiveMenuId: 16,
      //鼠标悬浮时，切换样式 存在之前的导航选项
      titlePopup: "", //弹窗标题
      itemPopup: "", //存储当前点击的弹窗卡片内容
      seekText: "", //搜索框内容
      ImageUrl: "", //大图
    };
  },
  methods: {
    //输入框事件
    async getInput(val) {
      if (val == "") {
        await this.getShowList(this.isActive);
      }
    },
    //搜索内容
    async seek() {
      this.currentPage = 1;
      let res = await getAPPLY({
        menuId: this.isActive + 16,
        key: this.seekText,
        pageNum: this.currentPage,
        pageSize: this.numberPage,
      });
      this.total = res.total;
      this.showList = res.rows;
    },
    //导航点击 传递获取的文章id 进行获取数据
    async setActive(e, id) {
      //设置选中id
      this.isActive = id;
      this.jumpTo("/info", 5, id + 16);
      // await this.getShowList(id);
    },

    //获取列表数据
    async getShowList(id) {
      //获取最新动态
      let newres = await getNEWEST({ menuId: id + 16 });
      this.newestList = newres.rows;

      //请求导航选中的菜单数据--id为0时对应的是 基金动态16 所以加16 （其他的同理
      let res = await getAPPLY({ menuId: id + 16 });
      this.total = res.total;

      this.currentPage = 1;
      let respage = [];

      if (id == 2 || id == 3) {
        respage = await getAPPLY({
          menuId: id + 16,
          pageNum: this.currentPage,
          pageSize: this.numberPage,
        });
      } else if (id == 4) {
        respage = await getAPPLY({
          menuId: id + 16,
          pageNum: this.currentPage,
          pageSize: this.numberPage,
        });
        respage.rows.forEach((element) => {
          element.isShow = false;
        });
      } else {
        respage = await getAPPLY({
          menuId: id + 16,
          pageNum: this.currentPage,
          pageSize: this.numberPage,
          newStatus: 1,
        });
      }
      this.showList = respage.rows;
    },

    async handleSizeChange(val) {
      console.log("条数" + val);
      //请求每页数据
      let respage = await getAPPLY({
        menuId: this.isActive + 16,
        pageNum: this.currentPage,
        pageSize: val,
      });
      this.showList = respage.rows;
      //更改每页条数
      this.numberPage = val;
    },
    async handleCurrentChange(val) {
      console.log("页数" + val);
      //请求下一页
      let respage = await getAPPLY({
        menuId: this.isActive + 16,
        pageNum: val,
        pageSize: this.numberPage,
      });
      this.showList = respage.rows;
      console.log(respage.rows);
      //更改当前页数
      this.currentPage = val;
    },
    jumpTo(val, index, id) {
      this.index = index;
      sessionStorage.setItem("index", index);
      if (val == "/aboutus" && id == "6") {
        this.$router.push({
          path: "/#page3",
        });
        return;
      }
      if (id) {
        sessionStorage.setItem("menuId", id);
        this.$router.push({
          path: val,
          query: {
            menuId: id,
          },
        });
      } else {
        this.$router.push({
          path: val,
        });
      }
    },
    //弹窗  传入点击当前卡片的数据
    async setpop(t) {
      //更改弹窗样式
      this.isDialog = false;
      //获取当前点击的卡片详情信息
      let res = await getDETAILS({ articleId: t.articleId });
      this.dialogVisible = true;
      //存储当前点击的卡片数据
      this.itemPopup = res.msg;
      this.titlePopup = t.title;

      //添加访问量
      await addVISIT({ articleId: t.articleId });
    },
    //关闭弹窗
    closeDialog() {
      this.itemPopup = "";
    },
    //弹窗  传入点击当前卡片的数据
    async setpopSH(t) {
      //更改弹窗样式
      this.isDialog = true;
      this.dialogVisible = true;
      //存储当前点击的卡片数据
      this.itemPopup = t.extPdf;
      this.titlePopup = t.title;

      //添加访问量
      await addVISIT({ articleId: t.articleId });
    },

    // 鼠标移除
    isShowFalse(item) {
      item.isShow = false;
    },
    // 鼠标移入
    isShowTrue(item) {
      item.isShow = true;
    },
  },
  watch: {
    async isActiveMenuId() {
      this.isActive = this.isActiveMenuId - 16;
      await this.getShowList(this.isActiveMenuId - 16);
    },
  },
  components: {
    HeaDer,
  },
  async created() {
    //获取导航栏菜单
    let menures = await getMENU({ parentId: 15 });
    this.menuList = menures.data;

    // //获取默认获取基金动态
    // let res = await getAPPLY({ menuId: 16 });
    // this.total = res.total;

    // //默认请求第一页9条数据
    // let respage = await getAPPLY({
    //   menuId: 16,
    //   pageNum: 1,
    //   pageSize: 9,
    //   newStatus: 1,
    // });
    // console.log(respage);
    // this.showList = respage.rows;

    // //默认获取最新基金会动态
    // let newres = await getNEWEST({ menuId: 16 });
    // this.newestList = newres.rows;
    // this.jumpTo('/info',5,id+16);

    ///获取图片
    let resImage = await getMUNt({ menuId: 15 });
    this.ImageUrl = resImage.data.menuImgUrl;
  },
};
</script>
<style scoped lang='scss'>
.el-dialog-div {
  height: 60vh;
  overflow: auto;
}
::v-deep .el-dialog__title {
  // transform: translateX(100%);
  width: 60vw;
  text-align: center;
  display: block;
  font-weight: 600;
  font-size: 23px;
  margin-top: 20px;
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
}
.show_container {
  font-family: "微软雅黑" !important;
}
.header {
  position: fixed;
  z-index: 99;
  top: 0;
  left: 0;
  right: 0;
}
.cycle_image {
  font-size: 0px;
  margin-top: 95px;
}
.nav {
  height: 68px;
  border-bottom: 1px solid #efeff1;
  background-color: white;
  display: flex;
  justify-content: center;
}
.title_container {
  width: 72%;
  // max-width: 1400px;
  display: flex;
}
.nav li {
  cursor: pointer;
  width: 130px;
  height: 64px;
  line-height: 64px;
  font-size: 16px;
  text-align: center;
  color: #666666;
}
.nav li a {
  display: inline-block;
  width: 100%;
  height: 100%;
  line-height: 64px;
  font-size: 16px;
  text-align: center;
  color: #666666;
}
.active {
  border-bottom: 4px solid #ffa630;
}
.nav li:hover {
  border-bottom: 4px solid #ffa630;
}
.dynamic {
  width: 100%;
  display: flex;
  cursor: pointer;
  align-items: center;
  flex-direction: column;
  background-color: #fafafc;
  padding-bottom: 50px;
}
.dynamic_title {
  width: 72%;
  // max-width: 1400px;
  margin-top: 50px;
  margin-bottom: 30px;
  font-size: 22px;
  color: #30312c;
}
.box-card {
  width: 72%;
  // max-width: 1400px;
  // height: 40vw;
  // max-height: 320px;
  // min-height: 300px;
  padding-bottom: 20px;
}
.box-card-1 {
  width: 36%;
  float: left;
}
.box-1 {
  width: 90%;
}
.box-card-1 p:nth-child(1) {
  font-size: 1.5em;
  font-family: "微软雅黑";
  letter-spacing: 4px;
  color: #434343;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.box-card-1 p:nth-child(2) {
  font-size: 1.2em;
  font-family: "微软雅黑";
  color: #919191;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  margin-top: 10px;
}
.box-time {
  font-size: 1em;
  color: #919191;
  margin-top: 20px;
}
.box-time span:nth-child(2) {
  position: relative;
  padding-left: 1.4vw;
}
.box-time .icon-shijian {
  position: absolute;
  left: 1.5vw;
  font-size: 1.2em;
  right: 1em;
}
.box-btn {
  margin-top: 24px;
  background-color: #ffa631;
  height: 36px;
  line-height: 1px;
}
.box-card-2 {
  width: 35%;
  float: left;
  font-size: 0;
  overflow: hidden;
  padding-right: 3.5%;
}
.box-card-2 img {
  display: block;
  width: 100%;
  height: 26vw;
  max-height: 240px;
  object-fit: cover;
}
.box-dot {
  width: 100%;
  display: flex;
  justify-content: center;
  padding-top: 20px;
}
.box-dot span:nth-child(1) {
  width: 32px;
  height: 6px;
  border-radius: 10px;
  background-color: #ffa630;
  margin-left: 10px;
}
.box-dot span:nth-child(2) {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background-color: #dddddd;
  margin-left: 10px;
}
.box-card-3 {
  width: 21%;
  float: left;
  border-left: 1px solid #e6e6e6;
  padding-left: 3.5%;
  // height: 4em;
}
.box-card-3 img {
  width: 100%;
  height: 7vw;
  object-fit: cover;
}
.box-card-3 p:nth-child(2) {
  color: #30383a;
  font-size: 18px;
  font-weight: 520;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  margin-top: 5px;
}
.box-card-3 p:nth-child(3) {
  color: #9ea1a6;
  font-size: 12px;
  margin-top: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  margin-top: 10px;
  height: 6em;
  max-height: 80px;
  line-height: 2.1em;
}
.funds {
  width: 100%;
  display: flex;
  justify-content: center;
}
.row {
  width: 72%;
  display: flex;
  // max-width: 1400px;
  align-items: center;
  justify-content: space-between;
  height: 100px;
}
.box1 {
  display: flex;
  position: relative;
  left: 4px;
}
.icon {
  height: 4em;
  // min-width: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.icon h5 {
  font-size: 16px;
  font-weight: 800;
  width: 5em;
}
.icon i {
  margin-left: 6px;
  font-size: 32px;
  color: #1e5ba7;
}
.tp {
  font-size: 22px;
  color: #30312c;
}
.box2_content {
  width: 100%;
  display: flex;
  position: relative;
  top: 12px;
  .box2 {
    .input-with-select {
      width: 22em;
      margin-right: 3.7em;
    }
    .div_btn {
      display: inline-block;
      position: absolute;
      cursor: pointer;
      top: -0.2em;
      right: -0.2em;
      .btn {
        position: relative;
        right: 5px;
        height: 2.9em;
        border-radius: 2px;
        width: 4em;
        background: #ffa632;
        top: 0.15vw;
      }
      i {
        position: absolute;
        left: 13px;
        font-size: 22px;
        top: 9px;
        color: white;
      }
    }
  }
}
.funds-list {
  width: 100%;
  display: flex;
  justify-content: center;
}
.list-row {
  cursor: pointer;
  width: 74%;
  // max-width: 1500px;
  display: flex;
  // justify-content:space-around;
  flex-wrap: wrap;
  padding-left: 2%;
}
.image {
  display: block;
  width: 100%;
  height: 16em;
}
.list-el-col:nth-child(n) {
  width: 31%;
  margin-right: 2.333%;
}
.el-card-item {
  margin-bottom: 2em;
}
.clearfix {
  position: relative;
  margin-top: 10px;
  margin-bottom: 6px;
  color: #8a8889;
  font-size: 1em;
}
.clearfix span:nth-child(2) {
  display: inline-block;
  margin-left: 0.6em;
}
.clearfix .icon-shijian {
  position: relative;
  font-size: 14px;
  left: 0.2em;
}
.clearfix .icon-tubiaozhizuomoban {
  font-size: 14px;
  margin-left: 20px;
  margin-right: 2px;
}
.el-card-item:hover p {
  color: #ffa62e;
  cursor: default;
}
.el-card-item:hover h5 {
  color: #ffa62e;
  cursor: default;
}
.el-card-item:hover .clearfix {
  color: #ffa62e;
  cursor: default;
}
.box-list h5 {
  font-size: 1.2em;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.box-list > p {
  color: #8a8889;
  font-size: 12px;
  height: 4em;
  line-height: 2em;
  word-wrap: break-word;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  margin-top: 12px;
}
.details {
  height: 50px;
  position: relative;
  text-align: center;
  line-height: 50px;
  border-top: 1px solid #f5f5f5;
  color: #ffa62e;
  cursor: default;
  padding: 5px;
  top: 10px;
  span {
    font-size: 18px;
  }
}
.paging {
  width: 100%;
  text-align: center;
  padding-top: 50px;
  padding-bottom: 50px;
}

.content {
  overflow: hidden;
  display: flex;
  width: 74%;
  margin: 0 auto;
  flex-wrap: wrap;
  align-items: center;
  justify-content: flex-start;
  box-sizing: border-box;
  padding-left: 2%;
  max-width: 1600px;
  margin-top: -30px;
  div {
    width: 31%;
    margin: 20px 0.5%;
    display: flex;
    justify-content: space-around;
    align-items: center;
    overflow: hidden;
    flex-direction: column;
    font-size: 20px;
    padding: 0px 5px;
    position: relative;
    p {
      margin: 10px 0px;
      word-wrap: break-word;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 1;
      -webkit-box-orient: vertical;
    }
    .img_div {
      width: 90% !important;
      height: 488px;
      padding: 0 !important;
      border: 1px solid #ccc;
      cursor: pointer;
      img {
        width: 100% !important;
        margin-bottom: 5px;
        transition: all 0.5s;
        max-width: 360px;
      }
    }
  }
}
.content div .img_div:hover img {
  transform: scale(1.05, 1.05);
}
.hover:hover {
  background: #ffa631;
  color: #ffff;
}
.crate {
  background: #ffa631;
  color: #fff;
}
</style>
