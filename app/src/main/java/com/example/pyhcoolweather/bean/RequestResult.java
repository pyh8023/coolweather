package com.example.pyhcoolweather.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11.
 */
public class RequestResult<T> {
    private int errNum;
    private String errMsg;
    private List<T> retData;

    public List<T> getRetData() {
        return retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RequestResult(int errNum, String errMsg, List<T> retData) {
        this.errNum = errNum;
        this.errMsg = errMsg;
        this.retData = retData;
    }
}
