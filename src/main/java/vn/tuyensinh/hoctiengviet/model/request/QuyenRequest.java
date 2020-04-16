package vn.tuyensinh.hoctiengviet.model.request;

public class QuyenRequest {


    private Integer id;
    private String tenQuyen;
    private  String moTa;
    private Integer nhomQuyen;
    private String nguoiTao;
    private String nguoiSua;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public QuyenRequest() {
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getNhomQuyen() {
        return nhomQuyen;
    }

    public void setNhomQuyen(Integer nhomQuyen) {
        this.nhomQuyen = nhomQuyen;
    }
}
