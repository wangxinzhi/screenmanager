package com.system.pojo;

/**
 * 页码实体
 */
public class Page {
    //当前页码,默认为1
    private int currentPageNo=1;
    //总页数
    private int totalCount;
    //页面容量
    private int pageSize=5;
    //上一页
    private int upPageNo;
    //下一页
    private int nextPageNo;
    //要前往的页码,默认是0
    private int toPageNo=0;

    public void setToPageNo(Integer toPageNo){
        //新一页
        this.toPageNo=(toPageNo-1)*pageSize;
        setCurrentPageNo(toPageNo);
    }

    public Integer getToPageNo(){
        return this.toPageNo;
    }

    public int getCurrentPageNo() {
        return this.currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo){
        if (currentPageNo!=1){
            this.upPageNo=currentPageNo-1;
        }
        this.nextPageNo=currentPageNo+1;
        this.currentPageNo=currentPageNo;
    }

    public int getTotalCount(){
        return this.totalCount;
    }

    public void setTotalCount(int totalCount){
        if (totalCount%pageSize>0){
            this.totalCount=(totalCount/pageSize)+1;
        }else {
            this.totalCount=totalCount/pageSize;
        }
    }

    public int getPageSize(){
        return this.pageSize;
    }

    public void setPageSize(int pageSize){
        this.pageSize=pageSize;
    }

    public int getUpPageNo(){
        return this.upPageNo;
    }

    public void setUpPageNo(int upPageNo){
        this.upPageNo=upPageNo;
    }

    public int getNextPageNo(){
        return this.nextPageNo;
    }

    public void setNextPageNo(int nextPageNo){
        this.nextPageNo=nextPageNo;
    }

    /*private int start=0;
    private int last=0;
    private int pageSize=5;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void caulLast(int total){
        if (0==total%pageSize){
            this.last=total-pageSize;
        }else {
            this.last=total-total%pageSize;
        }
    }*/
}
