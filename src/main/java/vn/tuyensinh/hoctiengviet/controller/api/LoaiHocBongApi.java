package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.tuyensinh.hoctiengviet.entity.LoaiHocBong;
import vn.tuyensinh.hoctiengviet.services.impl.LoaiHocBongServiceImpl;

import java.util.List;

@RestController
public class LoaiHocBongApi {
    @Autowired
    private LoaiHocBongServiceImpl loaiHocBongService;

    @GetMapping("/admin/scholarships")
    public ResponseEntity<List<LoaiHocBong>> findAll() {
        List<LoaiHocBong> list = loaiHocBongService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/admin/scholarships/{id}")
    public ResponseEntity<LoaiHocBong> findByID(@PathVariable("id")Integer id) {
        LoaiHocBong obj= loaiHocBongService.findByID(id);
        if (obj==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obj);
    }
}
