package com.erp.biz.classes.controller;

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
import com.erp.biz.classes.vo.BizClassesVo;
import com.erp.biz.classes.bo.BizClassesQueryBo;
import com.erp.biz.classes.bo.BizClassesAddBo;
import com.erp.biz.classes.bo.BizClassesEditBo;
import com.erp.biz.classes.service.IBizClassesService;
import com.erp.common.utils.poi.ExcelUtil;
import com.erp.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 班级管理Controller
 *
 * @author erp
 * @date 2025-10-30
 */
@Api(value = "班级管理控制器", tags = {"班级管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/biz/classes")
public class BizClassesController extends BaseController {

    private final IBizClassesService iBizClassesService;

    /**
     * 查询班级管理列表
     */
    @ApiOperation("查询班级管理列表")
    @GetMapping("/list")
    public TableDataInfo<BizClassesVo> list(@Validated BizClassesQueryBo bo) {
        return iBizClassesService.queryPageList(bo);
    }

    /**
     * 导出班级管理列表
     */
    @ApiOperation("导出班级管理列表")
    @PreAuthorize("@ss.hasPermi('biz:classes:export')")
    @Log(title = "班级管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(@Validated BizClassesQueryBo bo) {
        List<BizClassesVo> list = iBizClassesService.queryList(bo);
        ExcelUtil<BizClassesVo> util = new ExcelUtil<BizClassesVo>(BizClassesVo.class);
        return util.exportExcel(list, "班级管理数据");
    }

    /**
     * 获取班级管理详细信息
     */
    @ApiOperation("获取班级管理详细信息")
    @PreAuthorize("@ss.hasPermi('biz:classes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable(value = "id") Long id) {
        return AjaxResult.success(iBizClassesService.queryById(id));
    }

    /**
     * 新增班级管理
     */
    @ApiOperation("新增班级管理")
    @PreAuthorize("@ss.hasPermi('biz:classes:add')")
    @Log(title = "班级管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BizClassesAddBo bo) {
        return toAjax(iBizClassesService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改班级管理
     */
    @ApiOperation("修改班级管理")
    @PreAuthorize("@ss.hasPermi('biz:classes:edit')")
    @Log(title = "班级管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BizClassesEditBo bo) {
        return toAjax(iBizClassesService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除班级管理
     */
    @ApiOperation("删除班级管理")
    @PreAuthorize("@ss.hasPermi('biz:classes:remove')")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iBizClassesService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}