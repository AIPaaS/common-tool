package com.ai.paas;

/**
 * pass 层运行异常定义
 *
 */
public class GeneralRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -8886495803406807620L;
    private final String errCode;
    private final String errDetail;

    public GeneralRuntimeException(String errDetail) {
        super(errDetail);
        this.errCode = Constant.ERROR_RESULT + "";
        this.errDetail = errDetail;
    }

    public GeneralRuntimeException(String errCode, String errDetail) {
        super(errCode + ":" + errDetail);
        this.errCode = errCode;
        this.errDetail = errDetail;
    }

    public GeneralRuntimeException(String errCode, Exception ex) {
        super(errCode, ex);
        this.errCode = errCode;
        this.errDetail = "";
    }

    public GeneralRuntimeException(String errCode, String errDetail, Exception ex) {
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
