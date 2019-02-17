package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator {

	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		//el dni está vacio
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		
		//dni no tiene el numero correcto de caracteres
		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		
		//el usuario ya está registrado
		if (usersService.getUserByDni(user.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		
		//el nombre no tiene el numero correcto de caracteres
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		
		//El apellido no tiene el numero correcto de caracteres
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}
		
		//la contraseña no tiene el numero correcto de caracteres
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		
		//la contraseña y la repeticion de la contraseña no coinciden
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
		}

	}

}
