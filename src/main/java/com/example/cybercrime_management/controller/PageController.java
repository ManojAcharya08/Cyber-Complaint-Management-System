package com.example.cybercrime_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    // Login page (index.html)
    @GetMapping({"/", "/login"})
    public String login() {
        logger.info("Navigating to login page");
        return "index";  // Expects index.html in templates
    }

    // Home page (home.html)
    @GetMapping("/home")
    public String home() {
        logger.info("Navigating to home page");
        return "home";  // Expects home.html in templates
    }

    // File a complaint page (file-complaint.html)
    @GetMapping("/file-complaint")
    public String fileComplaint() {
        logger.info("Navigating to file complaint page");
        return "file-complaint";  // Expects file-complaint.html in templates
    }

    // View complaints page (view-complaints.html)
    @GetMapping("/view-complaints")
    public String viewComplaints() {
        logger.info("Navigating to view complaints page");
        return "view-complaints";  // Expects view-complaints.html in templates
    }
}
