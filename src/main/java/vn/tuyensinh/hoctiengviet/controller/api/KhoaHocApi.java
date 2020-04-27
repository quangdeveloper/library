package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.KhoaHoc;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.model.request.DeleteRequest;
import vn.tuyensinh.hoctiengviet.model.request.KhoaHocRequest;
import vn.tuyensinh.hoctiengviet.services.impl.KhoaHocServiceImpl;
import vn.tuyensinh.hoctiengviet.services.impl.TrangThaiServiceImpl;
import vn.tuyensinh.hoctiengviet.uitls.ParesStringToTimeStamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class KhoaHocApi {
    @Autowired
    KhoaHocServiceImpl khoaHocService;


//    @GetMapping("/v1/api/admin/courses/{pageNo}/{pageSize}")
//    public ResponseEntity<List<KhoaHoc>> findAll(@PathVariable int pageNo,@PathVariable int pageSize) {
//        List<KhoaHoc> list = khoaHocService.findAll(pageNo,pageSize);
//        if (list.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ResponseEntity.ok(list);
//    }

    @GetMapping("/v1/api/admin/courses/{id}")
    public ResponseEntity<KhoaHoc> findByID(@PathVariable("id") Long id) {
        KhoaHoc course = khoaHocService.findByID(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }

    @GetMapping("/v1/api/admin/courses/courseid/{id}")
    public ResponseEntity<KhoaHoc> findByCourseID(@PathVariable("id") String id) {
        KhoaHoc course = khoaHocService.findByCourseID(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(course);
    }

    @Autowired
    private TrangThaiServiceImpl trangThaiService;
//    @Autowired
//    private ParesStringToTimeStamp paresStringToTimeStamp;
//    @GetMapping("/v1/api/admin/courses/search")
//    public ResponseEntity<List<KhoaHoc>> findByStartDateAndStatus(@RequestParam("pageNo") Integer pageNo,
//                                                                  @RequestParam("pageSize") Integer pageSize,
//                                                                  @RequestParam("maKhoaHoc") String maKhoaHoc,
//                                                                  @RequestParam("tenKhoaHoc") String tenKhoaHoc,
//                                                                  @RequestParam("ngayBatDau") String ngayBatDau,
//                                                                  @RequestParam("ngayKetThuc") String ngayKetThuc,
//                                                                                                               @RequestParam("trangThai") Integer trangThai) {
//        Timestamp dateFrom,dateTo;
//        if (ngayBatDau == null|| ngayBatDau == "") {
//             dateFrom = Constant.DATE_DEFAULT;
//        }else {
//            dateFrom = paresStringToTimeStamp.paresStringToTimeStamp(ngayBatDau);
//        }
//        if (ngayKetThuc == null || ngayKetThuc == "") {
//            dateTo = Constant.MAX_DATE_DEFAULT;
//        }else {
//            dateTo = paresStringToTimeStamp.paresStringToTimeStamp(ngayKetThuc);
//        }
//        List<KhoaHoc> list = khoaHocService.
//                findByStartDateAndStatus(maKhoaHoc,
//                        tenKhoaHoc,
//                        dateFrom,
//                        dateTo,
//                        trangThaiService.findByID(trangThai),
//                        pageNo- 1,
//                        pageSize
//                );
//        if (list.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return ResponseEntity.ok(list);
//    }

    @PostMapping("/v1/api/admin/courses")
    public ResponseEntity<Void> AddCourse(@RequestBody KhoaHocRequest khoaHoc,
                                          UriComponentsBuilder ucBuilder) {
        if (khoaHocService.findByCourseID(khoaHoc.getMaKhoaHoc()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        khoaHocService.insert(khoaHoc);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/v1/api/admin/courses")
    public ResponseEntity<KhoaHoc> updateCourse(@RequestBody KhoaHocRequest khoaHoc) {
        KhoaHoc old = khoaHocService.findByID(khoaHoc.getId());
        if (old == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            //Xu li truong hop ma khoa moi trung voi 1 ma khoa hoc da ton tai nao do.(ko phai khoa hoc dang update)
            if (khoaHoc.getMaKhoaHoc() != old.getMaKhoaHoc() && khoaHocService.checkCodeExists(khoaHoc.getMaKhoaHoc()) == false) {
                khoaHocService.update(khoaHoc);
                return ResponseEntity.ok(khoaHocService.findByID(khoaHoc.getId()));
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }
    }
    //xoa khoa hoc
    @DeleteMapping("/v1/api/admin/courses")
    public ResponseEntity<TaiKhoan> deleteCourse(@RequestBody DeleteRequest rq) {
        Long ids[] = rq.getIds();
        boolean check = false;
        for(Long id : ids){
            if (khoaHocService.findByID(id) == null){
                check = true;
            }
        }
        if (check == true){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            for (Long id : ids) {
                khoaHocService.delete(id);
            }
            return ResponseEntity.noContent().build();
        }
    }


}
