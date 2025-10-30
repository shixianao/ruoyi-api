package com.erp.biz.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.common.exception.CustomException;
import com.erp.common.utils.file.FileUtils;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(value = "业务通用控制器", tags = { "通用" })
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/biz/common")
public class BizCommonController {
	private static final Logger log = LoggerFactory.getLogger(BizCommonController.class);

	@ApiOperation("下载Excel模板")
	@GetMapping("/download/excel/template")
	public void fileDownload(
			@ApiParam(name = "要下载的文件名", required = true) String fileName,
			@ApiParam(required = false) Long merchId,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			fileName = URLDecoder.decode(fileName, "utf-8");
			if (!FileUtils.checkAllowDownload(fileName)) {
				throw new CustomException(StrUtil.format("文件名称({})非法，不允许下载。 ", fileName));
			}
			ClassPathResource resource = new ClassPathResource("excelTemplate/" + fileName);
			InputStream inputStream = resource.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				FileUtils.setAttachmentResponseHeader(response, fileName);
//				if (fileName.equals("库存订单导入.xlsx")) {
//					List<ClExcelStockVo> list = new ArrayList<>();
//					ClGoodsQueryBo bo = new ClGoodsQueryBo();
//					bo.setMerchId(merchId);
//					List<ClGoodsVo> goods = iClGoodsService.queryList(bo);
//					List<ClWarehouseVo> whList = iClWarehouseService.queryList(new ClWarehouseQueryBo(), merchId);
//					int index = 1;
//					for (ClWarehouseVo wh : whList) {
//						for (ClGoodsVo g : goods) {
//							ClExcelStockVo v = new ClExcelStockVo();
//							v.setBarCode(g.getBarCodes());
//							v.setGoodsName(g.getGoodsName());
//							v.setVendorName(g.getVendorName());
//							v.setBrandName(g.getBrandName());
//							v.setNo(String.valueOf(index++));
//							v.setUnitName(g.getUnitName());
//							v.setWhName(wh.getName());
//							list.add(v);
//						}
//					}
//					ExcelUtil<ClExcelStockVo> util = new ExcelUtil<ClExcelStockVo>(ClExcelStockVo.class);
//					if (list.size() > 0) {
//						util.exportExcel(list, 1, workbook);
//					}
//				}
				workbook.write(response.getOutputStream());
			}
		} catch (CustomException e) {
			throw e;
		} catch (FileNotFoundException e2) {
			log.error("下载文件失败", e2);
			throw new CustomException("没有这个文件");
		} catch (Exception e) {
			log.error("下载文件失败", e);
			throw new CustomException("下载文件失败");
		}
	}
}
