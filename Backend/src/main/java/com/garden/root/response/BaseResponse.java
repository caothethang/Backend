package com.garden.root.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    protected int rc;
    protected String rd;
    protected Integer status;
    protected T data;
    
    public void setSuccess() {
        this.rc = 0;
        this.rd = "OK";
        this.status = 200;
    }
    
    public void setFailed(int rc, String rd,Integer status) {
        this.rc = rc;
        this.rd = rd;
        this.status = status;
    }
    public String info() {
        return "rc=" + rc + "|rd=" + rd;
    }
    
}
