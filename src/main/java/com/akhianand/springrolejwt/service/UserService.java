package com.akhianand.springrolejwt.service;

import com.akhianand.springrolejwt.dao.UserDao;
import com.akhianand.springrolejwt.mapper.UserDTOToUserEntity;
import com.akhianand.springrolejwt.model.Role;
import com.akhianand.springrolejwt.model.UserDto;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final RoleService roleService;

    private final BCryptPasswordEncoder bcryptEncoder;

    private final UserDTOToUserEntity userDTOToUserEntity;

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> list = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public void saveEntity(UserEntity userEntity){
        repository.save(userEntity);
    }

    public UserEntity save(UserDto user) {

        UserEntity nUser = userDTOToUserEntity.convert(user);
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);


        nUser.setRoles(roleSet);
        return repository.save(nUser);
    }
}
