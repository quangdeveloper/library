package vn.tuyensinh.hoctiengviet.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.services.impl.TaiKhoanServiceImpl;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;

    //    lay trang login
    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }
    //    lay trang chu admin
    @GetMapping("/admin/home")
    public ModelAndView adminHomePage() {
        return new ModelAndView("admin/home");
    }

    //    lay trang quan li nguoi dung
    @GetMapping("/admin/accounts")
    public ModelAndView accountPage() {
        List<TaiKhoan> list = taiKhoanService.findAll();
        ModelAndView mav = new ModelAndView("admin/account/list");
        mav.addObject("list",list);
        return mav;
    }

    //    lay trang them or sua nguoi dung
    @GetMapping("/admin/accounts/add")
    public ModelAndView accountEditPage(@RequestParam("id")Long id) {
        ModelAndView mav = new ModelAndView("admin/account/edit");
        if (id == null){
            return mav;
        }else {
            mav.addObject("obj",taiKhoanService.findByID(id));
            return mav;
        }
    }

// controller web page //

    // lay trang chu web
    @GetMapping("/web")
    public ModelAndView getWebHomePage() {
        return new ModelAndView("web/home");
    }

    @GetMapping("/403")
    public ModelAndView get403Page() {
        return new ModelAndView("error/403");
    }


}
