package com.jialian.api.domain.entity;

public class HouseStyleWithBLOBs extends HouseStyle {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6023697634990220447L;

	private String styleIntroduction;

    private String styleDetails;

    public String getStyleIntroduction() {
        return styleIntroduction;
    }

    public void setStyleIntroduction(String styleIntroduction) {
        this.styleIntroduction = styleIntroduction == null ? null : styleIntroduction.trim();
    }

    public String getStyleDetails() {
        return styleDetails;
    }

    public void setStyleDetails(String styleDetails) {
        this.styleDetails = styleDetails == null ? null : styleDetails.trim();
    }
}