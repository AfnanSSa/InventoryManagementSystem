package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack")
public class SlackController {
    @Autowired
    SlackService slackService;
    @GetMapping("messages")
    public void sendMessage(){
        slackService.sendMessage("", "");
    }

}