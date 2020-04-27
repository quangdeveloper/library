package vn.tuyensinh.hoctiengviet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import vn.tuyensinh.hoctiengviet.services.impl.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class
WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //tat bao ve he thong cua spring security
        http.csrf().disable();

        //cac page khong yeu cau quyen dang nhap
        http.authorizeRequests().antMatchers("/login", "/logout", "/v1/web","/v1/*").permitAll();
//      full quyen truy cap cac trang cho quyen nay
//        http.authorizeRequests().antMatchers("/v1/admin","/v1/api/admin")
//                .access("hasAnyRole('SYSTEM_MANAGE')");
//      cac trang chi danh cho admin co quyen trong dinh dang duoi
//        http.authorizeRequests().antMatchers("/v1/admin/home")
//                .access("hasAnyRole('SYSTEM_MANAGE','FILE_UPLOAD','FILE_EDIT','FOLDER_CREATE'," +
//                        "'FOLDER_EDIT','HEAD_BOX','MENU','HTML_PAGE','LECTURE','INFORMATION_REGISTER','PICTURE'," +
//                        "'POST','COURSE','FEATURE')");
////      //module quản lí giảng viên
//        http.authorizeRequests().antMatchers("/v1/admin/lectures*","/v1/api/admin/lectures*")
//                .access("hasAnyRole('SYSTEM_MANAGE','LECTURE','INFORMATION_REGISTER','COURSE')");
////      //module quản lí quyền truy cập
//        http.authorizeRequests().antMatchers("/v1/admin/roles*", "/v1/api/admin/roles*")
//                .access("hasAnyRole('SYSTEM_MANAGE')");
//        //module quản lí tài khoản
//        http.authorizeRequests().antMatchers("/v1/admin/accounts*","/v1/api/admin/accounts*")//
//                .access("hasAnyRole('SYSTEM_MANAGE')");

        //khi nguoi dung da dang nhap voi vai tro XX
        // nhung truy cap vao trang yeu cau vai tro YY
        //ngoai le AccessDeniedException se duoc nem ra
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        //cau hinh cho login form va xu li dang nhap
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")//submit URL trang login
                .loginPage("/login")//URL page login
                .defaultSuccessUrl("/v1/admin/home")//login thanh cong se toi trang nay
                .failureUrl("/login?error=true")//neu loi xay ra se tra ve link nay
                .usernameParameter("username")//lay username tren URL
                .passwordParameter("password")//lay password tren URL
                .and().logout().logoutUrl("/logout")//nhan URL tu trang html
                .logoutSuccessUrl("/v1/web");//cau hinh cho logout page

        //cau hinh cho remenber me
        http.authorizeRequests().and().rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(1 * 24 * 60 * 60);//thiet lap gio luu tru (24h)
    }

//    // Token stored in Table (Persistent_Logins)
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        return tokenRepository;
//    }

    // Token stored in Memory (Of Web Server).
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }


}
