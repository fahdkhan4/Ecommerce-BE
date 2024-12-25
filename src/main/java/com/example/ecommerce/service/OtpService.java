package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class OtpService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JavaMailSender emailSender;


    private final Map<String, String> otpToEmailMap = new ConcurrentHashMap<>(); // Maps OTP to email
    private final Map<String, OtpDetails> otpStore = new ConcurrentHashMap<>(); // Maps email to OTP details

    // Generate OTP for a user
    public String generateOtp(String email) {

        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Email not found in the database.");
        }

        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // 1 minute expiration


        OtpDetails otpDetails = new OtpDetails(otp, expirationTime);
        otpStore.put(otp, otpDetails);
        otpToEmailMap.put(otp, email);

        sendEmail(email, otp);

        return otp;
    }

    // Verify OTP without email
    public boolean verifyOtp(String otp) {
        OtpDetails otpDetails = otpStore.get(otp);

        if (otpDetails != null && otpDetails.getExpirationTime().isAfter(LocalDateTime.now())) {
            otpStore.remove(otp);
            return true;
        }

        return false;
    }

    public boolean updatePassword(String newPassword) {
        if (otpToEmailMap.isEmpty()) {
            System.out.println("No OTP to email mapping found.");
            return false; // No OTP/email mapping exists
        }

        // Get the most recent OTP (you may use other strategies for production)
        String recentOtp = otpToEmailMap.keySet().iterator().next(); // Use the first OTP for simplicity
        String email = otpToEmailMap.get(recentOtp);

        if (email == null) {
            System.out.println("No email found for the provided OTP.");
            return false; // Email not found for the OTP
        }



        try {
            // Update password in the database
            String updateQuery = "UPDATE user SET password = ? WHERE email = ?";
            int rowsUpdated = jdbcTemplate.update(updateQuery, newPassword, email);
            if (rowsUpdated > 0) {
                otpStore.remove(email);
                otpToEmailMap.remove(recentOtp); // Clean up OTP mappings
                return true;
            } else {
                System.out.println("No user found with email: " + email);
                return false; // User not found
            }
        } catch (Exception e) {
            System.out.println("Error updating password: " + e.getMessage());
            return false; // Password update failed
        }
    }





    // Send OTP email
    private void sendEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Ecommercestore819@gmail.com");
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Dear User,\n\n" +
                "We have received your request to reset your password. Use the following OTP to proceed:\n\n" +
                "OTP: " + otp + "\n\n" +
                "This OTP will expire in 1 minute. If you did not request a password reset, please ignore this email.");

        try {
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error sending OTP email: " + e.getMessage());
        }
    }

    // Inner class to store OTP details
    private static class OtpDetails {
        private final String otp;
        private final LocalDateTime expirationTime;

        public OtpDetails(String otp, LocalDateTime expirationTime) {
            this.otp = otp;
            this.expirationTime = expirationTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpirationTime() {
            return expirationTime;
        }
    }
}