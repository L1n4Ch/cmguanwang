<template>
  <div class="partner">
    <!-- <Header class="header"></Header> -->
    <!-- 图片区域 -->
    <div class="img-div">
      <img :src="link+title_img.menuImgUrl" alt="" width="100%" />
    </div>
    <el-row type="flex" class="row-bg" justify="space-around">
      <el-col :span="18">
        <div class="mbx">
          <i class="iconfont icon-shouye"></i>首页
          <i class="iconfont icon-jiang-right" ></i>合作伙伴
          <i class="iconfont icon-jiang-right"></i>{{title}}
        </div>
      </el-col>
    </el-row>
    <!-- 内容 -->
    <el-row type="flex" class="cont" justify="space-around">
      <el-col :span="18">
        <el-card>
          <el-row>
            <el-col :span="6">
              <div class="cont-left">
                <div class="left-1">
                  <p>COOPERATE</p>
                  <h4>合作伙伴</h4>
                </div>
                <div class="left-2">
                  <el-menu default-active="1" class="el-menu-vertical-demo" text-color="#343434"
                  >
                    <el-menu-item v-for="(item,index) in title_tabs" :class="index=== activeIndex?'activeIndex':''" :key="index" @click="AtIndex(item,index)" >
                      <span slot="title">{{item.menuName}}</span>
                    </el-menu-item>
                  </el-menu>
                </div>
                <el-row class="left-3" type="flex" justify="center">
                  <el-col :span="24">
                    <el-input
                      placeholder="请输入内容"
                      class="input-with-select"
                      clearable
                      v-model="input"
                      @clear="removeContent"
                      >
                        <el-button slot="append" icon="el-icon-search" @click="searchFor" class="btn">
                        </el-button>
                      </el-input>
                  </el-col>
                </el-row>
              </div>
            </el-col>
            <el-col :span="18">
              <div class="cont-right">
                  <div class="top">
                      <p>{{title}}</p>
                  </div>
                  <div class="ali-content">
                      <div v-for="(item,index) in imgList" :key="index">
                          <el-tooltip class="item" :content="item.title" placement="top" effect="light">
                            <img :src="link+item.bgImageUrl" alt="">
                          </el-tooltip>
                      </div>
                  </div>
              </div>
            </el-col>
          </el-row>
          <br><br><br>
          <el-row type="flex" justify="center">
            <el-pagination
              @size-change="partnerSizeChange"
              @current-change="partnerNumChange"
              :current-page="partnerList.pageNum"
              :page-sizes="[12,15,30]"
              :page-size="partnerList.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
            </el-pagination>
            <br><br><br>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { getSArticleList , getMenuList , getMUNt} from "@/api/methods";
import { link } from "@/utils/common.ts";
export default {
//    watch:{
//      $route:{//深度监听，可监听到对象、数组的变化
//          handler(val, oldVal){
//            this.partnerList.menuId = val.query.menuId;
//              this.goto(val.query.menuId)
//          },
//          deep:true //true 深度监听
//      }
//  },
  data(){
    return {
      activeIndex:0,
      link,
      // 获取到的图片地址
      title_img:{},
      // 查看详情
      input:"",
      title:"爱心企业",
      total:0,
      title_tabs:[],
      // 合作伙伴获取接口
      partnerList:{
        key:"",
        menuId:29,
        newStatus:'0',
        pageNum:1,
        pageShow:'0',
        pageSize:12,
        showDetail:'1'
      },
      // 获取大图
      Img_List:{
        menuId:28,
      },
      imgList:[],
      articleId:0,
      // 详情信息
      msg:"",
      obj:{
        parentId:28
      }
    }
  },
  mounted(){
    if(this.$route.query.menuId){
      this.queryMenuId();
    }
  },
  watch:{
    "$route.query.menuId"(){
      this.queryMenuId();
    }
  },
  methods:{
    queryMenuId(){
      switch (this.$route.query.menuId) {
        case "29":
          this.activeIndex = 0;
          break;
        case "30":
          this.activeIndex = 1;
          break;
        case "31":
          this.activeIndex = 2;
          break;
        case "32":
          this.activeIndex = 3;
          break;
        case "33":
          this.activeIndex = 4;
          break;
      }
      this.partnerList.menuId = this.$route.query.menuId;
      this.getpartnerList();
      this.title_tabs.length !== 0 ? this.title = this.title_tabs[this.activeIndex].menuName : this.title = this.title;
    },
    goto(id) {
      setTimeout(()=>{
        // document.getElementById(id).scrollIntoView()
        this.getpartnerList();
      }, 1000)
    },
    // 查看详情
    // async seeDetails(item){
    //   if(item.ext == "") return this.$message.error("暂无数据！");
    //   location.href = item.ext;
    // },
    // 分页的条数发生变化
    partnerSizeChange(e){
      this.partnerList.pageSize = e;
      this.getpartnerList();
    },
    // 分页的页数发生变化
    partnerNumChange(e){
      this.partnerList.pageNum = e;
      this.getpartnerList();
    },
    // 点击左测菜单发生变化
    AtIndex(item,index){
      this.queryMenuId();
      this.activeIndex = index;
      this.title = item.menuName;
      this.partnerList.menuId = item.menuId;
      this.getpartnerList();
    },
    // 请求数据
    async getpartnerList(){
      let res = await getSArticleList(this.partnerList);
      if(res.code !== 0) return this.$message.error("数据请求失败，请检查你的网络！");
      this.total = res.total;
      this.imgList = res.rows;
    },
    // 输入框清空
    removeContent(){
      this.partnerList.key = this.input;
      this.getpartnerList();
    },
    // 输入框搜索按钮
    searchFor(){  
      this.partnerList.key = this.input;
      this.getpartnerList();
    },
    // 获取点击菜单栏
    async getMonList(){
      let res = await getMenuList(this.obj);
      if(res.code !== 0) return this.$message.error("数据请求失败，请检查你的网络！");
      console.log(res);
      this.title_tabs = res.data;
    },
    // 图片title图片
    async getTitel_img(){
      let res = await getMUNt(this.Img_List);
      if(res.code !== 0) return this.$message.error("数据请求失败，请检查你的网络！");
      this.title_img = res.data;
    },
  },
  created(){
    this.getpartnerList();
    this.getMonList();
    this.getTitel_img();
    window.scrollTo(0,0);
  }
};
</script>

