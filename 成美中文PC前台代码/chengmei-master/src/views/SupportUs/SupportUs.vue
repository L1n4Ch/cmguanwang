<template>
  <div class="Support-us" ref="supportus">
    <!-- 头部区域 -->
    <Header class="header"></Header>
    <!-- 图片区域 -->
    <div class="av_img">
      <img :src="link + titleImg_Dist.menuImgUrl" alt="" width="100%" />
      <div class="img_tohash ">
        <h1>截止{{title_num.dTime}}日</h1>
        <div>
          <p>捐赠收入总额<span>￥
            <animate-number from="1" :key="title_num.dTotalSum" :to="title_num.dTotalSum" duration="3000" easing="easeOutQuad"
              :formatter="formatter"
            ></animate-number>
          </span>元</p>
          <p>捐赠支出总额<span>￥
            <animate-number from="1" :key="title_num.dTotalOut" :to="title_num.dTotalOut" duration="2500" easing="easeOutQuad"
              :formatter="formatter"
            ></animate-number>
          </span>元</p>
          <p>捐赠人数<span>
            <animate-number from="1" :key="title_num.dCount" :to="title_num.dCount" duration="2000" easing="easeOutQuad"
              :formatter="formatter"
            ></animate-number>
          </span>名</p>
        </div>
      </div>
    </div>
    <div class="content">
      <!-- 步骤区域 -->
      <div :class="['steps',flexd?'flexd':'',active===6?'sb_yh':'']">
        <div>
        <div>
          <el-steps direction="vertical" process-status="process" finish-status="process" :active="active" :space="100">
            <el-step  v-for="(item,index) in routerProcess" 
            :key="index+'step'" :title="item.name" 
            :class="(index+1)===active?'activeChange':''" 
            @click.native="goto(item.id)" 
            >
            </el-step>
          </el-steps>
        </div>
      </div>
      </div>
      <!-- 内容区域 -->
          <div class="num1" style="background:#ffff" ref="logoTop1">
            <el-row type="flex" class="row-bg" justify="space-around" >
              <el-col :span="17" style="">
              <div class="col-div" >
                <!-- 第一部分 -->
              <div class="col-div-logo">
                <!-- 头部 -->
                <div class="div-1">
                  <!-- <img src="../../assets/supportus/logo.png" alt="" />
                  <p><b>{{title_list.title}}</b></p> -->
                </div>
                <div class="div-2" id='24'>
                  <p>SUPPORT</p>
                  <p>{{title_list.menuName}}</p>
                  <span>DONATION GUIDE</span>
                  <div></div>
                </div>
              </div>
              <div class="col-div-con">
                <div class="col-1">
                  <!-- :src="link+title_length[0].menuImgUrl" -->
                  <img :src="link+title_length_1.menuImgUrl" alt="" style="object-fit:cover" />
                  <el-button type="warning" @click="meDonateClick">我要捐款</el-button>
                </div>
                <div class="col-2" v-html="title_list.detail">
                </div>
              </div>
            </div>
            </el-col>
            </el-row>
          </div>
          <!-- 第二部分 -->
          <div class="num2"  ref="logoTop2">
            <el-row type="flex" class="row-bg" justify="space-around" >
                <el-col :span="17">
                  <div class="col-div-id" id='25'>
                    <div class="col-div-logo">
                      <div class="div-1">
                        <!-- <img src="../../assets/supportus/logo.png" alt="" />
                        <p><b>在线捐款</b></p> -->
                      </div>
                      <div class="div-2">
                        <p>SUPPORT</p>
                        <p>在线捐款</p>
                        <span>ONLINE DONATION</span>
                        <div></div>
                      </div>
                    </div>
                    <div class="div-id-con">
                        <div class="id-con-1">
                            <div>
                                <p style="visibility:hidden">事实孤儿二维码在线捐款</p>
                                <div class="con-1-1">
                                    <div>
                                        <img :src="link+meDonate.orphanWeixin" width="65%" height="80%" alt="">
                                        <span>支付宝捐赠二维码</span>
                                    </div>
                                    <div>
                                        <img :src="link+meDonate.orphanZhifubao" width="65%" height="80%" alt="">
                                        <span>微信捐赠二维码</span>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <p  style="visibility:hidden">情暖少数民族女孩二维码在线捐款</p>
                                <div class="con-1-2">
                                    <div>
                                        <img :src="link+meDonate.girlWeixin" width="65%" height="80%" alt="">
                                        <span>腾讯公益首页二维码</span>
                                    </div>
                                    <div>
                                        <img :src="link+meDonate.girlZhifubao" width="65%" height="80%" alt="">
                                        <span>支付宝公益首页二维码</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="id-con-2">
                          <!--  -->
                            <img :src="link+title_length_2.menuImgUrl" width="100%" height="47%" alt="">
                            <div class="con-2-xx">
                                <p style="font-size:16px;color:#777778;">第二步:填写捐款人信息</p>
                                <p style="font-size:16px;color:#777778;"> (留下您的信息,获得电子感谢信和实时项目更新报告)</p>
                                <div class="xx-x">
                                  <el-input v-model="jkrXingXiL.duName" placeholder="请输入您的姓名"></el-input>
                                  <el-input v-model="jkrXingXiL.duPhone" placeholder="请输入您的手机号码"></el-input>
                                  <el-input v-model="jkrXingXiL.duEmail" placeholder="E-MALIL"></el-input>
                                  <el-input v-model="jkrXingXiL.duAddress" placeholder="地址"></el-input>
                                </div>
                                <div class="upload">
                                    <label for="">
                                      <span v-text="jkrXingXiL.uploadMsg"></span>
                                        <input type="file" ref='InputValue' name="" @change="Upload" id="file">
                                    </label>
                                </div>
                                <el-row type="flex" class="row-bg" justify="center">
                                    <el-button type="warning" @click="addjkrXingXiLUser">提交信息</el-button>
                                </el-row>
                            </div>
                        </div>
                    </div>
                  </div>
              </el-col>
            </el-row>
          </div>
          <!-- 第三部分 -->
          <div class="num3" ref="logoTop3">
            <el-row type="flex" class="row-bg" justify="space-around" >
              <el-col :span="17">
                <div class="col-div-id-class" >
                  <div class="col-div-logo" >
                    <div class="div-1">
                          <!-- <img src="../../assets/supportus/logo.png" alt="" />
                          <p><b>爱心名录</b></p> -->
                    </div>
                    <div class="inp">
                          <p>请输入查询条件</p>
                          <el-input placeholder="捐款人名称......" v-model="jkrName" class="input-with-select">
                            <el-button slot="append" icon="el-icon-search" @click="donation"></el-button>
                          </el-input>
                          &nbsp;&nbsp;&nbsp;&nbsp;
                          <el-input placeholder="捐款日期 YYYY-MM-DD" v-model="donationList.donationTime" class="input-with-select">
                            <el-button slot="append" icon="el-icon-search" @click="donation"></el-button>
                          </el-input>
                        </div>
                        <!-- <div class="div-el-date-picker">
                          <el-date-picker
                            v-model="donationList.donationTime"
                            type="datetime"
                            format="yyyy 年 MM 月 dd 日"
                            @change="elDatePicker"
                            value-format='yyyy-MM-dd'
                            placeholder="选择日期时间"
                            default-time="12:00:00">
                          </el-date-picker>
                        </div> -->
                    <div class="div-2" id='26'>
                        <p>SUPPORT</p>
                        <p>爱心名录</p>
                        <span>LIST OF LOVE</span>
                        <div></div>
                    </div>
                  </div>
                    <div class="class-tabs">
                        <div>
                            <ul class="ul">
                              <li style="flex: 1">捐款人</li>
                              <li style="flex: 1">捐款金额</li>
                              <li style="flex: 1">捐款用途</li>
                              <li style="flex: 1">捐款时间</li>
                            </ul>
                        </div>
                        <div class="marquee-list-box" >
                          <vueSeamlessScroll :data="tableData" :class-option="classOption" class="seamless-warp">
                          <ul class="marquee-list">
                            <li v-for="(item, index) in tableData" style="display:flex;text-align:center" :key="index">
                              <span class="time">{{item.donationCompany}}</span>
                              <span class="date">{{item.donationMoney}}</span>
                              <span class="num">{{item.donationItem}}</span>
                              <span class="num">{{item.donationTime}}</span>
                            </li>
                          </ul>
                          </vueSeamlessScroll>
                        </div>
                    </div>
                </div>
              </el-col>
            </el-row>
          </div>
          <!-- 第四部分 -->
          <div class="num4" ref="logoTop4">
            <el-row type="flex" class="row-bg" justify="space-around" >
              <el-col :span="17">
                <div class="col-div-id-class-tabs" > 
                    <div class="col-div-logo">
                      <div class="div-1">
                            <!-- <img src="../../assets/supportus/logo.png" alt="" />
                            <p><b>爱心留言</b></p> -->
                      </div>
                      <div class="div-2" id='27'>
                          <p>SUPPORT</p>
                          <p>爱心留言</p>
                          <span>LEAVE A MESSAGE</span>
                          <div></div>
                          <span style="display: inline-block; width: 530px; margin-top: 5px">如有反馈意见可发送至邮箱:</span>
                          <span style="display: inline-block; width: 530px">comments@chengmei.org.cn</span>
                      </div>
                    </div>
                    <div class="tabs-card">
                      <el-card class="box-card" v-for="(item,index) in showMsg" :key="index" >
                          <div @click="dialogFormVisible2Click(item)">
                            <div class="card-1">
                              <i class="iconfont icon-xinxi"></i>
                              <span>{{item.msgUse}}</span>
                            </div>
                            <p >{{item.msgDetail}}</p>
                            <div class="card-2" >
                              <span v-show="item.msgAnswer">
                              成美回答：{{item.msgAnswer}}
                              </span>
                            </div>
                            <div class="card-3"> 
                              <i class="iconfont icon-shijian"></i>
                              发布时间：{{item.msgTime}}
                            </div>
                          </div>
                      </el-card>
                      <div class="bottom" style="width:100%;height:50px;">
                        <i @click="iconLeft" class="iconfont icon-zuo"></i>
                        <i @click="iconRight" class="iconfont icon-liuchengtubiaosheji_huabanfuben"></i>
                        <el-button class="btnner" type="warning" @click="leaveAmessage">我要留言</el-button>
                      </div>
                    </div>
                </div>
              </el-col>
            </el-row>
          </div>
          <!-- 第五部分 -->
          <div class="num5"  ref="logoTop5">
            <el-row type="flex" class="row-bg" justify="space-around" >
              <el-col :span="17">
                <div class="div-id-calss-my">
                    <div class="col-div-logo">
                      <div class="div-1">
                            <!-- <img src="../../assets/supportus/logo.png" alt="" />
                            <p><b>加入我们</b></p> -->
                      </div>
                      <div class="div-2" id='35'>
                          <p>SUPPORT</p>
                          <p>加入我们</p>
                          <span>JOIN US</span>
                          <div></div>
                      </div>
                    </div>
                    <div class="class-input">
                      <h5>成美慈善基金志愿者申请表</h5><br><br>
                      <div class="form">
                        <el-form :model="ruleForm" ref="ruleFormRefs" :rules="formRules" label-width="200px" class="demo-ruleForm">
                          <el-form-item label="姓名" prop="name">
                            <el-input v-model="ruleForm.name"></el-input>
                          </el-form-item>
                          <el-form-item label="性别">
                            <el-select v-model="ruleForm.gender"  style="width:100%;" >
                              <el-option v-for="(item,index) in sex" :key="index" :label="item.value" :value="item.key"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="年龄">
                            <el-select v-model="ruleForm.age" style="width:100%;">
                              <el-option v-for="(item,index) in ages" :key="index" :label="item" :value="item"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="学历">
                            <el-select v-model="ruleForm.edu" style="width:100%;" placeholder="">
                              <el-option label="本科以上" value="1"></el-option>
                              <el-option label="本科" value="2"></el-option>
                              <el-option label="大专" value="3"></el-option>
                              <el-option label="高中" value="4"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="手机号码" prop="phone">
                            <el-input v-model="ruleForm.phone"></el-input>
                          </el-form-item>
                          <el-form-item label="电子邮箱" prop="email">
                            <el-input v-model="ruleForm.email"></el-input>
                          </el-form-item>
                          <el-form-item label="联系地址" prop="address">
                            <el-input v-model="ruleForm.address"></el-input>
                          </el-form-item>
                          <el-form-item label="身份证号码" prop="cardId">
                            <el-input v-model="ruleForm.cardId"></el-input>
                          </el-form-item>
                          <el-form-item label="有否志愿者经验">
                            <el-select v-model="ruleForm.isVolunteer" style="width:100%;" placeholder="">
                              <el-option label="有" value="Y"></el-option>
                              <el-option label="无" value="N"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="有否伤残/病历" prop="case">
                            <el-select v-model="ruleForm.isMayhem" style="width:100%;" placeholder="">
                              <el-option label="有" value="Y"></el-option>
                              <el-option label="无" value="N"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="学校/专业/单位/职业" prop="vocation">
                            <el-input  v-model="ruleForm.vocation"></el-input>
                          </el-form-item>
                          <el-form-item label="自我介绍" prop="introduce">
                            <el-input style="width:100%;" :rows="2" type="textarea" v-model="ruleForm.introduce"></el-input>
                          </el-form-item>
                          <el-form-item label="有兴趣参与的志愿服务">
                            <el-checkbox-group v-model="ruleForm.serviceIds">
                                <el-checkbox v-for="(item,index) in getItem" :key="index" :checked="false" :label="item.sId + ''" name="type">
                                  <p>{{item.sItem}}</p>
                                </el-checkbox>
                            </el-checkbox-group>
                          </el-form-item>
                          <el-form-item label="预计每月服务最少(小时)" prop="minTime">
                            <el-input style="width:100%;"  class="mini" v-model="ruleForm.minTime"></el-input>
                          </el-form-item>
                          <el-form-item label="预计服务期限">
                            <el-select style="width:100%" v-model="ruleForm.estimateTime" placeholder="">
                              <el-option v-for="(item,index) in serviceTime" :key="index" :label="item.value" :value="item.key"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="志愿者誓言">
                            <span>我愿意成为成美慈善基金会一名光荣的志愿者！我承诺认同基金会理念，遵守基金会规章制度，尽己所能，不计报酬，帮助他人，服务社会，践行志愿精神，传播先进文化，为建设团结互助、平等友爱、共同前进的美好社会贡献力量。</span>
                          </el-form-item>
                          <el-form-item>
                            <el-row type="flex" class="row-bg" justify="center">
                                <el-button type="danger" plain @click="querySend">提交信息</el-button>
                            </el-row>
                          </el-form-item>
                        </el-form>
                        </div>
                    </div>
                </div>
              </el-col>
            </el-row>
          </div>
          <!-- 第六部分 -->
          <div class="num6"  ref="logoTop6">
            <el-row type="flex" class="row-bg" justify="space-around" >
              <el-col :span="17">
                <div class="talentrecruitment">
                    <div class="col-div-logo">
                      <div class="div-1">
                            <!-- <img src="../../assets/supportus/logo.png" alt="" />
                            <p><b>人才招聘</b></p> -->
                      </div>
                      <div class="div-2" id="36">
                          <p>SUPPORT</p>
                          <p>人才招聘</p>
                          <span>TALENT RECRUTMENT</span>
                          <div></div>
                      </div>
                    </div>
                    <div class="talen-tabls" >
                      <el-table :row-style="{height:'60px'}"  :data="talentData" ref="MontTabls" style="width:100%; line-height:40px" :highlight-current-row="true" 
                      :stripe="true" :header-cell-style="{background:'#FFA630','text-align':'center','color':'#fff'}" 
                      height="360" @row-click="rowClick"> 
                          <el-table-column prop="positionName" label="招聘岗位"  align="center"></el-table-column>
                          <el-table-column align="center" prop="positionObject" label="招聘对象" ></el-table-column>
                          <el-table-column align="center" prop="positionAddress" label="工作地点" ></el-table-column>
                          <el-table-column align="center" prop="needNum" label="人数" ></el-table-column>
                          <el-table-column align="center" prop="publishTime" label="发布时间" ></el-table-column>
                            <el-table-column align="left" type="expand" >
                                <template slot-scope="scope" >
                                  <div class="expand animated fadeInRight delay-1s" >
                                    <div class="positionDetail" v-html="scope.row.positionDetail"></div>
                                    <div class="expand03">
                                      <!-- <el-button type="warning">申请职位</el-button> -->
                                    </div>
                                  </div>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                    <div id='divId'></div>
                </div>
              </el-col>
            </el-row>
          </div>
    </div>
    <!-- 回到顶部 -->
      <div class="backtop" v-show="active>=2" @click="ActionTop"><i class="iconfont icon-tubiao02-copy"></i></div>
      <el-dialog title="我要留言" :visible.sync="dialogFormVisible" >
        <el-form :model="lidateForm" :rules="FormRules" ref="formRef" label-width="120px" class="demo-dynamic">
          <el-form-item prop="msgUse" label="姓名">
            <el-input v-model="lidateForm.msgUse"></el-input>
          </el-form-item>
          <el-form-item prop="msgPhone" label="电话">
            <el-input v-model="lidateForm.msgPhone"></el-input>
          </el-form-item>
          <el-form-item prop="msgEmail" label="邮箱">
            <el-input v-model="lidateForm.msgEmail"></el-input>
          </el-form-item>
          <el-form-item prop="msgDetail" label="留言内容">
            <el-input v-model="lidateForm.msgDetail" :rows="3" type="textarea"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="resetFieldsFormVisible">取 消</el-button>
          <el-button type="primary" @click="addDialogFormVisible">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="留言信息" :visible.sync="dialogFormVisible2" width="25%" style="top:20%">
        <div class="dialogFormVisible2" v-html="dialogFormVisible2Value">
        </div>
        <br />
        <div v-if="dialogFormVisible2Answer != ''"  class="dialogFormVisible2" v-html="`成美回复：${dialogFormVisible2Answer}`">
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible2 = false">确 定</el-button>
        </div>
      </el-dialog>
  </div>
