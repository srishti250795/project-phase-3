package com.SportyShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SportyShoes.model.Admin;
import com.SportyShoes.repository.AdminRepository;
import com.SportyShoes.repository.ProductRepository;
import com.SportyShoes.repository.PurchaseReportRepository;
import com.SportyShoes.repository.Repository;
import com.SportyShoes.repository.UserRepository;

@Controller
public class MyController 
{
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchaseReportRepository purchaseReportRepository;
    @Autowired
    Repository repository;
    
    @RequestMapping("/")
    public String showHome() {
        return"home.jsp";
    }
    
    @RequestMapping(value="adminLogin")
    public String adminLoginPage() {
        return"AdminLogin.jsp";
    }
    
    @RequestMapping(value="forgotPassword")
    public String forgotPassword() {
        return"ForgotPassword.jsp";
    }
    
    @RequestMapping(value="adminForgotPassword")
    public String adminForgotPasswordPage(@RequestParam("email")String email,
            @RequestParam("password")String password,ModelMap map) {
        
        if(repository.forgotPassword(email,password))
            map.addAttribute("message","Password Updated");
        else
            map.addAttribute("message","Invalid Details");
        
        return"ForgotPassword.jsp";
    }
    
    @RequestMapping(value="adminPage")
    public String adminPage(@RequestParam(name="email")String email,
            @RequestParam(name="password")String password,ModelMap map) {
        
        if(adminRepository.verifyAdmin(new Admin(email,password)))
            return"AdminPage.jsp";
        else {
            map.addAttribute("message", "Invalid Details");
            return"AdminLogin.jsp";
        }   
    }
    
    @RequestMapping(value="product")
    public String getAllProducts(ModelMap map) {
        map.addAttribute("productList",productRepository.getAllProducts());
        return"Product.jsp";   
    }
    
    @RequestMapping(value="user")
    public String getAllUsers(ModelMap map) {
        
        map.addAttribute("userList",userRepository.getAllUsers());
        return"User.jsp";  
    }
    
    @RequestMapping(value="searchUser")
    public String searchUser(@RequestParam("email")String email,ModelMap map) {
        
        map.addAttribute("userList",userRepository.searchUser(email));
        return"User.jsp";  
    }
    
    @RequestMapping(value="purchaseReport")
    public String getRport(ModelMap map) {
        
        map.addAttribute("report",purchaseReportRepository.getReport());
        return"PurchaseReport.jsp";    
    }
    
    @RequestMapping(value="deleteProduct")
    public String deleteProduct(@RequestParam("id")int id,ModelMap map) {
        
        if(productRepository.deleteProduct(id))
            map.addAttribute("message","Product Deleted Successfully");
        else
            map.addAttribute("message","Try after few minutes");
        
        map.addAttribute("productList",productRepository.getAllProducts());
        
        return"Product.jsp";   
    }
}
