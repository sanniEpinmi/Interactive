package com.bankit.controller;

import com.bankit.model.User;
import com.bankit.service.UserRepository;
import com.bankit.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(description = "User Module", value = "User Management")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateBook(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = Optional.ofNullable(userRepository.findOne(id));
       // Optional<Book> bookData = bookRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            _user.setPassword(encodedPassword);
            _user.setAddress(user.getAddress());
            _user.setAge(user.getAge());
            _user.setBankAcc(user.getBankAcc());
            _user.setBusType(user.getBusType());
            _user.setCompanyName(user.getCompanyName());

            _user.setEmail(user.getEmail());
            _user.setFoneNo(user.getFoneNo());
            _user.setUsername(user.getUsername());

            //PASSWORD HAS TO BE ENCRYPTED USING BCRYPT FROM THE MAIN METHOD
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }



}
