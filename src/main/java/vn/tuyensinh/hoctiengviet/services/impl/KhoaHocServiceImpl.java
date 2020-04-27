package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.KhoaHoc;
import vn.tuyensinh.hoctiengviet.entity.TrangThai;
import vn.tuyensinh.hoctiengviet.model.request.KhoaHocRequest;
import vn.tuyensinh.hoctiengviet.repository.KhoaHocRepository;
import vn.tuyensinh.hoctiengviet.repository.TrangThaiRepository;
import vn.tuyensinh.hoctiengviet.services.KhoaHocService;
import vn.tuyensinh.hoctiengviet.uitls.ParesStringToTimeStamp;


import java.sql.Timestamp;
import java.util.List;

@Service
public class KhoaHocServiceImpl implements KhoaHocService {

    @Autowired
    KhoaHocRepository khoaHocRepository;

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private ParesStringToTimeStamp paresStringToTimeStamp;

    @Override
    public List<KhoaHoc> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("ID").descending());
        Page<KhoaHoc> list = khoaHocRepository.findAll(pageable);
        return list.toList();
    }

    @Override
    public List<KhoaHoc> findByCondition(String maKhoaHoc, String tenKhoaHoc, String ngayBatDau, String ngayKetThuc, Integer trangThai) {
        //chuyen doi thoi gian tu String qua TimeStamp
        Timestamp dateFrom, dateTo;
        if (ngayBatDau == null || ngayBatDau == "") {
            dateFrom = null;
        } else {
            dateFrom = paresStringToTimeStamp.paresStringToTimeStamp(ngayBatDau);
        }
        if (ngayKetThuc == null || ngayKetThuc == "") {
            dateTo = null;
        } else {
            dateTo = paresStringToTimeStamp.paresStringToTimeStamp(ngayKetThuc);
        }
        //cau hinh page de tra ra ket qua tuong ung
//        if (pageSize == null ){
//            pageSize =10;
//        }
//        if (pageNo==null){
//            pageNo = 0;
//        }
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("ID").descending());
//        Page<KhoaHoc> list = khoaHocRepository.findByCondition(dateFrom,dateTo,maKhoaHoc,tenKhoaHoc,trangThaiRepository.findByID(trangThai),pageable);
        List<KhoaHoc> list = khoaHocRepository.findByCondition(dateFrom,dateTo,maKhoaHoc,tenKhoaHoc,trangThaiRepository.findByID(trangThai));
        return list;
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
    public void insert(KhoaHocRequest khoaHocRequest) {
        KhoaHoc newKhoaHoc = new KhoaHoc();
        BeanUtils.copyProperties(khoaHocRequest, newKhoaHoc);
        newKhoaHoc.setNgayBatDau(paresStringToTimeStamp.paresStringToTimeStamp(khoaHocRequest.getNgayBatDau()));
        newKhoaHoc.setNgayKetThuc(paresStringToTimeStamp.paresStringToTimeStamp(khoaHocRequest.getNgayKetThuc()));
        newKhoaHoc.setTrangThai(trangThaiRepository.findByID(khoaHocRequest.getTrangThai()));
        newKhoaHoc.setNguoiSua(Constant.INFO_DEFAULT);
        newKhoaHoc.setNgayTao(new Timestamp(System.currentTimeMillis()));
        newKhoaHoc.setNgaySua(Constant.DATE_DEFAULT);
        khoaHocRepository.save(newKhoaHoc);
    }

    @Override
    public void update(KhoaHocRequest khoaHocRequest) {
        KhoaHoc newKhoaHoc = new KhoaHoc();
        BeanUtils.copyProperties(khoaHocRequest, newKhoaHoc);
        newKhoaHoc.setNgayBatDau(paresStringToTimeStamp.paresStringToTimeStamp(khoaHocRequest.getNgayBatDau()));
        newKhoaHoc.setNgayKetThuc(paresStringToTimeStamp.paresStringToTimeStamp(khoaHocRequest.getNgayKetThuc()));
        newKhoaHoc.setTrangThai(trangThaiRepository.findByID(khoaHocRequest.getTrangThai()));
        newKhoaHoc.setNgaySua(new Timestamp(System.currentTimeMillis()));
        khoaHocRepository.save(newKhoaHoc);
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
