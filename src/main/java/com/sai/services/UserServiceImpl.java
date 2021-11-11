package com.sai.services;

import com.sai.api.dto.UserDto;
import com.sai.api.requests.PatientRequest;
import com.sai.api.requests.UserRequest;
import com.sai.mapper.DoctorMapper;
import com.sai.mapper.PatientMapper;
import com.sai.mapper.UserMapper;
import com.sai.model.Doctor;
import com.sai.model.Patient;
import com.sai.model.Role;
import com.sai.model.User;
import com.sai.repository.UserDao;
import com.sai.service.DoctorService;
import com.sai.service.PatientService;
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

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        // user.getRoles().forEach(role -> {
        Long roleId = user.getRoleId();
        Role role = roleService.findRoleById(roleId);
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));//user.getRole().getName()));//as its single role no multiple roles
        //   });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    public UserDto getUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setRoleId(user.getRoleId());
        userDto.setPhone(user.getPhone());
        userDto.setContactEmail(user.getContactEmail());
        return userDto;
    }

    @Override
    public UserDto save(UserRequest userRequest) {// this is where we have to configure doctor and patient

        User nUser = userMapper.map(userRequest);

        nUser.setPassword(bcryptEncoder.encode(userRequest.getPassword()));

        User user = userDao.save(nUser);
        Long roleId = user.getRoleId();
        Role role = roleService.findRoleById(roleId);
        if (role.getName().equals("DOCTOR")) {
            Doctor doctor = doctorMapper.map(userRequest);
            doctorService.create(doctor);
        }
        else {
            Patient patient = patientMapper.map(userRequest);
            patientService.create(patient);
        }

        return userMapper.map(nUser);
    }
}
