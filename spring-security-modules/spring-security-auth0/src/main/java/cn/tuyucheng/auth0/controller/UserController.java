package cn.tuyucheng.auth0.controller;

import cn.tuyucheng.auth0.AuthConfig;
import cn.tuyucheng.auth0.service.ApiService;
import com.auth0.IdentityVerificationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private AuthConfig config;

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<String> users(HttpServletRequest request, HttpServletResponse response) throws IOException, IdentityVerificationException {
        return apiService.getCall(config.getUsersUrl());
    }

    @GetMapping(value = "/userByEmail")
    @ResponseBody
    public ResponseEntity<String> userByEmail(HttpServletResponse response, @RequestParam String email) {
        return apiService.getCall(config.getUsersByEmailUrl() + email);
    }

    @GetMapping(value = "/createUser")
    @ResponseBody
    public ResponseEntity<String> createUser(HttpServletResponse response) {
        JSONObject request = new JSONObject();
        request.put("email", "norman.lewis@email.com");
        request.put("given_name", "Norman");
        request.put("family_name", "Lewis");
        request.put("connection", "Username-Password-Authentication");
        request.put("password", "Pa33w0rd");

        return apiService.postCall(config.getUsersUrl(), request.toString());
    }
}