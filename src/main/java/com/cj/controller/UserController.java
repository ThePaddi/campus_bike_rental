package com.cj.controller;

import com.cj.common.R;
import com.cj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 疾风亦有归途
 * @version v1.0
 * @project campus_bike_rental
 * @package com.cj.controller
 * @company 千锋教育
 * @date 2024/3/16 15:37
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(){

    }
}
