package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.DeleteReQuestInteger;
import vn.tuyensinh.hoctiengviet.model.request.DeleteRequest;
import vn.tuyensinh.hoctiengviet.model.request.QuyenRequest;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;
import vn.tuyensinh.hoctiengviet.services.impl.QuyenServiceImpl;

@RestController
public class QuyenApi {
    @Autowired
    private QuyenServiceImpl quyenService;

    //    xoa ds tai khoan
    @DeleteMapping("/v1/api/admin/roles")
    public ResponseEntity<Quyen> removeRole(@RequestBody DeleteReQuestInteger rq) {
        Integer ids[] = rq.getIds();
        boolean check = false;
        for(Integer id : ids){
            if (quyenService.findByID(id) == null){
                check = true;
            }
        }
        if (check == true){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            for (Integer id : ids) {
                quyenService.delete(id);
            }
            return ResponseEntity.noContent().build();
        }
    }

    //    them moi 1 tai khoan
    @PostMapping("/v1/api/admin/roles")
    public ResponseEntity<Void> addRole(@RequestBody QuyenRequest quyenRequest, UriComponentsBuilder ucBuilder) {
        if (quyenService.findByNameRole(quyenRequest.getTenQuyen()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
          Quyen quyen = quyenService.insert(quyenRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/v1/admin/roles/{id}").buildAndExpand(quyen.getID()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

    //  sua thong tin giang vien
    @PutMapping("/v1/api/admin/roles")
    public ResponseEntity<GiangVien> updateRole(@RequestBody QuyenRequest quyenRequest) {
        if (quyenService.findByID(quyenRequest.getID()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        quyenService.update(quyenRequest);
        return ResponseEntity.noContent().build();

    }
}