</template>
<script>
import { addDocutionUser , addJoinUsPost , getService ,
  getShowMsg , getDonationList , getShowRecruits , getBaseInfo , getSex , PostaddDocutionUser ,
  getServiceTime , getSArticleList , getMUNt , getMenuList
} from "@/api/methods";
import { link } from "@/utils/common.ts";
import vueSeamlessScroll from 'vue-seamless-scroll';
import $ from "jquery";
export default {
  components: {
      vueSeamlessScroll
  },
  computed: {
    classOption () {
      return {
        step: 1,
        limitMoveNum: 5,
        hoverStop: true, 
        singleHeight: 0,
         waitTime: 1000,
         openWatch: true,
      }
    }
  },
  data() {
    return {
      dialogFormVisible2Value:"",
      dialogFormVisible2Answer:"",
      dialogFormVisible2:false,
      // 步骤
      routerProcess:[{
        name:"捐款指南",id:"24"
      },{
        name:"在线捐款",id:"25"
      },
      {
        name:"爱心名录",id:"26"
        },
      {
        name:"爱心留言",id:"27"
        },
      {
        name:"加入我们",id:"35"
        },
      {
        name:"人才招聘",id:"36"
      }],
      link,
      animateUp:false,
      // 获取大图图片
      title_img:{
        menuId:23
      },
      // 我要捐款数据
      meDonate:{},
      // 性别
      sex:[],
      // 服务最少期限
      serviceTime:[],
      // 图片div款数据
      title_num:{
        dTotalSum:0,
        dTotalOut:0,
        dCount:0
      },
      // 留言的条数
      total:0,
      // 留言信息
      msg:{
        pageNum:1,
        pageSize:4
      },
      // 留言信息展示
      showMsg:[],
      // 我要留言信息
      lidateForm:{
        msgDetail: "",
        msgEmail: "",
        msgPhone: "",
        msgUse: ""
      },
      // 隐藏留言框
      dialogFormVisible:false,
      ages:[18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65],
      // 解决步骤定位
      flexd:false,
      // 捐款人信息
      jkrXingXiL:{
        duName:"",
        duPhone:"",
        duEmail:"",
        duAddress:"",
        // 表单数据
        file:null ,
        uploadMsg : "+ 点 击 上 传 捐 款 订 单 截 图"
      },
      // 加入我们
      ruleForm:{
        // 姓名
        name:"",
        // 性别
        gender:"",
        // 年龄
        age:18,
        edu:"",
        // 手机号码
        phone:"",
        // 邮箱
        email:"",
        // 联系地址
        address:"",
        // 身份证号码
        cardId:"",
        // 志愿者经验
        isVolunteer:"",
        // 有否伤残/病例
        isMayhem:"",
        // 学校/专业，单位/职业
        vocation:"",
        // 自我介绍
        introduce:"",
        // 志愿服务列表
        serviceIds:[],
        // 每月最少服务时间
        minTime:'',
        // 服务期限
        estimateTime:""
      },
      // 步骤
      active: 1,
      // 查询捐款
      jkrName:'',
      // 捐款数据
      donationList:{
        donationTime:"",
        donationCompany:"",
        pageNum:1,
        pageSize:2500
      },
      // 捐款信息
      tableData:[],
      // 人才招聘
      talentData:[],
      // 验证
      FormRules: {
          msgUse: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
          ],
          msgPhone:[
            { required: true, message: '请输入联系方式(电话)', trigger: 'blur' },
            { min: 11, max: 11, message: '长度在 11 个字符', trigger: 'blur' }
          ],
          msgEmail:[
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ],
          msgDetail:[
            { required: true, message: '请输入正确地址', trigger: 'blur' },
            {min: 4, max: 22, message: '请输入正确的地址', trigger: ['blur', 'change'] }
          ]
      },
      // 兴趣获取列表
      getItem:[],
      // 加入我们表单验证
      formRules:{
        name:[
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        phone:[
          { required: true, message: '请输入联系方式', trigger: 'blur' },
          { min: 2, max: 11, message: '长度在 11 个字符', trigger: 'blur' }
        ],
        email:[
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        address:[
          { required: true, message: '请输入地址', trigger: 'blur' },
          { min: 4,max:25 , message: '请输入正确的地址', trigger: ['blur', 'change'] }
        ],
        cardId:[
          { required: true, message: '请输入身份证号码', trigger: 'blur' },
          {min: 4,max:25 , message: '请输入正确的身份证号码', trigger: ['blur', 'change'] }
        ],
        vocation:[
          { required: true, message: '请输入学校/专业/单位/职业', trigger: 'blur' },
          { min: 4, max:25 , message: '请输入学校/专业/单位/职业', trigger: ['blur', 'change'] }
        ],
        introduce:[
          { required: true, message: '请输入自我介绍', trigger: 'blur' },
          { min: 4, max:25 , message: '请输入自我介绍', trigger: ['blur', 'change'] }
        ],
        minTime:[
          { required: true, message: '请输入预计每月服务最少(小时)', trigger: 'blur' },
          { max:3 , message: '请输入预计每月服务最少(小时)', trigger: ['blur', 'change'] }
        ]
      },
      // 人才招聘
      ruilts:{
        pageNum:1,
        pageSize:200
      },
      // 文章Id-24
      SArticleList_num:{
        key:"",
        menuId:24,
        newStatus:'0',
        pageNum:1,
        pageShow:'1',
        pageSize:10,
        showDetail:1
      },
      // 文章id-24信息
      title_list:{},
      // 头部图片
      titleImg_Dist:{},
      // table滚动
      table_item:"",
      // 清除定时器
      removeItem:null,
      // 获取菜单编号 
      parID:{parentId:23},
      // 使用层级菜单数据
      title_length_1:{},
      title_length_2:{},
      menuId: sessionStorage.getItem('menuId'),
      
    };
  },
  filters:{
      numFilter(value){
        if(!value) return '0.00';
        let intPart = Number(value).toFixed(0); //获取整数部分
        let intPartFormat = intPart.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,'); //将整数部分逢三一断
        return intPartFormat;
      }
  },
  watch:{
     $route:{//深度监听，可监听到对象、数组的变化
         handler(val, oldVal){
             this.goto(val.query.menuId)
         },
         deep:true //true 深度监听
     }
 },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    this.$route.query.menuId ? this.goto(this.$route.query.menuId) : "";
    if(sessionStorage.getItem('menuId')) {
      this.goto(sessionStorage.getItem('menuId'))
    }
  },
  methods: {
    rowClick(row){
      // 点击全部隐藏的内容显示出来
      this.$refs.MontTabls.toggleRowExpansion(row);
    },
    // 留言弹框
    dialogFormVisible2Click(item){
     this.dialogFormVisible2Value = item.msgDetail;
     this.dialogFormVisible2Answer = item.msgAnswer ;
     this.dialogFormVisible2 = true;
    },
    // 图片上传
    Upload(){
      this.jkrXingXiL.file = this.$refs.InputValue.files[0];
      console.log(this.jkrXingXiL.file);
      if(this.jkrXingXiL.file != null){
        this.jkrXingXiL.uploadMsg = "上 传 成 功"
      }
    },
    // 触发当前用户选的时间
    elDatePicker(){
      this.getTabList();
    },
    // 我要捐款按钮
    meDonateClick(){
      document.documentElement.scrollTop = (this.$refs.logoTop2.offsetTop + 500);
    },
    // 大图数字区域
    formatter: function (num) {
        let temp = this.$options.filters['numFilter'];
        return temp(num);
    },
    // 这里是回到顶部
    async ActionTop(){
      this.$refs.supportus.scrollIntoView();
      // $("body,html").animate({scrollTop:top},1000,"swing");
    },
    // 屏幕滚动多利用
    scrrolTop(){
        return new Promise((resolve,reject)=>{
          // 获取屏幕滚动的高度
          let scrollTop =
            window.pageYOffset ||
            document.documentElement.scrollTop ||
            document.body.scrollTop;
            resolve(scrollTop);
        })
    },
    // 侦听屏幕滚动
    async handleScroll() {
      let scrollTop = await this.scrrolTop();
      if(scrollTop >= 280) {
        this.flexd = true;
      }
      if(scrollTop <= 400){
        this.flexd = false;
        this.active = 1;
      }
      if( scrollTop >= (this.$refs.logoTop2.offsetTop + 200)){
        this.active = 2;
      }
      if( scrollTop >= (this.$refs.logoTop3.offsetTop + 200)){
        this.active = 3;
      }
      if( scrollTop >= (this.$refs.logoTop4.offsetTop + 200)){
        this.active = 4;
      }
      if( scrollTop >= (this.$refs.logoTop5.offsetTop + 200)){
        this.active = 5;
      }
      if( scrollTop >= (this.$refs.logoTop6.offsetTop + 200)){
        this.active = 6;
      }
    },
    // 加入我们（完成）
    async querySend(){
      this.$refs.ruleFormRefs.validate( async(ok)=>{
        if(this.ruleForm.serviceIds.length === 0) return this.$message.error("请最少选择一项兴趣活动！");
        if(ok !== true) return this.$message.error("验证未通过,请认真填写！"); 
        if(this.ruleForm.minTime.length === 0) return this.$message.error("认真填写服务时间...");
        this.ruleForm.minTime = parseInt(this.ruleForm.minTime);
        console.log(this.ruleForm);
        let res = await addJoinUsPost(this.ruleForm);
        if(res.code !== 0) return this.$message.error("提交信息失败,请认真填写！");
        this.$message.success("信息提交成功！"); 
        this.$refs.ruleFormRefs.resetFields();
      })
    },
    // 打开留言菜单
    leaveAmessage(){
      this.dialogFormVisible = true;
    },
    // 添加留言功能
    async addDialogFormVisible(){
      this.$refs.formRef.validate((checkout)=>{
        // 输入错误提醒并返回信息
          if(!checkout) return this.$message.error("请输入正确的留言信息!");
          this.PaddDocutionUser();
        // 输入之前执行
          this.dialogFormVisible = false;
      })
    },
    // 我要留言重置
    resetFieldsFormVisible(){
      this.$refs.formRef.resetFields();
      this.dialogFormVisible = false;
    },
    // 留言切换
    iconLeft(){
      if(this.msg.pageNum === 1) return this.$message.success("已是第一页数据!");
      this.msg.pageNum--;
      this.getShowMsgList();
    },
    // 留言切换
    iconRight(){
      let num = Math.ceil(this.total / this.msg.pageSize);
      if(this.msg.pageNum === num) return this.$message.success("已是最后一页数据!");
      this.msg.pageNum++;
      this.getShowMsgList();
    },
    // 捐款人信息(已完)
    async addjkrXingXiLUser(){
      let formData = new FormData();
      formData.append("duName",this.jkrXingXiL.duName);
      formData.append("duPhone",this.jkrXingXiL.duPhone);
      formData.append("duEmail",this.jkrXingXiL.duEmail);
      formData.append("duAddress",this.jkrXingXiL.duAddress);
      formData.append("file",this.jkrXingXiL.file);
      if(this.jkrXingXiL.file === null) return  this.$message.error("请发送捐款截图！");
      let res = await addDocutionUser(formData);
      if(res.code !== 0) return this.$message.error("提交信息失败,请认真填写！");
      this.jkrXingXiL.duName = "";
      this.jkrXingXiL.duPhone = "";
      this.jkrXingXiL.duEmail = "";
      this.jkrXingXiL.duAddress = "";
      this.jkrXingXiL.file = null;
      this.jkrXingXiL.uploadMsg = "+ 点 击 上 传 捐 款 订 单 截 图";
      this.$message.success("信息提交成功！");
    },
    // 获取加入我们兴趣列表
    async getServiceItem(){
      let res = await getService();
      if(res.code !== 0) return this.$message.error("兴趣列表获取失败");
      this.getItem = res.data;
    },
    // 获取留言信息展示
    async getShowMsgList(){
      let res = await getShowMsg(this.msg);
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      this.showMsg = res.rows;
      this.total = res.total;
    },
    // 获取捐款人信息
    async getTabList(){
      let res = await getDonationList(this.donationList);
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      console.log(res);
      this.tableData = res.rows;
    },
    // 搜索捐款
    donation(){
      this.donationList.donationCompany = this.jkrName;
      this.getTabList();
    },
    // 人才招聘请求
    async getRuilts(){
      let res = await getShowRecruits(this.ruilts);
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      this.talentData = res.rows;
    },
    // 基础设置
    async getTitelInfo (){
      let res = await getBaseInfo();
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      this.meDonate = res.data.qrcode;
      this.title_num = res.data.donateMoeny;
    },
    // 性别
    async getSexALL(){
      let res = await getSex();
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      this.sex = res.data;
    },
    // 服务最少期限
    async getService(){
      let res = await getServiceTime();
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      this.serviceTime = res.data;
    },
    // 获取文章接口
    async getSarticle(){
      let res = await getSArticleList(this.SArticleList_num);
      if(res.code !== 0) return this.$message.error("数据获取失败，请检查网络！");
      this.title_list = res.rows[0];
    },
    // 获取大图片
    async getTitleImg(){
      let res = await getMUNt(this.title_img);
      if(res.code !== 0 ) return this.$message.error("数据获取失败，请检查网络！");
      this.titleImg_Dist = res.data;
    },
    // 留言添加接口
    async PaddDocutionUser(){
      let res = await PostaddDocutionUser(this.lidateForm);
      if(res.code !== 0 ) return this.$message.error("留言添加失败，请信息是否正确！");
      this.$message.success("留言添加成功");
      this.getShowMsgList();
    },
    // 菜单获取接口
    async getMenuListId(){
      let res = await getMenuList(this.parID);
      if(res.code !== 0 ) return this.$message.error("留言添加失败，请信息是否正确！");
      this.title_length_1 = res.data[0];
      this.title_length_2 = res.data[1];
    },
    goto(id) {
      setTimeout(()=>{
        // 获取id然后跳转
        document.getElementById(id).scrollIntoView()
      }, 1000)
    }
  },
  created(){
    this.getServiceItem();
    this.getShowMsgList();
    this.getTabList();
    this.getRuilts();
    this.getTitelInfo();
    this.getSexALL();
    this.getService();
    this.getSarticle();
    this.getTitleImg();
    this.getMenuListId();
  }
};
</script>
<style lang='scss' scoped>
.div-el-date-picker{
  padding: 5px;
  position: absolute;
  left: 520px;
  top: 102px;
  
}
  .activeChange ::v-deep .el-step__icon.is-text{
    color: #ffa631 !important;
  }
  .activeChange ::v-deep .el-step__title.is-process{
    color: #ffa631 !important;
    font-weight: bold !important;
  }
  .activeChange ::v-deep .el-step__icon.is-text{
    border: 1px solid #ffa631;
  }
  ::v-deep .el-step__title.is-process{
    font-weight:bold !important;
  }
  ::v-deep .el-step__main{
    font-weight:bold !important;
  }
  ::v-deep .el-step__title.is-process,::v-deep .is-wait{
    color: #999;
    font-weight: bold !important; 
  }
