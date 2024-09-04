package com.example.apisecuritybasic.services;

import com.example.apisecuritybasic.models.User;
import com.example.apisecuritybasic.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userReposity){
        this.userRepository = userReposity;
    }
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuário não existe"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                user.getRoles().stream()
                        .map(role-> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList())
        );
    }
}
