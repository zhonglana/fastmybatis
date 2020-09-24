package com.lz.fastmybatis.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

public enum ResponseCodeEnum {
    // 业务行为
    RESPONSE_CODE_SUCCESS("success", 0),
    RESPONSE_CODE_BUSY("busy", 29001),
    RESPONSE_CODE_NOT_SUPPORT("not support", 29002),
    RESPONSE_CODE_INVALID_CONF("invalid conf", 29003),
    RESPONSE_CODE_INVALID_PARAMETER("invalid parameter", 29101),
    RESPONSE_CODE_ERROR("error", 29900),
    ;


    private static HashMap<Integer, ResponseCodeEnum> map = new HashMap<Integer, ResponseCodeEnum>();

    static {
        for (ResponseCodeEnum responseCodeEnum : ResponseCodeEnum.values()) {
            map.put(responseCodeEnum.getProtocolValue(), responseCodeEnum);
        }
    }

    private String protocolName;
    private int protocolValue;
    private Integer rawValue;

    ResponseCodeEnum(String protocolName, int protocolValue) {
        this.protocolName = protocolName;
        this.protocolValue = protocolValue;
    }

    @JsonCreator
    public static ResponseCodeEnum fromValue(Integer protocolValue) {
        if (null == protocolValue) {
            return RESPONSE_CODE_ERROR;
        }

        ResponseCodeEnum codeEnum = map.getOrDefault(protocolValue, RESPONSE_CODE_ERROR);
        codeEnum.setRawValue(protocolValue);
        return codeEnum;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public int getProtocolValue() {
        return protocolValue;
    }

    public void setRawValue(Integer rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public String toString() {
        return protocolName + " (" + protocolValue + ")";
    }

    @JsonValue
    public Integer toValue() {
        if (null != rawValue) {
            return rawValue;
        }
        return protocolValue;
    }
}
