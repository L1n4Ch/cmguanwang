<template>
<div class="gray">
  <div class="home">
    <Header></Header>
    <full-page ref="example" :options="options" @after-load="afterLoad">
      <div class="section">
        <div class="box box1">
          <elSwiper style="height: 100%" :bannerList="bannerList"></elSwiper>
        </div>
      </div>
      <div class="section">
        <div
          class="box box2"
          :style="{
            background:
              'url(' + imgLink + bannerList[2][0] + ') no-repeat center center',
          }"
        >
          <div class="contentIn">
            <div>
              <div class="d1">
                <h4 class="h4">捐赠收入总额（元）</h4>
                <p class="orange fz65" v-if="data.donateMoeny.dTotalSum">
                  <animate-number
                    ref="myNum"
                    v-if="data.donateMoeny.dTotalSum"
                    from="0"
                    :to="data.donateMoeny.dTotalSum"
                    :formatter="formatter"
                  ></animate-number>
                </p>
              </div>
              <div style="display: flex">
                <div>
                  <h5 class="h5">捐赠支出总额（元）</h5>
                  <p class="orange fz55">
                    <animate-number
                      ref="myNum2"
                      v-if="data.donateMoeny.dTotalOut"
                      from="0"
                      :to="data.donateMoeny.dTotalOut"
                      :formatter="formatter"
                      mode="manual"
                    ></animate-number>
                  </p>
                </div>
                <div style="margin-left: 330px">
                  <h5 class="h5">捐赠人数</h5>
                  <p class="orange fz55">
                    <animate-number
                      ref="myNum3"
                      v-if="data.donateMoeny.dCount"
                      from="0"
                      :to="data.donateMoeny.dCount"
                      :formatter="formatter"
                      mode="manual"
                    ></animate-number>
                  </p>
                </div>
              </div>
              <div class="juanZengData">
                <div class="marquee-wrap">
                  <ul
                    class="marquee-list"
                    :class="{ 'animate-up': animateUp }"
                    @mouseenter="stopUp"
                    @mouseleave="goUp"
                  >
                    <li
                      v-for="(item, index) in listData"
                      style="display: flex"
                      :key="index"
                    >
                      <span class="time">{{ item.donationTime }}</span>
                      <span class="date">{{ item.donationCompany }}</span>
                      <span class="date1">{{ item.donationItem }}</span>
                      <span class="num">{{ item.donationMoney }}元</span>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="query">
                <input type="text" v-model="donationVal" />
                <span class="quertTo" @click="donationList">捐赠者查询</span>
                <span class="me" @click="targetRouter('/supportus', '25')"
                  >我要捐赠</span
                >
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="section" style="position: relative; overflow: hidden">
        <div
          class="box box3_1"
          :style="{ backgroundImage: 'url(' + this.box3_1_Bg + ')' }"
        >
          <div class="content">
            <div class="title">
              <h2>成美大事记</h2>
              <p>
                成美慈善基金会自成立以来，始终秉持“人是我们的第一投资”的核心理念<br />爱心足迹遍布全国，并辐射尼泊尔等周边国家，真正做到了“成人之美，大爱无疆”
              </p>
            </div>
            <div class="con">
              <div>
                <swiperDSJ
                  :bigEventList="bigEventList"
                  @changeBg="changeBg"
                  @showItem="showItem"
                ></swiperDSJ>
              </div>
            </div>
          </div>
        </div>
        <div class="dialog" v-show="dialogVisible">
          <div class="dialogIn" style="height: 600px">
            <i
              class="el-icon-close iconClose"
              @click="dialogVisible = false"
            ></i>
            <div v-html="showItemData.detail"></div>
          </div>
        </div>
      </div>
      <div class="section">
        <div
          class="box box3"
          :style="{
            background:
              bannerList[4] != 0
                ? 'url(' +
                  imgLink +
                  bannerList[4][0] +
                  ') no-repeat center center'
                : '#fff',
          }"
        >
          <div class="content animated infinite">
            <a @click="jumpTo('/show')">查看更多 ></a>
            <div class="contentIn">
              <div class="xm0">
                <div>
                  <h3 v-if="menuArr[1].menuName">
                    {{ menuArr[1].menuName }}
                  </h3>
                  <p class="fz24">PROJECT SHOW</p>
                </div>
                <div class="xmzs2">
                  <span
                    :class="{ cur: xiangMuIndex == key }"
                    @click="tabsXiangMu(key, item.menuId)"
                    v-for="(item, key) in menuArr[1].children"
                    :key="item.menuId"
                    >{{ item.menuName }}</span
                  >
                </div>
                <div class="text">
                  <p>
                    成美慈善基金会关注教育、医疗、文化、环保等四大领域的社会发展工作，支持民间社会创新及可持续发展，推动现代公益事业发展。
                  </p>
                  <p>
                    成美慈善基金会自成立以来，始终秉持“人是我们的第一投资”的核心理念，先后开展了情暖少数民族女孩项目、中国青年创想计划、爱助事实孤儿、成美圆梦助学等公益慈善项目。爱心足迹遍布全国，并辐射尼泊尔等周边国家，真正做到了“成人之美，大爱无疆”。
                  </p>
                </div>
              </div>
              <div class="xmzs">
                <div class="xmBox">
                  <div
                    class="xmD hvr-grow"
                    v-for="(item, index) in project"
                    :key="index"
                    @mouseenter="enter(1, index)"
                    @mouseleave="leave"
                  >
                    <div class="img">
                      <img class="" :src="item.imgUrl" alt="" />
                    </div>
                    <div class="center fz24">
                      <!-- 不包含—— 显示 -->
                      <p
                        v-if="current != index &&item.title.indexOf('——') == -1 && xiangMuIndex != 3" style="line-height: 22px">
                        {{ item.title }}
                      </p>
                      <!-- 包含—— 显示 -->
                      <p
                        v-if="current != index && item.title.indexOf('——') != -1">
                        {{ item.title.split("——")[0] }}
                      </p>
                      <p v-if="current != index && item.title.indexOf('——') != -1" >
                        ——{{ item.title.split("——")[1] }}
                      </p>
                      <!-- 字符超过14 -->
                      <p v-if="current != index &&xiangMuIndex == 3 &&item.title.indexOf('——') == -1">
                        {{ item.title.substring(0, 13) }}
                      </p>
                      <p v-if="current != index &&xiangMuIndex == 3 &&item.title.indexOf('——') == -1">
                        {{ item.title.substring(13, item.title.length) }}
                      </p>
                      <p
                        style="height: 65px; line-height: 50px"
                        v-if="current == index"
                        class="clickDetail"
                      >
                        <span class="mfty" @click="box4setpop(item)"
                          >查看详情</span
                        >
                      </p>
                    </div>
                  </div>
                </div>
                <div class="xmBox">
                  <div
                    class="xmD hvr-grow"
                    v-for="(item, index) in project2"
                    :key="index"
                    @mouseenter="enter(2, index)"
                    @mouseleave="leave"
                  >
                    <div class="img">
                      <img class="" :src="item.imgUrl" alt="" />
                    </div>
                    <div class="center fz24">
                      <!-- 不包含—— 显示 -->
                      <p v-if="current2 != index && item.title.indexOf('——') == -1&&xiangMuIndex != 3" style="margin-top:5px;">
                        {{ item.title }}
                      </p>
                      <!-- 包含—— 显示 -->
                      <p v-if="current2 != index && item.title.indexOf('——') != -1">
                        {{ item.title.split("——")[0] }}
                      </p>
                      <p v-if="current2 != index && item.title.indexOf('——') != -1">
                        ——{{ item.title.split("——")[1] }}
                      </p>
                      <!-- 视频专区 13个字符换行 -->
                      <p v-if="current2 != index &&xiangMuIndex == 3 &&item.title.indexOf('——') == -1">
                        {{ item.title.substring(0, 13) }}
                      </p>
                      <p v-if="current2 != index &&xiangMuIndex == 3 &&item.title.indexOf('——') == -1">
                        {{ item.title.substring(13, item.title.length) }}
                      </p>
                      <p
                        style="height: 65px; line-height: 50px"
                        v-if="current2 == index"
                        class="clickDetail"
                      >
                        <span class="mfty" @click="box4setpop(item)"
                          >查看详情</span
                        >
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="section" style="overflow: hidden">
        <div
          v-if="bannerList[5]"
          class="box box4"
          :style="{
            background:
              bannerList[5] != 0
                ? 'url(' +
                  imgLink +
                  bannerList[5][0] +
                  ') no-repeat center center'
                : '#fff',
          }"
          style="position: relative"
        >
          <div class="content">
            <h3 class="h3" v-if="menuArr[3].menuName">
              {{ menuArr[3].menuName }}
            </h3>
            <p class="p">成人之美，大爱无疆</p>
            <div class="xmzs">
              <span
                :class="{ cur: box4Index == 0 }"
                @click="tabsBox4(menuArr[3].children[2].menuId, 0)"
                >{{ xinxigongkai }}</span
              >
              <span
                :class="{ cur: box4Index == 1 }"
                @click="tabsBox4(menuArr[3].children[5].menuId, 1)"
                >{{ xiangmujinzhan }}</span
              >
              <a
                @click="
                  box4Index == 0 ? jumpTo('/info', 18) : jumpTo('/info', 21)
                "
                >查看更多 ></a
              >
            </div>
            <div class="contentIn">
              <div style="width: 100%">
                <swiper :box4Obj="box4Obj1" @box4setpop="box4setpop"></swiper>
                <swiper :box4Obj="box4Obj2" @box4setpop="box4setpop"></swiper>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="section" style="overflow: hidden">
        <div
          class="box box5"
          :style="{ backgroundImage: 'url(' + imgLink + bannerList[6] + ')' }"
        >
          <div class="content">
            <h3>基金会动态</h3>
            <p style="color: #fff">给生命一次机会，给孩子一个未来</p>
            <div class="xmzs" v-if="menuArr[3].children">
              <span
                :class="{ cur: box5Index == 0 }"
                v-if="menuArr[3].children[0].menuName"
                @click="tabsBox5(menuArr[3].children[0].menuId, 0)"
                >{{ menuArr[3].children[0].menuName }}</span
              >
              <span
                :class="{ cur: box5Index == 1 }"
                v-if="menuArr[3].children[1].menuName"
                @click="tabsBox5(menuArr[3].children[1].menuId, 1)"
                >{{ menuArr[3].children[1].menuName }}</span
              >
              <a
                @click="
                  box5Index == 0 ? jumpTo('/info', 16) : jumpTo('/info', 17)
                "
                >查看更多 ></a
              >
            </div>
            <div class="contentIn">
              <div
                style="
                  display: flex;
                  align-items: center;
                  justify-content: space-between;
                  width: 100%;
                "
              >
                <div class="ct" @click="box4setpop(box5Obj[0])">
                  <!-- introduce这个字段 -->
                  <div class="img" v-if="box5Obj[0].bgImageUrl">
                    <img :src="imgLink + box5Obj[0].bgImageUrl" alt="" />
                  </div>
                  <div class="text" v-if="box5Obj[0].title">
                    {{ box5Obj[0].title }}
                    <br /><br />
                    <span class="introduce">{{ box5Obj[0].introduce }}</span>
                  </div>
                </div>
                <div class="ct ct2" style="height: auto">
                  <div style="display: flex" @click="box4setpop(box5Obj[1])">
                    <div class="ct2Img" v-if="box5Obj[1].bgImageUrl">
                      <img :src="imgLink + box5Obj[1].bgImageUrl" alt="" />
                    </div>
                    <div class="text">
                      <div>
                        <p v-if="box5Obj[1].title">{{ box5Obj[1].title }}</p>
                        <br />
                        <span class="introduce">{{
                          box5Obj[1].introduce
                        }}</span>
                        <p class="time" v-if="box5Obj[1].createTime">
                          {{ box5Obj[1].createTime }}
                        </p>
                      </div>
                    </div>
                  </div>
                  <div style="display: flex" @click="box4setpop(box5Obj[2])">
                    <div class="text">
                      <div>
                        <p v-if="box5Obj[2].title">{{ box5Obj[2].title }}</p>
                        <br />
                        <span class="introduce">{{
                          box5Obj[2].introduce
                        }}</span>
                        <p class="time" v-if="box5Obj[2].createTime">
                          {{ box5Obj[2].createTime }}
                        </p>
                      </div>
                    </div>
                    <div class="ct2Img" v-if="box5Obj[2].bgImageUrl">
                      <img :src="imgLink + box5Obj[2].bgImageUrl" alt="" />
                    </div>
                  </div>
                </div>
                <div class="ct ct3" style="position: relative">
                  <div v-if="box5Obj[3].title" @click="box4setpop(box5Obj[3])">
                    <div class="ellipsis" style="">{{ box5Obj[3].title }}</div>
                    <span class="introduce">{{ box5Obj[3].introduce }}</span>
                    <p>{{ box5Obj[3].createTime }}</p>
                  </div>
                  <div v-if="box5Obj[4].title" @click="box4setpop(box5Obj[4])">
                    <div class="ellipsis">{{ box5Obj[4].title }}</div>
                    <span class="introduce">{{ box5Obj[4].introduce }}</span>
                    <p>{{ box5Obj[4].createTime }}</p>
                  </div>
                  <div v-if="box5Obj[5].title" @click="box4setpop(box5Obj[5])">
                    <div class="ellipsis">{{ box5Obj[5].title }}</div>
                    <span class="introduce">{{ box5Obj[5].introduce }}</span>
                    <p>{{ box5Obj[5].createTime }}</p>
                  </div>
                  <div
                    style="
                      color: #ffa631;
                      border: none;
                      position: absolute;
                      bottom: 5px;
                      cursor: pointer;
                    "
                    @click="
                      box5Index == 0 ? jumpTo('/info', 16) : jumpTo('/info', 17)
                    "
                  >
                    更多新闻
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="section">
        <div
          class="box box6"
          :style="{ backgroundImage: 'url(' + imgLink + bannerList[7] + ')' }"
        >
          <div class="content">
            <div class="contentIn">
              <h3>爱心留言</h3>
              <p style="color: #999999; font-size: 24px">
                “成人之美，大爱无疆”。
              </p>
              <div class="xmzs">
                <span @click="targetRouter('/supportus', '27')">点此留言</span>
                <!-- <span>爱心故事</span> -->
                <a @click="targetRouter('/supportus', '27')">查看更多 ></a>
              </div>
              <div class="xq">
                <div
                  class="xqIn"
                  v-for="(item, index) in msgArr"
                  :key="index"
                  @click="box4setpop(item, 1)"
                >
                  <div class="title">
                    <span
                      ><img src="../../assets/image/icon01.png" alt=""
                    /></span>
                    <p>{{ item.msgUse }}</p>
                  </div>
                  <div class="text">
                    <span>{{ item.msgDetail }}</span>
                    <!-- <div class="text-eslint">
                      {{ item.msgDetail }}
                    </div> -->
                    <p v-if="item.msgAnswer">成美回复：{{ item.msgAnswer }}</p>
                  </div>
                  <div class="time">
                    {{ item.msgTime }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="section">
        <div class="box box7" style="overflow: hidden">
          <div class="content">
            <div class="content1">
              <div class="box7-div"></div>
              <div class="title">
                <div>
                  <p>基本信息<span>ESSENTIAL INFORMATION</span></p>
                </div>
              </div>
              <div class="top">
                <div class="disFlex">
                  <p><img src="../../assets/home/dainhau.png" alt="" /></p>
                  <p class="pl15">
                    <span class="text"><b>电&nbsp;&nbsp;话:</b></span>
                    <span v-if="data.baseInfo.iphone">{{
                      data.baseInfo.iphone
                    }}</span>
                  </p>
                </div>
                <div class="disFlex">
                  <p><img src="../../assets/home/chuanzhen.png" alt="" /></p>
                  <p class="pl15">
                    <span class="text"><b>传&nbsp;&nbsp;真:</b></span>
                    <span>{{ data.baseInfo.fax }}</span>
                  </p>
                </div>
                <div class="disFlex">
                  <p><img src="../../assets/home/youxiang.png" alt="" /></p>
                  <p class="pl15">
                    <span class="text"><b>邮&nbsp;&nbsp;箱:</b></span>
                    <span>{{ data.baseInfo.email }}</span>
                  </p>
                </div>
              </div>
              <div class="center">
                <div class="disFlex">
                  <p><img src="../../assets/home/dizhi.png" alt="" /></p>
                  <p class="pl15">
                    <span class="text"><b>地&nbsp;&nbsp;址</b>:</span>
                    <span
                      >中国海口市美兰区海甸四东路1号寰岛大厦写字楼20楼,570208</span
                    >
                  </p>
                </div>
              </div>
              <div class="map">
                <baidu-map
                  class="bm-view"
                  style="width: 100%; height: 100%"
                  :center="point"
                  :zoom="zoom"
                  :scroll-wheel-zoom="true"
                  ak="LopuXIb2EmGMCjyrWgO47sqKLZnPLUmi"
                >
                  <bm-Marker
                    style="
                      width: 100%;
                      height: 100%;
                      border-radius: 10px !important;
                    "
                    :position="point"
                    :dragging="true"
                    animation="BMAP_ANIMATION_BOUNCE"
                  ></bm-Marker>
                </baidu-map>
              </div>
            </div>
            <div class="section7-box2">
              <ul>
                <li>
                  <a
                    :class="{ current: currentIndex == 1 }"
                    @mouseenter="enterIn(1)"
                    @click="jumpTo('/aboutus', 2)"
                  >
                    <h3>基金会简介</h3>
                    <span>BRIEF INTRODUCTION</span>
                    <!-- <i>01</i> -->
                  </a>
                </li>
                <li>
                  <a
                    :class="{ current: currentIndex == 2 }"
                    @mouseenter="enterIn(2)"
                    @click="jumpTo('/aboutus', 3)"
                  >
                    <h3>基金会章程</h3>
                    <span>CONSTITUTION</span>
                    <!-- <i>02</i> -->
                  </a>
                </li>
                <li></li>
                <li></li>
                <li>
                  <a
                    :class="{ current: currentIndex == 3 }"
                    @mouseenter="enterIn(3)"
                    @click="jumpTo('/aboutus', 4)"
                  >
                    <h3>理事会介绍</h3>
                    <span
                      >INTRODUCTION TO <br />
                      THE COUNCIL</span
                    >
                    <!-- <i>03</i> -->
                  </a>
                </li>
                <li>
                  <a
                    :class="{ current: currentIndex == 4 }"
                    @mouseenter="enterIn(4)"
                    @click="jumpTo('/newsletter')"
                  >
                    <h3>成美年报</h3>
                    <span>CHENGMEI REPORT</span>
                    <!-- <i>04</i> -->
                  </a>
                </li>
                <li>
                  <a
                    :class="{ current: currentIndex == 5 }"
                    @mouseenter="enterIn(5)"
                    @click="jumpTo('/aboutus', 5)"
                  >
                    <h3>基金会框架</h3>
                    <span
                      >INTRODUCTION TO <br />
                      THE COUNCIL</span
                    >
                    <!-- <i>05</i> -->
                  </a>
                </li>
                <li></li>
                <li>
                  <a
                    :class="{ current: currentIndex == 6 }"
                    @mouseenter="enterIn(6)"
                    @click="jumpTo('/info', 18)"
                  >
                    <h3>规章制度</h3>
                    <span>REGULATIONS</span>
                    <!-- <i>06</i> -->
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="section fp-auto-height">
        <div class="footerH">
          <f></f>
        </div>
      </div>
    </full-page>
    <!-- 弹窗 -->
    <el-dialog
      @close="closeDialog"
      :title="dialogTitle"
      :visible.sync="isDialog"
      :style="heiTrueFalse ? 'top:20%' : ''"
      :width="widthBhua"
    >
      <div class="parent-dom">
        <vuescroll :ops="ops">
          <div
            :class="[
              heiTrueFalse ? 'div-dialog' : 'el-dialog-div',
              'child-dom',
            ]"
          >
            <span v-html="itemPopup"></span>
            <br /><br />
            <span
              v-if="itemPopupAnswer != ''"
              v-html="`成美回复：${itemPopupAnswer}`"
            ></span>
          </div>
        </vuescroll>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click.stop="isDialog = false">取 消</el-button>
        <el-button type="primary" @click.stop="isDialog = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Watch } from "vue-property-decorator";
