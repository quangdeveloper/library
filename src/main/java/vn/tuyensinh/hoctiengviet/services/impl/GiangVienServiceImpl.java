package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.model.request.GiangVienRequest;
import vn.tuyensinh.hoctiengviet.repository.GiangVienRepository;
import vn.tuyensinh.hoctiengviet.repository.GioiTinhRepository;
import vn.tuyensinh.hoctiengviet.services.GiangVienService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepository;
    @Override
    public List<GiangVien> findAll() {
        return giangVienRepository.findAll();
    }

    @Override
    public GiangVien findByLectureCode(String code) {
        return giangVienRepository.findByLectureCode(code);
    }

    @Override
    public GiangVien findByLectureID(Long gvid) {
        return giangVienRepository.findByID(gvid);
    }

    @Autowired
    private GioiTinhRepository gioiTinhRepository;
    @Override
    public void insert(GiangVienRequest gv) {
        GiangVien giangVien = new GiangVien();
        BeanUtils.copyProperties(gv,giangVien);
        giangVien.setGioiTinh(gioiTinhRepository.findByID(gv.getGioiTinh()));
        giangVienRepository.save(giangVien);
    }

    @Override
    public void update(GiangVienRequest gv) {
        GiangVien giangVien = new GiangVien();
        BeanUtils.copyProperties(gv, giangVien);
        giangVien.setGioiTinh(gioiTinhRepository.findByID(gv.getGioiTinh()));
        giangVienRepository.save(giangVien);
    }

    @Override
    public void delete(Long id) {
        giangVienRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        giangVienRepository.deleteAll();
    }

    @Override
    public List<GiangVien> findByStartDateAndAdrress(Timestamp fromDate,Timestamp toDate, String address) {
        return giangVienRepository.findByStartDateAndAddress(fromDate,toDate,address);
    }
}
