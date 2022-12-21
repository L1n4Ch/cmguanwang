package com.ruoyi.cm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmMsgManageMapper;
import com.ruoyi.cm.domain.CmMsgManage;
import com.ruoyi.cm.service.ICmMsgManageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 留言管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Service
public class CmMsgManageServiceImpl implements ICmMsgManageService 
{
    @Autowired
    private CmMsgManageMapper cmMsgManageMapper;

    /**
     * 查询留言管理
     * 
     * @param msgId 留言管理ID
     * @return 留言管理
     */
    @Override
    public CmMsgManage selectCmMsgManageById(Long msgId)
    {
        return cmMsgManageMapper.selectCmMsgManageById(msgId);
    }

    /**
     * 查询留言管理列表
     * 
     * @param cmMsgManage 留言管理
     * @return 留言管理
     */
    @Override
    public List<CmMsgManage> selectCmMsgManageList(CmMsgManage cmMsgManage)
    {
        return cmMsgManageMapper.selectCmMsgManageList(cmMsgManage);
    }

    /**
     * 新增留言管理
     * 
     * @param cmMsgManage 留言管理
     * @return 结果
     */
    @Override
    public int insertCmMsgManage(CmMsgManage cmMsgManage)
    {
        return cmMsgManageMapper.insertCmMsgManage(cmMsgManage);
    }

    /**
     * 修改留言管理
     * 
     * @param cmMsgManage 留言管理
     * @return 结果
     */
    @Override
    public int updateCmMsgManage(CmMsgManage cmMsgManage)
    {
        return cmMsgManageMapper.updateCmMsgManage(cmMsgManage);
    }

    /**
     * 删除留言管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmMsgManageByIds(String ids)
    {
        return cmMsgManageMapper.deleteCmMsgManageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除留言管理信息
     * 
     * @param msgId 留言管理ID
     * @return 结果
     */
    @Override
    public int deleteCmMsgManageById(Long msgId)
    {
        return cmMsgManageMapper.deleteCmMsgManageById(msgId);
    }
}
