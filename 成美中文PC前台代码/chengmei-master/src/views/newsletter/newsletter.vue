<template>
  <div class="add">
    <!-- 图片区域 -->
    <div style="margin-top:74px;">
        <img :src="link + titleImgList.menuImgUrl" width="100%" alt="">
    </div>
    <!-- 中间搜索框区域 -->
    <div class="absloter">
      <el-row class="row">
        <el-col :span="10" :offset="2" > 
          <h5 class="tp">宣传刊物<span>/PROPAGANDA</span></h5>
        </el-col>
        <el-col :span="6" :push="2" style="margin-left:15px;padding-left:2%;" >
          <div class="box1">
            <div class="icon">
              <i class="iconfont icon-sousuo"></i>
              <h5>关键词</h5>
            </div>
            <div class="box2">
              <!-- 搜索框部分 -->
              <el-input
                placeholder="请输入内容"
                class="input-with-select"
                clearable
                v-model="searchForContent"
                @clear="removeContent"
              >
                <el-button slot="append" icon="el-icon-search" @click="searchFor" class="btn">
                </el-button>
              </el-input>
            </div>
          </div>
        </el-col>
      </el-row>
      <!-- 内容主体区域 -->
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="18">
          <div class="content">
            <div v-for="(item,index) in img_list" :key="index" @mouseleave="isShowFalse(item)" >
              <div class="img_div" @mouseenter="isShowTrue(item)">
                <img :src="link+item.bgImageUrl"  />
              </div>
              <p>{{item.title}}</p>
              <el-button round :class="['hover',item.isShow?'crate':'']" @click="seeDetails(item)">查看详情</el-button>
            </div>
        </div>
        </el-col>
      </el-row>
      <br><br>
      <!-- 分页模块 -->
      <el-row type="flex" class="row-bg" justify="center">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="AsrticleList.pageNum"
          :page-sizes="[9,15,30]"
          :page-size="AsrticleList.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </el-row>
      <br>
      <br><br>
      <el-dialog title="详情" :visible.sync="dialogFormVisible" >
        <embed
          :src="link + msg"
          type="application/pdf"
          width="100%"
          height="550"
          internalinstanceid="81"
          v-show="msg !== ''"
        />
        
        <div v-show="msg === ''">暂无数据...</div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false; msg = ''">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false; msg = ''">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script >
import { getSArticleList , getArticleDetail , getMUNt} from "@/api/methods";
import { link } from "@/utils/common.ts";
export default {
  data() {
    return {
      link,
      // 电话框显示隐藏
      dialogFormVisible:false,
      // 搜索框内容
      searchForContent:"",
      // 文章获取接口
      AsrticleList:{
        key:"",
        menuId:'22',
        newStatus:'0',
        pageNum:1,
        pageShow:'0',
        pageSize:10,
        showDetail:'0'
      },
      // 获取大图图片
      title_img:{
        menuId:15,
      },
      // 头部图片
      titleImgList:{},
      // 数据跳数
      total:0,
      isShow:false,
      currentPage4: "",
      goodsParams: {
        query: "",
      },
      img_list:[],
      msg:""
    };
  },
  mounted(){
  },
  methods: {
    // 清空输入框事件
    removeContent(){
      this.AsrticleList.key = "";
      this.getsarticle();
    },
    // 关键字搜索
    searchFor(){
      console.log(this.searchForContent);
      this.AsrticleList.key = this.searchForContent;
      this.getsarticle();
    },
    // 点击下一页
    handleSizeChange(e) {
      console.log(e);
      this.AsrticleList.pageSize = e;
      this.getsarticle();

    },
    // 页数改变
    handleCurrentChange(e) {
      console.log(e);
      this.AsrticleList.pageNum = e;
      this.getsarticle();
    },
    // 鼠标移除
    isShowFalse(item){
      item.isShow = false;
    },
    // 鼠标移入
    isShowTrue(item){
      item.isShow = true;
    },
    // 文章获取
    async getsarticle(){
      let res = await getSArticleList(this.AsrticleList);
      console.log(res);
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      res.rows.forEach(element => {
        element.isShow = false;
      });
      this.total = res.total;
      this.img_list = res.rows;
    },
    // 查看权限
    async seeDetails(item){
      // let res = await getArticleDetail({articleId:item.articleId});
      // if(res.code !== 0 ) return this.$message.error("数据获取失败，请检查网络！");
      // this.msg = res.msg;
      console.log(this.img_list);
      console.log(item);
      this.msg = item.extPdf;
      this.dialogFormVisible = true;
    },
    // 获取头部图片
    async getTitleImg(){
      let res = await getMUNt(this.title_img);
      if(res.code !== 0 ) return this.$message.error("数据获取失败，请检查网络！");
      this.titleImgList = res.data;
    }
  },
  created(){
    this.getsarticle();
    this.getTitleImg();
  }
};
</script>

