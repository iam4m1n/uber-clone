package com.example.onlineTaxi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController {


    @GetMapping("/{id}")
    public ResponseEntity<?> testGetUser(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>("hi everthing is working", HttpStatus.OK);
    }

    public ResponseEntity<?> getUser(){
        return null;
    }




}
