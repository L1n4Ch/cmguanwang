package com.ruoyi.cm.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.cm.domain.CmDonation;

/**
 * 捐赠记录Service接口
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public interface ICmDonationService 
{
    /**
     * 查询捐赠记录
     * 
     * @param donationId 捐赠记录ID
     * @return 捐赠记录
     */
    public CmDonation selectCmDonationById(Long donationId);

    /**
     * 查询捐赠记录列表
     * 
     * @param cmDonation 捐赠记录
     * @return 捐赠记录集合
     */
    public List<CmDonation> selectCmDonationList(CmDonation cmDonation);

    /**
     * 新增捐赠记录
     * 
     * @param cmDonation 捐赠记录
     * @return 结果
     */
    public int insertCmDonation(CmDonation cmDonation);

    /**
     * 修改捐赠记录
     * 
     * @param cmDonation 捐赠记录
     * @return 结果
     */
    public int updateCmDonation(CmDonation cmDonation);

    /**
     * 批量删除捐赠记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmDonationByIds(String ids);

    /**
     * 删除捐赠记录信息
     * 
     * @param donationId 捐赠记录ID
     * @return 结果
     */
    public int deleteCmDonationById(Long donationId);

    /**
     * 导入数据
     * @param cmDonationList
     * @param updateSupport
     * @return
     */
    String importCmDonation(List<CmDonation> cmDonationList, boolean updateSupport);

    /**
     * 根据捐赠日期统计捐赠金额
     * @param donationStart
     * @param donationEnd
     * @param donationCompany
     * @return
     */
    BigDecimal sumMoney(String donationStart, String donationEnd, String donationCompany);
}
