package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/Hello")
    public String Hello(@RequestParam(value = "name", defaultValue="World") String name){
        return String.format("Hello %s!",name);
    }
}

@Controller
class MyController{
    // GetMapping 和 RequestMapping都可以正常转向
    // 暂时不知道他们有什么差别
    // TODO 了解他们的差别
    @RequestMapping("/")
    public String GotoHomeRedirect() throws UnsupportedEncodingException {
        // 重定向刷新界面，网址和界面都变
        // 但是他会刷新session和变量
        // return "redirect:/Hello?name=重定向"; // 这样会乱码
        return "redirect:/Hello?name="+ URLEncoder.encode("重定向","UTF-8");
    }

    @RequestMapping("/h2")
    public String GotoHomeForward(){
        // 转向不更新网址，只更新界面
        // 保留session和变量
        return "forward:/Hello?name=转向";
    }

    @GetMapping("/h1")
    public String GotoHomeGetMappingRedirect() throws UnsupportedEncodingException {
        return "redirect:/Hello?name=" + URLEncoder.encode("重定向GetMapping","UTF-8");
    }

    @GetMapping("/h3")
    public String GotoHomeGetMappingForward(){
        return "forward:/Hello?name=转向GetMapping";
    }
}
