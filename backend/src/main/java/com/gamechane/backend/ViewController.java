package com.gamechane.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

    @RequestMapping(value = "/**/{[path:[^\\.]*}")
    public String index(final HttpServletRequest request) {
        final String url = request.getRequestURI();
        
        if (!url.startsWith("/api") && !url.startsWith("/assets")) {
            return "forward:/index.html";
        }

        return String.format("forward:/%s", url);
    }
    
    @RequestMapping(value="/")
    public String toLeerling() {
        return "forward:/index.html";
    }
}
