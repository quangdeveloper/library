package vn.tuyensinh.hoctiengviet.controller.page;

public class Paging {
    private int pageNo;     //trang hien tai
    private int pageSize;      //so record toi da tren 1 trang
    private int totalPage;  //tong so trang
    private int totalItem;//tong so record tra ra

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }
}
