package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.QuyenRequest;
import vn.tuyensinh.hoctiengviet.services.impl.QuyenServiceImpl;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class QuyenApi {
    @Autowired
    private QuyenServiceImpl quyenService;

    //    lay danh sach quyen
    @GetMapping("/permissions")
    public ResponseEntity<List<Quyen>> findAll() {
        List<Quyen> list = quyenService.findAll();
        if (list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    //  tim kiem theo id
    @GetMapping("/permissions/{id}")
    public ResponseEntity<Quyen> findByID(@PathVariable("id") Integer id) {
        Quyen quyen = quyenService.findByID(id);
        if (quyen == null){
            return new ResponseEntity<Quyen>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(quyen);
    }

//    tim kiem theo ten
//    @GetMapping("/permissions/{name}")
//    public ResponseEntity<Quyen> findByID(@PathVariable("name")String name){
//        return ResponseEntity.ok(quyenService.findByNamePermiision(name));
//    }

    //    tim kiem theo ma nhom quyen
    @GetMapping("/permissions/groups/{id}")
    public ResponseEntity<List<Quyen>> findByGroup(@PathVariable("id") Integer id) {
        List<Quyen> list = quyenService.findByGroup(id);
        if (list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    //    them quyen moi
    @PostMapping("/permissions")
    public ResponseEntity<Void> insertPermission(@RequestBody QuyenRequest quyenRequest, UriComponentsBuilder ucBuilder) {
        if (quyenService.findByNamePermiision(quyenRequest.getTenQuyen()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Quyen quyen = new Quyen();
        BeanUtils.copyProperties(quyenRequest, quyen);
        quyen.setNgayTao(new Timestamp(System.currentTimeMillis()));
        quyen.setNgaySua(Constant.dateDefault);
        quyen.setNguoiSua(Constant.infoDefault);
        quyenService.insert(quyen);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/permission/{id}").buildAndExpand(quyenRequest.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
//  sua thong tin quyen
    @PutMapping("/permissions")
    public ResponseEntity<Quyen> updatePermission(@RequestBody QuyenRequest quyenRequest) {
        if (quyenService.findByID(quyenRequest.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quyen quyen= new Quyen();
        BeanUtils.copyProperties(quyenRequest,quyen);
        quyen.setID(quyenRequest.getId());
        quyen.setNgaySua(new Timestamp(System.currentTimeMillis()));
        quyenService.update(quyen);
        return  new ResponseEntity<>(quyen,HttpStatus.OK);
    }

//       xoa quyen khong su dung
    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Integer id) {
        if (quyenService.findByID(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quyenService.remove(id);
        return ResponseEntity.noContent().build();
    }
//    xoa tat ca
    @DeleteMapping("/permissions")
    public ResponseEntity<Void> deleteAll(){
        quyenService.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
