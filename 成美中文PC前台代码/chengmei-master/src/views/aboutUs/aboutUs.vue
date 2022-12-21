<template>
  <div class="about" ref="about">
    <div class="banner">
      <img src="../../assets/image/about.png" alt="" />
    </div>
    <div class="content" ref="ContentRef">
      <div :class="[flexd?'flexd':'left',scye?'sb_yh':'']">
        <div >
          <el-steps direction="vertical" process-status="process"  finish-status="process" :active="active" :space="100">
            <el-step
              v-for="(item, key) in menu.children"
              :key="item.menuId"
              :class="(key+1)===active?'activeChange':''"
              :title="item.menuName"
              @click.native="stepFn(item, key + 1)"
            ></el-step>
          </el-steps>
        </div>
      </div>
      <div class="right" ref="right">
        <div class="title">
          <div class="div-2">
            <p>ABOUT US</p>
            <p>{{ article.title }}</p>
            <span>INTRODUCE</span>
            <div></div>
          </div>
        </div>
        <div class="article" ref="article" v-show="showIndex" v-html="article.detail"></div>
        <template  v-if="showIndex?false:true">
          <el-tabs v-model="activeName" stretch @tab-click="handleClick" >
            <el-tab-pane v-for="(item,index) in TabsList" :key="'tabs'+index" :label="item.year + ''" :name="index + ''">
              <template>
                <br>
                <div v-html="TabsIndex.detail"></div>
                <br><br>
              </template>
            </el-tab-pane>
          </el-tabs>
        </template>
      </div>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Prop, Vue, Watch } from "vue-property-decorator";
import { menuList, articleList , bigEvent } from "@/api/methods";
import { link } from "@/utils/common";

@Component
export default class ComponentName extends Vue {
  private imgLink = link;
  private menu = [];
  private active = 1;
  private article = {};
  private showIndex = true;
  private flexd = false;
  private activeName = "0";
  private TabsList = [];
  private TabsIndex = {};
  private scye = false;
  mounted() {
    this.menuList();
    if(sessionStorage.getItem('menuId')) {
      this.goto(sessionStorage.getItem('menuId'))
      switch (sessionStorage.getItem('menuId')) {
        case '2':
          this.active= 1
          break
        case '3':
          this.active= 2
          break
        case '4':
          this.active= 3
          break
        case '5':
          this.active= 4
          break
        case "6":
          this.active= 5
          break
      }
    }
    window.addEventListener("scroll", this.handleScroll);
  }
  async handleScroll(){
    let scrollTop:any = await this.scrrolTop();
    let sco:number = ((this.$refs.right as any).offsetHeight) - 300;
    console.log(scrollTop,sco);
    if( scrollTop >= sco ){
      this.scye = true;
    }else{
      this.scye = false;
    }
    if(scrollTop >= (this.$refs.ContentRef as any).offsetTop - 300) {
      this.flexd = true;
    }else{
      this.flexd = false;
    }
  }
  scrrolTop(){
    return new Promise((resolve,reject)=>{
      // 获取屏幕滚动的高度
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
        resolve(scrollTop);
    })
  }

  @Watch('$route',{immediate: true})
  getInfo(val: any) {
    this.goto(val.query.menuId);
    switch (val.query.menuId) {
      case '2':
        this.active= 1
            break
      case '3':
        this.active= 2
        break
      case '4':
        this.active= 3
        break
      case '5':
        this.active= 4
        break
      case "6":
        this.active= 5
        break
      // default:
      //       this.$router.push({
      //         path:'/#page3'
      //       })
    }
  }
  goto(id: any) {
    sessionStorage.setItem('menuId',id)
    // window.location.reload();
    sessionStorage.getItem('menuId') == "6" ? this.getDo():this.showIndex = true;
      setTimeout(()=>{
        this.articleList(sessionStorage.getItem('menuId'));
      }, 500)
    }
  protected articleList(menuId: any) {
    let obj = {
      menuId: menuId,
      newStatus: 0,
      pageNum: 1,
      pageShow: 1,
      pageSize: 6,
      showDetail: 1,
    };
    articleList(obj).then((res) => {
      this.article = res.rows[0];
    });
  }
  protected menuList() {
    menuList({ parentId: 0 }).then((res) => {
      console.log(res);
      this.menu = res.data[0];
      console.log(res.data[0].children[0].menuId)
      if(this.active==1){
          this.articleList(res.data[0].children[0].menuId);
      }
    });
  }
  protected stepFn(item: any, num: any) {
    console.log(document.querySelectorAll(".is-vertical"));
    console.log(item,num);
    if (num != 5) {
      this.showIndex = true;
      this.active = num;
      this.articleList(item.menuId);
      return
    }
    // this.$router.push({
    //   path:'/#page3'
    // })
    this.getDo();
  }
  getDo(){
    bigEvent().then((res)=>{
      if(res.code !== 0) return this.$message.error("大事件获取失败...");
      console.log(res);

      this.TabsList = res.data;
      this.TabsIndex = this.TabsList[0];
      this.active = 5;
      this.showIndex = false;
    })
  }
  handleClick(val:any){
    let num:number = parseInt(val.index);
    console.log(num);
    this.TabsIndex = this.TabsList[num];
  }
}
</script>
<style lang='scss'>

