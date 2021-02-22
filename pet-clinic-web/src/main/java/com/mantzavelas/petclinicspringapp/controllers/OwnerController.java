package com.mantzavelas.petclinicspringapp.controllers;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    public static final String CREATE_OR_UPDATE_OWNER_FORM = "owners/ownerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwner(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");

            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.stream().findFirst().orElse(null);

            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);

            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));

        return modelAndView;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_FORM;
        }
        Owner savedOwner = ownerService.save(owner);

        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@PathVariable("ownerId") Long ownerId,@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_FORM;
        }
        Owner savedOwner = ownerService.save(owner);

        return "redirect:/owners/" + savedOwner.getId();
    }
}
