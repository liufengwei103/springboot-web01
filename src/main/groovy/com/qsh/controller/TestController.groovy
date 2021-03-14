package com.qsh.controller

import com.qsh.domain.User
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestController {

    @ResponseBody
    @GetMapping("test/file")
    FileSystemResource file(){
        return new FileSystemResource("E:/test.txt")
    }

    /**
     * 配置文件配置spring.mvc.contentnegotiation.favor-parameter=true后
     * /getUser?format=json (传format决定返回数据类型)
     */
    @GetMapping("/getUser")
    @ResponseBody
    User getUser() {
        User user = new User(username: "李四", age: 18)

        return user
    }
}
