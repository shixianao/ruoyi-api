package com.erp.biz.file.controller;

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
import com.erp.biz.file.vo.SysFileVo;
import com.erp.biz.file.bo.SysFileQueryBo;
import com.erp.biz.file.bo.SysFileAddBo;
import com.erp.biz.file.bo.SysFileEditBo;
import com.erp.biz.file.service.ISysFileService;
import com.erp.common.utils.poi.ExcelUtil;
import com.erp.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件管理Controller
 *
 * @author erp
 * @date 2023-11-22
 */
@Api(value = "文件管理控制器", tags = {"文件管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/file/manage")
public class SysFileController extends BaseController {

    private final ISysFileService iSysFileService;

    /**
     * 查询文件管理列表
     */
    @ApiOperation("查询文件管理列表")
    // @PreAuthorize("@ss.hasPermi('file:manage:list')")
    @GetMapping("/list")
    public TableDataInfo<SysFileVo> list(@Validated SysFileQueryBo bo) {
        return iSysFileService.queryPageList(bo);
    }

    /**
     * 导出文件管理列表
     */
    @ApiOperation("导出文件管理列表")
    // @PreAuthorize("@ss.hasPermi('file:manage:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<SysFileVo> export(@Validated SysFileQueryBo bo) {
        List<SysFileVo> list = iSysFileService.queryList(bo);
        ExcelUtil<SysFileVo> util = new ExcelUtil<SysFileVo>(SysFileVo.class);
        return util.exportExcel(list, "文件管理");
    }

    /**
     * 获取文件管理详细信息
     */
    @ApiOperation("获取文件管理详细信息")
    // @PreAuthorize("@ss.hasPermi('file:manage:query')")
    @GetMapping("/{id}")
    public AjaxResult<SysFileVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iSysFileService.queryById(id));
    }

    /**
     * 新增文件管理
     */
    @ApiOperation("新增文件管理")
    // @PreAuthorize("@ss.hasPermi('file:manage:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<SysFileVo> add(@Validated @RequestBody SysFileAddBo bo) {
        return getInfo(iSysFileService.insertByAddBo(bo));
    }

    /**
     * 修改文件管理
     */
    @ApiOperation("修改文件管理")
    // @PreAuthorize("@ss.hasPermi('file:manage:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody SysFileEditBo bo) {
        return toAjax(iSysFileService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除文件管理
     */
    @ApiOperation("删除文件管理")
    // @PreAuthorize("@ss.hasPermi('file:manage:remove')")
    @Log(title = "文件管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iSysFileService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
