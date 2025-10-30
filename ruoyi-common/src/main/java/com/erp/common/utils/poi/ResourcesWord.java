package com.erp.common.utils.poi;

import lombok.Data;

@Data
public class ResourcesWord {

	private String content;

	private int paragNum = 0; // 段落的个数

	private int sentenceNum = 0; // 句子个数

	private int wordNum = 0; // 字体个数


}