.marquee-list-box{
  height: 500px;overflow:hidden;overflow-y: scroll;-webkit-overflow-y: scroll;-webkit-overflow:hidden;
  will-change: transform;
}
.seamless-warp{
  height: 500px;
}
.ul{
  margin-top: 30px;
  padding: 8px 0px;
  display:flex;
  text-align:center; 
  line-height: 32px;
  background: rgb(255, 166, 48);
  text-align: center;
  color: rgb(255, 255, 255);
}
.marquee-list {
    li {
      // height: 100%;
      height: 50px;
      line-height: 50px;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
      padding: 0 20px;
      list-style: none;
      text-align: center;
      font-size: 16px;
      font-weight: 400;
      background: #F5F5F5;
      .time, .date, .num{flex: 1;}
      span{
        text-align: center;
      }
    }
    // 2n+1 获取双数数据
    li:nth-child(2n+1){
     background:url("../../assets/supportus/210608034807.jpg") no-repeat;
    }
  }
  .animate-up { 
    transition: all 0.5s ease-in-out;
    transform: translateY(-50px);
  }
::v-deep .el-table__body-wrapper::-webkit-scrollbar{
  width: 10px;
  background: #fff;
}
::v-deep .el-table__body-wrapper::-webkit-scrollbar-thumb{
  width: 6px;
  background: #ffa631;
  border-radius: 5px;
}
::v-deep .el-table__body-wrapper{
  cursor: pointer;
}
::v-deep .el-checkbox-group{
  display: flex;
  flex-direction: column;
}
.box-card:hover{
  cursor: pointer;
  border-bottom: 1px solid #f9780e;
}
::v-deep .el-table--striped .el-table__body tr.el-table__row--striped td{
  background: #F5F5F5 !important;
}
::v-deep .el-table__row:nth-child(2n+1){
  background:url("../../assets/supportus/210608034807.jpg") no-repeat !important;
}
.demo-dynamic{
  margin-right: 40px;
}
::v-deep .el-step__title.is-process{
  color: #999;
}
.btnner{
  background: #ffa613;
}
::v-deep .el-step__title.is-wait{
  color: #999;
}
// 修改人才招聘的padding
::v-deep .el-table__expanded-cell[class*=cell]{
  padding: 20px !important;
}
// 回到顶部的
.backtop{
  width: 80px;
  height: 80px;
  position: fixed;
  border-radius:50% ;
  right: 5%;
  bottom: 20%;
  box-shadow: 1px 1px #ccc , 1px 1px #ccc;
  text-align: center;
  i{
    line-height: 80px;
    font-size: 40px;
    color: #ffa613;
  }
}
// 选中的
::v-deep .el-step__head.is-finish{
  color: #ffa613;
  border-color: #ffa613 !important;
}
// 未选中的
::v-deep .el-step__head.is-process{
  color: #999;
  border-color: #999 !important;
  font-weight: bold;
}
// 未选中的线条颜色
::v-deep .el-step__line{
  background: #999;
}
::v-deep .el-step__title.is-process{
  font-weight: bold;
}
::v-deep .el-step__head.is-wait{
  color: #999;
}
::v-deep .el-step__icon.is-text{
      border: 2px solid;
}
::v-deep .el-step__title.is-finish{
  color: #ffa613;
  font-weight: bold;
  font-size: 18px;
}

