package com.erp.biz.file.service;

import com.erp.biz.file.domain.SysFile;
import com.erp.biz.file.vo.SysFileVo;
import com.erp.biz.file.bo.SysFileQueryBo;
import com.erp.biz.file.bo.SysFileAddBo;
import com.erp.biz.file.bo.SysFileEditBo;
import com.erp.common.core.service.DiBaseService;
import com.erp.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 文件管理Service接口
 *
 * @author erp
 * @date 2023-11-22
 */
public interface ISysFileService extends DiBaseService<SysFile> {
	/**
	 * 查询单个
	 * @return
	 */
	SysFileVo queryById(Long id);

	/**
	 * 查询列表
	 */
	TableDataInfo<SysFileVo> queryPageList(SysFileQueryBo bo);

	/**
	 * 查询列表
	 */
	List<SysFileVo> queryList(SysFileQueryBo bo);

	/**
	 * 根据新增业务对象插入文件管理
	 * @param bo 文件管理新增业务对象
	 * @return ID
	 */
	Long insertByAddBo(SysFileAddBo bo);

	/**
	 * 根据编辑业务对象修改文件管理
	 * @param bo 文件管理编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(SysFileEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
