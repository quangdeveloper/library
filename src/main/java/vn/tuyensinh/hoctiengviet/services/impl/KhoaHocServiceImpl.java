package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.KhoaHoc;
import vn.tuyensinh.hoctiengviet.model.request.KhoaHocRequest;
import vn.tuyensinh.hoctiengviet.repository.KhoaHocRepository;
import vn.tuyensinh.hoctiengviet.services.KhoaHocService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class KhoaHocServiceImpl implements KhoaHocService {
    @Autowired
    KhoaHocRepository khoaHocRepository;

    @Override
    public List<KhoaHoc> findAll() {
        return khoaHocRepository.findAll();
    }

    @Override
    public KhoaHoc findByID(Long id) {
        return khoaHocRepository.findByID(id);
    }

    @Override
    public KhoaHoc findByCourseID(String courseID) {
        return khoaHocRepository.findByCourseID(courseID);
    }

    @Override
    public List<KhoaHoc> findByStartDateAndStatus(Timestamp fromDate, Timestamp toDate, Integer status) {
        return khoaHocRepository.findByStartDateAndStatus(fromDate, toDate, status);
    }

    @Override
    public void insert(KhoaHocRequest khoaHocRequest) {
        KhoaHoc newKhoaHoc = new KhoaHoc();
        newKhoaHoc.setMaKhoaHoc(khoaHocRequest.getMaKhoaHoc());
        newKhoaHoc.setTenKhoaHoc(khoaHocRequest.getTenKhoaHoc());
        newKhoaHoc.setMoTa(khoaHocRequest.getMoTa());
        newKhoaHoc.setNoiDung(khoaHocRequest.getNoiDung());
        newKhoaHoc.setHocPhi(khoaHocRequest.getHocPhi());
        newKhoaHoc.setNgayBatDau(khoaHocRequest.getNgayBatDau());
        newKhoaHoc.setNgayketthuc(khoaHocRequest.getNgayKetThuc());
        newKhoaHoc.setTrangThai(khoaHocRequest.getTrangThai());
        newKhoaHoc.setNguoiTao(khoaHocRequest.getNguoiTao());
        newKhoaHoc.setNguoiSua(Constant.infoDefault);
        newKhoaHoc.setNgayTao(new Timestamp(System.currentTimeMillis()));
        newKhoaHoc.setNgaySua(Constant.dateDefault);
        khoaHocRepository.save(newKhoaHoc);
    }

    @Override
    public void update(KhoaHocRequest khoaHocRequest) {
        KhoaHoc oldKhoaHoc = khoaHocRepository.findByID(khoaHocRequest.getId());
        KhoaHoc newKhoaHoc = new KhoaHoc();
        newKhoaHoc.setID(khoaHocRequest.getId());
        newKhoaHoc.setMaKhoaHoc(khoaHocRequest.getMaKhoaHoc());
        newKhoaHoc.setTenKhoaHoc(khoaHocRequest.getTenKhoaHoc());
        newKhoaHoc.setMoTa(khoaHocRequest.getMoTa());
        newKhoaHoc.setNoiDung(khoaHocRequest.getNoiDung());
        newKhoaHoc.setHocPhi(khoaHocRequest.getHocPhi());
        newKhoaHoc.setNgayBatDau(khoaHocRequest.getNgayBatDau());
        newKhoaHoc.setNgayketthuc(khoaHocRequest.getNgayKetThuc());
        newKhoaHoc.setTrangThai(khoaHocRequest.getTrangThai());
        newKhoaHoc.setNguoiTao(oldKhoaHoc.getNguoiTao());
        newKhoaHoc.setNguoiSua(khoaHocRequest.getNguoiSua());
        newKhoaHoc.setNgayTao(oldKhoaHoc.getNgayTao());
        newKhoaHoc.setNgaySua(new Timestamp(System.currentTimeMillis()));
        khoaHocRepository.save(newKhoaHoc);
        //khong cho update ten moi trung voi ten 1 khoa hoc da co nao do
    }

    @Override
    public void delete(Long id) {
        khoaHocRepository.deleteById(id);

    }

    @Override
    public Boolean checkCodeExists(String code) {
        KhoaHoc khoaHoc = khoaHocRepository.checkCodeExists(code);
        if (khoaHoc != null) return true;
        return false;

    }
}
