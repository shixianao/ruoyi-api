package com.erp.biz.test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaProjectReactor {

	private static final String TEMPLATE_FROM = "xxx-parent";
	private static final String TEMPLATE_APPLICATION = "RuoYiApplication";
	private static final String TEMPLATE_SI = "RuoYiServletInitializer";
	private static final String TEMPLATE_EN_XXX = "ruoyi";
	private static final String TEMPLATE_CN_XXX = "若依管理系统";

	// ========== 配置，需要你手动修改 ==========
	private static final String EN_XXX = "ruoyi";
	private static final String EN_UPPER_XXX = "RuoYi";
	private static final String CN_XXX = "若依管理系统";
	// ========== ==========

	private static final String EN_APPLICATION = EN_UPPER_XXX + "Application";
	private static final String EN_SI = EN_UPPER_XXX + "ServletInitializer";


	public static void main(String[] args) {
		String projectBaseDir = getProjectBaseDir();
		String projectBaseDirNew = projectBaseDir.replace(TEMPLATE_FROM, EN_XXX + "-parent"); // 项目所在的目录

		// 获得需要复制的文件
		log.info("[main][开始获得需要重写的文件]");
		Collection<File> files = listFiles(projectBaseDir);
		log.info("[main][需要重写的文件数量：{}，预计需要 5-10 秒]", files.size());
		// 写入文件
		files.forEach(file -> {
			String content = replaceFileContent(file);
			writeFile(file, content, projectBaseDir, projectBaseDirNew);
		});
		log.info("[main][重写完成]" + projectBaseDirNew);
	}

	private static String getProjectBaseDir() {
		return "F:\\project\\codeTemplate\\" + TEMPLATE_FROM;
	}

	private static Collection<File> listFiles(String projectBaseDir) {
		Collection<File> files = FileUtils.listFiles(new File(projectBaseDir), null, true);
		files.removeIf(file -> file.getPath().contains("/target/") || file.getPath().contains("\\target\\"));
		files.removeIf(
				file -> file.getPath().contains("/node_modules/") || file.getPath().contains("\\node_modules\\"));
		files.removeIf(file -> file.getPath().contains("/.idea/") || file.getPath().contains("\\.idea\\")); // 移除 IDEA
																											// 自身的文件
		files.removeIf(file -> file.getPath().contains("/.git/") || file.getPath().contains("\\.git\\")); // 移除 Git
																											// 自身的文件
		files.removeIf(file -> file.getPath().contains("/dist/") || file.getPath().contains("\\dist\\")); // 移除 Node
		files.removeIf(file -> file.getPath().contains("/.settings/") || file.getPath().contains("\\.settings\\")); // 移除 Eclipse
		files.removeIf(file -> file.getPath().contains("/.classpath") || file.getPath().contains("\\.classpath")); // 移除 Eclipse
		files.removeIf(file -> file.getPath().contains("/.project") || file.getPath().contains("\\.project")); // 移除 Eclipse
		files.removeIf(file -> file.getPath().contains("/.factorypath") || file.getPath().contains("\\.factorypath")); // 移除 Eclipse

		return files;
	}

	private static String replaceFileContent(File file) {
		return FileUtil.readString(file, StandardCharsets.UTF_8).replaceAll(TEMPLATE_EN_XXX, EN_XXX)
				.replaceAll(TEMPLATE_CN_XXX, CN_XXX)
				.replaceAll(TEMPLATE_APPLICATION, EN_APPLICATION)
				.replaceAll(TEMPLATE_SI, EN_SI);
	}

	private static void writeFile(File file, String fileContent, String projectBaseDir, String projectBaseDirNew) {
		String newPath = file.getPath().replace(projectBaseDir, projectBaseDirNew)
				.replaceAll(TEMPLATE_EN_XXX, EN_XXX)
				.replaceAll(TEMPLATE_APPLICATION, EN_APPLICATION)
				.replaceAll(TEMPLATE_SI, EN_SI);
		FileUtil.writeUtf8String(fileContent, newPath);
	}

}
