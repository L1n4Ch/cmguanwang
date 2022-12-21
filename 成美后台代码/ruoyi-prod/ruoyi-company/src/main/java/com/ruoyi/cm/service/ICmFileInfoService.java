package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmFileInfo;

/**
 * 文件信息Service接口
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
public interface ICmFileInfoService 
{
    /**
     * 查询文件信息
     * 
     * @param fId 文件信息ID
     * @return 文件信息
     */
    public CmFileInfo selectCmFileInfoById(Long fId);

    /**
     * 查询文件信息列表
     * 
     * @param cmFileInfo 文件信息
     * @return 文件信息集合
     */
    public List<CmFileInfo> selectCmFileInfoList(CmFileInfo cmFileInfo);

    /**
     * 新增文件信息
     * 
     * @param cmFileInfo 文件信息
     * @return 结果
     */
    public int insertCmFileInfo(CmFileInfo cmFileInfo);

    /**
     * 修改文件信息
     * 
     * @param cmFileInfo 文件信息
     * @return 结果
     */
    public int updateCmFileInfo(CmFileInfo cmFileInfo);

    /**
     * 批量删除文件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmFileInfoByIds(String ids);

    /**
     * 删除文件信息信息
     * 
     * @param fId 文件信息ID
     * @return 结果
     */
    public int deleteCmFileInfoById(Long fId);

    /**
     * 查询未被使用的文件信息
     * @return
     */
    List<CmFileInfo> selectCmFileInfoNotUse();
}
