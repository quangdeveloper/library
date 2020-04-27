package vn.tuyensinh.hoctiengviet.services.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.entity.*;
import vn.tuyensinh.hoctiengviet.model.request.SinhVienRegister;
import vn.tuyensinh.hoctiengviet.repository.*;
import vn.tuyensinh.hoctiengviet.services.SinhVienDangKiService;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SinhVienDangKiServiceImpl implements SinhVienDangKiService {
    @Autowired
    private SinhVienDangKiRepository sinhVienDangKiRepository;
    @Autowired
    private GioiTinhRepository gioiTinhRepository;

    @Autowired
    private DoiTuongUuTienRepository doiTuongUuTienRepository;

    @Autowired
    private LoaiHocBongRepository loaiHocBongRepository;

    @Autowired
    private NguoiLienHeKhanCapRepository nguoiLienHeKhanCapRepository;

    @Autowired
    private ThanhTichHocTapRepository thanhTichHocTapRepository;

    @Autowired
    private KhoaHocRepository khoaHocRepository;
    @Autowired
    private NgoaiNguRepository ngoaiNguRepository;
    @Autowired
    private ThanNhanRepoisitory thanNhanRepoisitory;

    @Override
    public List<SinhVienDangKi> findAll(Integer pageNo,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by("ID").descending());
        Page<SinhVienDangKi> list = sinhVienDangKiRepository.findAll(pageable);
        return list.toList();
    }

    @Override
    public SinhVienDangKi findByID(Long id) {
        return sinhVienDangKiRepository.findByID(id);
    }

    @Override
    public SinhVienDangKi findByPassport(String passport) {
        return sinhVienDangKiRepository.findByPassport(passport);
    }

    @Override
    public void insert(SinhVienRegister sinhVienRegister) {

        SinhVienDangKi newStudent = new SinhVienDangKi();
        BeanUtils.copyProperties(sinhVienRegister,newStudent);
        newStudent.setGioiTinh(gioiTinhRepository.
                findByID(sinhVienRegister.getGioiTinh()));
        newStudent.setDoiTuongUuTien(doiTuongUuTienRepository.
                findByID(sinhVienRegister.getDoiTuongUuTien()));
        newStudent.setLoaiHocBong(loaiHocBongRepository.
                findByID(sinhVienRegister.getLoaiHocBong()));
        newStudent.setNgayTao(new Timestamp(System.currentTimeMillis()));

        //khoa hoc da co ID trc nen khong can tu sinh ra tronh bang khoa hoc
        KhoaHoc khoaHoc = khoaHocRepository.findByID(sinhVienRegister.getMaKhoaHoc());
        Set<KhoaHoc> khoaHocSet = new HashSet<>();
        khoaHocSet.add(khoaHoc);
        newStudent.setKhoaHocList(khoaHocSet);

//      than nhan chua co ID nen no dc coi la chua ton tai va tu dong dc cascade.all them vao trong bang than nhan
        Set<ThanNhan> thanNhanSet = sinhVienRegister.getListThanNhan();
        newStudent.setThanNhanList(thanNhanSet);
        SinhVienDangKi sv = sinhVienDangKiRepository.saveAndFlush(newStudent);

//      3 bang nay ko dc set trong khi khoi tao sinh vien de them vao DB cause:
//       khong luu ma dinh danh(id) cua cac filed nay trong bang sinh vien de giam tai viec luu du lieu trong bang SV
//       de tien truy xuat khi xac dinh thong tin trong bang ket qua hoc tap
        ThanhTichHocTap newThanhTich = sinhVienRegister.getThanhTichHocTap();
        newThanhTich.setMaSinhVien(sv);
        thanhTichHocTapRepository.save(newThanhTich);
        NguoiLienHeKhanCap newNguoiLienHe = sinhVienRegister.getNguoiLienHeKhanCap();
        newNguoiLienHe.setMaSinhVien(sv);
        nguoiLienHeKhanCapRepository.save(newNguoiLienHe);

        sinhVienRegister.getNgoaiNguList().forEach(obj -> {
            obj.setSinhVienDangKi(sv);
            ngoaiNguRepository.save(obj);
        });
    }

    @Override
    public void delete(Long id) {

        SinhVienDangKi oldStudent = sinhVienDangKiRepository.findByID(id);
        //xoa thanh tich cua sinh vien
        ThanhTichHocTap newTT = thanhTichHocTapRepository.findByStudentID(oldStudent);
        if (newTT != null) {
            thanhTichHocTapRepository.delete(newTT);
        }
        //xoa nguoi lien he cua sinh vien
        NguoiLienHeKhanCap newPerson = nguoiLienHeKhanCapRepository.findByStudent(oldStudent);
        if (newPerson != null) {
            nguoiLienHeKhanCapRepository.delete(newPerson);
        }

        //xoa danh sach thanh tich ngoai ngu cua sinh vien
        Set<NgoaiNgu> newSet = oldStudent.getNgoaiNguList();
        if (newSet.isEmpty()==false){
            newSet.forEach(obj -> {
                ngoaiNguRepository.delete(obj);
            });
        }

        //xoa sinh vien
        sinhVienDangKiRepository.deleteById(id);
    }
}
