package com.kishan.major.configuration;

import com.kishan.major.service.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    Google0Auth2SuccessHandler google0Auth2SuccessHandler;

    @Autowired
    CustomeUserDetailService customeUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
              http.authorizeRequests()
                .antMatchers("/","/shop/**","/register","/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(google0Auth2SuccessHandler)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              //  .logoutSuccessHandler("/logout")// /login
                .invalidateHttpSession(true)
                .deleteCookies("JESSIONID")
                .and()
                .exceptionHandling()
                .and()
                .csrf()
                .disable();
                 http.headers().frameOptions().disable();

    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customeUserDetailService);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**","static/**","/productImages/**","/css/**","/js/**");
    }

}
