package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klu.service.MessageService;
import com.klu.model.Message;

@Controller
public class HomeController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String home(Model model) {

        Message msg = new Message(messageService.getMessage());

        model.addAttribute("message", msg);

        return "home";
    }
}