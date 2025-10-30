package com.erp.biz.common.constant;

public class ClOrderWhStatusConstant {
	
	private ClOrderWhStatusConstant() {}
	
    /** 出入库状态  1-待入库 2-部分入库 3-已入库 4-待出库 5-部分出库 6-已出库 */
	
	public final static String IN_WAIT = "1";
	public final static String IN_PART = "2";
	public final static String IN_FINISH = "3";
	
	public final static String OUT_WAIT = "4";
	public final static String OUT_PART = "5";
	public final static String OUT_FINISH = "6";

}
