package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;
import vn.tuyensinh.hoctiengviet.repository.GioiTinhRepository;
import vn.tuyensinh.hoctiengviet.repository.QuyenRopository;
import vn.tuyensinh.hoctiengviet.repository.TaiKhoanRepository;
import vn.tuyensinh.hoctiengviet.repository.TrangThaiRepository;
import vn.tuyensinh.hoctiengviet.services.TaiKhoanService;

import java.sql.Timestamp;
import java.util.*;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public TaiKhoan findByID(Long id) {
        return taiKhoanRepository.findByID(id);
    }

    @Override
    public TaiKhoan findByTaiKhoan(String tk) {
        return taiKhoanRepository.findByTaiKhoan(tk);
    }

    @Override
    public List<TaiKhoan> findAll() {
        return taiKhoanRepository.findAll();
    }

    @Autowired
    private QuyenRopository quyenRopository;

    @Autowired
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private GioiTinhRepository gioiTinhRepository;
    @Override
    public TaiKhoan insert(TaiKhoanRequest taiKhoanRequest) {
        TaiKhoan newAcc = new TaiKhoan();
        BeanUtils.copyProperties(taiKhoanRequest, newAcc);
        newAcc.setNgayTao(new Timestamp(System.currentTimeMillis()));
        newAcc.setNgaySua(Constant.DATE_DEFAULT);
        newAcc.setNguoiSua(Constant.INFO_DEFAULT);
        newAcc.setGioiTinh(gioiTinhRepository.findByID(taiKhoanRequest.getGioiTinh()));
        newAcc.setTrangThai(trangThaiRepository.findByID(1));
        //ma hoa mat khau
        newAcc.setMatKhau(encoder.encode(taiKhoanRequest.getMatKhau()));
        //them tap quyen
        Set<Quyen> quyens = new HashSet<>();
        for (Integer id : taiKhoanRequest.getIds()){
            quyens.add(quyenRopository.findByID(id));
        }
        newAcc.setQuyenSet(quyens);
        return taiKhoanRepository.save(newAcc);
    }

    @Override
    public TaiKhoan update(TaiKhoanRequest taiKhoanRequest) {
        TaiKhoan newAcc = new TaiKhoan();
        TaiKhoan oldAcc = taiKhoanRepository.findByID(taiKhoanRequest.getID());
        BeanUtils.copyProperties(taiKhoanRequest, newAcc);
        newAcc.setNgaySua(new Timestamp(System.currentTimeMillis()));
        newAcc.setGioiTinh(gioiTinhRepository.findByID(taiKhoanRequest.getGioiTinh()));
        newAcc.setTrangThai(trangThaiRepository.findByID(taiKhoanRequest.getTrangThai()));
        newAcc.setNgayTao(oldAcc.getNgayTao());
        //ma hoa mat khau
        newAcc.setMatKhau(encoder.encode(taiKhoanRequest.getMatKhau()));
        //them tap quyen
        Set<Quyen> quyens = new HashSet<>();
        Set<Integer> ids = taiKhoanRequest.getIds();
        ids.forEach(obj -> {
            quyens.add(quyenRopository.findByID(obj));
        });
        newAcc.setQuyenSet(quyens);
        return taiKhoanRepository.save(newAcc);
    }

    @Override
    public void delete(Long id) {
        TaiKhoan taiKhoan =taiKhoanRepository.findByID(id);
        taiKhoan.getQuyenSet().forEach(obj->{
            obj.getTaiKhoanSet().remove(taiKhoan);
        });
        taiKhoanRepository.deleteById(id);
    }

    @Override
    public List<String> getRoleNames(Long id) {
        List<String> roleNames = new ArrayList<>();
        taiKhoanRepository.findByID(id).getQuyenSet().forEach(obj ->
            roleNames.add(obj.getTenQuyen())
        );
        return roleNames;
    }

    @Override
    public Set<Integer> getRoleIds(Long id) {
        Set<Integer> roleIds = new HashSet<>();
        taiKhoanRepository.findByID(id).getQuyenSet().forEach(obj ->
                roleIds.add(obj.getID())
        );
        return roleIds;
    }
}
