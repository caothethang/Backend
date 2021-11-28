package com.garden.root.response;

import com.garden.root.common.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class BaseResponse {
    protected int rc;
    protected String rd;
    protected Integer status;
    
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
