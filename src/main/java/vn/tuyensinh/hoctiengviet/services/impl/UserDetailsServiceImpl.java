package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.entity.Quyen;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;
    @Autowired
    private QuyenServiceImpl quyenService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TaiKhoan appUser = taiKhoanService.findByTaiKhoan(userName);
        if (appUser == null) {
            throw new UsernameNotFoundException("UserName: " + userName + " not found in database !!!");
        }
        Set<String> roleNames = taiKhoanService.getRoleNames(appUser.getID());
        List<GrantedAuthority> grandList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grandList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(appUser.getTaiKhoan(),
                appUser.getMatKhau(),
                grandList);
        return userDetails;
    }

}
