package com.ruoyi.cm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmDonationUserMapper;
import com.ruoyi.cm.domain.CmDonationUser;
import com.ruoyi.cm.service.ICmDonationUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 捐赠人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Service
public class CmDonationUserServiceImpl implements ICmDonationUserService 
{
    @Autowired
    private CmDonationUserMapper cmDonationUserMapper;

    /**
     * 查询捐赠人信息
     * 
     * @param duId 捐赠人信息ID
     * @return 捐赠人信息
     */
    @Override
    public CmDonationUser selectCmDonationUserById(Long duId)
    {
        return cmDonationUserMapper.selectCmDonationUserById(duId);
    }

    /**
     * 查询捐赠人信息列表
     * 
     * @param cmDonationUser 捐赠人信息
     * @return 捐赠人信息
     */
    @Override
    public List<CmDonationUser> selectCmDonationUserList(CmDonationUser cmDonationUser)
    {
        return cmDonationUserMapper.selectCmDonationUserList(cmDonationUser);
    }

    /**
     * 新增捐赠人信息
     * 
     * @param cmDonationUser 捐赠人信息
     * @return 结果
     */
    @Override
    public int insertCmDonationUser(CmDonationUser cmDonationUser)
    {
        cmDonationUser.setCreateTime(DateUtils.getNowDate());
        return cmDonationUserMapper.insertCmDonationUser(cmDonationUser);
    }

    /**
     * 修改捐赠人信息
     * 
     * @param cmDonationUser 捐赠人信息
     * @return 结果
     */
    @Override
    public int updateCmDonationUser(CmDonationUser cmDonationUser)
    {
        return cmDonationUserMapper.updateCmDonationUser(cmDonationUser);
    }

    /**
     * 删除捐赠人信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmDonationUserByIds(String ids)
    {
        return cmDonationUserMapper.deleteCmDonationUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除捐赠人信息信息
     * 
     * @param duId 捐赠人信息ID
     * @return 结果
     */
    @Override
    public int deleteCmDonationUserById(Long duId)
    {
        return cmDonationUserMapper.deleteCmDonationUserById(duId);
    }
}
