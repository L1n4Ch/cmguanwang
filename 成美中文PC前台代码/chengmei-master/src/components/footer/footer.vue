<template>
  <div class="footer">
    <div class="disF">
      <div class="left">
        <div v-for="item in menu" :key="item.menuId">
          <h4 v-if="item.menuName && item.children == 0" @click="jumpTo(item.url)">{{item.menuName}}</h4>
          <h4 v-if="item.menuName && item.children.length != 0">{{item.menuName}}</h4>
          <p v-for="itemSon in item.children" :key="itemSon.menuId"><a @click="itemSon.menuId>=16&&itemSon.menuId<=21?jumpInfo(item.url,itemSon.menuId):jumpTo(item.url)" v-if="itemSon.menuName">{{itemSon.menuName}}</a></p>
        </div>
      </div>
      <div class="right">
        <div>
          <!-- <div class="top"> -->
            <!-- <h3>Contact us</h3> -->
            <!-- <p>
              新浪微博：@成美慈善基金会 / 微信公众号：成美慈善基金会
            </p>
            <p>地址：中国海口市美兰区海甸四东路1号寰岛大厦写字楼20楼</p> -->
          <!-- </div> -->
          <div class="bottom">
              <span>
                <img src="../../assets/image/ewm.png" alt="">
                <b style="text-align: center;line-height: 28px;display: block;font-size: 14px;">官方微信公众号</b>
                @成美慈善基金会
              </span>
              <span>
                <img src="../../assets/image/weibo.png" alt="">
                <b style="text-align: center;line-height: 28px;display: block;font-size: 14px;">官方微博</b>
                @成美慈善基金会
              </span>
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
  protected jumpInfo(val: any,id?: any) {
    if(id) {
      sessionStorage.setItem("menuId", id)
      this.$router.push({
        path: val,
        query:{
          menuId: id
        }
      });
    } else {
        this.$router.push({
          path: val
        });
    } 
  }
  menuList(){
    menuList({parentId: 0}).then(res => {
      this.menu = res.data
      console.log('asdasdasd', this.menu[0].menuName);

    })
  }
}
</script>

<style scoped lang='scss'>
.footer {
  height: 100%;
  background: #fff;
  padding: 0 150px;
  background: url("../../assets/image/axbg.png") no-repeat;
  background-size:100vw 100vh;
  image-rendering: -webkit-optimize-contrast;
  .disF {
    display: flex;
    height: 370px;
  }
  h4 {
    font-weight: bold;
    color: #ffa631;
    font-size: 20px;
    margin-bottom: 5px;
  }
  a {
    color: #999999;
    line-height: 35px;
    font-size: 14px;
    cursor: pointer;
  }
  .left {
    display: flex;
    padding-top: 36px;
    padding-right: 50px;
    width: 1200px;
    height: 325px;
    text-align: center;
    justify-content: space-between;
    border-right: 1px solid #aaaaaa;
    border-bottom: 1px solid #aaa;
    > div {
      margin: 0 15px;
      cursor: pointer;
    }
  }
  .right {
    width: 600px;
    height: 325px;
    border-bottom:1px solid #aaaaaa ;
    padding-top: 36px;
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
      font-size: 16px;
    }
    .top{
        margin-bottom: 15px;
        // height: 170px;
        vertical-align: middle;
        margin-left: 16px;
    }
    .top, .bottom{
        padding-left: 35px;
    }
    .bottom{
        height: 261px;
        // border-top: 1px solid #aaaaaa;
        //border-bottom: 1px solid #aaa;
        display: flex;
        align-items:center;
        // margin-left: -14px;
        span{
          display: inline-block;
          width: 240px;
          /*height: 82px;*/
          // margin: 0 20px;
          text-align: center;
          color: #999;
        }
        img{
          width: 190px;
        }
    }
  }
  .banquan{
      text-align: center;
      display: block;
      // line-height: 60px;
      padding: 10px;
  }
}
</style>
