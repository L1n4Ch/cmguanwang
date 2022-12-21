package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmFileInfo;

/**
 * 文件信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
public interface CmFileInfoMapper 
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
     * 删除文件信息
     * 
     * @param fId 文件信息ID
     * @return 结果
     */
    public int deleteCmFileInfoById(Long fId);

    /**
     * 批量删除文件信息
     * 
     * @param fIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmFileInfoByIds(String[] fIds);

    /**
     * 未被使用的文件信息
     * @return
     */
    List<CmFileInfo> selectCmFileInfoNotUse();
}