<style lang="scss" scoped>
.msg{
  max-height: 500px;
  min-height: 500px;
  padding: 10px;
  overflow: hidden;
  overflow-y: scroll;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  p{
    width: 95% !important;
    margin: 0 auto;
    img{
      width: 100% !important;
    }
  }
}
// 修改滚动条的样式
.msg::-webkit-scrollbar{
  border-radius: 10px;
}
.msg::-webkit-scrollbar{
    width: 6px !important;
    background: #fff;
  }
.msg::-webkit-scrollbar-thumb{
    width: 6px;
    background: #ffa631;
    border-radius: 5px;
}   
::v-deep .el-dialog {
  width: 60%  !important;
  margin-top: 8% !important;
}
.absloter{
    position: relative;
    width: 100%;
    background: #FAFAFC;
}
.header {
  position: fixed;
  z-index: 999;
  top: 0;
  left: 0;
  right: 0;
}
.crate{
  background: #ffa631;
  color: #fff;
}
.orange{
  transform: scale(1.2,1.2);
}
::v-deep .btn{
  background: red;
}
 .hover:hover{
  background: #ffa631;
  color: #ffff;
}
.content {
  overflow: hidden;
  display: flex;
  width: 100%;
  margin: 0 auto;
  flex-wrap: wrap;
  align-items: center;
  justify-content: flex-start;
  box-sizing: border-box;
  max-width: 1600px;
  div {
    width: 30%;
    margin: 20px 1.3%;
    display: flex;
    justify-content: space-around;
    align-items: center;
    overflow: hidden;
    flex-direction: column;
    font-size: 20px;
    padding: 0px 5px;
    position: relative;
    p{
      margin: 10px 0px;
    }
    .img_div{
      width: 78% !important;
      height: 488px;
      padding: 0 !important;
      border: 1px solid #ccc;
      cursor: pointer;
      img {
        width: 100% !important;
        margin-bottom:5px ;
        transition: all .5s;
        max-width: 360px;
      }
    }
  }
}
.content div .img_div:hover img {
  transform: scale(1.05,1.05);
}
.add {
  background: #fff;
  min-width: 1400px;
  overflow: hidden;
}
.row {
  display: flex;
  align-items: center;
  height: 100px;
}
.box1 {
  display: flex;
}
.icon {
  height: 4em;
  min-width: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  i {
    font-size: 20px;
    color: #1e5ba7;
  }
  h5 {
    font-size: 16px;
    font-weight: 800;
    width: 60px;
  }
}
.box2 {
  width: 25em;
  height: 4em;
  line-height: 4em;
  margin-left: 5px;
  min-width: 300px;

}
::v-deep .box2  .el-button{
  background: #d7d7d8  !important;
  height: 40px;
}
.tp {
  font-size: 25px;
  font-weight: 400;
  margin-left: 2em;
}
.tp span {
  padding-left: 1em;
  font-size: 18px;
  color: #ccc;
}
</style>