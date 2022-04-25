package com.ecommerce.root.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
    
    public void setFailed(int rc, String rd) {
        this.rc = rc;
        this.rd = rd;
    }
    public String info() {
        return "rc=" + rc + "|rd=" + rd;
    }
    
}
