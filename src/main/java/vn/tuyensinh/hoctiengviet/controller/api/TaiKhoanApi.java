package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.DeleteRequest;
import vn.tuyensinh.hoctiengviet.model.request.TaiKhoanRequest;
import vn.tuyensinh.hoctiengviet.services.impl.TaiKhoanServiceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@RestController
public class TaiKhoanApi {

    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;

    //  lay danh sach tai khoan
    @GetMapping("/api/admin/accounts")
    public ResponseEntity<List<TaiKhoan>> findAll() {
        List<TaiKhoan> list = taiKhoanService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    //  tim kiem theo id
    @GetMapping("/api/admin/accounts/{id}")
    public ResponseEntity<TaiKhoan> findByID(@PathVariable("id") Long id) {
        TaiKhoan account = taiKhoanService.findByID(id);
        if (account == null) {
            return new ResponseEntity<TaiKhoan>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(account);
    }

    // tim kiem theo nhieu dieu kien
    @GetMapping("/api/admin/accounts/searchs")
    public ResponseEntity<List<TaiKhoan>> findByStatusAndDate(@RequestParam("fromDate") Timestamp fromDate,
                                                              @RequestParam("toDate") Timestamp toDate,
                                                              @RequestParam("status") Integer status) {
        List<TaiKhoan> list = taiKhoanService.findByStatusAndCreatedDate(status, fromDate, toDate);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);

    }

    //    Them tai khoan
    @PostMapping("/api/admin/accounts")
    public ResponseEntity<Void> insertAccount(@RequestBody TaiKhoanRequest accountRequest,
                                              UriComponentsBuilder ucBuilder) {
        if (taiKhoanService.findByTaiKhoan(accountRequest.getTaiKhoan()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        taiKhoanService.insertAccount(accountRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/accounts/{id}").buildAndExpand(accountRequest.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //    Sua thong tin tai khoan
    @PutMapping("/api/admin/accounts")
    public ResponseEntity<TaiKhoan> updateAccount(@RequestBody TaiKhoanRequest taiKhoanRequest) {
        if (taiKhoanService.findByID(taiKhoanRequest.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            taiKhoanService.updateAccount(taiKhoanRequest);
            TaiKhoan taiKhoan = taiKhoanService.findByID(taiKhoanRequest.getId());
            return ResponseEntity.ok(taiKhoan);
        }
    }

    //    Xoa tai khoan
    @DeleteMapping("/api/admin/accounts")
    public ResponseEntity<TaiKhoan> deleteAccount(@RequestBody DeleteRequest rq) {
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
                taiKhoanService.deleteAccount(id);
            }
            return ResponseEntity.noContent().build();
        }

    }

    //    lay ten quyen
    @GetMapping("/api/admin/accounts/{id}/roleName")
    public ResponseEntity<Set<String>> getRoleName(@PathVariable("id")Long id) {

        Set<String> set = taiKhoanService.getRoleNames(id);
        if (set.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(set);
    }


}
