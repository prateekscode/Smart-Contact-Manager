package com.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import com.scm.validators.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {

	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email Address [example@gmail.com]")
	private String email;

	@NotBlank(message = "Phone Number is required")
	@Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
	private String phoneNumber;
	
	@NotBlank(message = "Address is required")
	private String address;

	private String description;

	private Boolean favorite;

	private String websiteLink;

	private String linkedInLink;

	//create annotation to validate the file.
	//size, resolution
	@ValidFile
	private MultipartFile contactImage;

}
