package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.entity.KhoaHoc;

import java.sql.Timestamp;
import java.util.List;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc,Long> {
    @Query("select a from KhoaHoc  a")
    List<KhoaHoc> findAll();

    @Query("select a from KhoaHoc a where a.ID = :id")
    KhoaHoc findByID(@Param("id")Long id);

    @Query("select a from KhoaHoc a where a.maKhoaHoc = :id")
    KhoaHoc findByCourseID(@Param("id")String id);

    @Query("SELECT u FROM KhoaHoc u WHERE (:startDate IS NULL OR u.ngayBatDau >= :startDate)" +
            "AND (:endDate IS NULL OR u.ngayBatDau <= :endDate)" +
            "AND (:status IS NULL OR u.trangThai = :status)")
    List<KhoaHoc> findByStartDateAndStatus(@Param("startDate") Timestamp startDate,
                                              @Param("endDate") Timestamp endDate,
                                              @Param("status") Integer status);

    @Query("select a from KhoaHoc a where a.maKhoaHoc = :code")
    KhoaHoc checkCodeExists(@Param("code")String code);
}
