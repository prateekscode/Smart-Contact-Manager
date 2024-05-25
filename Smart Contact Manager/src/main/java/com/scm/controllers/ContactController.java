package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

	private Logger logger=LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;

	// add contact page: handler
	@RequestMapping("/add")
	public String addContactView(Model model) {
		ContactForm contactForm = new ContactForm();
		model.addAttribute("contactForm", contactForm);
		return "user/add_contact";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
			Authentication authentication, HttpSession session) {
		// process the form data

		// validate form
		
		if (result.hasErrors()) {
			session.setAttribute("message",
					Message.builder().content("Please correct the following errors").type(MessageType.red).build());
			return "user/add_contact";
		}

		String username = Helper.getEmailOfLoggedInUser(authentication);

		// form --> contact

		User user = userService.getUserByEmail(username);

		//process the contact picture
		//image process 
		
		
		//code for uploading image
		String fileName=UUID.randomUUID().toString();
		String fileURL=imageService.uploadImage(contactForm.getContactImage(),fileName);
		
		Contact contact = new Contact();
		contact.setName(contactForm.getName());
		contact.setFavorite(contactForm.getFavorite());
		contact.setAddress(contactForm.getAddress());
		contact.setDescription(contactForm.getDescription());
		contact.setEmail(contactForm.getEmail());
		contact.setLinkedinLink(contactForm.getLinkedInLink());
		contact.setWebsiteLink(contactForm.getWebsiteLink());
		contact.setPhoneNumber(contactForm.getPhoneNumber());
		contact.setUser(user);
		contact.setPicture(fileURL);
		contact.setCloudinaryImagePublicId(fileURL);
		contactService.save(contact);

		//set the contact picture url
		
		//set message to be displayed on view
		session.setAttribute("message",
				Message.builder().content("You have successfully added a new contact.").type(MessageType.green).build());
		return "redirect:/user/contacts/add";
	}
}
