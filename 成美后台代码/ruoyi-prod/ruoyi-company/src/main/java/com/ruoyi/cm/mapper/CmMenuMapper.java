package com.ruoyi.cm.mapper;

import java.util.List;
import com.ruoyi.cm.domain.CmMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 成美菜单Mapper接口
 * 
 * @author djl
 * @date 2021-05-29
 */
public interface CmMenuMapper 
{
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
     * 删除成美菜单
     * 
     * @param menuId 成美菜单ID
     * @return 结果
     */
    public int deleteCmMenuById(Long menuId);

    /**
     * 批量删除成美菜单
     * 
     * @param menuIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmMenuByIds(String[] menuIds);

    /**
     * 根据父ID获取菜单列表
     * @param parentId
     * @return
     */
    List<CmMenu> selectCmMenuByParentId(@Param("parentId") long parentId,@Param("languageType") String languageType);
}
