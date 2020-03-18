package com.liu.passport.config;

import com.liu.passport.security.UserDetailServiceImpl;
import com.liu.passport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * 不定义没有password grant_type
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    //定义非 oauth2 protected相关的 resource server权限控制
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.formLogin()
//                .usernameParameter("username").passwordParameter("password").loginPage("/login")
//                .permitAll()
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
//                .logoutSuccessUrl("/login?logout").permitAll()
//                .and().requestMatchers().antMatchers("/oauth/authorize")
//                .and().requestMatchers().antMatchers("/about/me")
//                .and().requestMatchers().antMatchers("/login")
//                .and().requestMatchers().antMatchers("/logout")
//                .and().requestMatchers().antMatchers("/")
//                .and().requestMatchers().antMatchers("/atd/**")
//                .and().requestMatchers().antMatchers("/tm/**")
//                .and().requestMatchers().antMatchers("/wa/**")
//                .and().authorizeRequests().antMatchers("/","/atd/**","/tm/**","/wa/**")
//                .permitAll()
//                .antMatchers("/oauth/authorize").authenticated().antMatchers("/about/me").authenticated()
//                .and().exceptionHandling().accessDeniedPage("/login?denied").and();

//        http.requestMatchers().antMatchers("/oauth/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").authenticated()
//                .and()
                http.csrf().disable();
        http.requestMatchers().antMatchers("/oauth/authorize")
                .and()
                .requestMatchers().antMatchers("/about/met")
                .and()
                .authorizeRequests()
                .antMatchers("/api/email").permitAll()
                .antMatchers("/").authenticated()
                .antMatchers("/oauth/authorize").authenticated()
                .antMatchers("/about/me").authenticated()
                .antMatchers("/api/login/github").permitAll();


    }

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
            String login = map.get("login").toString();//github的登录名
//            GithubUser githubUser = githubUserService.findByLogin(login);
//            User user;
//            if (githubUser == null) {
//                githubUser = new GithubUser();
//                githubUser = githubUserService.convert(map, githubUser);
//                //创建一个本地用户
//                user = userService.findByAccountName(login);
//                if (user == null) {
//                    user = new User();
//                    user.setAccountName(login);
//                } else {
//                    user.setAccountName(login + "_" + githubUser.getGithubId());
//                }
//                user.setEmail(githubUser.getEmail());
//                user.setBio(githubUser.getBio());
//                user.setUrl(githubUser.getHtml_url());
//                user.setPassword(new BCryptPasswordEncoder().encode(StrUtil.randomString(16)));
//                user.setInTime(new Date());
//                user.setBlock(false);
//                user.setToken(UUID.randomUUID().toString());
//                user.setAvatar(githubUser.getAvatar_url());
//                user.setAttempts(0);
//                user.setScore(2000);// first register score 2000
//                user.setSpaceSize(siteConfig.getUserUploadSpaceSize());
//                user.setGithubUser(githubUser);

//                // set user's role
//                Role role = roleService.findById(3); // normal user
//                Set roles = new HashSet();
//                roles.add(role);
//                user.setRoles(roles);
//                userService.insertUser(user);
//            } else {
//                githubUser = githubUserService.convert(map, githubUser);
//                user = githubUser.getUser();
//                githubUserService.save(githubUser);
//            }
            //加载用户的权限信息
            return userDetailsService.loadUserByUsername("liu");
        };
    }

}










