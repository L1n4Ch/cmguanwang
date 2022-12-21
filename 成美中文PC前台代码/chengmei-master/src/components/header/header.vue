<template>
  <div class="header">
    <div class="logo">
      <img src="../../assets/image/logo.png" alt="" />
    </div>
    <!-- pc要显示
      如果不是true 就是 false就是pc端让她显示
     -->
    <div class="nav" v-if="pc_yd_active == false">
      <ul class="">
        <li>
          <span :class="{ cur: index == 1 }" @click="jumpTo('/', 1)">首页</span>
        </li>
        <li>
          <el-popover placement="bottom" width="100" trigger="hover">
            <div class="navDiv">
              <div v-for="item in menu[0].children" :key="item.menuId">
                <a @click="jumpTo('/aboutus', 2, item.menuId)">{{
                  item.menuName
                }}</a>
              </div>
            </div>
            <span slot="reference">
              <span :class="{ cur: index == 2 }" @click="jumpTo('/aboutus', 2)"
                >关于我们</span
              >
            </span>
          </el-popover>
        </li>
        <li>
          <el-popover placement="bottom" width="100" trigger="hover">
            <div class="navDiv">
              <div v-for="item in menu[1].children" :key="item.menuId">
                <a @click="jumpTo('/show', 3, item.menuId)">{{
                  item.menuName
                }}</a>
              </div>
            </div>
            <span slot="reference">
              <span :class="{ cur: index == 3 }" @click="jumpTo('/show', 3)"
                >项目展示</span
              >
            </span>
          </el-popover>
        </li>
        <li>
          <el-popover placement="bottom" width="100" trigger="hover">
            <div class="navDiv">
              <div v-for="item in menu[2].children" :key="item.menuId">
                <a @click="jumpTo('/team', 4, item.menuId)">{{
                  item.menuName
                }}</a>
              </div>
            </div>
            <span slot="reference">
              <span :class="{ cur: index == 4 }" @click="jumpTo('/team', 4)"
                >项目合作</span
              >
            </span>
          </el-popover>
        </li>
        <li>
          <el-popover placement="bottom" width="100" trigger="hover">
            <div class="navDiv">
              <div v-for="item in menu[3].children" :key="item.menuId">
                <a @click="jumpTo('/info', 5, item.menuId)">{{
                  item.menuName
                }}</a>
              </div>
            </div>
            <span slot="reference">
              <span :class="{ cur: index == 5 }" @click="jumpTo('/info', 5, 16)"
                >信息公开</span
              >
            </span>
          </el-popover>
        </li>
        <li>
          <!-- <el-popover placement="bottom" width="100" trigger="hover">
              <div class="navDiv">
                <div v-for="item in menu[4].children" :key="item.menuId">
                  <a @click="jumpTo('/newsletter', 6, item.menuId)">{{item.menuName}}</a>
                </div>
              </div>
              <span slot="reference">
                <a :class="{ cur: index == 6 }" @click="jumpTo('/newsletter', 6)">宣传刊物</a>
              </span>
            </el-popover> -->
          <span :class="{ cur: index == 6 }" @click="jumpTo('/newsletter', 6)"
            >宣传刊物</span
          >
        </li>
        <li>
          <el-popover placement="bottom" width="100" trigger="hover">
            <div class="navDiv">
              <div v-for="item in menu[5].children" :key="item.menuId">
                <a @click="jumpTo('/supportus', 7, item.menuId)">{{
                  item.menuName
                }}</a>
              </div>
            </div>
            <span slot="reference">
              <span
                :class="{ cur: index == 7 }"
                @click="jumpTo('/supportus', 7)"
                >支持我们</span
              >
            </span>
          </el-popover>
        </li>
        <li>
          <el-popover placement="bottom" width="100" trigger="hover">
            <div class="navDiv">
              <div v-for="item in menu[6].children" :key="item.menuId">
                <a @click="jumpTo('/partner', 8, item.menuId)">{{
                  item.menuName
                }}</a>
              </div>
            </div>
            <span slot="reference">
              <span :class="{ cur: index == 8 }" @click="jumpTo('/partner', 8)"
                >合作伙伴</span
              >
            </span>
          </el-popover>
        </li>
        <li>
          <span :class="{ cur: index == 9 }" @click="jumpTo('/#page8', 9)"
            >联系方式</span
          >
        </li>
        <li>
          <button class="toCh" @click="open">English</button>
        </li>
      </ul>
      <div class="search">
        <el-popover placement="bottom" width="10" trigger="hover">
          <div class="iconP" style="text-align: right; margin: 0">
            <p>
              <el-popover placement="right" width="70" trigger="hover">
                <div class="wx">
                  <img :src="imgLink + baseInfo.qrcode.wxImg" alt="" />
                </div>
                <span slot="reference"
                  ><img
                    class="imgIcon"
                    src="../../assets/image/wechat.png"
                    alt=""
                /></span>
              </el-popover>
            </p>
            <p>
              <el-popover placement="right" width="70" trigger="hover">
                <div class="wx">
                  <img src="../../assets/image/shh.jpg" alt="" />
                </div>
                <span slot="reference"
                  ><img class="imgIcon" src="../../assets/image/zfb.png" alt=""
                /></span>
              </el-popover>
            </p>
            <p>
              <el-popover placement="right" width="70" trigger="hover">
                <div class="wx">
                    <img
                      class="imgIcon"
                      src="../../assets/image/weibo.png"
                      alt=""
                    />
                </div>
                <span slot="reference"
                >
                  <a href="http://weibo.com/u/2910272074">
                    <img
                            class="imgIcon"
                            src="../../assets/image/weiboIcon.png"
                            alt=""
                    />
                  </a>
                </span>
              </el-popover>
            </p>
          </div>
          <span slot="reference"
            ><img class="share" src="../../assets/image/share.png" alt=""
          /></span>
        </el-popover>
      </div>
    </div>
    <!-- 这里是移动端要显示的
      如果不是false 就是 true就是移动端让她显示
     -->
    <div v-if="pc_yd_active == true">
      <el-dropdown>
        <span class="el-dropdown-link">
          <i class="el-icon-s-fold"></i>
        </span>
        <el-dropdown-menu slot="dropdown"><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 1 }" @click="jumpTo('/', 1)">首页</span>
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 2 }" @click="jumpTo('/aboutus', 2)"
                >关于我们</span
              >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 3 }" @click="jumpTo('/show', 3)"
                >项目展示</span
              >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 4 }" @click="jumpTo('/team', 4)"
                >项目合作</span
              >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 5 }" @click="jumpTo('/info', 5, 16)"
                >信息公开</span
              >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 6 }" @click="jumpTo('/newsletter', 6)"
              >宣传刊物</span
            >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span
                :class="{ cur: index == 7 }"
                @click="jumpTo('/supportus', 7)"
                >支持我们</span
              >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 8 }" @click="jumpTo('/partner', 8)"
                >合作伙伴</span
              >
          </el-dropdown-item><br>
          <el-dropdown-item >
            <span :class="{ cur: index == 9 }" @click="jumpTo('/#page8', 9)"
              >联系方式</span
            >
          </el-dropdown-item><br>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Prop, Vue, Watch } from "vue-property-decorator";
