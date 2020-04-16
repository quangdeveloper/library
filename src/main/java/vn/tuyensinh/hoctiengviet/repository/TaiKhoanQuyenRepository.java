package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoanQuyen;

import java.util.Set;

public interface TaiKhoanQuyenRepository extends JpaRepository<TaiKhoanQuyen,Long> {


    @Query("select a from TaiKhoanQuyen a where a.taiKhoan = :taiKhoan and a.quyen = :quyen")
    Set<TaiKhoanQuyen> findByTaiKhoanAndQuyen(@Param("taiKhoan")TaiKhoan taiKhoan,@Param("quyen")Quyen quyen);

    @Query("select a from TaiKhoanQuyen a where a.taiKhoan = :taiKhoan")
    Set <TaiKhoanQuyen> findByTaiKhoan(@Param("taiKhoan") TaiKhoan taiKhoan);

    @Query("select a from TaiKhoanQuyen a where a.quyen = :quyen")
    Set <TaiKhoanQuyen> findByQuyen(@Param("quyen") Quyen quyen);
}
