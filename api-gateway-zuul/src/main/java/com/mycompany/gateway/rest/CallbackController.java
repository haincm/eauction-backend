package com.mycompany.gateway.rest;


import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CallbackController {

    @PostMapping("/callback/token")
    public Map<String, String> callbackToken(@RequestBody MultiValueMap<String, String> queryMap) {
        return queryMap.toSingleValueMap();
    }

}