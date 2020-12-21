package com.smart.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PublicKeyController {
    @Resource
    KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getPublicKey() {
        PublicKey key = keyPair.getPublic();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("key", key);
        return stringObjectHashMap;
    }

}