import {
  BANNERLIST,
  BASEINFO,
  donationList,
  bigEvent,
  menuList,
  articleList,
  AiXinshowMsg,
  addVISIT,
  getDETAILS,
} from "@/api/methods";
import { link, ellipsis } from "@/utils/common";
import $ from "jquery";

import swiper from "../../components/swiper/swiper.vue";
import swiperDSJ from "./components/swiperDSJ.vue";
import elSwiper from "../../components/swiperBanner/elSwiper.vue";
import BaiduMap from "vue-baidu-map/components/map/Map.vue";
import BmMarker from "vue-baidu-map/components/overlays/Marker.vue";
import vuescroll from "vuescroll";
// import vuescroll from 'vuescroll/dist/vuescroll-native';
import { Col } from "element-ui";

// import {numFilter} from '@/utils/filter'

@Component({
  components: {
    swiper,
    swiperDSJ,
    elSwiper,
    BaiduMap,
    BmMarker,
    vuescroll,
  },
  filters: {
    numFilter: (value: any) => {
      if (!value) return "0.00";
      let intPart = Number(value).toFixed(0); //获取整数部分
      let intPartFormat = intPart
        .toString()
        .replace(/(\d)(?=(?:\d{3})+$)/g, "$1,"); //将整数部分逢三一断
      return intPartFormat;
    },
  },
})
export default class Home extends Vue {
  ops = {
    vuescroll: {
      mode: "native",
      sizeStrategy: "percent",
      detectResize: true,
      /** 锁定一种滚动方向， 锁定的方向为水平或者垂直方向上滑动距离较大的那个方向 */
      locking: true,
    },
    scrollPanel: {},
    rail: {},
    bar: {
      showDelay: 500,
      onlyShowBarOnScroll: true,
      keepShow: true,
      background: "#c1c1c1",
      opacity: 1,
      hoverStyle: false,
      specifyBorderRadius: false,
      minSize: false,
      size: "6px",
      disable: false,
    },
  };
  //弹窗
  private dialogTitle = "";
  private isDialog = false;
  private itemPopup = "";
  private itemPopupAnswer = "";
  private titlePopup = "";
  //当弹窗是留言时候 给top:20
  private liuyan = false;
  // 显示宽度
  private heiTrueFalse = false;
  private widthBhua = "60%";
  private donationVal = "";
  private imgLink = link;
  private dialogVisible = false;
  private data: any = {
    donateMoeny: { dTotalSum: "" },
    baseInfo: { iphone: "" },
  };
  private curIndex = 1;
  private center = {
    lng: 110.348391,
    lat: 20.061395,
  };
  public get optionHover() {
    return {
      hoverStop: true,
      step: 2, //数值越大速度滚动越快
      limitMoveNum: 5, //开始无缝滚动的数据量  //this.fourDatata.length
      direction: 1, // 0向下 1向上 2向左 3向右
      openWatch: true, //开启数据实时监控刷新dom
      singleHeight: 95, //单步运动停止的高度(默认值0是无缝不停止的滚动) direction => 0/1
      singleWidth: 0, //单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
      waitTime: 1000, //单步运动停止的时间(默认值1000ms)
      // isSingleRemUnit: true
      // limitMoveNum: 97,
    };
  }

