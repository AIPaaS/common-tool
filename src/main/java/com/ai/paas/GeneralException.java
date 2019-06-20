package com.ai.paas;

/**
 * pass 层普通异常定义
 *
 */
public class GeneralException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -2084191514082840768L;
    private final String errCode;
    private final String errDetail;

    public GeneralException(String errDetail) {
        super(errDetail);
        this.errCode = Constant.ERROR_RESULT + "";
        this.errDetail = errDetail;
    }

    public GeneralException(String errCode, String errDetail) {
        super(errCode + ":" + errDetail);
        this.errCode = errCode;
        this.errDetail = errDetail;
    }

    public GeneralException(String errCode, Exception ex) {
        super(errCode, ex);
        this.errCode = errCode;
        this.errDetail = "";
    }

    public GeneralException(String errCode, String errDetail, Exception ex) {
        super(errCode + ":" + errDetail, ex);
        this.errCode = errCode;
        this.errDetail = errDetail;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDetail() {
        return errDetail;
    }

}