<style lang='scss' scoped>
.partner {
  background: #f5f5f5;
  box-sizing: border-box;
  padding-bottom: 5rem;
  height: 1700px;
}
::v-deep .el-menu{
  border-right: none !important;
}
::v-deep .el-menu-item {
  background: #ffff !important;
  text-align: center !important;
  border-bottom: 0.5px solid #e7e7e7 !important;
}
.activeIndex{
  background: #fea731 !important;
  color: #fff !important;
}
.img-div {
  margin-top: 95px;
}
::v-deep .row-bg {
  background: #FFFFFF;
  border-bottom: 1px solid #EBEBEB;
}
.mbx {
  height: 50px;
  margin-left: 28px;
  line-height: 50px;
  text-align: left;
  color: #b0b0b0;
  font-size: 15px;
  i {
    margin: 5px;
  }
}
.cont {
  margin-top: 20px;
  .cont-left {
    .left-1{
      width: 100%;
      margin: 10px 5px;
      height: 90px;
      p {
        font-size: 35px;
        color: #BFBFBF;
      }
      h4 {
        margin-top: 5px;
        font-size: 20px;
        color: #6F6A6B;
      }
    }
    .left-3{
      margin-top: 30px;
      
    }
  }
}
::v-deep .left-3 .el-input{
  width: 100%  !important;
}
::v-deep .left2  .el-menu{
  border: 0 !important;
  padding:0px 10px !important;
}
::v-deep .left2  .el-menu-item{
  text-align: center;
  height: 60px !important;
  font-size: 16px;
  border-bottom: 1px solid #dedede !important;
}
.cont-right{
    padding-left: 30px;
    margin-right: 30px;
    min-height: 800px;
    .top{
        width: 100%;
        margin-top: 15px;
        border-bottom: 1px solid #dedede;
        height: 35px;
        p{
            border-left: 4.5px solid #fea731;
            font-size: 18px;
            color: #fea731;
            padding-left: 8px;
        }
    }
    .ali-content{
        width: 100%;
        margin-top: 15px;
        div{
            width: 24%;
            height: 195px;
            background: #ffff;
            float: left;
            margin-top: 20px;
            box-sizing: border-box;
            padding: 10px;
            margin-left: 1%;
            cursor: pointer;
            img{
                width: 100%;
                height: 100%;
            }
            :hover {
              box-shadow: 0 0 10px 5px #ccc;
              transition: all .5s;
            }
        }
    }
}
::v-deep .el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{
  background: #ffff  !important;
  border: 1px solid #dedede  !important;
}
::v-deep .el-pagination.is-background .el-pager li:not(.disabled).active{
  color:#606266 !important;
  background: #fea731  !important;
}
::v-deep .el-menu-item.is-active{
    color: #606266;
}
</style>