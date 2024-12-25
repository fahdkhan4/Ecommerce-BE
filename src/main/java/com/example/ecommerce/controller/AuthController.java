package com.example.ecommerce.controller;

import com.example.ecommerce.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    OtpService otpService;

    // Method to generate OTP
    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        otpService.generateOtp(email);
        return ResponseEntity.ok("OTP sent to email!");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(otp);
        return ResponseEntity.ok(isValid);
    }


    @PatchMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String newPassword = request.get("newPassword");
        if (newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("Password cannot be null or empty.");
        }

        boolean isUpdated = otpService.updatePassword(newPassword);
        if (isUpdated) {
            return ResponseEntity.ok("Password updated successfully!");
        } else {
            return ResponseEntity.status(400).body("Error updating password. Please check OTP or email.");
        }
    }






}