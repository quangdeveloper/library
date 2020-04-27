package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;

import java.util.List;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {
    @Query("select u from TaiKhoan u")
    List<TaiKhoan> findAll();
    @Query("select u from TaiKhoan u where u.ID = :id")
    TaiKhoan findByID(@Param("id")Long id);
    @Query("select u from TaiKhoan  u  where u.taiKhoan = :tk")
    TaiKhoan findByTaiKhoan(@Param("tk")String tk);

}
