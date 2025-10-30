package com.erp.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.common.core.domain.entity.SysUser;
import com.erp.common.core.page.BaseMapperPlus;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表 数据层
 *
 * @author erp
 */
public interface SysUserMapper extends BaseMapperPlus<SysUser> {

    Page<SysUser> selectPageUserList(@Param("page") Page<SysUser> page, @Param("user") SysUser user);

    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

	/**
	 * 根据条件查询用户关联角色表
	 * @param page
	 * @param user
	 * @return
	 */
	Page<SysUser> selectPageUserRoleList(@Param("page") Page<SysUser> page,  @Param("user") SysUser user);

}
