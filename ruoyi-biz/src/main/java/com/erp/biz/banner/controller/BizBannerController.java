package com.erp.biz.banner.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.erp.common.annotation.RepeatSubmit;
import com.erp.common.annotation.Log;
import com.erp.common.core.controller.BaseController;
import com.erp.common.core.domain.AjaxResult;
import com.erp.common.enums.BusinessType;
import com.erp.biz.banner.vo.BizBannerVo;
import com.erp.biz.banner.bo.BizBannerQueryBo;
import com.erp.biz.banner.bo.BizBannerAddBo;
import com.erp.biz.banner.bo.BizBannerEditBo;
import com.erp.biz.banner.service.IBizBannerService;
import com.erp.common.utils.poi.ExcelUtil;
import com.erp.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 轮播BannerController
 *
 * @author erp
 * @date 2022-06-24
 */
@Api(value = "轮播Banner控制器", tags = {"轮播Banner管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/biz/banner")
public class BizBannerController extends BaseController {

    private final IBizBannerService iBizBannerService;

    /**
     * 查询轮播Banner列表
     */
    @ApiOperation("查询轮播Banner列表")
    // @PreAuthorize("@ss.hasPermi('biz:banner:list')")
    @GetMapping("/list")
    public TableDataInfo<BizBannerVo> list(@Validated BizBannerQueryBo bo) {
        return iBizBannerService.queryPageList(bo);
    }

    /**
     * 导出轮播Banner列表
     */
    @ApiOperation("导出轮播Banner列表")
    // @PreAuthorize("@ss.hasPermi('biz:banner:export')")
    @Log(title = "轮播Banner", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<BizBannerVo> export(@Validated BizBannerQueryBo bo) {
        List<BizBannerVo> list = iBizBannerService.queryList(bo);
        ExcelUtil<BizBannerVo> util = new ExcelUtil<BizBannerVo>(BizBannerVo.class);
        return util.exportExcel(list, "轮播Banner");
    }

    /**
     * 获取轮播Banner详细信息
     */
    @ApiOperation("获取轮播Banner详细信息")
    // @PreAuthorize("@ss.hasPermi('biz:banner:query')")
    @GetMapping("/{id}")
    public AjaxResult<BizBannerVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iBizBannerService.queryById(id));
    }

    /**
     * 新增轮播Banner
     */
    @ApiOperation("新增轮播Banner")
    // @PreAuthorize("@ss.hasPermi('biz:banner:add')")
    @Log(title = "轮播Banner", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<BizBannerVo> add(@Validated @RequestBody BizBannerAddBo bo) {
        return getInfo(iBizBannerService.insertByAddBo(bo));
    }

    /**
     * 修改轮播Banner
     */
    @ApiOperation("修改轮播Banner")
    // @PreAuthorize("@ss.hasPermi('biz:banner:edit')")
    @Log(title = "轮播Banner", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody BizBannerEditBo bo) {
        return toAjax(iBizBannerService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除轮播Banner
     */
    @ApiOperation("删除轮播Banner")
    // @PreAuthorize("@ss.hasPermi('biz:banner:remove')")
    @Log(title = "轮播Banner" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iBizBannerService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
