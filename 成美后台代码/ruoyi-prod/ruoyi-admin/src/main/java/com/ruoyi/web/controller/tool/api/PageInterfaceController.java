package com.ruoyi.web.controller.tool.api;

import com.ruoyi.cm.domain.*;
import com.ruoyi.cm.service.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.web.controller.tool.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 网页接口
 */
@Api("网页接口")
@RestController
@RequestMapping("/api/page")
@CrossOrigin
public class PageInterfaceController extends BaseController {
    @Autowired
    private ICmBannerConfigService cmBannerConfigService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private ICmMenuService cmMenuService;
    @Autowired
    private ICmDonationService cmDonationService;
    @Autowired
    private ICmBigEventService cmBigEventService;
    @Autowired
    private ICmArticleService cmArticleService;
    @Autowired
    private ICmMsgManageService cmMsgManageService;
    @Autowired
    private ICmRecruitService cmRecruitService;
    @Autowired
    private ICmDonationUserService cmDonationUserService;
    @Autowired
    private ICmJoinUsService cmJoinUsService;
    @Autowired
    private DictService dictService;
    @Autowired
    private ICmServiceService iCmServiceService;
    @Autowired
    private ICmBaseInfoService cmBaseInfoService;
    @Autowired
    private ICmFileInfoService cmFileInfoService;
    @Autowired
    private ICmVisitsService cmVisitsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IEmailService iEmailService;

    @ApiOperation("获取基础配置")
    @GetMapping("/baseInfo")
    public AjaxResult baseInfo() {
        List<CmBaseInfo> cmBaseInfos = cmBaseInfoService.selectCmBaseInfoList(null);
        if (cmBaseInfos != null && cmBaseInfos.size() > 0) {
            CmBaseInfo cmBaseInfo = cmBaseInfos.get(0);

            //基础信息
            BaseInfoVo baseInfoVo = new BaseInfoVo();
            BaseInfo baseInfo = new BaseInfo();
            BeanUtils.copyProperties(cmBaseInfo, baseInfo);

            if (cmBaseInfo.getLogo() != null) {
                CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(cmBaseInfo.getLogo());
                if (cmFileInfo != null) {
                    baseInfo.setLogo(cmFileInfo.getFileUrl());
                }
            }
            baseInfoVo.setBaseInfo(baseInfo);

            // 捐款信息
            DonateMoney d = new DonateMoney();
            BeanUtils.copyProperties(cmBaseInfo, d);
            baseInfoVo.setDonateMoeny(d);

            //二维码
            QRCode qrCode = new QRCode();
            Long wxImg = cmBaseInfo.getWxImg();
            if (wxImg != null) {
                CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(wxImg);
                if (cmFileInfo != null) {
                    qrCode.setWxImg(cmFileInfo.getFileUrl());
                }
            }

            Long orphanWeixin = cmBaseInfo.getOrphanWeixin();
            if (orphanWeixin != null) {
                CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(orphanWeixin);
                if (cmFileInfo != null) {
                    qrCode.setOrphanWeixin(cmFileInfo.getFileUrl());
                }
            }

            Long orphanZhifubao = cmBaseInfo.getOrphanZhifubao();
            if (orphanZhifubao != null) {
                CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(orphanZhifubao);
                if (cmFileInfo != null) {
                    qrCode.setOrphanZhifubao(cmFileInfo.getFileUrl());
                }
            }
            Long girlWeixin = cmBaseInfo.getGirlWeixin();
            if (girlWeixin != null) {
                CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(girlWeixin);
                if (cmFileInfo != null) {
                    qrCode.setGirlWeixin(cmFileInfo.getFileUrl());
                }
            }

            Long girlZhifubao = cmBaseInfo.getGirlZhifubao();
            if (girlZhifubao != null) {
                CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(girlZhifubao);
                if (cmFileInfo != null) {
                    qrCode.setGirlZhifubao(cmFileInfo.getFileUrl());
                }
            }
            baseInfoVo.setQRcode(qrCode);

            return AjaxResult.success(baseInfoVo);
        }
        return AjaxResult.error("基础信息配置有误！");
    }

