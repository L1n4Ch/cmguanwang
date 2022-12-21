package com.ruoyi.cm.service;

import java.util.List;
import com.ruoyi.cm.domain.CmDonationUser;

/**
 * 捐赠人信息Service接口
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public interface ICmDonationUserService 
{
    /**
     * 查询捐赠人信息
     * 
     * @param duId 捐赠人信息ID
     * @return 捐赠人信息
     */
    public CmDonationUser selectCmDonationUserById(Long duId);

    /**
     * 查询捐赠人信息列表
     * 
     * @param cmDonationUser 捐赠人信息
     * @return 捐赠人信息集合
     */
    public List<CmDonationUser> selectCmDonationUserList(CmDonationUser cmDonationUser);

    /**
     * 新增捐赠人信息
     * 
     * @param cmDonationUser 捐赠人信息
     * @return 结果
     */
    public int insertCmDonationUser(CmDonationUser cmDonationUser);

    /**
     * 修改捐赠人信息
     * 
     * @param cmDonationUser 捐赠人信息
     * @return 结果
     */
    public int updateCmDonationUser(CmDonationUser cmDonationUser);

    /**
     * 批量删除捐赠人信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmDonationUserByIds(String ids);

    /**
     * 删除捐赠人信息信息
     * 
     * @param duId 捐赠人信息ID
     * @return 结果
     */
    public int deleteCmDonationUserById(Long duId);
}
