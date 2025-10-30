package com.erp.demo.controller;

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
import com.erp.demo.vo.TestDemoRouteVo;
import com.erp.demo.bo.TestDemoRouteQueryBo;
import com.erp.demo.bo.TestDemoRouteAddBo;
import com.erp.demo.bo.TestDemoRouteEditBo;
import com.erp.demo.service.ITestDemoRouteService;
import com.erp.common.utils.poi.ExcelUtil;
import com.erp.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测试单RouteController
 *
 * @author erp
 * @date 2022-04-15
 */
@Api(value = "测试单Route控制器", tags = {"测试单Route管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/demo/demoRoute")
public class TestDemoRouteController extends BaseController {

    private final ITestDemoRouteService iTestDemoRouteService;

    /**
     * 查询测试单Route列表
     */
    @ApiOperation("查询测试单Route列表")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:list')")
    @GetMapping("/list")
    public TableDataInfo<TestDemoRouteVo> list(@Validated TestDemoRouteQueryBo bo) {
        return iTestDemoRouteService.queryPageList(bo);
    }

    /**
     * 导出测试单Route列表
     */
    @ApiOperation("导出测试单Route列表")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:export')")
    @Log(title = "测试单Route", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TestDemoRouteVo> export(@Validated TestDemoRouteQueryBo bo) {
        List<TestDemoRouteVo> list = iTestDemoRouteService.queryList(bo);
        ExcelUtil<TestDemoRouteVo> util = new ExcelUtil<TestDemoRouteVo>(TestDemoRouteVo.class);
        return util.exportExcel(list, "测试单Route");
    }

    /**
     * 获取测试单Route详细信息
     */
    @ApiOperation("获取测试单Route详细信息")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:query')")
    @GetMapping("/{id}")
    public AjaxResult<TestDemoRouteVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iTestDemoRouteService.queryById(id));
    }

    /**
     * 新增测试单Route
     */
    @ApiOperation("新增测试单Route")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:add')")
    @Log(title = "测试单Route", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TestDemoRouteAddBo bo) {
        return toAjax(iTestDemoRouteService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改测试单Route
     */
    @ApiOperation("修改测试单Route")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:edit')")
    @Log(title = "测试单Route", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TestDemoRouteEditBo bo) {
        return toAjax(iTestDemoRouteService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 修改测试单Route
     */
    @ApiOperation("修改测试单Route")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:edit')")
    @Log(title = "测试单Route", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping("change")
    public AjaxResult<Void> change(@Validated @RequestBody TestDemoRouteEditBo bo) {
        return toAjax(iTestDemoRouteService.changeByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除测试单Route
     */
    @ApiOperation("删除测试单Route")
    // @PreAuthorize("@ss.hasPermi('demo:demoRoute:remove')")
    @Log(title = "测试单Route" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iTestDemoRouteService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
