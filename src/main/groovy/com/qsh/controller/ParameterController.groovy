package com.qsh.controller

import com.qsh.domain.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

/**
 * 常用参数注解使用
 */
//@Slf4j
@Controller
class ParameterController {
    /**
     * @RequestBody ：获取请求体的值
     */
    @PostMapping("/saveUser")
    @ResponseBody
    User saveUser(User user) {
        return user
    }

    /**
     * @PathVariable ：获取路径上的参数
     * @PathVariable ( "id" )String id   获取指定值
     * @PathVariable Map<String,String>      pv  获取所有者（默认封装到map中了）
     */
    @GetMapping("region/{id}")
    @ResponseBody
    String getRegion(@PathVariable("id") String id,
                     @PathVariable Map<String, String> pv) {
        return "路径参数id=${id}"
    }

    /**
     * @RequestParam ；获取请求参数
     */

    @GetMapping("/param")
    @ResponseBody
    String getParam(@RequestParam("name") String name,
                    @RequestParam("age") int age,
                    @RequestParam Map<String, String> params) {

        return ("name=${name}；；； age=${age}；；；  params=${params}")
    }

    /**
     * @RequestBody ：获取请求体的值
     */
    @PostMapping("/body")
    @ResponseBody
    String getBody(@RequestBody String content) {
        return "content=${content}"
    }

    /**
     * @RequestHeader ：获取请求头信息
     */
    @GetMapping("/header")
    @ResponseBody
    String getHeaders(@RequestHeader("User-Agent") String userAgent,
                      @RequestHeader Map<String, String> headers) {
        return ("userAgent=${userAgent},headers=${headers}")
    }

    /**
     * @CookieValue ：获取cookie值
     */
    @GetMapping("/cookie")
    @ResponseBody
    String getCookie(@CookieValue("_ga") String _ga,
                     @CookieValue("_ga") Cookie cookie) {
        return ("cookies = ${cookie.getName()},${cookie.getValue()}")
//        return("ga = ${ga}")
    }

    /**
     * @RequestAttribute: 获取request域属性
     */
    @GetMapping("/goto")
    String getRequest(HttpServletRequest request) {
        request.setAttribute("msg", "成功了。。。")
        return "forward:/request"
    }

    @GetMapping("/request")
    @ResponseBody
    String request(@RequestAttribute("msg") String msg,
                   HttpServletRequest request) {
        def attribute = request.getAttribute("msg")
        return ("msg = ${msg},attribute=${attribute} ")
    }

    /**
     * @MatrixVariable: 矩阵参数*
     * 1、语法： 请求路径：/cars/sell;low=34;brand=byd,audi,yd
     * 2、SpringBoot默认是禁用了矩阵变量的功能
     *       手动开启：原理。对于路径的处理。UrlPathHelper进行解析。
     *       removeSemicolonContent（移除分号内容）支持矩阵变量的
     * 3、矩阵变量必须有url路径变量才能被解析
     */
    @GetMapping("/cars/{path}")
    @ResponseBody
    String carsSell(@MatrixVariable("low") Integer low,
                    @MatrixVariable("brand") List<String> brand,
                    @PathVariable("path") String path) {
        return ("low=${low},brand=${brand},path=${path}")
    }

    /**
     * 请求路径： /boss/1;age=20/2;age=10
     */
    @GetMapping("/boss/{bossId}/{empId}")
    @ResponseBody
    String boss(@MatrixVariable(value = "age", pathVar = "bossId") def bossAge,
                @MatrixVariable(value = "age", pathVar = "empId") def empAge,
                @PathVariable("bossId") String bossId,
                @PathVariable("empId") String empId) {
        return ("bossAge=${bossAge},empAge=${empAge}，bossId=${bossId},empId=${empId}")
    }
}