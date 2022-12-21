<template>
  <div class="footer">
    <div class="disF">
      <div class="left">
        <div>
          <div>
            <h4 v-if="menu[0].menuName">{{menu[0].menuName}}</h4>
            <p v-for="item in menu[0].children" :key='item.menuId'><a @click="jumpTo(item.url)" v-if="item.menuName">{{item.menuName}}</a></p>
            <!-- <p><a href="">基金会章程</a></p>
            <p><a href="">理事会介绍</a></p>
            <p><a href="">基金会框架</a></p>
            <p><a href="">成美大事记</a></p> -->
          </div>
          <div>
            <h4 v-if="menu[1].menuName">{{menu[1].menuName}}</h4>
            <p v-for="item in menu[1].children" :key='item.menuId'><a @click="jumpTo(item.url)" v-if="item.menuName">{{item.menuName}}</a></p>
          </div>
          <div>
            <h4 v-if="menu[2].menuName">{{menu[2].menuName}}</h4>
            <p v-for="item in menu[2].children" :key='item.menuId'><a @click="jumpTo(item.url)">{{item.menuName}}</a></p>
          </div>
          <div>
            <h4 v-if="menu[3].menuName">{{menu[3].menuName}}</h4>
            <p v-for="item in menu[3].children" :key='item.menuId'><a @click="jumpTo(item.url)">{{item.menuName}}</a></p>
          </div>
        </div>
        <div>
          <div>
          <h4>{{menu[4].menuName}}</h4>
            <p v-for="item in menu[4].children" :key='item.menuId'><a @click="jumpTo(item.url)">{{item.menuName}}</a></p>
        </div>
        <div>
          <h4 v-if="menu[5].menuName">{{menu[5].menuName}}</h4>
            <p v-for="item in menu[5].children" :key='item.menuId'><a @click="jumpTo(item.url)">{{item.menuName}}</a></p>
        </div>
        <div>
          <h4 v-if="menu[6].menuName">{{menu[6].menuName}}</h4>
          <p v-for="item in menu[6].children" :key='item.menuId'><a @click="jumpTo(item.url)">{{item.menuName}}</a></p>
        </div>
        <div>
          <h4 v-if="menu[7].menuName">{{menu[7].menuName}}</h4>
            <p v-for="item in menu[7].children" :key='item.menuId'><a @click="jumpTo(item.url)">{{item.menuName}}</a></p>
        </div>
        </div>

      </div>
      <div class="right">
        <div>
          <div class="top">
            <h3>Contact us</h3>
            <p>
              新浪微博：@成美慈善基金会 / 腾讯微博：@成美慈善基金会 /
              公众微信：成美慈善基金会
            </p>
            <p>地址：中国海口市美兰区海甸四东路1号寰岛大厦写字楼20楼</p>
          </div>
          <div class="bottom">
            <span><img src="../../assets/image/ewm.png" alt="" /></span>
            <span><img src="../../assets/image/ewm.png" alt="" /></span>
          </div>
        </div>
      </div>
    </div>
    <div class="banquan">
      © 2013 www.chengmei.org.cn 版权所有| 琼ICP备16001836号
    </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue } from "vue-property-decorator";
import {menuList} from '@/api/methods'

@Component
export default class Header extends Vue {
  private menu = [{menuName: ''}];
  mounted() {
    this.menuList()
  }
  protected jumpTo(path: any) {
    this.$router.push({
      path: path
    })
  }
  menuList(){
    menuList({parentId: 0}).then(res => {
      this.menu = res.data
    })
  }
}
</script>

<style scoped lang='scss'>
.footer {
  // margin-top: 95px;
  margin-top: 15px;
  height: 100%;
  background: #fff;
  padding: 95px 100px 0;
  background: url("../../assets/image/axbg.png") no-repeat;
  background-size: 100% 100%;
  .disF {
    display: flex;
    // height: 340px;
    width: 1480px;
    margin: 0 auto;
    margin-top: 15px;
    padding-bottom: 20px;
  }
  h4 {
    font-weight: normal;
    color: #ffa631;
    font-size: 20px;
    margin-bottom: 5px;
  }
  a {
    color: #999999;
    line-height: 35px;
    font-size: 16px;
    cursor: pointer;
  }
  .left {
    // display: flex;
    padding-top: 36px;
    width: 944px;
    // flex: 1;
    // height: 304px;
    justify-content: space-between;
    border-right: 1px solid #aaaaaa;
    border-bottom: 1px solid #aaa;
    padding-bottom: 30px;
    > div {
      margin: 0 15px;
      width: 100%;
      display: flex;
      margin-bottom: 50px;
      >div{
        flex: 1;
      }
    }
  }
  .right {
    width: 540px;
    // height: 304px;
    padding-top: 36px;
     padding-bottom: 30px;
    border-bottom: 1px solid #aaa;
    >div{
      height:100%
    }
    h3 {
      font-size: 36px;
      color: #ff9933;
      margin-bottom: 9px;
      position: relative;
      top: -10px;
    }
    p {
      line-height: 35px;
      color: #999;
    }
    .top {
      margin-bottom: 15px;
      // height: 170px;
      vertical-align: middle;
    }
    .top,
    .bottom {
      padding-left: 35px;
    }
    .bottom {
      // height: 118px;
      height: 68%;
      border-top: 1px solid #aaaaaa;

      display: flex;
      align-items: center;
      span {
        display: inline-block;
        width: 150px;
        height: 150px;
        margin: 0 20px;
      }
      img {
        width: 100%;
      }
    }
  }
  .banquan {
    text-align: center;
    display: block;
    // line-height: 60px;
    padding: 10px;
  }
}
</style>
