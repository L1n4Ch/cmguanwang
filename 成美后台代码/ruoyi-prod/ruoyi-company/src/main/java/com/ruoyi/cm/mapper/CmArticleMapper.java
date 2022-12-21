package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmArticle;
import org.apache.ibatis.annotations.Param;

/**
 * 文章发布Mapper接口
 * 
 * @author djl
 * @date 2021-05-30
 */
public interface CmArticleMapper 
{
    /**
     * 查询文章发布
     * 
     * @param articleId 文章发布ID
     * @return 文章发布
     */
    public CmArticle selectCmArticleById(Long articleId);

    /**
     * 查询文章发布列表
     * 
     * @param cmArticle 文章发布
     * @return 文章发布集合
     */
    public List<CmArticle> selectCmArticleList(CmArticle cmArticle);

    /**
     * 新增文章发布
     * 
     * @param cmArticle 文章发布
     * @return 结果
     */
    public int insertCmArticle(CmArticle cmArticle);

    /**
     * 修改文章发布
     * 
     * @param cmArticle 文章发布
     * @return 结果
     */
    public int updateCmArticle(CmArticle cmArticle);

    /**
     * 删除文章发布
     * 
     * @param articleId 文章发布ID
     * @return 结果
     */
    public int deleteCmArticleById(Long articleId);

    /**
     * 批量删除文章发布
     * 
     * @param articleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmArticleByIds(String[] articleIds);

    /**
     * 添加浏览量
     * @param articleId
     */
    void addReadNum(Long articleId);

    /**
     * 查询文章条数（限制）
     * @param cmArticle
     * @param limitNum
     * @return
     */
    List<CmArticle> selectCmArticleListByLimitNum(@Param("cmArticle") CmArticle cmArticle, @Param("limitNum") int limitNum);
}
