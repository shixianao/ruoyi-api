package com.erp.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.common.annotation.Log;
import com.erp.common.annotation.RepeatSubmit;
import com.erp.common.core.controller.BaseController;
import com.erp.common.core.domain.AjaxResult;
import com.erp.common.core.page.TableDataInfo;
import com.erp.common.enums.BusinessType;
import com.erp.common.utils.poi.ExcelUtil;
import com.erp.demo.bo.TestDemoAddBo;
import com.erp.demo.bo.TestDemoEditBo;
import com.erp.demo.bo.TestDemoQueryBo;
import com.erp.demo.domain.TestDemo;
import com.erp.demo.service.ITestDemoService;
import com.erp.demo.vo.TestDemoVo;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

/**
 * 测试单Controller
 *
 * @author erp
 * @date 2022-03-19
 */
@Api(value = "测试单控制器", tags = {"测试单管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/demo/demo")
public class TestDemoController extends BaseController {

    private final ITestDemoService iTestDemoService;

    /**
     * 查询测试单列表
     */
    @ApiOperation("查询测试单列表")
//    @PreAuthorize("@ss.hasPermi('demo:demo:list')")
    @GetMapping("/list")
    public TableDataInfo<TestDemoVo> list(@Validated TestDemoQueryBo bo) {
        return iTestDemoService.queryPageList(bo);
    }

	@ApiOperation("导入")
//	@PreAuthorize("@ss.hasPermi('demo:demo:import')")
//	@Log(title = "Demo导入", businessType = BusinessType.IMPORT)
	@PostMapping("/importData")
	public AjaxResult importData(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary"))) @Valid @RequestPart("file") MultipartFile file)
			throws Exception {
		
		ExcelUtil<TestDemoVo> util = new ExcelUtil<TestDemoVo>(TestDemoVo.class);
		List<TestDemoVo> scoreInfoList = util.importExcel(file.getInputStream(), 1);
		List<TestDemoVo> scoreInfoList2Save = scoreInfoList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
		for (TestDemoVo testDemoVo : scoreInfoList2Save) {
			testDemoVo.setStatus("1");
		}
		// TODO 具体导入逻辑需要在service里实现
		boolean hasError = !iTestDemoService.saveBatch(BeanUtil.copyToList(scoreInfoList2Save, TestDemo.class, null));
		if (hasError) {
			AjaxResult r = util.exportExcel(scoreInfoList, "导入客户信息有误", 1);
			r.setCode(555);
			return r;
		} else {
			return AjaxResult.success();
		}
	}

    /**
     * 导出测试单列表
     */
    @ApiOperation("导出测试单列表")
//    @PreAuthorize("@ss.hasPermi('demo:demo:export')")
    @Log(title = "测试单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TestDemoVo> export(@Validated TestDemoQueryBo bo) {
        List<TestDemoVo> list = iTestDemoService.queryList(bo);
        ExcelUtil<TestDemoVo> util = new ExcelUtil<TestDemoVo>(TestDemoVo.class);
        return util.exportExcel(list, "测试单");
    }

    /**
     * 获取测试单详细信息
     */
    @ApiOperation("获取测试单详细信息")
//    @PreAuthorize("@ss.hasPermi('demo:demo:query')")
    @GetMapping("/{id}")
    public AjaxResult<TestDemoVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iTestDemoService.queryById(id));
    }

    /**
     * 新增测试单
     */
    @ApiOperation("新增测试单")
//    @PreAuthorize("@ss.hasPermi('demo:demo:add')")
    @Log(title = "测试单", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TestDemoAddBo bo) {
        return toAjax(iTestDemoService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改测试单
     */
    @ApiOperation("修改测试单")
//    @PreAuthorize("@ss.hasPermi('demo:demo:edit')")
    @Log(title = "测试单", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TestDemoEditBo bo) {
        return toAjax(iTestDemoService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除测试单
     */
    @ApiOperation("删除测试单")
//    @PreAuthorize("@ss.hasPermi('demo:demo:remove')")
    @Log(title = "测试单" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iTestDemoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
