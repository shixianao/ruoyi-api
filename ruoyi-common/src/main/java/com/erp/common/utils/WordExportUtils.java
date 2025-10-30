package com.erp.common.utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Word导出工具类
 * 使用easypoi框架实现Word文档导出功能
 */
@Component
public class WordExportUtils {

    /**
     * 根据模板导出Word文档
     *
     * @param templatePath 模板文件路径
     * @param data         导出数据
     * @param response     HttpServletResponse对象
     * @param fileName     导出文件名
     * @throws IOException 导出失败时抛出异常
     */
    public static void exportWord(String templatePath, Map<String, Object> data, 
                                 HttpServletResponse response, String fileName) throws IOException {
        // 创建Word文档对象
        XWPFDocument document = WordExportUtil.exportWord07(templatePath, data);
        
        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-Disposition", 
                "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8") + ".docx");
        
        // 输出文档
        OutputStream outputStream = response.getOutputStream();
        document.write(outputStream);
        outputStream.flush();
        outputStream.close();
        document.close();
    }
    
    /**
     * 根据模板导出Word文档
     *
     * @param templatePath 模板文件路径
     * @param data         导出数据
     * @return XWPFDocument对象
     * @throws IOException 导出失败时抛出异常
     */
    public static XWPFDocument exportWord(String templatePath, Map<String, Object> data) throws IOException {
        return WordExportUtil.exportWord07(templatePath, data);
    }
}