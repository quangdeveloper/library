package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.DeleteRequest;
import vn.tuyensinh.hoctiengviet.model.request.GiangVienRequest;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;
import vn.tuyensinh.hoctiengviet.services.impl.QuyenServiceImpl;
import vn.tuyensinh.hoctiengviet.services.impl.TaiKhoanServiceImpl;

@RestController
public class TaiKhoanApi {
    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;



    //    xoa ds tai khoan
    @DeleteMapping("/v1/api/admin/accounts")
    public ResponseEntity<TaiKhoan> removeAccount(@RequestBody DeleteRequest rq) {
        Long ids[] = rq.getIds();
        boolean check = false;
        for(Long id : ids){
            if (taiKhoanService.findByID(id) == null){
                check = true;
            }
        }
        if (check == true){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            for (Long id : ids) {
                taiKhoanService.delete(id);
            }
            return ResponseEntity.noContent().build();
        }
    }

    //    them moi 1 tai khoan
    @PostMapping("/v1/api/admin/accounts")
    public ResponseEntity<Void> addAccount(@RequestBody TaiKhoanRequest tk, UriComponentsBuilder ucBuilder) {
        if (taiKhoanService.findByTaiKhoan(tk.getTaiKhoan()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } else {
            TaiKhoan taiKhoan= taiKhoanService.insert(tk);
            HttpHeaders headers = new HttpHeaders();

            headers.setLocation(ucBuilder.path("/v1/admin/accounts/{id}").buildAndExpand(taiKhoan.getID()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

    //  sua thong tin giang vien
    @PutMapping("/v1/api/admin/accounts")
    public ResponseEntity<GiangVien> updateAccount(@RequestBody TaiKhoanRequest tk) {
        if (taiKhoanService.findByID(tk.getID()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taiKhoanService.update(tk);
        return ResponseEntity.noContent().build();

    }
}
