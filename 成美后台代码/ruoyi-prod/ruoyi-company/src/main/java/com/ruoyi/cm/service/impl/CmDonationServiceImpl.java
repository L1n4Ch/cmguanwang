package com.ruoyi.cm.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmDonationMapper;
import com.ruoyi.cm.domain.CmDonation;
import com.ruoyi.cm.service.ICmDonationService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 捐赠记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Service
public class CmDonationServiceImpl implements ICmDonationService 
{
    private static final Logger log = LoggerFactory.getLogger(CmDonationServiceImpl.class);

    @Autowired
    private CmDonationMapper cmDonationMapper;

    /**
     * 查询捐赠记录
     * 
     * @param donationId 捐赠记录ID
     * @return 捐赠记录
     */
    @Override
    public CmDonation selectCmDonationById(Long donationId)
    {
        return cmDonationMapper.selectCmDonationById(donationId);
    }

    /**
     * 查询捐赠记录列表
     * 
     * @param cmDonation 捐赠记录
     * @return 捐赠记录
     */
    @Override
    public List<CmDonation> selectCmDonationList(CmDonation cmDonation)
    {
        return cmDonationMapper.selectCmDonationList(cmDonation);
    }

    /**
     * 新增捐赠记录
     * 
     * @param cmDonation 捐赠记录
     * @return 结果
     */
    @Override
    public int insertCmDonation(CmDonation cmDonation)
    {
        cmDonation.setCreateTime(DateUtils.getNowDate());
        return cmDonationMapper.insertCmDonation(cmDonation);
    }

    /**
     * 修改捐赠记录
     * 
     * @param cmDonation 捐赠记录
     * @return 结果
     */
    @Override
    public int updateCmDonation(CmDonation cmDonation)
    {
        cmDonation.setUpdateTime(DateUtils.getNowDate());
        return cmDonationMapper.updateCmDonation(cmDonation);
    }

    /**
     * 删除捐赠记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmDonationByIds(String ids)
    {
        return cmDonationMapper.deleteCmDonationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除捐赠记录信息
     * 
     * @param donationId 捐赠记录ID
     * @return 结果
     */
    @Override
    public int deleteCmDonationById(Long donationId)
    {
        return cmDonationMapper.deleteCmDonationById(donationId);
    }

    @Override
    @Transactional
    public String importCmDonation(List<CmDonation> cmDonationList, boolean updateSupport) {
        if (StringUtils.isNull(cmDonationList) || cmDonationList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (CmDonation cmDonation : cmDonationList)
        {
            try
            {
                // 校验格式
                Date donationTime = cmDonation.getDonationTime();
                BigDecimal donationMoney = cmDonation.getDonationMoney();

                String donationCompany = cmDonation.getDonationCompany();
                String donationItem = cmDonation.getDonationItem();
                if(donationTime == null){
                    failureNum++;
                    failureMsg.append("<br/>捐款日期格式不正确");
                }else if(donationMoney == null){
                    failureNum++;
                    failureMsg.append("<br/>捐款金额格式不正确");
                }else if(StringUtils.isEmpty(donationCompany)){
                    failureNum++;
                    failureMsg.append("<br/>捐款企业不能为空");
                }else  if(StringUtils.isEmpty(donationItem)){
                    failureNum++;
                    failureMsg.append("<br/>捐款项目不能为空");
                }else{
                    this.insertCmDonation(cmDonation);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、捐赠企业 " + cmDonation.getDonationCompany() + " 导入成功");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + successNum + "、捐赠企业 " + cmDonation.getDonationCompany() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public BigDecimal sumMoney(String donationStart, String donationEnd, String donationCompany) {
        return cmDonationMapper.sumMoney(donationStart,donationEnd,donationCompany);
    }
}
