package sdu.colloborIQ.colloborIQ.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sdu.colloborIQ.colloborIQ.dto.SdudentDTO;
import sdu.colloborIQ.colloborIQ.services.SdudentService;
import sdu.colloborIQ.colloborIQ.util.SdudentValidator;

@Controller
public class AuthController {
    private final SdudentService sdudentService;
    private final SdudentValidator sdudentValidator;
    public AuthController(SdudentService sdudentService, SdudentValidator sdudentValidator) {
        this.sdudentService = sdudentService;
        this.sdudentValidator = sdudentValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/register")
    public String registerPage(@ModelAttribute("sdudent")SdudentDTO sdudentDTO){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("sdudent") @Valid SdudentDTO sdudentDTO,
                           BindingResult bindingResult){
        sdudentValidator.validate(sdudentDTO,bindingResult);
        if (bindingResult.hasErrors())return "auth/register";
        sdudentService.save(sdudentDTO);
        System.out.println("sdudent post");
        return "auth/login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "auth/login";
    }

}
