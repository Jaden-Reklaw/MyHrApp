package com.astontech.hr.controllers;

import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.domain.VO.UserVO;
import com.astontech.hr.domain.VO.VehicleVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminUserController {

    //Add logging
    private Logger log = Logger.getLogger(AdminElementController.class);

    @RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
    public String adminVehicleMakeGet(Model model) {
        model.addAttribute("userVO", new UserVO());
        return "admin/user/user_add";
    }
}
