package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoanQuyen;
import vn.tuyensinh.hoctiengviet.entity.TrangThai;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;
import vn.tuyensinh.hoctiengviet.repository.QuyenRepository;
import vn.tuyensinh.hoctiengviet.repository.TaiKhoanQuyenRepository;
import vn.tuyensinh.hoctiengviet.repository.TaiKhoanRepository;
import vn.tuyensinh.hoctiengviet.repository.TrangThaiRepository;
import vn.tuyensinh.hoctiengviet.services.TaiKhoanService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private QuyenRepository quyenRepository;

    @Autowired
    private TaiKhoanQuyenRepository taiKhoanQuyenRepository;

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Override
    public List<TaiKhoan> findAll() {
        return taiKhoanRepository.findAll();
    }

    @Override
    public TaiKhoan findByID(Long id) {
        return taiKhoanRepository.findByID(id);
    }

    @Override
    public TaiKhoan findByTaiKhoan(String tk) {
        return taiKhoanRepository.findByTaiKhoan(tk);
    }

    //chua hoan thanh
    @Override
    public List<TaiKhoan> findByTrangThai(Integer trangThai) {
        return null;
    }

    @Override
    public List<TaiKhoan> findByStatusAndCreatedDate(Integer status, Timestamp fromDate,
                                                     Timestamp toDate) {
        TrangThai trangThai = trangThaiRepository.findByID(status);
        return taiKhoanRepository.findByStatusAndCreatedDate(trangThai, fromDate, toDate);
    }

    @Override
    public void insertAccount(TaiKhoanRequest taiKhoanRequest) {
        TrangThai trangThai= trangThaiRepository.findByID(1);

        TaiKhoan taiKhoan = new TaiKhoan();
        BeanUtils.copyProperties(taiKhoanRequest, taiKhoan);
        taiKhoan.setNgayTao(new Timestamp(System.currentTimeMillis()));
        taiKhoan.setNgaySua(Constant.dateDefault);
        taiKhoan.setTrangThai(trangThai);
        taiKhoan.setNguoiSua(Constant.infoDefault);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        taiKhoan.setMatKhau(encoder.encode(taiKhoanRequest.getMatKhau()));

//        kiem tra danh sach quyen tu client tra ve co ton tai khong
        List<Integer> ids = new ArrayList<>();
        for (Integer id : taiKhoanRequest.getRoles()) {
            if (quyenRepository.findByID(id) != null) {
                ids.add(id);
            }
    }
//        neu danh sach quyen gui ve ton tai thi them tai khoan

        if (ids.isEmpty() != true) {
            taiKhoanRepository.save(taiKhoan);

            for (Integer id : ids) {
                TaiKhoanQuyen taiKhoanQuyen = new TaiKhoanQuyen();
                taiKhoanQuyen.setTaiKhoan(taiKhoan);
                taiKhoanQuyen.setQuyen(quyenRepository.findByID(id));
                taiKhoanQuyenRepository.save(taiKhoanQuyen);
            }
        } else {
            System.out.println("Khong the tao tai khoan khong co quyen !!!");
        }

    }

    @Override
    public void deleteAccount(Long id) {

//        xoa het nhung ptu trong bang taikhoan_quyen co id_TaiKhoan = id
//        loai bo cac rang buoc toi tai khoan muon xoa
//
        Set<TaiKhoanQuyen> list = taiKhoanQuyenRepository.findByTaiKhoan(
                taiKhoanRepository.findByID(id));
        for (TaiKhoanQuyen tk : list) {
            taiKhoanQuyenRepository.delete(tk);
        }
//        sau do xoa tai khoan trong bang Tai_Khoan
        taiKhoanRepository.deleteById(id);
    }

    @Override
    public void updateAccount(TaiKhoanRequest taiKhoanRequest) {

        TaiKhoan oldAccount = taiKhoanRepository.findByID(taiKhoanRequest.getId());
        TaiKhoan newAccount = new TaiKhoan();
        newAccount.setID(taiKhoanRequest.getId());
        newAccount.setTaiKhoan(oldAccount.getTaiKhoan());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newAccount.setMatKhau(encoder.encode(taiKhoanRequest.getMatKhau()));
        newAccount.setEmail(taiKhoanRequest.getEmail());
        newAccount.setSoDienThoai(taiKhoanRequest.getSoDienThoai());
        newAccount.setHovaTen(taiKhoanRequest.getHoVaTen());
        newAccount.setNguoiTao(oldAccount.getNguoiTao());
        newAccount.setNguoiSua(taiKhoanRequest.getNguoiSua());
        newAccount.setTrangThai(taiKhoanRequest.getTrangThai());
        newAccount.setGioiTinh(taiKhoanRequest.getGioiTinh());
        newAccount.setNgayTao(oldAccount.getNgayTao());
        newAccount.setNgaySua(new Timestamp(System.currentTimeMillis()));
        taiKhoanRepository.save(newAccount);
//        xoa quyen cũ
        Set<TaiKhoanQuyen> list = taiKhoanQuyenRepository.findByTaiKhoan(oldAccount);
        for (TaiKhoanQuyen tk : list) {
            taiKhoanQuyenRepository.delete(tk);
        }

//        kiem tra danh sach quyen tu client tra ve co ton tai khong
        List<Integer> ids = new ArrayList<>();
        List<Quyen> roles = new ArrayList<>();
        for (Integer id : taiKhoanRequest.getRoles()) {

            if (quyenRepository.findByID(id) != null) {
                ids.add(id);
            }
        }

//        tao quyen moi cho tai khoan
        if (ids.isEmpty() != true) {

            for (Integer id : ids) {
                TaiKhoanQuyen taiKhoanQuyen = new TaiKhoanQuyen();
                taiKhoanQuyen.setTaiKhoan(newAccount);
                taiKhoanQuyen.setQuyen(quyenRepository.findByID(id));
                taiKhoanQuyenRepository.save(taiKhoanQuyen);
            }
        } else {
            System.out.println("Can not create a account has roles !!!");
        }
    }


    @Override
    public Set<String> getRoleNames(Long id) {
//        lay danh sach quyen cua tai khoan
        Set<TaiKhoanQuyen> listTaiKhoanQuyen = taiKhoanQuyenRepository.
                findByTaiKhoan(taiKhoanRepository.findByID(id));

        Set<Integer> listRoleID = new HashSet<>();
        for (TaiKhoanQuyen tk : listTaiKhoanQuyen) {
            listRoleID.add(tk.getQuyen().getID());
        }

//        lay ten quyen
        Set<String> listRoleNames = new HashSet<>();
        for (Integer roleID : listRoleID) {
            listRoleNames.add(quyenRepository.findByID(roleID).getTenQuyen());
        }
        return listRoleNames;
    }


}
