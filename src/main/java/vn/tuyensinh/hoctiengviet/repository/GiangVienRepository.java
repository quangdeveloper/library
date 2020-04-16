package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface GiangVienRepository extends JpaRepository<GiangVien,Long> {
    @Query("select a from GiangVien a")
    List<GiangVien> findAll();

    @Query("select a from GiangVien a where a.maGiangVien = :code")
    GiangVien findByLectureCode(@Param("code") String code);

    @Query("select a from GiangVien a where a.id = :id")
    GiangVien findByLectureID(@Param("id") Long id);

    @Query("SELECT u FROM GiangVien u WHERE (:fromDate IS NULL OR u.ngayBatDau >= :fromDate)" +
            "AND (:toDate IS NULL OR u.ngayBatDau <= :toDate)" +
            "AND (:address IS NULL OR u.diaChi = :address)")
    List<GiangVien> findByStartDateAndAddress(@Param("fromDate") Timestamp fromDate,
                                                   @Param("toDate") Timestamp toDate,
                                                   @Param("address") String address);
}
