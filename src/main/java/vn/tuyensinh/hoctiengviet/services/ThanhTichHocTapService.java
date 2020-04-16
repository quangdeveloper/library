package vn.tuyensinh.hoctiengviet.services;

import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.ThanhTichHocTap;

import java.util.List;

public interface ThanhTichHocTapService {
    List<ThanhTichHocTap> findAll();
    ThanhTichHocTap findByID(@Param("id") Long id);
    ThanhTichHocTap findByStudentID(@Param("id") Long id);
    void delete(Long id);
}
