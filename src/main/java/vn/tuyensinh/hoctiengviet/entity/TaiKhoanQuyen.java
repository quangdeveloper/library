package vn.tuyensinh.hoctiengviet.entity;

import javax.persistence.*;
@Entity
@Table(name = "taikhoan_quyen",
        uniqueConstraints = {@UniqueConstraint(name = "taikhoan_quyen_UK",columnNames = {"taikhoan_id","quyen_id"})})
public class TaiKhoanQuyen{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taikhoan_id",nullable = false)
    private TaiKhoan taiKhoan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quyen_id",nullable = false)
    private Quyen quyen;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Quyen getQuyen() {
        return quyen;
    }

    public void setQuyen(Quyen quyen) {
        this.quyen = quyen;
    }
}
