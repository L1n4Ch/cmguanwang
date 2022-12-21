package com.ruoyi.cm.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ruoyi.cm.domain.CmFileInfo;
import com.ruoyi.cm.service.ICmFileInfoService;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cm.mapper.CmMenuMapper;
import com.ruoyi.cm.domain.CmMenu;
import com.ruoyi.cm.service.ICmMenuService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成美菜单Service业务层处理
 *
 * @author djl
 * @date 2021-05-29
 */
@Service
public class CmMenuServiceImpl implements ICmMenuService {
    @Autowired
    private CmMenuMapper cmMenuMapper;
    @Autowired
    private ICmFileInfoService cmFileInfoService;

    /**
     * 查询成美菜单
     *
     * @param menuId 成美菜单ID
     * @return 成美菜单
     */
    @Override
    public CmMenu selectCmMenuById(Long menuId) {
        CmMenu cmMenu = cmMenuMapper.selectCmMenuById(menuId);
        if (cmMenu == null) {
            cmMenu = new CmMenu();
        }
        if (cmMenu != null && cmMenu.getMenuImg() != null) {
            cmMenu.setImageFile(cmFileInfoService.selectCmFileInfoById(cmMenu.getMenuImg()));
        } else {
            cmMenu.setImageFile(new CmFileInfo());
        }
        return cmMenu;
    }

    /**
     * 查询成美菜单列表
     *
     * @param cmMenu 成美菜单
     * @return 成美菜单
     */
    @Override
    public List<CmMenu> selectCmMenuList(CmMenu cmMenu) {
        return cmMenuMapper.selectCmMenuList(cmMenu);
    }

    /**
     * 新增成美菜单
     *
     * @param cmMenu 成美菜单
     * @return 结果
     */
    @Override
    public int insertCmMenu(CmMenu cmMenu) {
        cmMenu.setCreateTime(DateUtils.getNowDate());
        return cmMenuMapper.insertCmMenu(cmMenu);
    }

    /**
     * 修改成美菜单
     *
     * @param cmMenu 成美菜单
     * @return 结果
     */
    @Override
    public int updateCmMenu(CmMenu cmMenu) {
        cmMenu.setUpdateTime(DateUtils.getNowDate());
        return cmMenuMapper.updateCmMenu(cmMenu);
    }

    /**
     * 删除成美菜单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmMenuByIds(String ids) {
        return cmMenuMapper.deleteCmMenuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成美菜单信息
     *
     * @param menuId 成美菜单ID
     * @return 结果
     */
    @Override
    public int deleteCmMenuById(Long menuId) {
        return cmMenuMapper.deleteCmMenuById(menuId);
    }

    /**
     * 查询成美菜单树列表
     *
     * @param languageType 语言类型
     * @return 所有成美菜单信息
     */
    @Override
    public List<Ztree> selectCmMenuTree(String languageType) {
        CmMenu param = new CmMenu();
        languageType = StringUtils.isNotEmpty(languageType) ? languageType : "0";
        param.setLanguageType(languageType);
        List<CmMenu> cmMenuList = cmMenuMapper.selectCmMenuList(param);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (CmMenu cmMenu : cmMenuList) {
            Ztree ztree = new Ztree();
            ztree.setId(cmMenu.getMenuId());
            ztree.setpId(cmMenu.getParentId());
            ztree.setName(cmMenu.getMenuName());
            ztree.setTitle(cmMenu.getMenuName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public List<CmMenu> selectCmMenuByParentId(long parentId, String languageType) {
        return cmMenuMapper.selectCmMenuByParentId(parentId, languageType);
    }
}
