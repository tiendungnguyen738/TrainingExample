package com.example.demo.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			Role role = new Role();
			role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }
		
		if (roleRepository.findByName("ROLE_MEMBER") == null) {
			Role role = new Role();
			role.setName("ROLE_MEMBER");
            roleRepository.save(role);
        }
		
		if(userRepository.findByUserName("admin") == null) {
			User admin = new User();
			admin.setUserName("admin");
			admin.setPassword(passwordEncoder.encode("123"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			admin.setRoles(roles);
            userRepository.save(admin);
		}
		
		if (userRepository.findByUserName("user") == null) {
			User user = new User();
			user.setUserName("user");
			user.setPassword(passwordEncoder.encode("123"));
			
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			
			user.setRoles(roles);
            userRepository.save(user);
		}
	}
	
	

}
