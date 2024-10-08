package com.kishan.major.configuration;

import com.kishan.major.model.Role;
import com.kishan.major.model.User;
import com.kishan.major.repository.RoleRepository;
import com.kishan.major.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Google0Auth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                         Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email =token.getPrincipal().getAttributes().get("email").toString();
        if(!userRepository.findUserByEmail(email).isPresent()){

            User user=new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            user.setFirstName(token.getPrincipal().getAttributes().get("family_name").toString());
            user.setEmail(email);
//            List<User> roles= new ArrayList<>();
//            Role r;
//            User u = (User)roleRepository.findById(2).get();
//            roles.add(u);
//            user.setRoles(roles);
            userRepository.save(user);
        }
        redirectStrategy.sendRedirect(request,response,"/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }

}