  private animateUp = false;
  private timer: any = null;

  private time = 2;
  private value = 777777777;
  private showed = false;
  private currentIndex = 1;

  private project: any = [];
  private project2: any = [];
  private point = {
    lng: 110.348391,
    lat: 20.061395,
  };
  private zoom = 15;
  private options = {
    licenseKey: "OPEN-SOURCE-GPLV3-LICENSE",
    // afterLoad: this.afterLoad,
    scrollOverflow: true,
    scrollBar: false,
    resize: true,
    navigation: true,
    navigationPosition: "right",
    //是否可以使用键盘方向键导航（上下键翻页），默认为true
    keyboardScrolling: true,
    //字体是否随着窗口缩放而缩放 默认为true

    // anchors: ['firstPage', 'secondPage', 'thirdPage', 'fourthPage', 'lastPage'],
    menu: "#myMenu",

    navigationTooltips: [
      "首屏",
      "捐赠信息",
      "成美大事记",
      "项目展示",
      "信息公开",
      "基金会动态",
      "爱心留言",
      "联系方式",
      "底部",
    ],
    showActiveTooltip: false,
    slidesNavigation: true,
    slidesNavPosition: "right",
    scrollOverflowResetKey:
      "YWx2YXJvdHJpZ28uY29tXzlRaGMyTnliMnhzVDNabGNtWnNiM2RTWlhObGRBPT14Ykk=",
    anchors: [
      "page1",
      "page2",
      "page3",
      "page4",
      "page5",
      "page6",
      "page7",
      "page8",
      "page9",
    ],
    // sectionsColor: [
    //   "#41b883",
    //   "#ff5f45",
    //   "#0798ec",
    //   "#fec401",
    //   "#1bcee6",
    //   "#ee1a59",
    //   "#2c3e4f",
    // ],
    afterLoad: this.afterLoad,
  };

