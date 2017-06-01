package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class HouseStructureCategory implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8604415583610410304L;

	private Long id;

    private String name;

    private Short type;

    private Date createTime;

    private Short status;

    private Long houseStructureId;

    private HouseStructureInfo houseStructureInfo;
    
    private Long resourceInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getHouseStructureId() {
        return houseStructureId;
    }

    public void setHouseStructureId(Long houseStructureId) {
        this.houseStructureId = houseStructureId;
    }

    public Long getResourceInfoId() {
        return resourceInfoId;
    }

    public void setResourceInfoId(Long resourceInfoId) {
        this.resourceInfoId = resourceInfoId;
    }

	public HouseStructureInfo getHouseStructureInfo() {
		return houseStructureInfo;
	}

	public void setHouseStructureInfo(HouseStructureInfo houseStructureInfo) {
		this.houseStructureInfo = houseStructureInfo;
	}
}