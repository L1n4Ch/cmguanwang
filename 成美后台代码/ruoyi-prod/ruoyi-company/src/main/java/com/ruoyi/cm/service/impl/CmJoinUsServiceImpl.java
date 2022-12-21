package com.ruoyi.cm.service.impl;

import java.util.List;

import com.ruoyi.cm.domain.CmJoinService;
import com.ruoyi.cm.mapper.CmJoinServiceMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmJoinUsMapper;
import com.ruoyi.cm.domain.CmJoinUs;
import com.ruoyi.cm.service.ICmJoinUsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 加入我们Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-25
 */
@Service
public class CmJoinUsServiceImpl implements ICmJoinUsService 
{
    @Autowired
    private CmJoinUsMapper cmJoinUsMapper;


    @Autowired
    private CmJoinServiceMapper jsMapper ;

    /**
     * 查询加入我们
     * 
     * @param jId 加入我们ID
     * @return 加入我们
     */
    @Override
    public CmJoinUs selectCmJoinUsById(Long jId)
    {
        return cmJoinUsMapper.selectCmJoinUsById(jId);
    }

    /**
     * 查询加入我们列表
     * 
     * @param cmJoinUs 加入我们
     * @return 加入我们
     */
    @Override
    public List<CmJoinUs> selectCmJoinUsList(CmJoinUs cmJoinUs)
    {
        return cmJoinUsMapper.selectCmJoinUsList(cmJoinUs);
    }

    /**
     * 新增加入我们
     * 
     * @param cmJoinUs 加入我们
     * @return 结果
     */
    @Override
    public int insertCmJoinUs(CmJoinUs cmJoinUs , List<Long> ids)
    {
        cmJoinUs.setCreateTime(DateUtils.getNowDate());

        int j_id = cmJoinUsMapper.insertCmJoinUs(cmJoinUs);

        //加入我们   同时插入关联表数据
        for (int i = 0; i < ids.size(); i++) {
            CmJoinService cs = new CmJoinService();
            cs.setjId(cmJoinUs.getjId());
            cs.setsId(ids.get(i));
            jsMapper.insertCmJoinService(cs);
        }
        return j_id ;
    }

    /**
     * 修改加入我们
     * 
     * @param cmJoinUs 加入我们
     * @return 结果
     */
    @Override
    public int updateCmJoinUs(CmJoinUs cmJoinUs)
    {
        cmJoinUs.setUpdateTime(DateUtils.getNowDate());
        return cmJoinUsMapper.updateCmJoinUs(cmJoinUs);
    }

    /**
     * 删除加入我们对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmJoinUsByIds(String ids)
    {
        return cmJoinUsMapper.deleteCmJoinUsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加入我们信息
     * 
     * @param jId 加入我们ID
     * @return 结果
     */
    @Override
    public int deleteCmJoinUsById(Long jId)
    {
        return cmJoinUsMapper.deleteCmJoinUsById(jId);
    }
}
