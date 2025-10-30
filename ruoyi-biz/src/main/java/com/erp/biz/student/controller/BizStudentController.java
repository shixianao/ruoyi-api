package com.erp.biz.student.controller;

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
import com.erp.biz.student.vo.BizStudentVo;
import com.erp.biz.student.bo.BizStudentQueryBo;
import com.erp.biz.student.bo.BizStudentAddBo;
import com.erp.biz.student.bo.BizStudentEditBo;
import com.erp.biz.student.service.IBizStudentService;
import com.erp.common.utils.poi.ExcelUtil;
import com.erp.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 学生管理Controller
 *
 * @author erp
 * @date 2025-10-30
 */
@Api(value = "学生管理控制器", tags = {"学生管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/biz/student")
public class BizStudentController extends BaseController {

    private final IBizStudentService iBizStudentService;

    /**
     * 查询学生管理列表
     */
    @ApiOperation("查询学生管理列表")
    @GetMapping("/list")
    public TableDataInfo<BizStudentVo> list(@Validated BizStudentQueryBo bo) {
        return iBizStudentService.queryPageList(bo);
    }

    /**
     * 导出学生管理列表
     */
    @ApiOperation("导出学生管理列表")
    @PreAuthorize("@ss.hasPermi('biz:student:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(@Validated BizStudentQueryBo bo) {
        List<BizStudentVo> list = iBizStudentService.queryList(bo);
        ExcelUtil<BizStudentVo> util = new ExcelUtil<BizStudentVo>(BizStudentVo.class);
        return util.exportExcel(list, "学生管理数据");
    }

    /**
     * 获取学生管理详细信息
     */
    @ApiOperation("获取学生管理详细信息")
    @PreAuthorize("@ss.hasPermi('biz:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable(value = "id") Long id) {
        return AjaxResult.success(iBizStudentService.queryById(id));
    }

    /**
     * 新增学生管理
     */
    @ApiOperation("新增学生管理")
    @PreAuthorize("@ss.hasPermi('biz:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BizStudentAddBo bo) {
        return toAjax(iBizStudentService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改学生管理
     */
    @ApiOperation("修改学生管理")
    @PreAuthorize("@ss.hasPermi('biz:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BizStudentEditBo bo) {
        return toAjax(iBizStudentService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除学生管理
     */
    @ApiOperation("删除学生管理")
    @PreAuthorize("@ss.hasPermi('biz:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iBizStudentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}