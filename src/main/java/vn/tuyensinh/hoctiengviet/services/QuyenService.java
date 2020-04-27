package vn.tuyensinh.hoctiengviet.services;

import org.springframework.data.repository.query.Param;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.model.request.QuyenRequest;
import vn.tuyensinh.hoctiengviet.repository.QuyenRopository;

import java.util.List;

public interface QuyenService {
  List<Quyen> findAll();
  Quyen findByID(Integer id);
  String getRoleName(Quyen quyen);
  Quyen insert(QuyenRequest quyenRequest);
  void delete(Integer id);
  Quyen update(QuyenRequest quyenRequest);
  Quyen findByNameRole(String name);

}
