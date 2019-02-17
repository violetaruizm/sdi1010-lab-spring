package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByDni(String dni);
	//funciones findBy(atributo) se dotan de funcionalidad automática
}
