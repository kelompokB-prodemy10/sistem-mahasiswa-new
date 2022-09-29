package com.kelompokb.sistemmahasiswabackend.controller;

import com.kelompokb.sistemmahasiswabackend.model.dto.DefaultResponse;
import com.kelompokb.sistemmahasiswabackend.model.dto.LoginDto;
import com.kelompokb.sistemmahasiswabackend.model.dto.UserDto;
import com.kelompokb.sistemmahasiswabackend.model.entity.User;
import com.kelompokb.sistemmahasiswabackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public DefaultResponse login(@RequestBody UserDto userDto, LoginDto loginDto) {
        DefaultResponse response = new DefaultResponse();
        Optional<User> optionalUser = userRepo.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if (optionalUser.isPresent()) {
            response.setStatus(Boolean.TRUE);
            response.setMessage("Login Berhasil");
            User user = optionalUser.get();
            loginDto.setIdUser(user.getIdUser());
            loginDto.setRole(user.getRole());
            loginDto.setUsername(user.getUsername());
            response.setData(loginDto);
        } else {
            response.setStatus(Boolean.FALSE);
            response.setMessage("Username atau Password Salah");
        }
        return response;
    }

    @GetMapping("/{idUser}")
    public UserDto getUserById(@PathVariable Integer idUser) {
        Optional<User> optionalUser = userRepo.findById(idUser);
        UserDto dto = new UserDto();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            dto.setIdUser(user.getIdUser());
            dto.setUsername(user.getUsername());
            dto.setRole(user.getRole());
        }
        return dto;
    }
    @GetMapping("/users")
    public List<UserDto> getListUser() {
        List<UserDto> list = new ArrayList();
        for (User i : userRepo.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public UserDto convertEntityToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setIdUser(entity.getIdUser());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());

        return dto;
    }

    @PutMapping("/{idUser}")
    public DefaultResponse update(@PathVariable Integer idUser, @RequestBody UserDto userDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<User> optionalUser = userRepo.findById(idUser);
        User user = optionalUser.get();
        if (optionalUser.isPresent()) {
            user.setIdUser(userDto.getIdUser());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            userRepo.save(user);
            df.setStatus(Boolean.TRUE);
            df.setData(userDto);
            df.setMessage("Perubahan Berhasil Tersimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("ID Tidak Ditemukan");
        }
        return df;
    }

    @PostMapping("/register")
    public DefaultResponse<UserDto> saveUser(@RequestBody UserDto userDto) {
        User user = convertDtoToEntity(userDto);
        DefaultResponse<UserDto> df = new DefaultResponse<>();
        Optional<User> optionalUser = userRepo.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Gagal, Username Sudah Terdaftar");
        } else {
            userRepo.save(user);
            df.setStatus(Boolean.TRUE);
            userDto.setIdUser(user.getIdUser());
            df.setData(userDto);
            df.setMessage("Data Tersimpan");
        }
        return df;
    }

    public User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setIdUser(userDto.getIdUser());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

    @PostMapping("/logout")
    public DefaultResponse logout(@RequestBody UserDto userDto){
        DefaultResponse df = new DefaultResponse();
        Optional<User> optionalUser = userRepo.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if(optionalUser.isPresent()){
            df.setStatus(Boolean.TRUE);
            df.setMessage("Logout Berhasil");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Error silahkan login terlebih dahulu");
        }
        return df;
    }
}
