package com.jialian.api.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class ShopProdType  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5503125429714840057L;

	private Long id;

    private String sptypename;
    
    private String sptypeno;

    private Integer rank;

    private Long supid;

    private Integer sequence;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSptypename() {
        return sptypename;
    }

    public void setSptypename(String sptypename) {
        this.sptypename = sptypename == null ? null : sptypename.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getSupid() {
        return supid;
    }

    public void setSupid(Long supid) {
        this.supid = supid;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	/**
	 * @return sptypeno
	 */
	public String getSptypeno() {
		return sptypeno;
	}

	/**
	 * @param sptypeno 要设置的 sptypeno
	 */
	public void setSptypeno(String sptypeno) {
		this.sptypeno = sptypeno;
	}
    
}