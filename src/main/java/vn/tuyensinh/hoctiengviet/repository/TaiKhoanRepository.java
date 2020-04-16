package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.entity.TrangThai;

import java.sql.Timestamp;
import java.util.List;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {

    @Query("select a from TaiKhoan a where a.taiKhoan = :tk")
    TaiKhoan findByTaiKhoan(@Param("tk") String tk);

    @Query("select a from TaiKhoan a where a.trangThai = :tt")
    List<TaiKhoan> findByTrangThai(@Param("tt") Integer trangThai);

    @Query("select a from TaiKhoan a where a.ID = :id")
    TaiKhoan findByID(@Param("id") Long id);

    @Query("select a from TaiKhoan a")
    List<TaiKhoan> findAll();

    @Query("select a from TaiKhoan a where a.trangThai = :status " +
            " AND (:fromDate IS NULL OR a.ngayTao >= :fromDate)" +
            " AND (:toDate IS NULL OR a.ngayTao <= :toDate)")
    List<TaiKhoan> findByStatusAndCreatedDate(TrangThai status, Timestamp fromDate, Timestamp toDate);


}
