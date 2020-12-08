package com.astontech.hr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
    // the contact is the url to direct to
    @RequestMapping("/contact")
    public String contactPage() {
        //contactPage is the jsp it will be pointing to
        return "contactPage";
    }
}
