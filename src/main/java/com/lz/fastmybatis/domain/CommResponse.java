package com.lz.fastmybatis.domain;

public class CommResponse {
    private Integer code;
    private String msg;
    private Object data;

    public static CommResponse success(){
        CommResponse commResponse = new CommResponse(ResponseCodeEnum.RESPONSE_CODE_SUCCESS);
        return commResponse;
    }

    public static CommResponse success(Object data){
        CommResponse commResponse = new CommResponse(ResponseCodeEnum.RESPONSE_CODE_SUCCESS);
        commResponse.setData(data);
        return commResponse;
    }

    public static CommResponse invalidParameter(String msg){
        CommResponse commResponse = new CommResponse(ResponseCodeEnum.RESPONSE_CODE_INVALID_PARAMETER, msg);
        return commResponse;
    }

    public CommResponse setError(ResponseCodeEnum responseCode, String msg){
        setCode(responseCode.getProtocolValue());
        setMsg(responseCode.getProtocolName() + ":" + msg);
        return this;
    }

    public CommResponse() {
    }

    public CommResponse(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getProtocolValue();
        this.msg = responseCodeEnum.getProtocolName();
    }

    public CommResponse(ResponseCodeEnum responseCodeEnum, String err) {
        this.code = responseCodeEnum.getProtocolValue();
        this.msg = responseCodeEnum.getProtocolName() + ":" + err;
    }

    public CommResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setResponse(ResponseCodeEnum response) {
        this.code = response.getProtocolValue();
        this.msg = response.getProtocolName();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
