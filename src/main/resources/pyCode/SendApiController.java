package com.spring.libraryAPI.controller;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.spring.libraryAPI.common.UID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@JsonAutoDetect
@Controller
public class SendApiController {
    @PostMapping("test")
    @ResponseBody
    public UID test(@RequestBody UID uid) throws IOException {
        return uid;
    }
}
