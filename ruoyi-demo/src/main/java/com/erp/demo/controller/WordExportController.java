package com.erp.demo.controller;

import com.erp.common.utils.WordExportUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Word导出示例控制器
 */
@RestController
@RequestMapping("/demo/word")
public class WordExportController {

    /**
     * 导出用户基本信息示例
     *
     * @param response HttpServletResponse对象
     * @throws IOException 导出失败时抛出异常
     */
    @GetMapping("/exportUserInfo")
    public void exportUserInfo(HttpServletResponse response) throws IOException {
        // 准备导出数据
        Map<String, Object> data = new HashMap<>();
        data.put("name", "张三");
        data.put("role", "管理员");
        data.put("phone", "13800138000");
        data.put("email", "zhangsan@example.com");
        
        // 获取模板路径
        String templatePath = getClass().getClassLoader().getResource("templates/user_template.docx").getPath();
        
        // 导出Word文档
        WordExportUtils.exportWord(templatePath, data, response, "用户信息");
    }
}
