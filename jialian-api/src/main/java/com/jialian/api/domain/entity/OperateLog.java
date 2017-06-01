package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable{
	
	private static final long serialVersionUID = -8508585222376795231L;

	private Long id;

    private String operateName;

    private Long operateId;

    private Short operateType;

    private Short fromType;

    private String type;

    private String ip;

    private String operateContent;

    private String operateModule;

    private String table;

    private Short tableOperateType;

    private Date createTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public Short getOperateType() {
        return operateType;
    }

    public void setOperateType(Short operateType) {
        this.operateType = operateType;
    }

    public Short getFromType() {
        return fromType;
    }

    public void setFromType(Short fromType) {
        this.fromType = fromType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule == null ? null : operateModule.trim();
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table == null ? null : table.trim();
    }

    public Short getTableOperateType() {
        return tableOperateType;
    }

    public void setTableOperateType(Short tableOperateType) {
        this.tableOperateType = tableOperateType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}