  // 解决弹窗滚动 页面也滚动的bug
  @Watch("isDialog")
  getScroll(val: any) {
    if (val) {
      (this.$refs.example as any).api.setAllowScrolling(false);
    } else {
      (this.$refs.example as any).api.setAllowScrolling(true);
    }
  }

  jumpTo(val: any, id?: any) {
    this.$router.push({
      path: val,
      query: {
        menuId: id,
      },
    });
  }
  // 点击留言
  targetRouter(url: string, id?: string) {
    this.$router.push({
      path: url,
      query: {
        menuId: id,
      },
    });
  }
  formatter(num: any) {
    let temp = (this.$options as any).filters["numFilter"];
    // let temp = (this.$refs.myNum as any).filters['numFilter'];
    return temp(num);
  }

  //弹出弹窗
  public async box4setpop(item: any, lx?: any) {
    if (lx == 1) {
      this.liuyan = true;
    }
    this.isDialog = true;
    if (lx === 1) {
      this.dialogTitle = "留言信息";
      this.heiTrueFalse = true;
      this.widthBhua = "25%";
      this.itemPopupAnswer = item.msgAnswer;
      return (this.itemPopup = item.msgDetail);
    }
    this.dialogTitle = "";
    this.heiTrueFalse = false;
    this.widthBhua = "60%";
    this.titlePopup = item.title;
    //将当前点击的数组数据返回
    let res = await getDETAILS({ articleId: item.articleId });
    this.itemPopup = res.msg;
    //添加访问量
    await addVISIT({ articleId: item.articleId });
  }
  //关闭弹窗
  public closeDialog() {
    this.itemPopup = "";
    //防止弹窗抖动
    setTimeout(() => {
      this.liuyan = false;
    }, 200);
  }

  afterLoad(anchorLink: any, t: any) {
    if (t.index == 1 && !this.showed) {
      // this.numberGrow(this.$refs.numberGrow)
      (this.$refs.myNum as any).start();
      (this.$refs.myNum2 as any).start();
      (this.$refs.myNum3 as any).start();
      this.showed = true;
    }

    var i = this;
    $("#fp-nav").addClass("bai");
    $("#fp-nav li").removeClass("active");
    $("#fp-nav li").eq(t.index).addClass("active");
    t.index !== 0
      ? $("#fp-nav").addClass("xfpnav")
      : $("#fp-nav").removeClass("xfpnav");
    console.log(t.index);
    switch (t.index) {
    }
  }

  protected scrollAnimate() {
    if(this.listData.length > 1){
      this.animateUp = true;
      setTimeout(() => {
        this.listData.push(this.listData[0]);
        this.listData.shift();
        this.animateUp = false;
      }, 500);
    }
  }

  private listData = [];

  mounted() {
    this.AiXinshowMsg();
    this.BANNERLIST();
    this.BASEINFO();
    this.donationList();
    this.bigEvent();

    this.menuList();

    // this.articleList(this.menuArr[3].children[2].menuId, 1)
    //  alert(this.menuArr[3].children[2].menuId)

    this.timer = setInterval(this.scrollAnimate, 1500);
  }

  // box4切换
  box4Index = 0;
  private box4Obj1 = [];
  private box4Obj2 = [];
  tabsBox4(item: any, num: any) {
    this.box4Index = num;
    // this.articleList(item, 1)
    let obj = {
      menuId: item,
      newStatus: 0,
      pageNum: 1,
      pageShow: 1,
      pageSize: 20,
      showDetail: 1,
    };
    articleList(obj).then((res) => {
      this.box4Obj1 = res.rows.slice(0, res.rows.length / 2);
      this.box4Obj2 = res.rows.slice(res.rows.length / 2, res.rows.length);
    });
  }

  //
  box5Index = 0;
  private box5Obj: any = [
    { bgImageUrl: "" },
    { bgImageUrl: "" },
    { bgImageUrl: "" },
    { bgImageUrl: "" },
    { bgImageUrl: "" },
    { bgImageUrl: "" },
  ];
  tabsBox5(item: any, num: any) {
    console.log(item);
    this.box5Index = num;
    // this.articleList(item, 1)
    let obj = {
      menuId: item,
      newStatus: 0,
      pageNum: 1,
      pageShow: 1,
      pageSize: 6,
      showDetail: 1,
    };
    articleList(obj).then((res) => {
      this.box5Obj = res.rows;
    });
  }

  stopUp() {
    clearInterval(this.timer);
  }
  goUp() {
    this.timer = setInterval(this.scrollAnimate, 1500);
  }

  //获取banner列表
  private bannerList: any = { 1: [], 2: [], 3: [], 4: [], 5: [], 6: [] };
  protected async BANNERLIST() {
    BANNERLIST().then((res) => {
      // res.data.imgList.forEach((e: any) => {
      //  this.bannerList.push(link + e);
      // });
      //  = res.data.imgList
      this.bannerList = res.data.imgList;
    });
  }

