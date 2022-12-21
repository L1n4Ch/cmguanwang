package com.ruoyi.cm.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmFileInfoMapper;
import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-23
 */
@Service
public class CmFileInfoServiceImpl implements ICmFileInfoService 
{
    @Autowired
    private CmFileInfoMapper cmFileInfoMapper;

    /**
     * 查询文件信息
     * 
     * @param fId 文件信息ID
     * @return 文件信息
     */
    @Override
    public CmFileInfo selectCmFileInfoById(Long fId)
    {
        return cmFileInfoMapper.selectCmFileInfoById(fId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param cmFileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<CmFileInfo> selectCmFileInfoList(CmFileInfo cmFileInfo)
    {
        return cmFileInfoMapper.selectCmFileInfoList(cmFileInfo);
    }

    /**
     * 新增文件信息
     * 
     * @param cmFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int insertCmFileInfo(CmFileInfo cmFileInfo)
    {
        cmFileInfo.setCreateTime(DateUtils.getNowDate());
        return cmFileInfoMapper.insertCmFileInfo(cmFileInfo);
    }

    /**
     * 修改文件信息
     * 
     * @param cmFileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateCmFileInfo(CmFileInfo cmFileInfo)
    {
        cmFileInfo.setUpdateTime(DateUtils.getNowDate());
        return cmFileInfoMapper.updateCmFileInfo(cmFileInfo);
    }

    /**
     * 删除文件信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmFileInfoByIds(String ids)
    {
        // 删除服务器中对应的文件
        Arrays.stream(Convert.toStrArray(ids)).forEach(x->{
            CmFileInfo cmFileInfo = cmFileInfoMapper.selectCmFileInfoById(Long.valueOf(x));
            String fileAddr = cmFileInfo.getFileAddr();
            FileUtils.deleteFile(fileAddr);
        });
        return cmFileInfoMapper.deleteCmFileInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件信息信息
     * 
     * @param fId 文件信息ID
     * @return 结果
     */
    @Override
    public int deleteCmFileInfoById(Long fId)
    {
        // 删除服务器中对应的文件
        CmFileInfo cmFileInfo = cmFileInfoMapper.selectCmFileInfoById(fId);
        String fileAddr = cmFileInfo.getFileAddr();
        FileUtils.deleteFile(fileAddr);
        return cmFileInfoMapper.deleteCmFileInfoById(fId);
    }

    @Override
    public List<CmFileInfo> selectCmFileInfoNotUse() {
        return cmFileInfoMapper.selectCmFileInfoNotUse();
    }
}
