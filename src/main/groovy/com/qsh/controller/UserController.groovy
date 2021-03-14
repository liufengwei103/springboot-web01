package com.qsh.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.lang.reflect.Method

/**
 * 访问使用rest风格（针对资源CRUD）
 */
@RestController
@RequestMapping("/user")
class UserController {

//    @RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping
    String getUser() {
        return "getUser"
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping("")
    String saveUser() {
        return "saveUser"
    }

//    @RequestMapping(value = "", method = RequestMethod.PUT)
    @PutMapping("")
    String putUser() {
        return "putUser"
    }

//    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @DeleteMapping("")
    String deleteUser() {
        return "deleteUser"
    }
}
