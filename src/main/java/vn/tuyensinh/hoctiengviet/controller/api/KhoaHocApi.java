package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.entity.KhoaHoc;
import vn.tuyensinh.hoctiengviet.model.request.KhoaHocRequest;
import vn.tuyensinh.hoctiengviet.services.impl.KhoaHocServiceImpl;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class KhoaHocApi {
    @Autowired
    KhoaHocServiceImpl khoaHocService;

    @GetMapping("/admin/courses")
    public ResponseEntity<List<KhoaHoc>> findAll() {
        List<KhoaHoc> list = khoaHocService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/admin/courses/{id}")
    public ResponseEntity<KhoaHoc> findByID(@PathVariable("id") Long id) {
        KhoaHoc course = khoaHocService.findByID(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping("/admin/courses/courseid/{id}")
    public ResponseEntity<KhoaHoc> findByCourseID(@PathVariable("id") String id) {
        KhoaHoc course = khoaHocService.findByCourseID(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping("/admin/courses/search")
    public ResponseEntity<List<KhoaHoc>> findByStartDateAndStatus(@RequestParam("fromDate") Timestamp fromDate,
                                                                  @RequestParam("toDate") Timestamp toDate,
                                                                  @RequestParam("status") Integer status) {
        List<KhoaHoc> list = khoaHocService.findByStartDateAndStatus(fromDate, toDate, status);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping("/admin/courses")
    public ResponseEntity<Void> AddCourse(@RequestBody KhoaHocRequest khoaHoc,
                                          UriComponentsBuilder ucBuilder) {

        if (khoaHocService.findByCourseID(khoaHoc.getMaKhoaHoc()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        khoaHocService.insert(khoaHoc);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/amin/course/{id}").buildAndExpand(khoaHoc.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/admin/courses")
    public ResponseEntity<KhoaHoc> updateCourse(@RequestBody KhoaHocRequest khoaHoc) {
        KhoaHoc old = khoaHocService.findByID(khoaHoc.getId());
        if (old == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //Xu li truong hop ma khoa moi trung voi 1 ma khoa hoc da ton tai nao do.(ko phai khoa hoc dang updat)
            if (khoaHoc.getMaKhoaHoc() != old.getMaKhoaHoc() && khoaHocService.checkCodeExists(khoaHoc.getMaKhoaHoc()) == false) {
                khoaHocService.update(khoaHoc);
                KhoaHoc kh = khoaHocService.findByID(khoaHoc.getId());
                return ResponseEntity.ok(kh);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }
    }
    @DeleteMapping("/admin/courses/{id}")
    public ResponseEntity<KhoaHoc> deleteCourse(@PathVariable("id")Long id){
        if (khoaHocService.findByID(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        khoaHocService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
