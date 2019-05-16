package com.example.sweater.controllers;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepository;
import com.example.sweater.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messages;

    @Autowired
    private UserRepository users;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> param) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false,
                    defaultValue = "")
                    String filter,
            Map<String, Object> param) {

        Iterable<Message> byTag;

        if (filter == null || filter.isEmpty()) {
            byTag = messages.findAll();
        } else {
            byTag = messages.findByTag(filter);
        }

        param.put("messages", byTag);
        param.put("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> param
    ) throws IOException {
        Message message = new Message(text, tag, user);

        //noinspection ConstantConditions
        if (file != null && !file.getOriginalFilename().isEmpty()) {
           File uploadDir = new File(uploadPath);

           if(!uploadDir.exists()){
               uploadDir.mkdir();
           }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            File upload = new File(uploadPath + "/" +   resultFilename);

            file.transferTo(upload);

            message.setFilename(resultFilename);

        }
        messages.save(message);
        Iterable<Message> all = messages.findAll();

        param.put("messages", all);
        return "main";

    }


}