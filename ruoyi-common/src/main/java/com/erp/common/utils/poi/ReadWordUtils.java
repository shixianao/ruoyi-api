package com.erp.common.utils.poi;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ReadWordUtils {
//	private static Logger logger = Logger.getLogger(ReadWordUtils.class);
	protected static final String CHARSET_UTF8 = "UTF-8";
	private static String  tempImagePath = "";

	/**
	 * 读取docx
	 * @throws Exception
	 */
	public static ResourcesWord readDocx(String path) throws Exception {
		InputStream is = new FileInputStream(path);
		return readDocx(is);
	}

	/**
	 * 读取docx
	 * @throws Exception
	 */
	public static ResourcesWord readDocx(InputStream is) throws Exception {
		ResourcesWord resourcesWord = new ResourcesWord();
		int paragNum = 0; // 段落的个数
		int sentenceNum = 0; // 句子个数
		int wordNum = 0; // 字体个数
		StringBuffer content = new StringBuffer();
		XWPFDocument doc = new XWPFDocument(is);
		List<XWPFParagraph> paras = doc.getParagraphs();
		for (XWPFParagraph para : paras) {
			// 当前段落的属性
			if (!StringUtils.isEmpty(para.getText())) {
				paragNum++;
				sentenceNum += para.getText().replace("\r\n", "").trim().split("。").length;
				content.append(para.getText());
				String[] countStr = para.getText().trim().split(" ");
				for (String s : countStr) {
					wordNum += getWordCountStrLength(s);
				}
			}
		}
		// 获取文档中所有的表格
		List<XWPFTable> tables = doc.getTables();
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		for (XWPFTable table : tables) {
			// 表格属性
			// 获取表格对应的行
			rows = table.getRows();
			for (XWPFTableRow row : rows) {
				// 获取行对应的单元格
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					content.append(cell.getText());
					String[] countStr = cell.getText().trim().split(" ");
					for (String s : countStr) {
						wordNum += getWordCountStrLength(s);
					}
				}
			}


			/*
			 * MongoDBUtils mongoDb = new MongoDBUtils("javadb"); DBObject dbs =
			 * new BasicDBObject(); dbs.put("name", "创新性"); //分类
			 * dbs.put("major", "医疗"); //专业 dbs.put("content",
			 * content.toString().trim()); dbs.put("paragNum", paragNum);
			 * dbs.put("sentenceNum", sentenceNum); dbs.put("wordNum", wordNum);
			 * mongoDb.insert(dbs, "javadb");
			 */
		}

		// 得到全部内容的字数
		resourcesWord.setContent(content.toString());
		resourcesWord.setParagNum(paragNum);
		resourcesWord.setSentenceNum(sentenceNum);
		resourcesWord.setWordNum(wordNum);
		close(is);
		return resourcesWord;
	}

	/**
	 * 读取doc文件的内容
	 *
	 * @throws IOException
	 */
	public static ResourcesWord readDoc(String path) throws Exception {
		File f = new File(path);
		FileInputStream is = new FileInputStream(f);
		return readDoc(is);
	}

	/**
	 * 读取doc文件的内容
	 *
	 * @throws IOException
	 */
	public static ResourcesWord readDoc(InputStream is) throws IOException {
		int paragNum = 0; // 段落的个数
		int sentenceNum = 0; // 句子个数
		int wordNum = 0; // 字体个数
		ResourcesWord resourcesWord = new ResourcesWord();
		StringBuffer content = new StringBuffer();
		try {

			WordExtractor ex = new WordExtractor(is);// is是WORD文件的InputStream
			String[] paragraph = ex.getParagraphText();
			for (int i = 0; i < paragraph.length; i++) {
				paragNum++;
				sentenceNum += paragraph[i].replace("\r\n", "").trim().split("。").length;
				String[] countStr = paragraph[i].trim().split(" ");
				for (String s : countStr) {
					wordNum += getWordCountStrLength(s);
				}
				content.append(paragraph[i].trim());
			}

			resourcesWord.setContent(content.toString());
			resourcesWord.setParagNum(paragNum);
			resourcesWord.setSentenceNum(sentenceNum);
			resourcesWord.setWordNum(wordNum);
			/*
			 * MongoDBUtils mongoDb = new MongoDBUtils("javadb"); DBObject dbs =
			 * new BasicDBObject(); dbs.put("name", "创新性"); //分类
			 * dbs.put("major", "医疗"); //专业 dbs.put("content",
			 * content.toString()); dbs.put("paragNum", paragNum);
			 * dbs.put("sentenceNum", sentenceNum); dbs.put("wordNum", wordNum);
			 * mongoDb.insert(dbs, "javadb");
			 */
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourcesWord;
	}


	/**
	 * \brief doc转换成html,并返回输出的相对路径
	 * @param filePath                  :要转换的doc文档
	 * @param outPutFilePath                :文档输出的位置
	 * @attention
	 * @author toto
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ParserConfigurationException
	 * @date 2017年2月27日
	 * @note  begin modify by 涂作权  2017年2月27日   原始创建
	 */
//	public static String doc2Html(
//		String filePath,
//		final String outPutFilePath)
//		throws TransformerException, IOException, ParserConfigurationException {
//
//		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(filePath));
//		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//			DocumentBuilderFactory
//				.newInstance()
//				.newDocumentBuilder()
//				.newDocument());
//
//		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
//				//File file = new File(outPutFilePath);
//				//String name = file.getName();
//				tempImagePath = outPutFilePath.substring(0,outPutFilePath.indexOf(".html")) + File.separator;
//
//				File imageFolder = new File(tempImagePath);
//				if (!imageFolder.exists()) {
//					try {
//						FileUtils.forceMkdir(imageFolder);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				String newTempImagePath = imageFolder.getPath().replace(imageFolder.getParentFile().getPath() + File.separator, "");
//				return newTempImagePath + File.separator + suggestedName;
//			}
//		});
//		wordToHtmlConverter.processDocument(wordDocument);
//		// 保存图片
//		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
//		if (pics != null) {
//			for (int i = 0; i < pics.size(); i++) {
//				Picture pic = (Picture) pics.get(i);
//				try {
//					File picOutFolder = new File(tempImagePath + File.separator);
//					if (!picOutFolder.exists()) {
//						picOutFolder.mkdirs();
//					}
//					pic.writeImageContent(new FileOutputStream(tempImagePath + File.separator + pic.suggestFullFileName()));
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		Document htmlDocument = wordToHtmlConverter.getDocument();
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		DOMSource domSource = new DOMSource(htmlDocument);
//		StreamResult streamResult = new StreamResult(out);
//
//		TransformerFactory tf = TransformerFactory.newInstance();
//		Transformer serializer = tf.newTransformer();
//		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
//		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//		serializer.setOutputProperty(OutputKeys.METHOD, "html");
//		serializer.transform(domSource, streamResult);
//		out.close();
//		writeFile(new String(out.toByteArray()), outPutFilePath);
//		return gainRelativePathByOutputPath(outPutFilePath);
//	}

	/**
	 * 将docx格式的word转换为html格式的文档
	 *
	 * @param filePath 原始的docx文件路径存储位置
	 * @param outPutFile html输出文件路径
	 * @return
	 * @throws TransformerException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
//	public static String docx2Html(String filePath,
//								   final String outPutFilePath)
//		throws TransformerException, IOException, ParserConfigurationException {
//		//String fileOutName = outPutFile;
//		XWPFDocument wordDocument = new XWPFDocument(new FileInputStream(filePath));
//		XHTMLOptions options = XHTMLOptions.create().indent(4);
//
//		// 导出图片
//		Map<String, String> imageInfoMap = gainTempImagePath(outPutFilePath);
//		File imageFolder = new File(imageInfoMap.get("imageStoredPath"));
//		options.setExtractor(new FileImageExtractor(imageFolder));
//		// URI resolver
//		//这种方式获得word中的图片地址是绝对地址
//		//options.URIResolver(new FileURIResolver(imageFolder));
//		//设置生成的html中的img src中的地址是相对路径
//		options.URIResolver(new BasicURIResolver(imageInfoMap.get("imageFolder")));
//
//		File outFile = new File(outPutFilePath);
//		outFile.getParentFile().mkdirs();
//		OutputStream out = new FileOutputStream(outFile);
//		XHTMLConverter.getInstance().convert(wordDocument, out, options);
//
//		return gainRelativePathByOutputPath(outPutFilePath);
//		//System.out.println("Generate " + fileOutName + " with " + (System.currentTimeMillis() - startTime) + " ms.");
//	}

	/**
	 * \brief 将内容写到path路径下面
	 * @param content            :文档内容
	 * @param path               :最终的文件存储路径
	 * @attention 方法的使用注意事项
	 * @author toto
	 * @date 2017年2月27日
	 * @note  begin modify by 涂作权 2017年2月27日   修改输出的文件名称
	 */
//	public static void writeFile(String docContent, String path) {
//		FileOutputStream outDocFos = null;
//		try {
//			//判断文件是否为空的
//			if (StringUtils.isNotBlank(path)) {
//				File file = new File(path);
//				if (!file.exists()) {
//					FileUtils.forceMkdir(file.getParentFile());
//				}
//
//				outDocFos = new FileOutputStream(path);
//				IOUtils.write(docContent, outDocFos,CHARSET_UTF8);
//			}
//		} catch (FileNotFoundException fnfe) {
//			fnfe.printStackTrace();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		} finally {
//			try {
//				if (outDocFos != null)
//					outDocFos.close();
//			} catch (IOException ie) {
//			}
//		}
//	}

	/**
	 * 关闭输入流
	 *
	 * @param is
	 */
	private static void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * \brief 通过文档输出路径获得图片存储路径
	 * @param outPutFile             :文档输出路径
	 * @return
	 * @attention 方法的使用注意事项
	 * @author toto
	 * @date 2017年2月28日
	 * @note  begin modify by 修改人 修改时间   修改内容摘要说明
	 */
	private static Map<String, String> gainTempImagePath(String outPutFilePath) {
		Map<String,String> imageInfoMap = new HashMap<String,String>();
		try {
			//File file = new File(outPutFilePath);
			tempImagePath = outPutFilePath.substring(0,outPutFilePath.indexOf(".html")) + File.separator;

			File imageFolder = new File(tempImagePath);
			if (!imageFolder.exists()) {
				try {
					FileUtils.forceMkdir(imageFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			//System.out.println(imageFolder.getPath().replace(imageFolder.getParentFile().getPath() + File.separator, ""));
			//return imageFolder.getPath().replace(imageFolder.getParentFile().getPath() + File.separator, "");

			imageInfoMap.put("imageStoredPath", imageFolder.getPath());
			imageInfoMap.put("imageFolder", imageFolder.getPath().replace(imageFolder.getParentFile().getPath(), "").replace(File.separator, ""));

			return imageInfoMap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String gainRelativePathByOutputPath(String outPutFilePath) {
		//用于预览的存储路径
//		String docsPreviewPath = ExtendedServerConfig.getInstance().getStringProp("DOCS_PREVIEW_PREFIX");
//		return  outPutFilePath.split(docsPreviewPath)[1];
		return  null;
	}

	/**
	 * \brief
	 * @param orgStr            :表示要替换的就得字符串
	 * @param regEx             :表示的是正则表达式
	 * @param targetStr         :表示要替换的字符串
	 * @return
	 * @attention 方法的使用注意事项
	 * @author toto
	 * @date 2017年3月4日
	 * @note  begin modify by 涂作权  原始创建  2017年3月4日
	 */
	public static String replaceStr(String orgStr,String regEx,String targetStr){
		if (null !=orgStr && !"".equals(orgStr.trim())) {
			//String regEx="[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(orgStr);
			return m.replaceAll(targetStr);
		}
		return null;
	}

	public static int getWordCountStrLength(String str){
		int count = 0;
		char[] chars = str.toCharArray();
		boolean lastIsLetter = false;
		for (char c : chars) {
			boolean isLetter = isLetter(c);
			if (isLetter) {
				lastIsLetter = true;
			} else {
				if (lastIsLetter) {
					count = count + 2;
				} else {
					count++;
				}
				lastIsLetter = false;
			}
		}
		if (lastIsLetter) {
			count++;
		}
		return count;
	}

	public static boolean isLetter(Character character){
		if (character > 32 && character <127) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
//		String uploadFile = ExtendedServerConfig.getInstance().getStringProperty("UPLOAD_PATH");
//		String docsTempPath = ExtendedServerConfig.getInstance().getStringProperty("DOCS_TEMP_PATH");
//		String docsOutputPath = ExtendedServerConfig.getInstance().getStringProp("DOCS_OUTPUT_PATH");
//		System.out.println("uploadFile = " + uploadFile + "  " + docsTempPath + "  " + docsOutputPath);
//
		// Testtest.readWord("E://111.doc");
		// Testtest.readDoc();
		// System.out.println(content);
//		ResourcesWord readDocx = ReadWordUtils.readDoc(uploadFile + "/大学生创新创业项目申报书.doc");
//		logger.info(readDocx.getContent());
//		logger.info(readDocx.getParagNum());
//
//		new ReadWordUtils().doc2Html(uploadFile + "/大学生创新创业项目申报书.doc" , docsOutputPath + "/大学生创新创业项目申报书.html");
		//new ReadWordUtils().docx2Html(uploadFile + "/大学生创新创业项目申报书副本.docx" , docsOutputPath + "/大学生创新创业项目申报书副本.html");

//		String newStr = replaceStr("afdas//\\as   dfasd     a//asd\\\\\\asd\\/", "[\\\\]","/");
//		newStr = replaceStr(newStr, "(/){1,}", "/");
//		newStr = replaceStr(newStr, "[ ]", "");
//
//		System.out.println(newStr);

		System.out.println(getWordCountStrLength("%￥$11"));

	}
}
