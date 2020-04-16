package vn.tuyensinh.hoctiengviet.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "quyen", uniqueConstraints = {@UniqueConstraint(name = "quyen_UK", columnNames = "tenquyen")})
public class Quyen extends  BaseSmall{

    @Column(name = "tenquyen", nullable = false)
    private String tenQuyen;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "nhomquyen")
    private Integer nhomQuyen;

    public Integer getNhomQuyen() {
        return nhomQuyen;
    }

    public void setNhomQuyen(Integer nhomQuyen) {
        this.nhomQuyen = nhomQuyen;
    }

    public Quyen() {
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


}
