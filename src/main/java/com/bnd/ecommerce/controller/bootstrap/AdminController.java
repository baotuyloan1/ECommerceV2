package com.bnd.ecommerce.controller.bootstrap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author BAO 6/29/2023
 */
@RequestMapping()
@Controller
public class AdminController {
    @GetMapping("/")
    public String showAdminPanel(){
        return "bootstrapUI/adminPanel";
    }
}
