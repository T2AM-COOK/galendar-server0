package com.k.garlander.controller;

import com.k.garlander.dto.JoinDTO;
import com.k.garlander.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDto){

        joinService.joinProcess(joinDto);

        return "ok";
    }
}