package com.alibaba.testme.client.common;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

public class FeedbackResult implements Serializable {
    private static final long serialVersionUID = -4228782165603161299L;

    //Container端运行Job是否成功
    @JSONField(name = "STATUS")
    private boolean           status;

    //表示container接收的指令类型
    private String            directionType;

    //错误信息
    @JSONField(name = "ERROR_MSG")
    private String            errormsg;

    //返回的Job运行ID  
    @JSONField(name = "JOB_ID")
    private String            jobRunId;

    //返回的内容
    @JSONField(name = "CONTENT")
    private String            content;

    //返回的附加内容
    @JSONField(name = "KEY_VALUE")
    private Map<String, Long> keyValue;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getJobRunId() {
        return jobRunId;
    }

    public void setJobRunId(String jobRunId) {
        this.jobRunId = jobRunId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Long> getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Map<String, Long> keyValue) {
        this.keyValue = keyValue;
    }
}
