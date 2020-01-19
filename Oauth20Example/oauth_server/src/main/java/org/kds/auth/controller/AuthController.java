package org.kds.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/redirect")
    public ResponseEntity handleCodeRedirect(@RequestParam(name = "code") String code) {
        LOG.info("Authorization code received as : {}", code);
        return (ResponseEntity) ResponseEntity.ok();
    }


}