    @ApiOperation("获取Banner列表")
    @GetMapping("/bannerList")
    @ApiImplicitParam(name = "clientType", value = "客户端类型，1:手机端，0:PC端",
            required = false, dataType = "Long", defaultValue = "0", paramType = "query")
    public AjaxResult bannerList(@RequestParam(required = false, defaultValue = "0") Long clientType) {
        BannerVo vo = new BannerVo();
        CmBannerConfig config = new CmBannerConfig();
        config.setClientType(clientType);
        List<CmBannerConfig> cmBannerConfigs = cmBannerConfigService.selectCmBannerConfigList(config);
        List<SysDictData> typeList = dictService.getType("cm_banner_type");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (SysDictData s : typeList) {
            String type = s.getDictValue();
            List<String> list = new ArrayList<>();
            for (CmBannerConfig c : cmBannerConfigs) {
                if (type.equals(c.getTypeId().toString())) {
                    list.add(c.getImageFile().getFileUrl());
                }
            }
            map.put(type, list);
        }
        vo.setImgList(map);
        String loopSpeed = sysConfigService.selectConfigByKey("loop_speed");
        vo.setLoopSpeed(Integer.valueOf(StringUtils.isEmpty(loopSpeed) ? "3000" : loopSpeed));
        return AjaxResult.success(vo);
    }

    @ApiOperation("菜单获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "parentId", value = "父菜单ID",
            required = false, dataType = "Long", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam( name = "languageType", value = "语言：0中文，1英文",
                    required = false, dataType = "String", defaultValue = "0", paramType = "query")
        }
    )
    @GetMapping("/menuList")
    public AjaxResult menuList(@RequestParam(name = "parentId",defaultValue = "0") Long parentId,
                               @RequestParam(name = "languageType",defaultValue = "0")  String languageType) {
        List<MenuVo> menuList = new ArrayList<>();
        List<CmMenu> list = cmMenuService.selectCmMenuByParentId(parentId, languageType);
        menuList = getMenuList(list, true, languageType);
        return AjaxResult.success(menuList);
    }

    @ApiOperation("菜单详情接口")
    @ApiImplicitParam(name = "menuId", value = "菜单ID",
            required = true, dataType = "Long", paramType = "query")
    @GetMapping("/menu")
    public AjaxResult menu(Long menuId) {
        CmMenu x = cmMenuService.selectCmMenuById(menuId);

        MenuVo vo = new MenuVo();
        vo.setMenuId(x.getMenuId());
        if (x.getImageFile() != null) {
            vo.setMenuImgUrl(x.getImageFile().getFileUrl());
        } else {
            vo.setMenuImgUrl("");
        }
        vo.setMenuModel(x.getMenuModel());
        vo.setMenuName(x.getMenuName());
        vo.setUrl(x.getUrl());
        return AjaxResult.success(vo);
    }

    private List<MenuVo> getMenuList(List<CmMenu> list, boolean type, String languageType) {
        List<MenuVo> menuList = new ArrayList<>();
        list.stream().forEach(x -> {
            MenuVo vo = new MenuVo();
            vo.setMenuId(x.getMenuId());
            if (x.getImageFile() != null) {
                vo.setMenuImgUrl(x.getImageFile().getFileUrl());
            } else {
                vo.setMenuImgUrl("");
            }
            vo.setMenuModel(x.getMenuModel());
            vo.setMenuName(x.getMenuName());
            vo.setUrl(x.getUrl());
            if (true) {
                vo.setChildren(getMenuList(cmMenuService.selectCmMenuByParentId(vo.getMenuId(), languageType), false, languageType));
            }
            menuList.add(vo);

        });
        return menuList;
    }


