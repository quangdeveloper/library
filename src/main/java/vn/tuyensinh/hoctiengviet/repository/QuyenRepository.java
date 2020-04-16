package vn.tuyensinh.hoctiengviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoanQuyen;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface QuyenRepository extends JpaRepository<Quyen,Integer>{



    @Query("select a from Quyen a")
    List<Quyen> findAll();

    @Query("select a from Quyen a where a.ID = :id")
    Quyen findByID(@Param("id")Integer id);

    @Query("select a from Quyen a where a.tenQuyen = :name")
    Quyen findByNamePermission(@Param("name") String name);

    @Query("select a from Quyen a where a.nhomQuyen = :nhom_id")
    List<Quyen> findByGroup(@Param("nhom_id") Integer nhom_id);



}

