package com.mantzavelas.petclinicspringapp.controllers;

import com.mantzavelas.petclinicspringapp.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"","/index", "/index.html"})
    public String listOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwner() {

        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));

        return modelAndView;
    }
}
