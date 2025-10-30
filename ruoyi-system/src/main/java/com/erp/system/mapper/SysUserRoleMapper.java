package com.erp.system.mapper;

import com.erp.common.core.page.BaseMapperPlus;
import com.erp.system.domain.SysUserRole;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户与角色关联表 数据层
 *
 * @author erp
 */
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRole> {

	/**
	 * 批量新增用户角色信息
	 *
	 * @param userRoleList 用户角色列表
	 * @return 结果
	 */
	public int batchUserRole(List<SysUserRole> userRoleList);

	/**
	 * 查询角色数量=1的用户
	 *
	 * @param ids  用户id
	 * @return
	 */
	List<Long> selectUserRoleCount(@Param("ids") Collection<Long> ids);
}
