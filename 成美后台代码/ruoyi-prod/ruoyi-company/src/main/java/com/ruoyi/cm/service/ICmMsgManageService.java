package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmMsgManage;

/**
 * 留言管理Service接口
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public interface ICmMsgManageService 
{
    /**
     * 查询留言管理
     * 
     * @param msgId 留言管理ID
     * @return 留言管理
     */
    public CmMsgManage selectCmMsgManageById(Long msgId);

    /**
     * 查询留言管理列表
     * 
     * @param cmMsgManage 留言管理
     * @return 留言管理集合
     */
    public List<CmMsgManage> selectCmMsgManageList(CmMsgManage cmMsgManage);

    /**
     * 新增留言管理
     * 
     * @param cmMsgManage 留言管理
     * @return 结果
     */
    public int insertCmMsgManage(CmMsgManage cmMsgManage);

    /**
     * 修改留言管理
     * 
     * @param cmMsgManage 留言管理
     * @return 结果
     */
    public int updateCmMsgManage(CmMsgManage cmMsgManage);

    /**
     * 批量删除留言管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmMsgManageByIds(String ids);

    /**
     * 删除留言管理信息
     * 
     * @param msgId 留言管理ID
     * @return 结果
     */
    public int deleteCmMsgManageById(Long msgId);
}
