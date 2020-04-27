package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.entity.SinhVienDangKi;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.DeleteRequest;
import vn.tuyensinh.hoctiengviet.model.request.SinhVienRegister;
import vn.tuyensinh.hoctiengviet.services.impl.SinhVienDangKiServiceImpl;

import java.util.List;

@RestController
public class SinhVienDangKiApi {
    @Autowired
    private SinhVienDangKiServiceImpl sinhVienDangKiService;
//    @GetMapping("/admin/students")
//    public ResponseEntity<List<SinhVienDangKi>> findAll(){
//        List<SinhVienDangKi>list = sinhVienDangKiService.findAll();
//        if (list.isEmpty()){
//            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ResponseEntity.ok(list);
//    }

    @GetMapping("/admin/students/{id}")
    public ResponseEntity<SinhVienDangKi> findByID(@PathVariable("id")Long id){
        SinhVienDangKi obj = sinhVienDangKiService.findByID(id);
        if (obj==null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/v1/api/web/students")
    public ResponseEntity<Void> insertStudent(@RequestBody SinhVienRegister sv, UriComponentsBuilder ucBuilder){

        if (sinhVienDangKiService.findByPassport(sv.getSoHoChieu())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        sinhVienDangKiService.insert(sv);
        SinhVienDangKi newS =sinhVienDangKiService.findByPassport(sv.getSoHoChieu());
        HttpHeaders headers=new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/students/{id}").buildAndExpand(newS.getID()).toUri());
        return new ResponseEntity<>(headers,HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/api/admin/students")
    public ResponseEntity<TaiKhoan> deleteStudent(@RequestBody DeleteRequest rq) {
        Long ids[] = rq.getIds();
        boolean check = false;
        for(Long id : ids){
            if (sinhVienDangKiService.findByID(id)==null){
                check = true;
            }
        }
        if (check == true){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            for (Long id : ids) {
                sinhVienDangKiService.delete(id);
            }
            return ResponseEntity.noContent().build();
        }
    }

}
