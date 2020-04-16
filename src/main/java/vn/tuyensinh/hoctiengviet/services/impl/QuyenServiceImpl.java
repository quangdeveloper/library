package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.repository.QuyenRepository;
import vn.tuyensinh.hoctiengviet.services.QuyenService;

import java.util.List;

@Service
public class QuyenServiceImpl implements QuyenService {
    @Autowired
    private QuyenRepository  quyenRepository;

    @Override
    public List<Quyen> findAll() {
        return quyenRepository.findAll();
    }

    @Override
    public Quyen findByID(Integer id) {
        return quyenRepository.findByID(id);
    }

    @Override
    public Quyen findByNamePermiision(String name) {
        return  quyenRepository.findByNamePermission(name);
    }

    @Override
    public List<Quyen> findByGroup(Integer id) {
        return quyenRepository.findByGroup(id);
    }

    @Override
    public void remove(Integer id) {
        quyenRepository.deleteById(id);
    }

    @Override
    public void insert(Quyen quyen) {
        quyenRepository.save(quyen);
    }

    @Override
    public void update(Quyen quyen) {
        quyenRepository.save(quyen);
    }

    @Override
    public void deleteAll() {
        quyenRepository.deleteAll();
    }
}
