package com.ruoyi.cm.service;

import java.util.List;

import com.ruoyi.cm.domain.CmMenu;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 成美菜单Service接口
 *
 * @author djl
 * @date 2021-05-29
 */
public interface ICmMenuService {
    /**
     * 查询成美菜单
     *
     * @param menuId 成美菜单ID
     * @return 成美菜单
     */
    public CmMenu selectCmMenuById(Long menuId);

    /**
     * 查询成美菜单列表
     *
     * @param cmMenu 成美菜单
     * @return 成美菜单集合
     */
    public List<CmMenu> selectCmMenuList(CmMenu cmMenu);

    /**
     * 新增成美菜单
     *
     * @param cmMenu 成美菜单
     * @return 结果
     */
    public int insertCmMenu(CmMenu cmMenu);

    /**
     * 修改成美菜单
     *
     * @param cmMenu 成美菜单
     * @return 结果
     */
    public int updateCmMenu(CmMenu cmMenu);

    /**
     * 批量删除成美菜单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmMenuByIds(String ids);

    /**
     * 删除成美菜单信息
     *
     * @param menuId 成美菜单ID
     * @return 结果
     */
    public int deleteCmMenuById(Long menuId);

    /**
     * 查询成美菜单树列表
     *
     * @param languageType 语言类型
     * @return 所有成美菜单信息
     */
    public List<Ztree> selectCmMenuTree(String languageType);

    /**
     * 根据parentId 获取菜单列表
     *
     * @param parentId
     * @param languageType
     * @return
     */
    List<CmMenu> selectCmMenuByParentId(long parentId, String languageType);
}
