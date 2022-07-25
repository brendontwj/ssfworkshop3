package com.vttp2022.ssfworkshop3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vttp2022.ssfworkshop3.model.Contact;
import com.vttp2022.ssfworkshop3.util.Contacts;

@Controller
@RequestMapping(path="/addressbook")
public class AddressController {
    @Autowired
    Contacts ctcz;

    @Autowired
    ApplicationArguments appArgs;
    
    @GetMapping
    public String showAddressbookForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    @PostMapping
    public String saveContact(@ModelAttribute Contact contact, Model model) {
        ctcz.saveContact(contact, model, appArgs);
        return "showcontact";
    }

    @GetMapping("{contactId}")
    public String getContactById(Model model, @PathVariable String contactId ) {
        ctcz.getContactById(model, contactId, appArgs);
        return "showcontact";
    }
}