  //获取基础配置
  protected async BASEINFO() {
    BASEINFO().then((res) => {
      this.data = res.data;
      // this.data.donateMoeny.dTotalSum = separateNum(this.data.donateMoeny.dTotalSum)
      this.$store.commit("setBaseInfo", res.data);
    });
  }
  //捐款记录查询
  protected async donationList() {
    donationList({
      pageNum: 1,
      pageSize: 100,
      donationCompany: this.donationVal,
    }).then((res) => {
      this.listData = res.rows;
    });
  }
  //成美大事件
  private bigEventList: any = [];
  private box3_1_Bg = "";
  protected async bigEvent() {
    bigEvent().then((res) => {
      console.log("成美大事件", res.data);
      // res.data.forEach((item: any) => {
      //   item.desc = ellipsis(item.detail, 250);
      // });
      this.bigEventList = res.data;
      this.box3_1_Bg = link + this.bigEventList[0].bgImageUrl;
      // this.listData = res.rows
    });
  }
  changeBg(e: any) {
    this.box3_1_Bg = link + this.bigEventList[e].bgImageUrl;
  }
  private showItemData = {};
  showItem(item: any) {
    console.log(item);
    this.dialogVisible = true;
    this.showItemData = item;
  }

  // 项目展示
  private menuArr: any = [
    { menuName: "" },
    { menuName: "" },
    { menuName: "" },
    { menuName: "" },
  ];
  xiangMuIndex = 0;
  private xinxigongkai = "";
  private xiangmujinzhan = "";
  protected menuList() {
    menuList({ parentId: 0 }).then((res: any) => {
      this.xinxigongkai = res.data[3].children[2].menuName;
      this.xiangmujinzhan = res.data[3].children[5].menuName;
      this.menuArr = res.data;
      this.articleList(res.data[1].children[0].menuId);
      this.tabsBox4(this.menuArr[3].children[2].menuId, 0);
      this.tabsBox5(this.menuArr[3].children[0].menuId, 0);
    });
  }

  // 爱心留言
  protected msgArr: any = [];
  protected AiXinshowMsg() {
    AiXinshowMsg({ pageNum: 1, pageSize: 6 }).then((res) => {
      // res.rows.forEach((item: any) => {
      //   item.msgDetail = ellipsis(item.msgDetail, 40);
      // });
      this.msgArr = res.rows;
    });
  }

  protected articleList(menuId: any, showDetail = 0) {
    let obj = {
      menuId: menuId,
      newStatus: 0,
      pageNum: 1,
      pageShow: 1,
      pageSize: 6,
      showDetail: showDetail,
    };
    articleList(obj).then((res) => {
      res.rows.forEach((item: any, index: any) => {
        item.imgUrl = link + item.bgImageUrl;
        if (index < 3) {
          this.project.push(item);
        } else {
          this.project2.push(item);
        }
      });
    });
  }

  protected tabsXiangMu(key: any, menuId: any) {
    this.project = [];
    this.project2 = [];
    this.xiangMuIndex = key;
    this.articleList(menuId);
  }

  protected userDetail = {
    address: "中国海口市美兰区海甸四东路1号寰岛大厦",
  };
  enterIn(index: number) {
    this.currentIndex = index;
  }

  private current = -1;
  private current2 = -1;
  protected enter(type: any, index: any) {
    type == 1 ? (this.current = index) : (this.current2 = index);
  }
  protected leave() {
    this.current = -1;
    this.current2 = -1;
  }

  numberGrow(ele: any) {
    console.log("ele", ele);
    let _this = this;

    let step = ((_this as any).value * 10) / ((_this as any).time * 1000);
    let current = 0;
    let start = 0;
    let t: any = setInterval(function () {
      start += step;
      if (start > (_this as any).value) {
        clearInterval(t);
        start = (_this as any).value;
        t = null;
      }
      if (current === start) {
        return;
      }
      current = start;
      ele.innerHTML = current
        .toString()
        .replace(/(\d)(?=(?:\d{3}[+]?)+$)/g, "$1,");
    }, 10);
  }
}
</script>
<style>

.gray {
  -webkit-filter: grayscale(100%);
  -moz-filter: grayscale(100%);
  -ms-filter: grayscale(100%);
  -o-filter: grayscale(100%);
  filter: grayscale(100%);
}

.liuyan {
  top: 20%;
}
.el-dialog {
  border-radius: 10px !important;
}
/* 弹窗 */
.el-dialog-div {
  height: 60vh !important;
  overflow: auto !important;
}
.div-dialog {
  /* height: 25vh !important; */
  opacity: 0.8;
}
.ellipsis {
  overflow: hidden;
  width: 100%;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.iScrollIndicator {
  opacity: 0;
}
.introduce {
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  padding: 5px 20px 0px 0px;
  color: #ccc !important;
  display: block;
  overflow: hidden;
  font-size: 14px !important;
  overflow: hidden;
  width: 100%;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.introduce:nth-child(1) {
  height: 25px !important;
  -webkit-line-clamp: 1 !important;
}
/* 修改zp */
.box4 .content {
  /* position: absolute !important; */
  width: 92% !important;
  /*height: 850px !important;*/
  height: auto !important;
  right: 8% !important;
  /* margin-top:115px !important; */
  top: 55% !important;
  transform: translateY(-50%);
}
.box5 .content {
  top: 50px !important;
}
.box2 .content {
  height: 48rem !important;
  margin-top: 9rem !important;
}
.box3 .content .contentIn {
  background: none !important;
}
.bac {
  /* background: url("../../assets/image/bjhome.jpg") no-repeat !important; */
  height: 38rem;
  padding: 20px;
  padding-right: 0px;
  border-radius: 12px;
}
.detail::-webkit-scrollbar {
  width: 8px !important;
  background: none !important;
}
#fp-nav {
  position: fixed;
  z-index: 100;
  top: 50%;
  opacity: 1;
  transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  -webkit-transform: translate3d(0, -50%, 0);
}

#fp-nav {
  position: fixed;
  z-index: 100;
  margin-top: -32px;
  top: 42%;
  opacity: 1;
  -webkit-transform: translate3d(0, 0, 0);
}
#fp-nav::before {
  content: "";
  position: absolute;
  height: 100px;
  width: 1px;
  background: #bdbbbc;
  top: -100px;
  left: 50%;
  margin-left: 1px;
  display: block;
}

#fp-nav::after {
  position: absolute;
  content: "";
  height: 100px;
  width: 1px;
  background: #bdbbbc;
  bottom: -100px;
  left: 50%;
  margin-left: 2.2px;
  display: block;
}

#fp-nav.fp-right {
  right: 17px;
  z-index: 999;
}

::v-deep #fp-nav.fp-left {
  left: 17px;
}

.fp-slidesNav {
  position: absolute;
  z-index: 4;
  opacity: 1;
  -webkit-transform: translate3d(0, 0, 0);
  -ms-transform: translate3d(0, 0, 0);
  transform: translate3d(0, 0, 0);
  left: 0 !important;
  right: 0;
  margin: 0 auto !important;
}

.fp-slidesNav.fp-bottom {
  bottom: 17px;
}

.fp-slidesNav.fp-top {
  top: 17px;
}

#fp-nav ul,
.fp-slidesNav ul {
  margin: 0;
  padding: 0;
}

#fp-nav ul li,
.fp-slidesNav ul li {
  display: block;
  width: 28px;
  height: 26px;
  margin: 7px;
  position: relative;
}

.fp-slidesNav ul li {
  display: inline-block;
}

