package com.philips.healthSystems.admin.response;



public class ExcelResult {
    private String ResultCode = "0000";
	private String ResultMsg = "정상";
    private String ExcelMsg = "";
    private int SuccessCnt = 0;
    private int FailureCnt = 0;
    
    public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getResultMsg() {
		return ResultMsg;
	}
	public void setResultMsg(String resultMsg) {
		ResultMsg = resultMsg;
	}
	public String getExcelMsg() {
		return ExcelMsg;
	}
	public void setExcelMsg(String excelMsg) {
		ExcelMsg = excelMsg;
	}
	public int getSuccessCnt() {
		return SuccessCnt;
	}
	public void setSuccessCnt(int successCnt) {
		SuccessCnt = successCnt;
	}
	public int getFailureCnt() {
		return FailureCnt;
	}
	public void setFailureCnt(int failureCnt) {
		FailureCnt = failureCnt;
	}
}