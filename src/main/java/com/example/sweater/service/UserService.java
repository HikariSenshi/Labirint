package com.example.sweater.service;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepository;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


//this is user details service from tutorial
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private MailSender sender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.sweater.domain.User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));

        System.out.println(username);
        System.out.println(repository.findByUsername(username));

        return user;

    }

    public boolean addUser(com.example.sweater.domain.User user){
        System.out.println(user.getUsername());

        com.example.sweater.domain.User oldUser = repository.findByUsername(user.getUsername());
        if (oldUser != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        repository.save(user);
        System.out.println(user.getEmail());
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello , %s! Welcome to Sweater. Please , visit" +
                    " next link http://%s/activation/%s",
                    user.getUsername(),"localhost:8080",user.getActivationCode()
            );
            System.out.println(message);
            sender.send(user.getEmail(),"Your account activation",message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = repository.findByActivationCode(code);
        if (user == null){
            return false;
        }

        user.setActivationCode(null);
        repository.save(user);

        return true;
    }
}