.activeChange{
  color: #ffa631 !important;
  .el-step__icon.is-text{
    border:1px solid #ffa631 !important;
  }
  .el-step__icon-inner{
    color: #ffa631 !important;
  }
  .el-step__main{
    .el-step__title{
      // background: aqua !important;
      color: #ffa631 !important;
    }
  }
}

  .article {
    img {
      width: 100% !important;
    }
  }
</style>
<style lang='scss' scoped>
::v-deep .el-step__title.is-process{
    font-weight:100 !important;
  }
  .activeChange ::v-deep .el-step__title.is-process{
    color: #ffa631 !important;
    font-weight: bold !important;
  }
// 屏幕滚动到哪里显示的位置
.flexd{
  position: fixed;
  top: 20%;
  left: 140px;
  cursor: pointer;
}
.sb_yh{
  position: fixed !important;
  top: -4% !important;
}
.about {
  margin-top: 95px;
  ::v-deep .el-step__title.is-finish, ::v-deep .el-step__head.is-finish{
    color: #f9780e;
  }
  ::v-deep .el-step__head.is-finish{
      border-color: #f9780e;
       .el-step__icon-inner{
        color: #f9780e;
      }
      .el-step__icon.is-text{
        border-color: #f9780e;
      }
  }
  ::v-deep .el-step__title.is-process{
  font-weight:bold !important;
  }
  ::v-deep .el-step__title.is-process, ::v-deep .el-step__icon-inner, ::v-deep .is-wait{
    color: #999;
    font-weight: bold !important;
  }
  :v-deep .el-step__icon{
    // border-color: #f9780e
    border: 1px solid #999
  }
  ::v-deep .el-step__icon.is-text{
    border-color: #999;
  }
  // :v-deep .is-finish{
  //   .el-step__icon-inner{
  //   color: #10aca1;
  // }
  // }
  .banner {
    width: 100%;
    img {
      width: 100%;
      display: block;
    }
  }
  .content {
    /*width: 1040px;*/
    margin: 78px auto 0;
    position: relative;
    .left {
      position: absolute;
      top: 1%;
      left: 140px;
      cursor: pointer;
    }
    .right {
      width: 65%;
      margin: 0 auto;
      .title {
        position: relative;
        height: 170px;
        .div-2 {
          position: absolute;
          right: 0;
          flex: 2.5;
          max-width: 360px;
          min-width: 260px;
          p:nth-child(1) {
            font-size: 60px;
            text-align: right;
            margin-top: -10px;
            color: #e2e2e2;
            width: 450px;
            position: relative;
            top: -20px;
            z-index: -9;
            left: -25px;
          }
          p:nth-child(2) {
            font-size: 38px;
            margin-top: -10px;
            font-weight: 540;
            color: #666666;
            margin-left: 40px;
          }
          span {
            font-size: 25px;
            color: #d1d1d1;
            position: relative;
            left: 115px;
          }
          div {
            width: 29%;
            height: 5px;
            margin-top: 8px;
            background: #ffa631;
            position: relative;
            left: 160px;
          }
        }
      }
      .jian {
        display: flex;
        align-items: center;
        img {
          width: 490px;
          height: 315px;
          display: inherit;
        }
        span {
          font-size: 20px;
          line-height: 47px;
          text-indent: 2em;
        }
        span:nth-child(2) {
          margin-left: 50px;
          color: #818181;
        }
      }
      h1 {
        text-align: center;
        color: #666666;
        margin-bottom: 50px;
        font-size: 34px;
      }
      h2 {
        color: #666666;
        font-size: 28px;
        margin: 40px 0 15px;
      }
      div {
        color: #666666;
      }
      p {
        line-height: 36px;
        text-indent: 2em;
        font-size: 20px;
      }
      .rongyu {
        margin: 80px 0 50px;
      }
      .rongyu ul {
        display: flex;
        justify-content: space-between;
        li {
          width: 388px;
          height: 265px;
          img {
            width: 100%;
            height: 100%;
          }
        }
      }
    }
  }
}
</style>
