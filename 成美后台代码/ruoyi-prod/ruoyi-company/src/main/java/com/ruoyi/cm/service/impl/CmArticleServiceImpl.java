package com.ruoyi.cm.service.impl;

import java.util.List;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.domain.CmMenu;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.cm.service.ICmMenuService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmArticleMapper;
import com.ruoyi.cm.domain.CmArticle;
import com.ruoyi.cm.service.ICmArticleService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.web.client.RestTemplate;

/**
 * 文章发布Service业务层处理
 *
 * @author djl
 * @date 2021-05-30
 */
@Service
public class CmArticleServiceImpl implements ICmArticleService
{
    private static final Logger logger = LoggerFactory.getLogger(CmArticleServiceImpl.class);
    @Autowired
    private CmArticleMapper cmArticleMapper;
    @Autowired
    private ICmFileInfoService cmFileInfoService;
    @Autowired
    private ICmMenuService cmMenuService;
    @Autowired
    private RedisTemplate redisTemplate;

    // PC端NG缓存
    @Value("${clear.nginx.cache.url}")
    private String clearCacheUrl;
    // 手机端NG缓存
    @Value("${clear.nginx.cache.url2}")
    private String clearCacheUrl2;
    /**
     * 查询文章发布
     *
     * @param articleId 文章发布ID
     * @return 文章发布
     */
    @Override
    public CmArticle selectCmArticleById(Long articleId)
    {
        CmArticle cmArticle =  cmArticleMapper.selectCmArticleById(articleId);
        if(cmArticle != null && cmArticle.getBgImage() != null){
            CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(cmArticle.getBgImage());
            cmArticle.setImageFile(cmFileInfo);
        }else{
            cmArticle.setImageFile(new CmFileInfo());
        }
        if(cmArticle != null && cmArticle.getMenuId() != null){
            CmMenu cmMenu = cmMenuService.selectCmMenuById(cmArticle.getMenuId());
            cmArticle.setCmMenu(cmMenu);
        }
        return cmArticle;
    }

    /**
     * 查询文章发布列表
     *
     * @param cmArticle 文章发布
     * @return 文章发布
     */
    @Override
    public List<CmArticle> selectCmArticleList(CmArticle cmArticle)
    {
        return cmArticleMapper.selectCmArticleList(cmArticle);
    }

    /**
     * 新增文章发布
     *
     * @param cmArticle 文章发布
     * @return 结果
     */
    @Override
    public int insertCmArticle(CmArticle cmArticle)
    {
        cmArticle.setCreateTime(DateUtils.getNowDate());
        cmArticle.setUpdateTime(DateUtils.getNowDate());
        return cmArticleMapper.insertCmArticle(cmArticle);
    }

    /**
     * 修改文章发布
     *
     * @param cmArticle 文章发布
     * @return 结果
     */
    @Override
    public int updateCmArticle(CmArticle cmArticle)
    {
        cmArticle.setUpdateTime(DateUtils.getNowDate());
        // 删除掉缓存
        try{
            if(cmArticle.getArticleId() != null){
                redisTemplate.delete("intf:articleDetail:" + cmArticle.getArticleId());
                HttpUtils.sendGet(clearCacheUrl+cmArticle.getArticleId(),null);
                HttpUtils.sendGet(clearCacheUrl2+cmArticle.getArticleId(),null);
            }

        }catch (Exception e){
            logger.error("清除缓存异常:" + e.getMessage());
        }

        return cmArticleMapper.updateCmArticle(cmArticle);
    }

    /**
     * 删除文章发布对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmArticleByIds(String ids)
    {
        return cmArticleMapper.deleteCmArticleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章发布信息
     *
     * @param articleId 文章发布ID
     * @return 结果
     */
    @Override
    public int deleteCmArticleById(Long articleId)
    {
        return cmArticleMapper.deleteCmArticleById(articleId);
    }

    @Override
    public void addReadNum(Long id) {
        cmArticleMapper.addReadNum(id);
    }

    @Override
    public List<CmArticle> selectCmArticleListByLimitNum(CmArticle cmArticle, int limitNum) {

        return cmArticleMapper.selectCmArticleListByLimitNum(cmArticle, limitNum);
    }
}
