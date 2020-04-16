package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.SinhVienDangKi;

import java.util.List;

public interface SinhVienDangKiRepository extends JpaRepository<SinhVienDangKi,Long> {
    @Query("select a from  SinhVienDangKi  a")
    List<SinhVienDangKi> findAll();
    @Query("select a from  SinhVienDangKi  a where a.ID = :id")
    SinhVienDangKi findByID(Long id);
    @Query("select a from  SinhVienDangKi  a where a.soHoChieu = :passport")
    SinhVienDangKi findByPassport(@Param("passport") String passport);
}