#fp-nav ul li a,
.fp-slidesNav ul li a {
  display: block;
  position: relative;
  z-index: 1;
  width: 100%;
  height: 100%;
  cursor: pointer;
  text-decoration: none;
}

#fp-nav ul li a.active span,
#fp-nav ul li:hover a.active span,
.fp-slidesNav ul li a.active span,
.fp-slidesNav ul li:hover a.active span {
  height: 12px;
  width: 12px;
  margin: -6px 0 0 -6px;
  border-radius: 100%;
}
#fp-nav ul li.active:hover .fp-tooltip,
#fp-nav.fp-show-active a.active + .fp-tooltip {
  opacity: 0;
}

#fp-nav ul li a.active span,
#fp-nav ul li:hover a.active span {
  position: absolute;
  left: auto;
  top: 3px;
  right: 4px;
  width: 130px;
  height: 24px;
  font-size: 20px;
  border-radius: 0;
  background: none;
  margin: 0;
  clip: auto;
  text-align: right;
  /*color: gray;*/
  color: #ffa62e;
}

#fp-nav ul li a span,
.fp-slidesNav ul li a span {
  border-radius: 50%;
  position: absolute;
  z-index: 1;
  height: 10px;
  width: 10px;
  border: 0;
  background: #bdbbbc;
  left: 50%;
  top: 50%;
  margin: -2px 0 0 -2px;
  -webkit-transition: all 0.1s ease-in-out;
  -moz-transition: all 0.1s ease-in-out;
  -o-transition: all 0.1s ease-in-out;
  transition: all 0.1s ease-in-out;
}

#fp-nav ul li:hover a span,
.fp-slidesNav ul li:hover a span {
  width: 14px;
  height: 14px;
  margin: -5px 0 0 -5px;
}

#fp-nav ul li .fp-tooltip {
  position: absolute;
  top: 2px;
  margin-right: 4px;
  color: #666;
  font-size: 18px;
  font-family: arial, helvetica, sans-serif;
  white-space: nowrap;
  max-width: 220px;
  overflow: hidden;
  display: block;
  opacity: 0;
  width: 0;
  cursor: pointer;
}

#fp-nav ul li:hover .fp-tooltip,
#fp-nav.fp-show-active a.active + .fp-tooltip {
  -webkit-transition: opacity 0.2s ease-in;
  transition: opacity 0.2s ease-in;
  width: auto;
  opacity: 1;
}

#fp-nav ul li .fp-tooltip.fp-right {
  right: 20px;
}

#fp-nav ul li .fp-tooltip.fp-left {
  left: 20px;
}
.fp-auto-height .fp-slide,
.fp-auto-height .fp-tableCell,
.fp-auto-height.fp-section {
  height: auto !important;
}

.fp-responsive .fp-auto-height-responsive .fp-slide,
.fp-responsive .fp-auto-height-responsive .fp-tableCell,
.fp-responsive .fp-auto-height-responsive.fp-section {
  height: auto !important;
}

.fp-sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border: 0;
}
</style>
<style scoped lang='scss'>
.dialog {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.7);
  z-index: 999;
  .dialogIn {
    width: 70%;
    height: 70%;
    padding: 50px;
    line-height: 30px;
    background: #fff;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border-radius: 10px;
    .iconClose {
      font-size: 30px;
      position: absolute;
      top: 20px;
      right: 20px;
      cursor: pointer;
    }
    > div {
      height: 100%;
      overflow: auto;
    }
  }
}
.fp-auto-height.fp-section,
.fp-auto-height .fp-slide,
.fp-auto-height .fp-tableCell,
.fp-tableCell {
  height: 410px !important;
}

$orange: #ffa32a;
.marquee-wrap {
  width: 100%;
  height: 100px;
  margin: 0 auto;
  overflow: hidden;
  .marquee-list {
    li {
      height: 100%;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
      padding: 0 20px;
      list-style: none;
      line-height: 100px;
      text-align: center;
      font-size: 18px;
      font-weight: 400;
      .time,
      .date,
      .date1,
      .num {
        flex: 1;
      }
      .time,
      .date {
        text-align: left;
      }
      .num {
        text-align: right;
      }
    }
  }
  .animate-up {
    transition: all 0.5s ease-in-out;
    transform: translateY(-100px);
  }
}
.seamless-warp {
  height: 229px;
  overflow: hidden;
}
.orange {
  color: $orange;
}
.fz65 {
  font-size: 65px;
  font-weight: bold;
}
.fz55 {
  font-size: 55px;
  font-weight: bold;
}
.h4 {
  font-size: 30px;
}
.h5 {
  font-size: 25px;
}
.header {
  position: fixed;
  z-index: 99;
  top: 0;
  left: 0;
  right: 0;
}
.box {
  position: absolute;
  width: 100%;
  height: 100vh;
  /* min-height: 1440px; */
  object-position: center;
  background-position: center center;
  background-size: cover !important;
  display: list-item;
}
.box1 {
  /*position: relative;*/
  overflow: hidden;
  // background: url("../../assets/image/banner.png") no-repeat center center;
  background-size: 100% 100% !important;
}
.box2 {
  // background: url("../../assets/image/bg2.png") no-repeat center center;
  position: relative;
  h4 {
    font-family: inherit;
    font-size: 30px;
  }
  .orange {
    font-family: Impact;
    font-size: 140px;
  }
  .fz55 {
    font-size: 72px;
  }
  .contentIn {
    width: 91%;
    /*height: 705px;*/
    background: #fff;
    margin: auto 0;
    position: absolute;
    left: 50%;
    right: 0;
    top: 55%;
    transform: translate(-50%, -50%);
    padding: 60px 0;
    > div {
      padding: 0px 170px;
      position: relative;
    }
    .d1 {
      // position: absolute;
      margin-bottom: 40px;
    }
    .juanZengData {
      margin-top: 55px;
      height: 100px;
      overflow: hidden;
      border: 1px solid #bfbfbf;
      border-radius: 10px;
      .item {
        height: 97px;
        overflow: hidden;

        li {
          font-size: 24px;
          div {
            line-height: 97px;
            // padding: 40px 15px;
            display: flex;
          }

          width: 100%;
          span {
            flex: 1;
          }
        }
      }
    }
    .query {
      margin-top: 35px;
      display: flex;
      input {
        display: inline-block;
        width: 800px;
        height: 98px;
        border-radius: 10px;
        outline: none;
        font-size: 24px;
        padding-left: 20px;
        border: 2px solid #bfbfbf;
      }
      .quertTo {
        font-size: 24px;
        display: inline-block;
        width: 295px;
        height: 102px;
        background: #ffa32a;
        text-align: center;
        color: #fff;
        line-height: 102px;
        margin: 0 40px;
        border-radius: 10px;
        cursor: pointer;
      }
      .me {
        font-size: 24px;
        display: inline-block;
        width: 190px;
        height: 102px;
        background: #ffa32a;
        text-align: center;
        color: #fff;
        line-height: 102px;
        border-radius: 10px;
        cursor: pointer;
      }
    }
  }
}
.box3_1 {
  //   background: url("../../assets/image/cmdsj.png") no-repeat center center;
  // background-size: 100% 100%!;
  overflow: hidden;
  position: relative;
  .content {
    position: absolute;
    margin: auto 0;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    height: 550px;
    .title {
      padding: 0 165px;
      color: #fff;
      h2 {
        font-size: 50px;
        margin-bottom: 40px;
      }
      p {
        font-size: 14px;
        line-height: 35px;
        margin-bottom: 30px;
      }
    }
  }
}

