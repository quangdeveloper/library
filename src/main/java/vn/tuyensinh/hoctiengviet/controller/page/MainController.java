package vn.tuyensinh.hoctiengviet.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.tuyensinh.hoctiengviet.constant.Constant;
import vn.tuyensinh.hoctiengviet.entity.*;
import vn.tuyensinh.hoctiengviet.model.request.KhoaHocRequest;
import vn.tuyensinh.hoctiengviet.services.impl.*;
import vn.tuyensinh.hoctiengviet.uitls.ParesStringToTimeStamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;
    @Autowired
    private SinhVienDangKiServiceImpl sinhVienDangKiService;

    //    lay trang login
    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    //    lay trang chu admin
    @GetMapping("/v1/admin/home")
    public ModelAndView adminHomePage() {
        return new ModelAndView("admin/home");
    }

    //    lay trang quan li nguoi dung
    @GetMapping("/v1/admin/accounts")
    public ModelAndView accountPage() {
        List<TaiKhoan> list = taiKhoanService.findAll();
        ModelAndView mav = new ModelAndView("admin/account/list");
        mav.addObject("list", list);

        return mav;
    }

    @Autowired
    private GioiTinhServiceImpl gioiTinhService;
    @Autowired
    private QuyenServiceImpl quyenService;
    @Autowired
    private TrangThaiServiceImpl trangThaiService;

    //    lay trang them or sua nguoi dung
    @GetMapping("/v1/admin/accounts/add")
    public ModelAndView accountEditPage(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/account/edit");
        mav.addObject("genders", gioiTinhService.findAll());
        mav.addObject("roles", quyenService.findAll());
        mav.addObject("status", trangThaiService.findAll());
        if (id == null) {
            return mav;
        } else {
            mav.addObject("tk_roles", taiKhoanService.getRoleIds(id));
            mav.addObject("obj", taiKhoanService.findByID(id));
            return mav;
        }
    }

    @Autowired
    private GiangVienServiceImpl giangVienService;

    //   lay trang quan li giang vien
    @GetMapping("/v1/admin/lectures")
    public ModelAndView lecturePage() {
        List<GiangVien> list = giangVienService.findAll();
        ModelAndView mav = new ModelAndView("admin/lecture/list");
        mav.addObject("list", list);
        return mav;
    }


    //    lay trang them or sua giang vien
    @GetMapping("/v1/admin/lectures/add")
    public ModelAndView lectureEditPage(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/lecture/edit");
        mav.addObject("genders", gioiTinhService.findAll());
        if (id == null) {
            return mav;
        } else {
            mav.addObject("obj", giangVienService.findByLectureID(id));
            return mav;
        }
    }


    //   lay trang quan li role
    @GetMapping("/v1/admin/roles")
    public ModelAndView rolePage() {
        List<Quyen> list = quyenService.findAll();
        ModelAndView mav = new ModelAndView("admin/role/list");
        mav.addObject("list", list);
        return mav;
    }

    @Autowired
    private NhomQuyenServiceImpl nhomQuyenService;

    //    lay trang them or sua role
    @GetMapping("/v1/admin/roles/add")
    public ModelAndView roleEditPage(@RequestParam("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/role/edit");
        mav.addObject("nhomQuyenList", nhomQuyenService.findAll());
        if (id == null) {
            return mav;
        } else {
            mav.addObject("obj", quyenService.findByID(id));
            return mav;
        }
    }

    @Autowired
    private KhoaHocServiceImpl khoaHocService;

    //   lay trang quan li course
    @GetMapping("/v1/admin/courses")
    public ModelAndView coursePage(@RequestParam("pageNo") int pageNo,
                                   @RequestParam("pageSize") int pageSize
    ) {
        ModelAndView mav = new ModelAndView("admin/course/list");
        List<KhoaHoc> list = khoaHocService.findAll(pageNo - 1, pageSize);
        mav.addObject("status", trangThaiService.findAll());
        mav.addObject("list", list);
        Paging paging = new Paging();
        paging.setPageNo(pageNo);
        paging.setTotalPage(list.size() / pageSize + 5);
        mav.addObject("pagination", paging);
        return mav;
    }

    @GetMapping("/v1/admin/courses/search")
    public ModelAndView searchCondition(@RequestParam("maKhoaHoc") String maKhoaHoc,
                                        @RequestParam("tenKhoaHoc") String tenKhoaHoc,
                                        @RequestParam("ngayBatDau") String ngayBatDau,
                                        @RequestParam("ngayKetThuc") String ngayKetThuc,
                                        @RequestParam("trangThai") Integer trangThai) {
        ModelAndView mav = new ModelAndView("admin/course/list");
        mav.addObject("status", trangThaiService.findAll());
//        List<KhoaHoc> list = khoaHocService.findByCondition(maKhoaHoc, tenKhoaHoc, ngayBatDau, ngayKetThuc, trangThai, pageNo - 1, pageSize);
        List<KhoaHoc> list = khoaHocService.findByCondition(maKhoaHoc, tenKhoaHoc, ngayBatDau, ngayKetThuc, trangThai);

        mav.addObject("list", list);
//        Paging paging = new Paging();
//        paging.setPageNo(pageNo);
//        paging.setTotalPage(list.size() / pageSize + 5);
//        mav.addObject("pagination", paging);
        return mav;
    }

    //    lay trang them or sua khoa hoc
    @GetMapping("/v1/admin/courses/add")
    public ModelAndView courseEditPage(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/course/edit");
        mav.addObject("status", trangThaiService.findAll());
        if (id == null) {
            return mav;
        } else {
            mav.addObject("obj", khoaHocService.findByID(id));
            return mav;
        }
    }


    //   lay trang quan li ho so
    @GetMapping("/v1/admin/registers")
    public ModelAndView registersPage(@RequestParam("pageNo") int pageNo,
                                   @RequestParam("pageSize") int pageSize
    ) {
        ModelAndView mav = new ModelAndView("admin/register/list");
        List<SinhVienDangKi> list = sinhVienDangKiService.findAll(pageNo - 1, pageSize);
        mav.addObject("list", list);
        Paging paging = new Paging();
        paging.setPageNo(pageNo);
        paging.setTotalPage(list.size() / pageSize + 5);
        mav.addObject("pagination", paging);
        return mav;
    }

// controller web page //

    // lay trang chu web
    @GetMapping("/v1/web")
    public ModelAndView getWebHomePage() {
        return new ModelAndView("web/home");
    }

    @GetMapping("/403")
    public ModelAndView get403Page() {
        return new ModelAndView("error/403");
    }


}
