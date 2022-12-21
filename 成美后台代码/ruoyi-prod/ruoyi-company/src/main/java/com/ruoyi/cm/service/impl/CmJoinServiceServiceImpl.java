package com.ruoyi.cm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmJoinServiceMapper;
import com.ruoyi.cm.domain.CmJoinService;
import com.ruoyi.cm.service.ICmJoinServiceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 加入-志愿列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Service
public class CmJoinServiceServiceImpl implements ICmJoinServiceService 
{
    @Autowired
    private CmJoinServiceMapper cmJoinServiceMapper;

    /**
     * 查询加入-志愿列表
     * 
     * @param jsId 加入-志愿列表ID
     * @return 加入-志愿列表
     */
    @Override
    public CmJoinService selectCmJoinServiceById(Long jsId)
    {
        return cmJoinServiceMapper.selectCmJoinServiceById(jsId);
    }

    /**
     * 查询加入-志愿列表列表
     * 
     * @param cmJoinService 加入-志愿列表
     * @return 加入-志愿列表
     */
    @Override
    public List<CmJoinService> selectCmJoinServiceList(CmJoinService cmJoinService)
    {
        return cmJoinServiceMapper.selectCmJoinServiceList(cmJoinService);
    }

    /**
     * 新增加入-志愿列表
     * 
     * @param cmJoinService 加入-志愿列表
     * @return 结果
     */
    @Override
    public int insertCmJoinService(CmJoinService cmJoinService)
    {
        cmJoinService.setCreateTime(DateUtils.getNowDate());
        return cmJoinServiceMapper.insertCmJoinService(cmJoinService);
    }

    /**
     * 修改加入-志愿列表
     * 
     * @param cmJoinService 加入-志愿列表
     * @return 结果
     */
    @Override
    public int updateCmJoinService(CmJoinService cmJoinService)
    {
        cmJoinService.setUpdateTime(DateUtils.getNowDate());
        return cmJoinServiceMapper.updateCmJoinService(cmJoinService);
    }

    /**
     * 删除加入-志愿列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmJoinServiceByIds(String ids)
    {
        return cmJoinServiceMapper.deleteCmJoinServiceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加入-志愿列表信息
     * 
     * @param jsId 加入-志愿列表ID
     * @return 结果
     */
    @Override
    public int deleteCmJoinServiceById(Long jsId)
    {
        return cmJoinServiceMapper.deleteCmJoinServiceById(jsId);
    }
}
