package com.ruoyi.cm.service;

import com.ruoyi.cm.domain.CmArticle;

import java.util.List;

/**
 * 文章发布Service接口
 * 
 * @author djl
 * @date 2021-05-30
 */
public interface ICmArticleService 
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
     * 批量删除文章发布
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmArticleByIds(String ids);

    /**
     * 删除文章发布信息
     * 
     * @param articleId 文章发布ID
     * @return 结果
     */
    public int deleteCmArticleById(Long articleId);

    /**
     * 添加访问量
     * @param id
     */
    void addReadNum(Long id);

    /**
     * 查询文章列表（限制展示条数）
     * @param cmArticle
     * @param limitNum
     * @return
     */
    List<CmArticle> selectCmArticleListByLimitNum(CmArticle cmArticle, int limitNum);
}
