package com.ruoyi.cm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmServiceMapper;
import com.ruoyi.cm.domain.CmService;
import com.ruoyi.cm.service.ICmServiceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 志愿服务列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Service
public class CmServiceServiceImpl implements ICmServiceService 
{
    @Autowired
    private CmServiceMapper cmServiceMapper;

    /**
     * 查询志愿服务列表
     * 
     * @param sId 志愿服务列表ID
     * @return 志愿服务列表
     */
    @Override
    public CmService selectCmServiceById(Long sId)
    {
        return cmServiceMapper.selectCmServiceById(sId);
    }

    /**
     * 查询志愿服务列表列表
     * 
     * @param cmService 志愿服务列表
     * @return 志愿服务列表
     */
    @Override
    public List<CmService> selectCmServiceList(CmService cmService)
    {
        return cmServiceMapper.selectCmServiceList(cmService);
    }

    /**
     * 新增志愿服务列表
     * 
     * @param cmService 志愿服务列表
     * @return 结果
     */
    @Override
    public int insertCmService(CmService cmService)
    {
        cmService.setCreateTime(DateUtils.getNowDate());
        return cmServiceMapper.insertCmService(cmService);
    }

    /**
     * 修改志愿服务列表
     * 
     * @param cmService 志愿服务列表
     * @return 结果
     */
    @Override
    public int updateCmService(CmService cmService)
    {
        cmService.setUpdateTime(DateUtils.getNowDate());
        return cmServiceMapper.updateCmService(cmService);
    }

    /**
     * 删除志愿服务列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmServiceByIds(String ids)
    {
        return cmServiceMapper.deleteCmServiceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除志愿服务列表信息
     * 
     * @param sId 志愿服务列表ID
     * @return 结果
     */
    @Override
    public int deleteCmServiceById(Long sId)
    {
        return cmServiceMapper.deleteCmServiceById(sId);
    }
}
