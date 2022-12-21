package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmService;

/**
 * 志愿服务列表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
public interface CmServiceMapper 
{
    /**
     * 查询志愿服务列表
     * 
     * @param sId 志愿服务列表ID
     * @return 志愿服务列表
     */
    public CmService selectCmServiceById(Long sId);

    /**
     * 查询志愿服务列表列表
     * 
     * @param cmService 志愿服务列表
     * @return 志愿服务列表集合
     */
    public List<CmService> selectCmServiceList(CmService cmService);

    /**
     * 新增志愿服务列表
     * 
     * @param cmService 志愿服务列表
     * @return 结果
     */
    public int insertCmService(CmService cmService);

    /**
     * 修改志愿服务列表
     * 
     * @param cmService 志愿服务列表
     * @return 结果
     */
    public int updateCmService(CmService cmService);

    /**
     * 删除志愿服务列表
     * 
     * @param sId 志愿服务列表ID
     * @return 结果
     */
    public int deleteCmServiceById(Long sId);

    /**
     * 批量删除志愿服务列表
     * 
     * @param sIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmServiceByIds(String[] sIds);
}
