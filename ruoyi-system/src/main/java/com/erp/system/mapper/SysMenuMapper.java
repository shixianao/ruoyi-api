package com.erp.system.mapper;

import com.erp.common.core.domain.entity.SysMenu;
import com.erp.common.core.page.BaseMapperPlus;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单表 数据层
 *
 * @author erp
 */
public interface SysMenuMapper extends BaseMapperPlus<SysMenu> {

    /**
     * 根据用户所有权限
     *
     * @return 权限列表
     */
    public List<String> selectMenuPerms();

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuListByUserId(SysMenu menu);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId            角色ID
     * @param menuCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    public List<Integer> selectMenuListByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

	/**
	 * 根据角色ID查询菜单列表
	 * @param roleId
	 * @return
	 */
	List<SysMenu> selectMenuTreeByRoleId(Long roleId);
}
