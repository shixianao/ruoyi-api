package com.erp.biz.banner.service;

import com.erp.biz.banner.domain.BizBanner;
import com.erp.biz.banner.vo.BizBannerVo;
import com.erp.biz.banner.bo.BizBannerQueryBo;
import com.erp.biz.banner.bo.BizBannerAddBo;
import com.erp.biz.banner.bo.BizBannerEditBo;
import com.erp.common.core.service.DiBaseService;
import com.erp.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 轮播BannerService接口
 *
 * @author erp
 * @date 2022-06-24
 */
public interface IBizBannerService extends DiBaseService<BizBanner> {
	/**
	 * 查询单个
	 * @return
	 */
	BizBannerVo queryById(Long id);

	/**
	 * 查询列表
	 */
	TableDataInfo<BizBannerVo> queryPageList(BizBannerQueryBo bo);

	/**
	 * 查询列表
	 */
	List<BizBannerVo> queryList(BizBannerQueryBo bo);

	/**
	 * 根据新增业务对象插入轮播Banner
	 * @param bo 轮播Banner新增业务对象
	 * @return ID
	 */
	Long insertByAddBo(BizBannerAddBo bo);

	/**
	 * 根据编辑业务对象修改轮播Banner
	 * @param bo 轮播Banner编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(BizBannerEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
