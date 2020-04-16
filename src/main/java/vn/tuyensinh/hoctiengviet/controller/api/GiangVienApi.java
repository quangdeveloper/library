package vn.tuyensinh.hoctiengviet.controller.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.GiangVien;
import vn.tuyensinh.hoctiengviet.model.request.GiangVienRequest;
import vn.tuyensinh.hoctiengviet.services.impl.GiangVienServiceImpl;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class GiangVienApi {


    @Autowired
    private GiangVienServiceImpl giangVienService;

    //    Lay danh sach tat ca giang vien
    @GetMapping("/lectures")
    public ResponseEntity<List<GiangVien>> findAll() {
        List<GiangVien> list = giangVienService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    //   lay thong tin giang vien thong qua ma giang vien code
//    @GetMapping("/lectures/code")
//    public ResponseEntity<GiangVien> findByCodeLecture(@RequestParam(name = "code") String code) {
//        return ResponseEntity.ok(giangVienService.findByLectureCode(code));
//    }

    // lay thong tin giang vien thong qua id
    @GetMapping("/lectures/{id}")
    public ResponseEntity<GiangVien> findByLectureID(@PathVariable(name = "id") Long id) {
        GiangVien giangVien = giangVienService.findByLectureID(id);
        if (giangVien == null) {
            return new ResponseEntity<GiangVien>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(giangVien);


    }

    //    them moi 1 giang vien
    @PostMapping("/lectures")
    public ResponseEntity<Void> addLecture(@RequestBody GiangVienRequest gv, UriComponentsBuilder ucBuilder) {
        if (giangVienService.findByLectureCode(gv.getMaGiangVien()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } else {
            GiangVien giangVien = new GiangVien();
            giangVien.setMaGiangVien(gv.getMaGiangVien());
            giangVien.setHoVaTen(gv.getHoVaTen());
            giangVien.setDiaChi(gv.getDiaChi());
            giangVien.setNoiSinh(gv.getNoiSinh());
            giangVien.setEmail(gv.getEmail());
            giangVien.setSoDienThoai(gv.getSoDienThoai());
            giangVien.setNgayBatDau(new Timestamp(System.currentTimeMillis()));
            giangVien.setNgayKetThuc(Constant.dateDefault);
            giangVienService.insert(giangVien);
            HttpHeaders headers = new HttpHeaders();
            GiangVien gv1= giangVienService.findByLectureCode(gv.getMaGiangVien());
            headers.setLocation(ucBuilder.path("/lectures/{id}").buildAndExpand(gv1.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
    }

    //  sua thong tin giang vien
    @PutMapping("/lectures")
    public ResponseEntity<GiangVien> updateLecture(@RequestBody GiangVienRequest gv) {
        if (giangVienService.findByLectureID(gv.getId()) == null) {
            return new ResponseEntity<GiangVien>(HttpStatus.NOT_FOUND);
        }
        GiangVien giangVien = new GiangVien();
        BeanUtils.copyProperties(gv, giangVien);
        giangVienService.update(giangVien);
        return ResponseEntity.ok(giangVien);

    }

    //    xoa 1 giang vien
    @DeleteMapping("/lectures/{id}")
    public ResponseEntity<GiangVien> removeLecture(@PathVariable(name = "id") Long id) {
        if (giangVienService.findByLectureID(id) == null) {
            return new ResponseEntity<GiangVien>(HttpStatus.NOT_FOUND);
        } else {
            giangVienService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

    //  xoa tat ca giang vien
    @DeleteMapping("/lectures")
    public ResponseEntity<GiangVien> removeLectures() {
        giangVienService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    //tim kiem theo ngay bat dau va dia chi noi o hien tai
    @GetMapping("/lectures/search")
    public ResponseEntity<List<GiangVien>> findByDateAndAddress(@RequestParam("fromDate") Timestamp fromDate,
                                                                @RequestParam("toDate") Timestamp toDate,
                                                                @RequestParam("address") String address) {
        List<GiangVien> list = giangVienService.findByStartDateAndAdrress(fromDate, toDate, address);
        if (list.isEmpty() == true) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }


}




