package vn.tuyensinh.hoctiengviet.services;

import vn.tuyensinh.hoctiengviet.entity.KhoaHoc;
import vn.tuyensinh.hoctiengviet.entity.TrangThai;
import vn.tuyensinh.hoctiengviet.model.request.KhoaHocRequest;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface KhoaHocService {
    List<KhoaHoc> findAll(Integer pageNo,Integer pageSize);
    KhoaHoc findByID(Long id);
    KhoaHoc findByCourseID(String courseid);
    List<KhoaHoc> findByCondition(String maKhoaHoc,String tenKhoaHoc,String fromDate, String toDate,Integer status);
    void insert(KhoaHocRequest khoaHocRequest);
    void update(KhoaHocRequest khoaHoc);
    void delete(Long id);
    Boolean checkCodeExists(String code);

}
