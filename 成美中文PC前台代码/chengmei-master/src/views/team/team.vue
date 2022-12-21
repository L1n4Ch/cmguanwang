<template>
  <div id="container">
    <!-- 头部区域 -->
    <hea-der class="header"></hea-der>
    <!-- 大图 -->
    <div class="cycle_image">
      <img width="100%" :src="link + ImageUrl" />
    </div>
    <!-- 项目申请标题 -->
    <div class="title">
      <span class="title_content">
        <ul>
          <li>{{navtitle}}</li>
        </ul>
      </span>
    </div>
    <!-- 图片内容展示 -->
    <div class="content-bac">
      <div class="content">
        <div
          v-for="(t, i) of arry"
          :key="i"
          class="content-row"
          @click="setpop(t)"
        >
          <div class="content-img">
            <img :src="link + t.bgImageUrl" />
          </div>
          <div class="content-comment">
            <div class="comment-left">
              <h5>{{sliceTime(t.createTime,1)}}</h5>
              <p>{{sliceTime(t.createTime,2)}}</p>
            </div>
            <div class="comment-right">
              <h5>{{ t.title }}</h5>
              <p>POSTED BY CHENGMEI</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 分页 -->
    <div class="paging">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[9, 15, 30]"
        :page-size="6"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total"
      >
      </el-pagination>
    </div>
    <!-- 弹窗 -->
    <el-dialog
      :title="this.titlePopup"
      :visible.sync="dialogVisible"
      width="60%"
    >
      <div class="el-dialog-div">
        <span v-html="this.itemPopup"></span>
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
import { getAPPLY, getDETAILS,addVISIT,getMENU,getMUNt} from "@/api/methods.ts";
import {link} from '@/utils/common.ts'
export default {
  data() {
    return {
      link,
      arry: [], //文章数组
      dialogVisible: false, //弹窗开关
      itemPopup: "", //弹窗数据
      titlePopup: "", //弹窗标题
      currentPage: 1, //默认页数
      total: 0, //总条数
      numberPage: 9, //每页的条数
      navtitle:'',//导航标题
      ImageUrl:''//大图
    };
  },
  methods: {
    //截取日期月和日
    sliceTime(t,id){
      if(id==1){
        return t.slice(5,7);
      }else{
        return t.slice(8,10);
      }  
    },
    async handleSizeChange(val) {
      //更改每页条数
      let respage = await getAPPLY({
        menuId: 13,
        pageNum: this.currentPage,
        pageSize: val,
      });
      this.arry = respage.rows;
      //修改每一页条数
      this.numberPage = val;
    },
    async handleCurrentChange(val) {
      //获取下一页
      let respage = await getAPPLY({
        menuId: 13,
        pageNum: val,
        pageSize: this.numberPage,
      });
      this.arry = respage.rows;
      //修改页数
      this.currentPage = val;
    },

    async setpop(t) {
      this.dialogVisible = true;
      //将当前点击的数组数据返回
      let res = await getDETAILS({ articleId: t.articleId });
      this.itemPopup = res.msg;
      //保存弹窗标题
      this.titlePopup = t.title;
      
      //添加访问量
      await addVISIT({articleId: t.articleId})
    },
    
  },
  components: {
    HeaDer,
  },
  async mounted() {
    let res = await getAPPLY({ menuId: 13 });
    //总条数
    this.total = res.total;
    //默认获取9条数据
    let respage = await getAPPLY({ menuId: 13, pageNum: 1, pageSize: 9 });
    this.arry = respage.rows;

     //获取导航栏菜单
    let menures = await getMENU({ parentId: 12 });
    this.navtitle = menures.data[0].menuName

    //获取图片
    let resImage = await getMUNt({ menuId: 12 });
    this.ImageUrl =resImage.data.menuImgUrl;
  },
};
</script>
<style scoped lang='scss'>
.el-dialog-div {
  height: 60vh;
  overflow: auto;
}
// 修改滚动条的样式
.el-dialog-div::-webkit-scrollbar{
  border-radius: 10px;
}
.el-dialog-div::-webkit-scrollbar{
    width: 6px !important;
    height: 8px;
    background: #fff;
  }
.el-dialog-div::-webkit-scrollbar-thumb{
    width: 6px !important;
    background: #ffa631;
    border-radius: 5px;
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
.title {
  display: flex;
  justify-content: center;
  width: 100%;
  position: relative;
  height: 68px;
  border-bottom: 1px solid #efeff1;
  background-color: white;
}
.title_content {
  width: 72%;
  max-width: 1400px;
}
.title_content li {
  text-align: center;
  width: 130px;
  height: 64px;
  line-height: 64px;
  font-size: 16px;
  font-family: "微软雅黑";
  color: #666666;
  border-bottom: 4px solid #ffa630;
}
.content-bac {
  width: 100%;
  display: flex;
  justify-content: center;
  background: #fafafc;
}
.paging{
  background: #fafafc;
}
.content {
  width: 75%;
  max-width: 1400px;
  display: flex;
  // justify-content: space-between;
  flex-wrap: wrap;
  margin-top: 50px;
}
.content-row {
  width: 31%;
  height: 55em;
  max-height: 310px;
  border: 1px solid #bfbfbf;
  background-color: white;
  margin-bottom: 2%;
  margin-right: 1.8%;
}
.content-row:hover .content-comment p{
  color: #ffa62e;
  font-weight: bold;
}
.content-row:hover .content-comment h5{
  color: #ffa62e;
}
.content-row:hover .content-img {
  padding: 0;
  padding-bottom: 10px;
  transform: translateZ(200px);
  transition: all 0.5s;
}
.content-img {
  padding: 10px;
  font-size: 0;
}
.content-img img{
  width: 100%;
  height: 12.6vw;
  // max-height: 240px;
  object-fit: contain;
}
.content-img:hover img{
  height: 13vw;
  // max-height: 250px;
  padding-bottom: 2px;
  object-fit: contain;
}
.content-comment {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  padding-top: 0;
  font-family: "微软雅黑";
}
.comment-left {
  text-align: center;
  position: relative;
  margin-top: 6px;

}
.comment-left h5{
  font-size: 16px;
}
.comment-left p{
  // transform: 12px;
  transform: scale(0.9);
  font-size: 12px;
  color: #8c7f76;
  border-bottom: 1px solid #f5f5f5;
  position: absolute;
  // transform: translateY(-1px);
  top: 18px;
  // left: 2px;
}
.comment-right {
  flex: 1;
  margin-left: 10px;
  margin-top: 0.42em;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.comment-right h5{
  font-size: 16px;
  font-family:"微软雅黑" ;
  // color: #636162;
  overflow: hidden;
  white-space:nowrap;
  text-overflow: ellipsis;
}
.comment-right p{
  color: #d5d6da;
  /* 文字居左 居上 */
  -webkit-transform-origin-x: 0;
  -webkit-transform-origin-y: 0;
  transform: scale(0.8);
  font-size: 12px;
  // font-size: 5px;
}
.paging {
  width: 100%;
  text-align: center;
  padding-bottom: 50px;
  padding-top: 50px;
}
</style>