import { link } from "@/utils/common";
import { BASEINFO, menuList, addVISITS } from "@/api/methods";

@Component
export default class Header extends Vue {
  @Prop(String) readonly type: string|undefined
  private imgLink = link;
  private baseInfo = { qrcode: { wxImg: "" } };
  private data = {};
  private index = 1;
  private pc_yd_active:boolean | null = null;
  menu = [
    { children: [] },
    { children: [] },
    { children: [] },
    { children: [] },
    { children: [] },
    { children: [] },
    { children: [] },
  ];
  menuList() {
    menuList({ parentId: 0 }).then((res) => {
      this.menu = res.data;
    });
  }
  @Watch("$route")
  getChangeMenu(val: any) {
    console.log("valval", val);
  }
  mounted() {
    // 这里判断如果不是null 就是移动端 否则 是
    this._isMobile() !== null ? this.pc_yd_active = true : this.pc_yd_active = false;

    this.UpdateRouter();
    this.menuList();
    // if (sessionStorage.getItem("index")) {
    //   this.index = Number(sessionStorage.getItem("index"));
    // }
    if (this.$store.state.baseInfo.qrcode) {
      this.baseInfo = this.$store.state.baseInfo;
      return;
    }
    this.BASEINFO();
  }
  _isMobile() {
    let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
    return flag;
  }
  UpdateRouter() {
    switch (this.$route.path) {
      case "/" || "//#page1":
        this.index = 1;
        this.Storage(1);
        break;
      case "/aboutus":
        this.index = 2;
        this.Storage(2);
        break;
      case "/show":
        this.index = 3;
        this.Storage(3);
        break;
      case "/team":
        this.index = 4;
        this.Storage(4);
        break;
      case "/info":
        this.index = 5;
        this.Storage(5);
        break;
      case "/newsletter":
        this.index = 6;
        this.Storage(6);
        break;
      case "/supportus":
        this.index = 7;
        this.Storage(7);
        break;
      case "/partner":
        this.index = 8;
        this.Storage(8);
        break;
      case "/#page8":
        this.index = 9;
        this.Storage(9);
        break;
      default:
        this.index = 1;
    }
  }
  Storage(val: number) {
    sessionStorage.setItem("index", val + "");
  }
  // jumpTo2(val: any, index: any, id?: any) {
  //   alert(1)
  // }
  async jumpTo(val: any, index: any, id?: any) {
    // console.log(id)
    let nav = "";
    switch (index) {
      case 1:
        nav = "首页";
        break;
      case 2:
        nav = "关于我们";
        break;
      case 3:
        nav = "项目展示";
        break;
      case 4:
        nav = "项目合作";
        break;
      case 5:
        nav = "信息公开";
        break;
      case 6:
        nav = "宣传刊物";
        break;
      case 7:
        nav = "支持我们";
        break;
      case 8:
        nav = "合作伙伴";
        break;
      case 9:
        nav = "联系方式";
        break;
    }
    // console.log(nav)
    //添加点击导航访问量
    let res =  await addVISITS({ mName: nav });
    // console.log(res)

    this.index = index;
    console.log('this.index-----'+this.index)

    sessionStorage.setItem("index", index);
    if (index === 9) {
      if (this.$route.path !== "/") {
        this.$router.push({
          path: "/#page8",
        });
      } else {
        this.$router.push({
          path: "/#page8",
        });
        location.reload();
      }
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
  }

  //获取基础配置
  protected async BASEINFO() {
    BASEINFO().then((res) => {
      this.baseInfo = res.data;
    });
  }
  open():void{
    window.open("http://www.chengmei.org.cn:8667/");
  }
}
</script>

<style>
/* 清除苹果浏览器默认的外轮廓 */
a:focus,
input:focus,
p:focus,
span:focus,
li:focus,
div:focus {
  outline: none !important;
  /* /* -webkit-tap-highlight-color:rgba(0,0,0,0); */
  /* -webkit-user-modify:read-write-plaintext-only;  */
}
.toCh{
  padding:10px 20px;
  border-radius: 40px;
  border: 0px;
  color: #fff;
  background: orange;
  cursor: pointer;
}
.el-popover {
  min-width: 100px;
  cursor: pointer;
  text-align: center;
}
</style>
<style scoped lang='scss'>
.navDiv {
  line-height: 40px;
  a {
    color: #333333;
  }
}
.imgIcon {
  width: 46px;
  height: 46px;
}
.wx {
  background: #fff;
  padding: 10px;
  position: absolute;
  right: 10px;
  top: -50px;
  border-radius: 10px;
  width: 189px;
  height: 189px;
  img {
    width: 100%;
    height: 100%;
  }
}
.share {
  width: 36px;
  height: 34px;
}
.iconP {
  p {
    padding: 10px;
    text-align: center;
    position: relative;
  }
}
.header {
  height: 95px;
  background: #fff;
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 999;
  // border-bottom: 1px solid #eeeeee;

  ::v-deep .logo,
  .nav {
    padding: 17px 0 14px 0;
    ul {
      height: 100%;
      li {
        height: 100%;
        text-align: center;
        line-height: 62px;
      }
    }
    span {
      font-size: 14px;
      cursor: pointer;
      display: block;
      height: 100%;
    }
  }
  .logo {
    // margin-right: 325px;
    flex: 1;
    padding-left: 25px;
    align-items: center;
    margin: auto 0;
    img {
      width: 280px;
      height: 64px;
      display: block;
    }
  }
  .nav {
    margin-left: auto;
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    a {
      color: #333333;
      cursor: pointer;
      outline: none;
    }
    .cur {
      color: #ffa62e;
    }
    ul {
      display: flex;
      justify-content: center;
      align-items: center;
    }
    li {
      width: 104px;
      text-align: center;
    }
  }
  .search {
    width: 125px;
    border-left: 1px solid #ededed;
    text-align: center;
  }
}
</style>