//    @ApiOperation("捐款金额查询接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "donationStart", value = "捐赠开始日期（yyyy-MM-dd）",
//                    required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "donationEnd", value = "捐赠结束日期（yyyy-MM-dd）",
//                    required = false, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "donationCompany", value = "捐赠企业",
//                    required = false, dataType = "String", paramType = "query")
//    })
//    @GetMapping("/donationMoney")
//    public AjaxResult donationMoney(String donationStart, String donationEnd, String donationCompany) {
//
//        BigDecimal money = cmDonationService.sumMoney(donationStart, donationEnd, donationCompany);
//        if (money == null) {
//            money = BigDecimal.valueOf(0);
//        }
//        return AjaxResult.success(money.setScale(2).doubleValue()); // 保留两位小数
//    }

    @ApiOperation("捐款记录查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "donationCompany", value = "捐赠企业",
                    required = false, dataType = "String", defaultValue = "", paramType = "query"),
            @ApiImplicitParam(name = "donationTime", value = "捐赠日期", defaultValue = "",
                    required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", defaultValue = "10",
                    required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页次", defaultValue = "1",
                    required = false, dataType = "Integer", paramType = "query")

    })
    @GetMapping("/donationList")
    public TableDataInfo donationList(String donationCompany, String donationTime, Integer pageSize, Integer pageNum) {
        startPage();  // 此方法配合前端完成自动分页
        CmDonation dto = new CmDonation();
        if (donationCompany != "") {
            dto.setDonationCompany(donationCompany);
        }
        if (donationTime != "" && donationTime != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dto.setDonationTime(df.parse(donationTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<CmDonation> list = cmDonationService.selectCmDonationList(dto);
        return getDataTable(list);
    }


    @ApiOperation("大事件查询接口")
    @GetMapping("/bigEvent")
    public AjaxResult bigEvent(@RequestParam(name = "pubLang", defaultValue = "CN")  String pubLang) {
        CmBigEvent cmBigEvent = new CmBigEvent();
        Map<String, Object> params = new HashMap<>();
        params.put("showDetail", 1); // 查询的时候直接将详情数据查询处理
        params.put("pubLang", pubLang);
        cmBigEvent.setParams(params);
        List<CmBigEvent> cmBigEvents = cmBigEventService.selectCmBigEventList(cmBigEvent);
        List<BigEventVo> retList = cmBigEvents.stream().map(x -> {
            BigEventVo vo = new BigEventVo();
            BeanUtils.copyProperties(x, vo);
            if (x.getBgImageFile() != null) {
                vo.setBgImageUrl(x.getBgImageFile().getFileUrl());
            }
            return vo;
        }).collect(Collectors.toList());
        return AjaxResult.success(retList);
    }


    // 最新文章获取接口
    @ApiOperation("最新文章获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "菜单ID",
                    required = true, dataType = "String", paramType = "query"),

            @ApiImplicitParam(name = "showDetail", value = "结果是否包含文章详情:0 不包含 1 包含",
                    required = false, dataType = "String", paramType = "query", defaultValue = "0"),


    })
    @GetMapping("/newArticleList")
    public TableDataInfo newArticleList(String menuId, String showDetail) {
        CmArticle cmArticle = new CmArticle();
        cmArticle.setMenuId(Long.valueOf(menuId));
        cmArticle.setStatus("0"); // 正常状态
        Map<String, Object> map = new HashMap<>();
        map.put("showDetail", showDetail);
        cmArticle.setParams(map);
        // 获取最新的3条记录
        int limitNum = 2;
        List<CmArticle> newList = cmArticleService.selectCmArticleListByLimitNum(cmArticle, limitNum);
        List<ArticleVo> list = newList.stream().map(x -> {
            ArticleVo vo = new ArticleVo();
            BeanUtils.copyProperties(x, vo);
            if (x.getCmMenu() != null) {
                vo.setMenuName(x.getCmMenu().getMenuName());
            }

            if (x.getImageFile() != null) {
                vo.setBgImageUrl(x.getImageFile().getFileUrl());
            }
            return vo;
        }).collect(Collectors.toList());
        return getDataTable(list);
    }

    // 文章获取接口
    @ApiOperation("文章获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "菜单ID",
                    required = true, dataType = "String", paramType = "query"),

            @ApiImplicitParam(name = "key", value = "搜索关键字",
                    required = false, dataType = "String", paramType = "query"),

            @ApiImplicitParam(name = "newStatus", value = "页面是否存在最新动态（0 不存在，1 存在）",
                    required = false, dataType = "String", paramType = "query", defaultValue = "0"),

            @ApiImplicitParam(name = "showDetail", value = "结果是否包含文章详情:0 不包含 1 包含",
                    required = false, dataType = "String", paramType = "query", defaultValue = "0"),

            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", defaultValue = "10",
                    required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页次", defaultValue = "1",
                    required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageShow", value = "是否是在首页展示 0是 1否", defaultValue = "1",
                    required = false, dataType = "String", paramType = "query"),

    })
    @GetMapping("/articleList")
    public TableDataInfo articleList(String menuId, String key, String newStatus, String showDetail, Integer pageSize, Integer pageNum, String pageShow) {
        CmArticle cmArticle = new CmArticle();
        cmArticle.setMenuId(Long.valueOf(menuId));
        if ("0".equals(pageShow)) {
            cmArticle.setPageShow(pageShow); //首页展示的文章获取
        }
        cmArticle.setStatus("0"); // 正常状态
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("showDetail", showDetail);
        cmArticle.setParams(map);
        List<CmArticle> newList = null;
        if ("1".equals(newStatus)) {
            // 获取最新的3条记录
            int limitNum = 2;
            newList = cmArticleService.selectCmArticleListByLimitNum(cmArticle, limitNum);
            List<Long> ids = newList.stream().map(x -> x.getArticleId()).collect(Collectors.toList());
            map.put("newIds", ids);
        }


        startPage();  // 此方法配合前端完成自动分页
        List<CmArticle> cmArticles = cmArticleService.selectCmArticleList(cmArticle);
        TableDataInfo dataTable = getDataTable(cmArticles);
        long total = dataTable.getTotal(); // 获取总数居

        List<ArticleVo> list = cmArticles.stream().map(x -> {
            ArticleVo vo = new ArticleVo();
            BeanUtils.copyProperties(x, vo);
            vo.setMenuName(x.getCmMenu().getMenuName());
            if (x.getImageFile() != null) {
                vo.setBgImageUrl(x.getImageFile().getFileUrl());
            }
            return vo;
        }).collect(Collectors.toList());
        TableDataInfo retData = getDataTable(list);
        retData.setTotal(total);
        return retData;
    }

    // 文章详情接口
    @ApiOperation("文章详情接口")
    @ApiImplicitParam(name = "articleId", value = "文章ID",
            required = true, dataType = "String", paramType = "query")
    @GetMapping("/articleDetail")
    public AjaxResult articleDetail(String articleId) {

        // 从缓存中获取
        Object json = redisTemplate.opsForValue().get("intf:articleDetail:" + articleId);
        if (json != null) {
            // 缓存存在
            AjaxResult success = AjaxResult.success(json.toString());
            return success;
        }

        CmArticle cmArticle = cmArticleService.selectCmArticleById(Long.valueOf(articleId));
        if (cmArticle != null) {
            // 存入缓存中
            AjaxResult success = AjaxResult.success(cmArticle.getDetail());
            // 30天过期
            redisTemplate.opsForValue().set("intf:articleDetail:" + articleId, cmArticle.getDetail(), 30, TimeUnit.DAYS);
            return success;
        }
        return AjaxResult.error("文章内容不存在！");
    }


    // 文章访问量添加接口
    @ApiOperation("文章访问量添加")
    @ApiImplicitParam(name = "articleId", value = "文章ID",
            required = true, dataType = "String", paramType = "query")
    @GetMapping("/addReadNum")
    public AjaxResult addReadNum(String articleId) {
        cmArticleService.addReadNum(Long.valueOf(articleId));
        return AjaxResult.success();
    }

    // 留言展示接口
    @ApiOperation("留言展示接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", defaultValue = "10",
                    required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页次", defaultValue = "1",
                    required = false, dataType = "Integer", paramType = "query")
    })
    @GetMapping("/showMsg")
    public TableDataInfo showMsg(Integer pageSize, Integer pageNum) {
        startPage();  // 此方法配合前端完成自动分页
        CmMsgManage dto = new CmMsgManage();
        List<CmMsgManage> list = cmMsgManageService.selectCmMsgManageList(dto);
        return getDataTable(list);
    }

    // 人才招聘接口
    @ApiOperation("人才招聘列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", defaultValue = "10",
                    required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页次", defaultValue = "1",
                    required = false, dataType = "Integer", paramType = "query")
    })
    @GetMapping("/showRecruits")
    public TableDataInfo showRecruits(Integer pageSize, Integer pageNum) {
        startPage();  // 此方法配合前端完成自动分页
        CmRecruit dto = new CmRecruit();
        List<CmRecruit> list = cmRecruitService.selectCmRecruitList(dto);
        list.stream().forEach(x -> {
            if (StringUtils.isNotEmpty(x.getEdu())) {
                x.setEduStr(dictService.getLabel("cm_education", x.getEdu()));
            }else{
                x.setEduStr("不限");
            }
        });
        return getDataTable(list);
    }


    //访问量列表接口
    @ApiOperation("访问量接口添加")
    @ApiImplicitParam(name = "mName", value = "菜单名称",
            required = true, dataType = "String", defaultValue = "首页", paramType = "query")
    @GetMapping("/addVisits")
    public AjaxResult addVisits(String mName) {
        CmVisits cmVisits = new CmVisits();
        cmVisits.setmName(mName);
        cmVisitsService.insertCmVisits(cmVisits);
        return AjaxResult.success();
    }


    // 性别获取接口
    @ApiOperation("性别获取接口")
    @GetMapping("/getSex")
    public AjaxResult getSex() {
        return AjaxResult.success(toVo(dictService.getType("sys_user_sex")));
    }

    private List<DictVo> toVo(List<SysDictData> dictData) {
        return dictData.stream().map(x -> {
            DictVo dictVo = new DictVo();
            dictVo.toVo(x);
            return dictVo;
        }).collect(Collectors.toList());
    }


    // 学历获取接口
    @ApiOperation("学历获取接口")
    @GetMapping("/getEducation")
    public AjaxResult getEducation() {
        return AjaxResult.success(toVo(dictService.getType("cm_education")));
    }

    // 兴趣服务获取接口
    @ApiOperation("兴趣服务获取接口")
    @GetMapping("/getServiceItem")
    public AjaxResult getServiceItem() {
        return AjaxResult.success(iCmServiceService.selectCmServiceList(new CmService()));
    }

    // 预计服务期限获取接口
    @ApiOperation("预计服务期限获取接口")
    @GetMapping("/getServiceTime")
    public AjaxResult getServiceTime() {
        return AjaxResult.success(toVo(dictService.getType("cm_service_time")));
    }


    // 加入我们添加接口
    @ApiOperation("加入我们接口")
    @PostMapping("/addJoinUs")
    public AjaxResult addJoinUs(@RequestBody JoinUsVo joinUsVo) {
        String[] serviceIds = joinUsVo.getServiceIds();
        List<Long> ids = Arrays.stream(serviceIds).map(Long::valueOf)
                .collect(Collectors.toList());
        CmJoinUs joinUs = new CmJoinUs();
        BeanUtils.copyProperties(joinUsVo, joinUs);
        cmJoinUsService.insertCmJoinUs(joinUs, ids);
        return AjaxResult.success(joinUs);
    }

    // 捐款人信息添加接口
    @ApiOperation("捐款人信息添加接口")
    @PostMapping("/addDocutionUser")
    public AjaxResult addDocutionUser(DonationUserVo donationUser) {
        MultipartFile file = donationUser.getFile();
        Long fileId = saveFile(file);

        CmDonationUser cmDonationUser = new CmDonationUser();
        BeanUtils.copyProperties(donationUser, cmDonationUser);
        cmDonationUser.setCreateTime(new Date());
        cmDonationUser.setFileId(fileId);
        cmDonationUserService.insertCmDonationUser(cmDonationUser);
        String mailSend = sysConfigService.selectConfigByKey("mail_send");
        if (mailSend != null && "Y".equalsIgnoreCase(mailSend)) {
            iEmailService.sendMail(null, "捐款人信息", cmDonationUser.toString());
        }
        return AjaxResult.success();
    }

    private Long saveFile(MultipartFile file) {
        if (file != null) {
            // 上传并返回新文件名称
            try {
                Map<String, Object> map = FileUploadUtils.upload2(file, "docution");
                File f = (File) map.get("file");
                String url = (String) map.get("url");
                CmFileInfo cmFileInfo = new CmFileInfo();
                // 保存图片
                cmFileInfo.setFileUrl(url);
                cmFileInfo.setFileSize(file.getSize() + "");
                cmFileInfo.setFileName(f.getName());
                cmFileInfo.setFileAddr(f.getAbsolutePath());
                cmFileInfo.setCreateBy("intf");
                cmFileInfo.setCreateTime(new Date());
                // 保存数据库
                cmFileInfoService.insertCmFileInfo(cmFileInfo);
                return cmFileInfo.getfId();
            } catch (IOException e) {
                logger.error("上传图片失败：" + e.getMessage());
            }
        }
        return null;
    }

    //我要留言添加接口
    @ApiOperation("我要留言添加接口")
    @PostMapping("/addMsg")
    public AjaxResult addMsg(@RequestBody MsgVo msgVo) {
        CmMsgManage cmMsgManage = new CmMsgManage();
        BeanUtils.copyProperties(msgVo, cmMsgManage);
        cmMsgManage.setMsgTime(new Date());
        cmMsgManage.setMsgAnswer("");
        cmMsgManageService.insertCmMsgManage(cmMsgManage);
        return AjaxResult.success();
    }


}
