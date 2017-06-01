package com.jialian.api.domain.basic;

import java.io.Serializable;

/** 
 *@Description:开始页，结束页封装 
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since: 2015年12月2日
 *@Version:1.0
 */
public class PageIndex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 417706425599058683L;

	/** 
     * 结束页 
     */  
    private int endIndex;  
  
    /** 
     * 开始页 
     */  
    private int startIndex;  
  
    /** 
     * 计算开始页和结束页 
     *  
     * @param viewpagecount 
     *            页面中要显示的页面个数 
     * @param currentPage 
     *            当前页 
     * @param totalpage 
     *            总页面数 
     * @return PageIndex 记录开始页（startindex）和结束页（endindex） 
     */  
    public static PageIndex getPageIndex(int viewpagecount, int currentPage,  
            int totalpage) {  
        int startpage = currentPage
                - (viewpagecount % 2 == 0 ? viewpagecount / 2 - 1  
                        : viewpagecount / 2);  
        int endpage = currentPage + viewpagecount / 2;  
        if (startpage < 1) {  
            startpage = 1;  
            if (totalpage >= viewpagecount)  
                endpage = viewpagecount;  
            else  
                endpage = totalpage;  
        }  
        if (endpage > totalpage) {  
            endpage = totalpage;  
            if ((endpage - viewpagecount) > 0)  
                startpage = endpage - viewpagecount + 1;  
            else  
                startpage = 1;  
        }  
        return new PageIndex(startpage, endpage);  
    }  
  
    public PageIndex(int startIndex, int endIndex) {  
        this.startIndex = startIndex;  
        this.endIndex = endIndex;  
    }  
  
    public int getEndIndex() {  
        return endIndex;  
    }  
  
    public void setEndIndex(int endIndex) {  
        this.endIndex = endIndex;  
    }  
  
    public int getStartIndex() {  
        return startIndex;  
    }  
  
    public void setStartIndex(int startIndex) {  
        this.startIndex = startIndex;  
    }  
  
}
