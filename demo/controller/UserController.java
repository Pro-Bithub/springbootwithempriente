package com.demo.controller;

import com.demo.dao.UserRepository;
import com.demo.model.Device;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;




public class UserController {

    @Autowired
private UserRepository userRepository;
    @GetMapping("/all")
    public List<User> findAll() {return userRepository.findAll();}






}
