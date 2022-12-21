package com.ruoyi.cm.service.impl;

import java.util.List;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmBigEventMapper;
import com.ruoyi.cm.domain.CmBigEvent;
import com.ruoyi.cm.service.ICmBigEventService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成美大事件Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
@Service
public class CmBigEventServiceImpl implements ICmBigEventService 
{
    @Autowired
    private CmBigEventMapper cmBigEventMapper;

    @Autowired
    private ICmFileInfoService cmFileInfoService;
    /**
     * 查询成美大事件
     * 
     * @param beId 成美大事件ID
     * @return 成美大事件
     */
    @Override
    public CmBigEvent selectCmBigEventById(Long beId)
    {
        CmBigEvent cmBigEvent = cmBigEventMapper.selectCmBigEventById(beId);
        if(cmBigEvent != null || cmBigEvent.getBgImage() != null){
            CmFileInfo cmFileInfo = cmFileInfoService.selectCmFileInfoById(cmBigEvent.getBgImage());
            cmBigEvent.setBgImageFile(cmFileInfo);
        }
        return cmBigEvent;
    }

    /**
     * 查询成美大事件列表
     * 
     * @param cmBigEvent 成美大事件
     * @return 成美大事件
     */
    @Override
    public List<CmBigEvent> selectCmBigEventList(CmBigEvent cmBigEvent)
    {
        return cmBigEventMapper.selectCmBigEventList(cmBigEvent);
    }

    /**
     * 新增成美大事件
     * 
     * @param cmBigEvent 成美大事件
     * @return 结果
     */
    @Override
    public int insertCmBigEvent(CmBigEvent cmBigEvent)
    {
        cmBigEvent.setCreateTime(DateUtils.getNowDate());
        return cmBigEventMapper.insertCmBigEvent(cmBigEvent);
    }

    /**
     * 修改成美大事件
     * 
     * @param cmBigEvent 成美大事件
     * @return 结果
     */
    @Override
    public int updateCmBigEvent(CmBigEvent cmBigEvent)
    {
        return cmBigEventMapper.updateCmBigEvent(cmBigEvent);
    }

    /**
     * 删除成美大事件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmBigEventByIds(String ids)
    {
        return cmBigEventMapper.deleteCmBigEventByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成美大事件信息
     * 
     * @param beId 成美大事件ID
     * @return 结果
     */
    @Override
    public int deleteCmBigEventById(Long beId)
    {
        return cmBigEventMapper.deleteCmBigEventById(beId);
    }
}
