package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.service.UsersService;

@Component
public class AddUserValidator implements Validator {

	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		// el dni está vacio
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.empty");

		// dni no tiene el numero correcto de caracteres
		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}

		// el usuario ya está registrado
		if (usersService.getUserByDni(user.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}

		// el nombre no tiene el numero correcto de caracteres
		if (user.getName().length() < 4 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}

		// El apellido no tiene el numero correcto de caracteres
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}

	}

}
