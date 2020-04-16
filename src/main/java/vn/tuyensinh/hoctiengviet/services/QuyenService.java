package vn.tuyensinh.hoctiengviet.services;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tuyensinh.hoctiengviet.entity.Quyen;

import java.util.List;

public interface QuyenService {

    List<Quyen>  findAll();
    Quyen findByID(Integer id);
    Quyen findByNamePermiision(String name);
    List<Quyen> findByGroup(Integer id);
    void remove(Integer id);
    void insert(Quyen quyen);
    void update(Quyen quyen);
    void deleteAll();

}
