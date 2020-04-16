package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.repository.GiangVienRepository;
import vn.tuyensinh.hoctiengviet.services.GiangVienService;

import java.sql.Time;
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
        return giangVienRepository.findByLectureID(gvid);
    }

    @Override
    public void insert(GiangVien gv) {
        giangVienRepository.save(gv);

    }

//    @Override
//    public void update(GiangVien gv) {
//
//        giangVienRepository.update(gv.getDiaChi(),gv.getEmail(),gv.getSoDienThoai(),
//                gv.getNgayKetThuc(),gv.getId());
//    }
    @Override
    public void update(GiangVien gv) {
        giangVienRepository.save(gv);
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
