package com.erp.biz.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.regex.Matcher;

@Slf4j
public class ProjectReactor {

    private static final String GROUP_ID = "cn.iocoder.boot";
    private static final String ARTIFACT_ID = "yudao";
    private static final String PACKAGE_NAME = "cn.iocoder.yudao";
    private static final String TITLE = "xxx管理系统";

    private static String getProjectBaseDir() {
//    	return "D:\\project\\codeTemplate\\ruoyi-vue-pro-master";
    	return "D:\\project\\slb\\风控\\project\\risk-control-data-ui";
        // noinspection ConstantConditions
//        return StrUtil.subBefore(ProjectReactor.class.getClassLoader().getResource("").getFile(),
//                "/yudao-server", false).substring(1);
    }

    public static void main(String[] args) {
        String projectBaseDir = getProjectBaseDir();

        // ========== 配置，需要你手动修改 ==========
        String groupIdNew = "com.erp";
        String artifactIdNew = "xx";
        String packageNameNew = "com.erp";
        String titleNew = "xx管理系统";
        String projectBaseDirNew = projectBaseDir + "-xx"; // 一键改名后，“新”项目所在的目录
        // ==========                  ==========

        // 获得需要复制的文件
        log.info("[main][开始获得需要重写的文件]");
        Collection<File> files = listFiles(projectBaseDir);
        log.info("[main][需要重写的文件数量：{}，预计需要 5-10 秒]", files.size());
        // 写入文件
        files.forEach(file -> {
            String content = replaceFileContent(file, groupIdNew, artifactIdNew, packageNameNew, titleNew);
            writeFile(file, content, projectBaseDir, projectBaseDirNew, packageNameNew, artifactIdNew);
        });
        log.info("[main][重写完成]");
    }

    private static Collection<File> listFiles(String projectBaseDir) {
        Collection<File> files = FileUtils.listFiles(new File(projectBaseDir), null, true);
        files.removeIf(file -> file.getPath().contains("/target/") || file.getPath().contains("\\target\\"));
        files.removeIf(file -> file.getPath().contains("/node_modules/") || file.getPath().contains("\\node_modules\\"));
        files.removeIf(file -> file.getPath().contains("/.idea/") || file.getPath().contains("\\.idea\\")); // 移除 IDEA 自身的文件
        files.removeIf(file -> file.getPath().contains("/.git/") || file.getPath().contains("\\.git\\")); // 移除 Git 自身的文件
        files.removeIf(file -> file.getPath().contains("/dist/") || file.getPath().contains("\\dist\\")); // 移除 Node 编译出来的
        return files;
    }

    private static String replaceFileContent(File file, String groupIdNew,
                                             String artifactIdNew, String packageNameNew,
                                             String titleNew) {
        return FileUtil.readString(file, StandardCharsets.UTF_8)
                .replaceAll(GROUP_ID, groupIdNew)
                .replaceAll(PACKAGE_NAME, packageNameNew)
                .replaceAll(ARTIFACT_ID, artifactIdNew) // 必须放在最后替换，因为 ARTIFACT_ID 太短！
                .replaceAll(StrUtil.upperFirst(ARTIFACT_ID), StrUtil.upperFirst(artifactIdNew))
                .replaceAll(TITLE, titleNew);
    }

    private static void writeFile(File file, String fileContent, String projectBaseDir,
                                  String projectBaseDirNew, String packageNameNew, String artifactIdNew) {
        String newPath = file.getPath().replace(projectBaseDir, projectBaseDirNew) // 新目录
                .replace(PACKAGE_NAME.replaceAll("\\.", Matcher.quoteReplacement(File.separator)),
                        packageNameNew.replaceAll("\\.", Matcher.quoteReplacement(File.separator)))
                .replace(ARTIFACT_ID, artifactIdNew) //
                .replaceAll(StrUtil.upperFirst(ARTIFACT_ID), StrUtil.upperFirst(artifactIdNew));
        FileUtil.writeUtf8String(fileContent, newPath);
    }

}
