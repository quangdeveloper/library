package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.QuyenRequest;
import vn.tuyensinh.hoctiengviet.repository.NhomQuyenRepository;
import vn.tuyensinh.hoctiengviet.repository.QuyenRopository;
import vn.tuyensinh.hoctiengviet.repository.TaiKhoanRepository;
import vn.tuyensinh.hoctiengviet.services.QuyenService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
public class QuyenServiceImpl implements QuyenService {
    @Autowired
    private QuyenRopository quyenRopository;

    @Override
    public List<Quyen> findAll() {

        return quyenRopository.findAll();
    }

    @Override
    public Quyen findByID(Integer id) {
        return quyenRopository.findByID(id);
    }

    @Override
    public String getRoleName(Quyen quyen) {
        return quyen.getTenQuyen();
    }

    @Autowired
    private NhomQuyenRepository nhomQuyenRepository;

    @Override
    public Quyen insert(QuyenRequest quyenRequest) {
        Quyen quyen = new Quyen();
        BeanUtils.copyProperties(quyenRequest, quyen);
        quyen.setNgayTao(new Timestamp(System.currentTimeMillis()));
        quyen.setNgaySua(Constant.DATE_DEFAULT);
        quyen.setNguoiSua(Constant.INFO_DEFAULT);
        quyen.setNhomQuyen(nhomQuyenRepository.findByID(quyenRequest.getNhomQuyen()));
        return quyenRopository.save(quyen);
    }

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public void delete(Integer id) {
        Set<TaiKhoan> taiKhoans= quyenRopository.findByID(id).getTaiKhoanSet();
        if (taiKhoans== null) {
            quyenRopository.deleteById(id);
        } else {
            taiKhoans.forEach(obj->taiKhoanRepository.deleteById(obj.getID()));
            quyenRopository.deleteById(id);
        }
    }

    @Override
    public Quyen update(QuyenRequest quyenRequest) {
        Quyen old = quyenRopository.findByID(quyenRequest.getID());
        Quyen quyen = new Quyen();
        BeanUtils.copyProperties(quyenRequest, quyen);
        quyen.setNgayTao(old.getNgayTao());
        quyen.setNgaySua(new Timestamp(System.currentTimeMillis()));
        quyen.setNhomQuyen(nhomQuyenRepository.findByID(quyenRequest.getNhomQuyen()));
        return quyenRopository.save(quyen);
    }

    @Override
    public Quyen findByNameRole(String name) {
        return quyenRopository.findByNameRole(name);
    }
}