// .el-dialog {
//   width: 70%  !important;
// }
::v-deep .el-card__header{
  border-bottom:0px !important ;
}
::v-deep .el-table td , .el-table th{
  text-align: center !important;
}
::v-deep .el-checkbox__input.is-checked+.el-checkbox__label{
  color: #ffa631 ;
}
::v-deep .el-checkbox__input.is-checked .el-checkbox__inner, .el-checkbox__input.is-indeterminate .el-checkbox__inner{
  background-color: #ffa631 ;
  border-color: #fffcf9 ;
}

::v-deep .el-button--danger.is-plain{
    color: white;
    background: #ffa631;
    border-color: #ffa631;
}
// 最外的
.Support-us {
  width: 100%;
  height: 100%;
  min-width: 1300px;
  box-sizing: border-box;
  background: #ffff;
  height:100%;		//核心
  overflow-x:hidden ;
}
// 屏幕滚动到哪里显示的位置
.flexd{
  position: fixed !important;
  left: 6%;
  top: 20% ;
  cursor: pointer;
}
.sb_yh{
  position: fixed !important;
  top: -10% !important;
}
// 头部导航
.header {
  position: fixed;
  z-index: 99;
  top: 0;
  left: 0;
  right: 0;
}
// 中间大图
.av_img {
  margin-top: 95px;
  position: relative;
  .img_tohash{
    position: absolute;
    right: 10%;
    top: 15%;
    width: 25%;
    height: 70%;
    z-index: 1;
    h1{
      height: 20%;
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 1.5em;
      color: #ff4700;
      background:rgba( #ffff,0.8);
    }
    div{
      display: flex;
      height: 80%;
      width: 100%;
      justify-content: space-around;
      flex-direction: column;
      box-sizing: border-box;
      padding: 10px;
      background: rgba(#e9f6f9,0.5);
      p{
        text-align: center;
        font-size: 20px;
        span{
          color:#f9780e ;
          margin-right: 5px;
        }
      }
      p:nth-child(3){
        span{
          margin-left: 5px;
        }
      }
    }
  }
}
// 整体内容
.content {
  position: relative;
  margin-top: 60px;
  .steps {
    position: absolute;
    left: 6%;
    z-index: 990;
    cursor: pointer;
  }
}
// 第一部分样式
.num1{
  width: 100%;
  display: flex;
  height: 700px;
  justify-content: center;
  .el-col {
  .col-div {
    width: 100%;
    .col-div-con {
      display: flex;
      height: 500px;
      margin-top: 30px;
      .col-1 {
        margin-top: 3px;
        flex: 1.3;
        background: #fff;
        margin-right: 20px;
        img {
          width: 100%;
          height: 82%;
        }
        .el-button {
          margin-top: 20px;
          background: #ffa631;
        }
      }
      .col-2 {
        width: 100%;
        flex: 1.5;
        background: #fafafc;
        overflow: scroll;
        overflow-x: hidden;
        padding: 5px 10px;
        font-size: 14px;
        color: #666666;
        padding: 20px;
        padding-bottom: 10px;
        p{
            margin: 5px;
        }
        p:nth-child(5){
            margin: 30px 5px;
        }
      }
      .col-2::-webkit-scrollbar{
        width: 6px !important;
        background: #fff;
      }
      .col-2::-webkit-scrollbar-thumb{
        width: 6px;
        background: #ffa631;
        border-radius: 5px;
      }
    }
  }
}
}
.num2{
  width: 100%;
  background: #FAFAFC;
  // 第二部分样式
  .col-div-id {
      width: 100%;
      padding: 0px;
      margin-top: 30px;
      padding-bottom: 60px;
      .div-id-con{
          /*height: 37.375rem;*/
          width: 100%;
          box-sizing: border-box;
          display: flex;
          .id-con-1{
              flex: 1.3;
              padding: 10px;
              display: flex;
              flex-direction: column;
              div:nth-child(1){
                  flex: 1;
                  .con-1-1{
                      display: flex;
                      div{
                          display: flex;
                          width: 50%;
                          flex-direction: column;
                          justify-content: center;
                          align-items: center;
                          span{
                              margin-top: 5px;
                              color: #725F6C;
                          }
                      }
                  }
                  p{
                    color: #594F4C;
                    margin: 15px 0px;
                  }
              }
              div:nth-child(2){
                  flex: 1;
                  p{
                      margin: 15px;
                      color: #594F4C;
                  }
                  .con-1-2{
                      display: flex;
                      div{
                          display: flex;
                          width: 50%;
                          flex-direction: column;
                          justify-content: center;
                          align-items: center;
                          span{
                              color: #725F6C;
                              margin-top: 5px;
                          }
                      }
                  }
              }
          }
          .id-con-2{
              flex: 1.5;
              padding: 10px;
              margin-top: 2%;
              height: 80%;
              .con-2-xx{
                  background: #ffff;
                  margin-top: 5%;
                  padding: 10px;
                  p{
                      margin: 5px;
                  }
                  p:nth-child(2){
                      margin-left: 5px;
                  }
                  .xx-x{
                      display: flex;
                      justify-content: space-around;
                      flex-wrap: wrap;
                      .el-input{
                          width: 40%;
                          margin: 18px;
                      }
                      .el-input__inner{
                          height: 36px !important;
                      }
                  }
                  .upload{
                    width: 40%;
                    height: 29px;
                    line-height: 29px;
                    border-radius: 5px;
                    // background: #ccc;
                    margin:  0 auto;
                    text-align: center;
                    overflow: hidden;
                    cursor: pointer;
                    font-size: 13px;
                    color: #ffa631;
                    #file {
                        width:180px;
                        height: 29px;
                        opacity: 0;
                        margin-top: 5px;
                        margin-left: -170px;
                        overflow: hidden;
                        position: absolute;
                        cursor: pointer;
                    }
                  }
                   .el-button{
                      margin-top: 10px;
                      width: 90%;
                      background: #ffa613;
                  }
              }
          }
      }
  }
}
.num3{
    width: 100%;
    background: #ffff;
    height: 800px;
    // 第三部分样式
    .col-div-id-class{
      margin-top: 50px;
      height: 200px;
      width: 100%;
      .class-tabs{
        height: 500px;
      }
      .el-input{
        width: 100%;
      }
    }
}
// tabs样式第四部分样式
.num4{
  background: #FAFAFC;
  .col-div-id-class-tabs{
  margin-top: 30px;
  padding-bottom: 40px;
  .tabs-card{
    margin-top: 20px;
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    .el-card{
      width: 45%;
      padding: 10px;
      margin: 15px 10px;
    }
    .bottom{
      display: flex;
      justify-content: flex-end;
      padding: 10px;
      align-items: center;
      i{
        font-size: 40px;
        margin-right:20px ;
        color: #ccc;
        cursor: pointer;
      }
      i:hover {
        color: #ffa631;
      }
    }
    .box-card{
      animation: bounce; /* referring directly to the animation's @keyframe declaration */
      animation-duration: 1s; /* don't forget to set a duration! */
      .card-1{
        margin-bottom:40px;
        i{
          font-size:20px;
          margin-right:20px;
        }
      }
      p{
        width: 100%;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: hidden;
        margin:10px 0px 40px; color:#C1C1C1; font-size:14px;
      }
      .card-2{
        width: 100%;
        margin:20px 0px; 
        color:#9C9C9C; 
        font-size:14px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: hidden;
      }
      .card-3{
        margin:15px 0px 5px; 
        float:right;
        color:#B6C6D9;
        font-size:14px;
      }
    }
  }
}
}
// 第五部分样式
.num5{
  background: #fff;
.div-id-calss-my{
  width: 100%;
  margin-top: 30px;
  .class-input{
    padding: 20px;
    width: 100%;
    padding-left: 20px;
    border: 20px solid #FAFAFC;
    h5{
      text-align: center;
      font-weight: 500;
      font-size: 18px;
    }
    el-input{
      width: 40%;
    }
    .form{
      width: 80%;
      .el-button{
        background: #ffa613;
      }
    }
  }
}
}
  .el-table{
    margin-top: 30px;
  }
// 第六部分样式代码
.num6{
  margin-top: 80px;
  background: #FAFAFC;
  padding: 40px;
  width: 100%;
  padding-bottom: 70px;
.talen-tabls{
  width: 100%;
  padding: 20px;
  background: #ffff;
  .expand{
    width: 100%;
    text-align: left;
    background: #FAFAFC;
    padding: 0px 30px;
    font-size: 12px;
    .positionDetail{
      text-align: left !important;
    }
    .expand03{
      margin-top: 29px;
      .el-button{
        background: #ffa631;
        margin-top: 28px;
        margin-bottom: 10px;
        height: 35px;
        line-height: 5px;
      }
    } 
    span{
      margin-right: 5px;
    }
  }
}
}

// 公共样式 爱心logo
.col-div-logo {
  display: flex;
  justify-content: space-between;
  position: relative;
  .div-1 {
    flex: 7;
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 10px;
    p {
      margin-left: 15px;
      font-size: 25px;
      font-weight: 530;
      margin-top: .3em;
    }
  }
  .div-2 {
    flex: 2.5;
    max-width: 280px;
    min-width: 260px;
    p:nth-child(1) {
      font-size: 60px;
      text-align: right;
      margin-top: -10px;
      color: #e2e2e2;
    }
    p:nth-child(2) {
      font-size: 38px;
      margin-left:6px;
      margin-top: -20px;
      font-weight: 540;
      color: #666666;
    }
    span {
      font-size: 25px;
      color: #d1d1d1;
      margin-left:6px;
    }
    div {
      width: 29%;
      height: 5px;
      margin-top: 8px;
      background: #ffa631;
      margin-left:7px;
    }
  }
}
.inp{
  position: absolute;
  bottom: 0px;
  width: 60%;
  display: flex;
  min-width: 500px;
  p{
    width: 40%;
    text-align: left;
    line-height: 40px;
  }
}
::v-deep .num3 .col-div-id-class .el-input{
  width:80%;
}
</style>
