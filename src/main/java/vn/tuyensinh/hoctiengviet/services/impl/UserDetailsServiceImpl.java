package vn.tuyensinh.hoctiengviet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.tuyensinh.hoctiengviet.entity.TaiKhoan;
import vn.tuyensinh.hoctiengviet.repository.TaiKhoanRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTaiKhoan(userName);
        if (taiKhoan == null) {
            throw new UsernameNotFoundException("UserName: " + userName + " not found in database !!!");
        }
        List<String> roleNames = taiKhoanService.getRoleNames(taiKhoan.getID());
        List<GrantedAuthority> grandList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grandList.add(authority);
            }
        }
        UserDetails userDetails = new User(taiKhoan.getTaiKhoan(),
                taiKhoan.getMatKhau(),
                grandList);
        return userDetails;
    }
}
