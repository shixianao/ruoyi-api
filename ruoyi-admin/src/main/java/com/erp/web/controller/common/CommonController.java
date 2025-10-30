package com.erp.web.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.common.config.ErpConfig;
import com.erp.common.constant.Constants;
import com.erp.common.core.domain.AjaxResult;
import com.erp.common.utils.file.FileTypeUtils;
import com.erp.common.utils.file.FileUploadUtils;
import com.erp.common.utils.file.FileUtils;
import com.erp.framework.config.ServerConfig;

import cn.hutool.core.util.StrUtil;

/**
 * 通用请求处理
 *
 * @author erp
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StrUtil.format("文件名称({})非法，不允许下载。 ", fileName));
            }
//            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = ErpConfig.getDownloadPath() + fileName;
            String fileType = FileTypeUtils.getFileType(fileName);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            if (fileType.equals("xls") || fileType.equals("xlsx")) {
    			try (XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.getInputStream(filePath))) {
    				workbook.write(response.getOutputStream());
    			}
            } else {
				FileUtils.writeToStream(filePath, response.getOutputStream());
            }
            if (delete)
            {
				FileUtils.del(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = ErpConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
			Map<String,Object> ajax = new HashMap<>();
			ajax.put("originalFilename", file.getOriginalFilename());
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return AjaxResult.success(ajax);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StrUtil.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = ErpConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StrUtil.subAfter(resource, Constants.RESOURCE_PREFIX,false);
            // 下载名称
            String downloadName = StrUtil.subAfter(downloadPath, "/",true);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeToStream(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