.box4,
.box5 {
  // background: url("../../assets/image/bg3.png") no-repeat center center;
  background: #fff;
  position: relative;
  .content {
    width: 78%;
    position: absolute;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
    .xmzs {
      text-align: right;
      margin-bottom: 18px;
      cursor: pointer;
      font-size: 20px;
      a {
        color: #ffa632;
        border: 1px solid #ffa632;
        border-radius: 10px;
        padding: 8px 13px;
        margin-left: 15px;
      }
      span {
        color: #999999;
        display: inline-block;
        padding: 0 15px;
      }
    }
    h3 {
      font-size: 42px;
      margin-bottom: 10px;
    }
    > p {
      font-size: 20px;
      color: #676565;
    }
    .contentIn {
      background: #ffff;
      display: flex;

      border-radius: 30px;
      overflow: hidden;
    }
  }
}
// 修改zp
.box4 .content .contentIn {
  // height: 680px !important;
}
.box4 {
  .content {
    > h3 {
      font-size: 42px !important;
      margin-left: 12% !important;
      // transform: translateY(34px);
    }
    > p {
      margin-left: 12% !important;
      // transform: translateY(34px);
    }
  }
}
.box5 {
  /*.contentIn{*/
  /*  height: 590px;*/
  /*}*/
  .content {
    position: absolute;
    top: 55% !important;
    width: 78%;
    height: 620px;
    transform: translateY(-50%);
    > h3 {
      font-size: 42px !important;
      margin-top: -28px;
    }
    .xmzs {
      margin-top: 10px;
    }
  }
}
.box3 {
  // background: url("../../assets/image/bg3.png") no-repeat center center;
  a {
    position: absolute;
    top: 1px;
    right: 7.55%;
    margin-bottom: 50px;
    cursor: pointer;
    color: #ffa632;
    border: 0.005319rem solid #ffa632;
    border-radius: 0.053191rem;
    padding: 0.042553rem 0.069149rem;
    font-size: 20px;
  }
  position: relative;
  .content {
    height: 720px;
    position: absolute;
    margin: auto;
    left: 0;
    right: 0;
    top: 55%;
    transform: translateY(-50%);

    .xmzs {
      text-align: right;
      margin-bottom: 18px;
      margin-left: 10px;
      cursor: pointer;
      font-size: 20px;
      a {
        color: #ffa632;
        border: 1px solid #ffa632;
        border-radius: 10px;
        padding: 8px 13px;
        margin-left: 15px;
      }
    }
    .xmzs2 {
      width: 100%;
      display: flex;
      justify-content: space-between;
      cursor: pointer;
      span {
        color: #666;
        display: inline-block;
        padding: 0 4px 5px;
        font-size: 20px;
      }
      .cur {
        border-bottom: 2px solid $orange;
      }
    }
    h3 {
      font-size: 42px;
      margin-bottom: 10px;
      margin-top: -32px;
    }
    > p {
      display: block;
      font-size: 20px;
      color: #676565;
      // margin-bottom: 45px;
    }
    .contentIn {
      background: #ffff;
      display: flex;
      height: 590px;
      border-radius: 30px;
      overflow: hidden;
      /*width: 1800px;*/
      // margin: 0 150px ;
      margin-left: 200px !important;
    }
    .xmBox {
      display: flex;
      > div {
        cursor: pointer;
        text-align: center;
        border: 1px solid #dce2e7;
        margin-right: -1px;
        margin-bottom: -1px;
        position: relative;
        flex: 1;
      }
      .xmD {
        position: relative;
        height: 288px;
        width: 385px;
        .img {
          position: absolute;
          top: 0;
          right: 0;
          left: 0;
          height: 210px;
          padding: 15px 15px 0 15px;
          display: inline-block;
          overflow: hidden;
          img {
            height: 100%;
            width: 100%;
            // object-fit: fill;
            // object-fit: cover;
            object-fit: contain;
          }
        }
      }
      .fz24 {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        width: 100%;
        font-size: 20px;
        height: 65px;
        // line-height: 65px;
      }
      .clickDetail {
        display: flex;
        border: 1px solid #fdc830;
        border-top: none;
        border-bottom-right-radius: 20px;
        border-bottom-left-radius: 20px;
        &:first-child {
          color: #fdc830;
        }
        span {
          flex: 1;
          text-align: center;
        }
        .mfty {
          color: #fff;
          background: linear-gradient(to right, #fbbb31, #f58634);
          opacity: 0.8;
        }
      }
    }
  }
}
.box3 {
  // background: #fff;
  .content {
    h3 {
      // margin-bottom: ;
      font-size: 42px;
    }
    .contentIn {
      border-radius: 0;
      margin: 70px auto 0;
      overflow: inherit;
      .xmBox .xmD {
        margin-right: 30px;
        margin-bottom: 30px;
        border-radius: 20px;
        background: white;
        overflow: hidden;
        .fz24 {
          font-size: 20px;
          color: #333;
        }
      }
    }
    .fz24 {
      font-size: 24px;
      color: #999;
    }
    .aixinIcon {
      margin-top: 145px;
      width: 175px;
      height: 112px;
      img {
        width: 100%;
        height: 100%;
        display: block;
      }
    }
    .text {
      // margin-top: 200px;
      position: absolute;
      bottom: -10px;
      // width: 200px;
      p {
        text-indent: 2em;
        font-size: 16px;
        color: #666;
        line-height: 33px;
      }
    }
    .xmzs {
      width: 1010px;
      font-size: 20px;
      text-align: left;
    }
    .xm0 {
      width: 480px;
      margin-right: 70px;
      margin-top: 20px;
      position: relative;
      .fz24 {
        margin-bottom: 45px;
      }
    }
  }
}
.box4 {
  .contentIn {
    background: none !important;
    padding-left: 205px;
  }
  .swiper-slide {
    width: 388px;
  }
  .item {
    // width: 388px!important;
    // height: 472px!important;
    background: red;
  }
  .content .xmzs .cur {
    color: #999999;
    display: inline-block;
    /*padding: 0 30px 10px 30px;*/
    padding: 0 10px 5px;
    border-bottom: 2px solid #ffa32a;
  }
}
.box5 {
  background: url("../../assets/image/jjhdt.png") no-repeat center center;

  p {
    font-size: 20px !important;
  }
  .content .xmzs .cur {
    color: #999999;
    display: inline-block;
    padding: 0 10px 5px;
    border-bottom: 2px solid #ffa32a;
  }
  .content {
    h3 {
      color: #fff;
      font-size: 42px;
    }
    .contentIn {
      background: none;
      height: 500px;
      border-radius: 0px;
      margin-top: 40px;
      .ct {
        width: 465px;
        height: 470px;
        background: #fff;
        border-radius: 20px;
        cursor: pointer;
        overflow: hidden;
        /*margin: 0 30px;*/
        .img {
          width: 100%;
          height: 265px;
          img {
            display: block;
            height: 100%;
            width: 100%;
            object-fit: cover;
          }
        }
        .text {
          padding: 30px;
          font-size: 28px;
        }
      }
      .ct2 {
        font-size: 28px;
        overflow: hidden;
        width: 465px;
        > div {
          height: 235px;
        }
        .ct2Img {
          width: 245px;
          img {
            height: 100% !important;
            width: 100% !important;
            object-fit: cover;
          }
        }
        img {
          display: block;
        }
        .text {
          width: 232px;
          padding: 0px;
          > div {
            padding: 22px 30px;
          }
        }
        .text:nth-child(1) {
          > div {
            padding: 10px 30px;
          }
          p {
            margin: 0;
            padding: 0;
            height: 105px;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 4;
            overflow: hidden;
          }
        }
        .time {
          font-size: 22px;
          color: #7d8eae;
          margin-top: 10px;
        }
      }
      .ct3 {
        font-size: 20px;
        > div {
          margin: 0 35px;
          padding: 20px 0 10px 0;
          border-bottom: 1px solid #e9e8ef;
          p {
            font-size: 20px;
            margin-top: 15px;
            color: #747cad;
            cursor: pointer;
          }
        }
      }
    }
  }
}
.box6 {
  /*background: url("../../assets/image/axly.png") no-repeat center center;*/
  position: relative;
  .contentIn {
    padding: 35px 0 0 45px;
    position: absolute;
    bottom: 0;
    right: 0;
    width: 1190px;
    height: 755px;
    background: url("../../assets//image/axbg.png") no-repeat;
    background-size: 100% 100%;
    overflow: hidden;
    h3 {
      font-size: 42px;
      margin-bottom: 30px;
    }
    .xmzs {
      text-align: right;
      margin-bottom: 18px;
      cursor: pointer;
      font-size: 20px;
      margin-right: 10px;
      padding-right: 15px;
      a {
        color: #ffa632;
        border: 1px solid #ffa632;
        border-radius: 10px;
        padding: 8px 13px;
        margin-left: 15px;
      }
      span {
        color: #999999;
        display: inline-block;
        padding: 0 15px;
      }
    }
    .xq {
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-flow: row wrap;
      padding-left: 100px;
      padding-right: 50px;
      margin-top: 60px;

      .xqIn {
        width: 260px;
        height: 188px;
        background: #fff;
        box-shadow: -10px 12px 7px 0px rgba(0, 0, 0, 0.11);
        padding: 20px;
        margin-bottom: 45px;
        position: relative;
        cursor: pointer;
        .title {
          display: flex;
          p {
            overflow: hidden;
            text-overflow: ellipsis;
            /* 弹性伸缩盒子模型显示 */
            display: -webkit-box;
            /* 限制在一个块元素显示的文本的行数 */
            -webkit-line-clamp: 2;
            /* 设置或检索伸缩盒对象的子元素的排列方式 */
            -webkit-box-orient: vertical;
          }
          span {
            padding-right: 15px;
          }
          font-size: 18px;
          img {
            width: 33px;
            height: 28px;
          }
        }
        .text {
          font-size: 16px;
          line-height: 30px;
          color: #999;
          p,
          span {
            overflow: hidden;
            text-overflow: ellipsis;
            /* 弹性伸缩盒子模型显示 */
            display: -webkit-box;
            /* 限制在一个块元素显示的文本的行数 */
            -webkit-line-clamp: 2;
            /* 设置或检索伸缩盒对象的子元素的排列方式 */
            -webkit-box-orient: vertical;
          }
          // .text-eslint{
          //   height: 90px;
          //   width: 100%;
          //   // background: red;
          //   display: -webkit-box;
          //   -webkit-box-orient: vertical;
          //   -webkit-line-clamp: 3;
          //   overflow: hidden;
          // }
        }
        .time {
          text-align: right;
          position: absolute;
          bottom: 15px;
          right: 15px;
        }
      }
    }
  }
}
.bm-view div {
  border-radius: 10px !important;
}
.box7 {
  background: url("../../assets/image/address.png") no-repeat center center;
  position: relative;
  .map {
    margin-top: 20px;
    height: 273px;
    border-radius: 10px;
    overflow: hidden;
    -webkit-overflow-scrolling: touch;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .top {
    margin-top: 20px;
  }
  .center {
    // margin-top: 45px;
    text-align: left;
  }
  .content {
    position: absolute;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    height: 540px;
    width: 1545px;
    display: flex;
  }
  .content1 {
    width: 720px;
    height: 520px;
    border-radius: 10px;
    padding: 25px;
    position: relative;
    background: rgba(0, 0, 0, 0.3);
    color: #fff !important;
    z-index: 2;
    .box7-div {
      overflow: hidden;
      position: absolute;
      top: 0px;
      left: 2px;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.01);
      /*border-top: solid  3px rgba(255,255,255,.3);*/
      border-radius: 10px;
      z-index: -1;
    }
    .box7-div:after {
      position: absolute;
      left: -10px;
      top: 0;
      right: -10px;
      bottom: -10px;
      content: "";
      z-index: 2;
      background: rgba(0, 0, 0, 0.01);
      // border-top: 3px solid rgba(255,255,255,.3);
    }
    .box7-div:before {
      background: url("../../assets/image/address.png") no-repeat -410px -200px;
      filter: blur(20px);
      position: absolute;
      left: -10px;
      top: -10px;
      right: -10px;
      bottom: -10px;
      content: "";
      z-index: 1;
    }
    .title {
      width: 100%;
      height: 60px;
      border-bottom: 1px solid #fff;
      p {
        font-size: 26px;
        color: #fff;
        span {
          font-size: 15px;
          margin-left: 10px;
        }
      }
    }
    > div {
      // display: flex;
      // justify-content: space-between;
    }
    .disFlex {
      width: 100%;
      margin-top: 15px;
      p {
        img {
          width: 20px;
          height: 20px;
        }
      }
      span {
        margin-right: 10px;
      }
      .text {
        margin-bottom: 5px;
      }
      .pl15 {
        padding-left: 10px;
        font-size: 16px !important;
      }
      img {
        width: 50px;
        height: 50px;
      }
    }
  }
  .section7-box2 {
    width: 730px;
    margin-left: 45px;
  }
  .section7-box2 li i {
    position: absolute;
    right: 0;
    bottom: 0;
    font-family: Impact, sans-serif;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.053);
    font-size: 4rem;
    display: block;
    font-style: normal;
    line-height: 1;
    font-size: 120px;
    bottom: -0.5rem;
  }
  .section7-box2 li a {
    cursor: pointer;
    display: block;
    position: relative;
    background: #fff;
    border-radius: 3px;
    height: 100%;
    padding-left: 38px;
    overflow: hidden;
    h3 {
      margin-top: 55px;
      font-size: 22px;
      color: #161e20;
    }
    span {
      font-size: 18px;
      font-weight: bold;
      color: #93888c;
    }
  }
  .section7-box2 li a.current {
    background: #ffa632;
    color: #fff;
    h3 {
      color: #fff;
    }
    span {
      color: #fff;
    }
  }
  .section7-box2 li {
    width: 240px;
    height: 177px;
    float: left;
    margin-right: 3px;
    margin-bottom: 3px;
    border-radius: 5px;
  }
}
.footerH {
  height: 1000px;
}
</style>
<style scoped lang="scss">
// 动画
.bounceIn {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  animation: bounceIn 3s linear 2s none;
}
@keyframes bounceIn {
  0%,
  20%,
  40%,
  60%,
  80%,
  to {
    -webkit-animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
    animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
  }
  0% {
    opacity: 0;
    -webkit-transform: scale3d(0.3, 0.3, 0.3);
    transform: scale3d(0.3, 0.3, 0.3);
  }
  20% {
    -webkit-transform: scale3d(1.1, 1.1, 1.1);
    transform: scale3d(1.1, 1.1, 1.1);
  }
  40% {
    -webkit-transform: scale3d(0.9, 0.9, 0.9);
    transform: scale3d(0.9, 0.9, 0.9);
  }
  60% {
    opacity: 1;
    -webkit-transform: scale3d(1.03, 1.03, 1.03);
    transform: scale3d(1.03, 1.03, 1.03);
  }
  80% {
    -webkit-transform: scale3d(0.97, 0.97, 0.97);
    transform: scale3d(0.97, 0.97, 0.97);
  }
  to {
    opacity: 1;
    -webkit-transform: scaleX(1);
    transform: scaleX(1);
  }
}
</style>