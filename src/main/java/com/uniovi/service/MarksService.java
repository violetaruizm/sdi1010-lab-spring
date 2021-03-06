package com.uniovi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.repositories.MarksRepository;

@Service
public class MarksService {
	// private List<Mark> marksList = new LinkedList<Mark>();

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MarksRepository marksRepository;

	/*
	 * @PostConstruct public void init() { marksList.add(new Mark(1L, "Ejercicio 1",
	 * 10.0)); marksList.add(new Mark(2L, "Ejercicio 2", 9.0)); marksList.add(new
	 * Mark(3L, "Violeta", 10.0)); }
	 */

	public Page<Mark> getMarks(Pageable pageable) {
		Page<Mark> marks =marksRepository.findAll(pageable);

		return marks;
	}

	public Mark getMark(Long id) {
		Set<Mark> consultedList = (Set<Mark>) httpSession.getAttribute("consultedList");

		if (consultedList == null) {
			consultedList = new HashSet<Mark>();
		}

		Mark markObtained = marksRepository.findById(id).get();
		consultedList.add(markObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return markObtained;

	}

	public void addMark(Mark mark) {
// Si en Id es null le asignamos el ultimo + 1 de la lista
		marksRepository.save(mark);
	}

	public void deleteMark(Long id) {
		marksRepository.deleteById(id);
	}

	public void setMarkResend(Boolean revised, Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();
		Mark mark = marksRepository.findById(id).get();

		if (mark.getUser().getDni().contentEquals(dni)) {
			marksRepository.updateResend(revised, id);
		}
	}
	
	public Page<Mark> getMarksForUser(Pageable pageable,User user){
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		if(user.getRole().equals("ROLE_STUDENT")) {
			marks = marksRepository.findAllByUser(pageable,user);
		}
		if(user.getRole().equals("ROLE_PROFESSOR")) {
			marks = getMarks(pageable);
		}
		
		return marks;
	}
	
	
	public Page<Mark> searchMarksByDescriptionAndNameForUser(Pageable pageable,String searchtext,User user){
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		searchtext = "%"+searchtext+"%";
		
		if(user.getRole().equals("ROLE_STUDENT")) {
			marks = marksRepository.searchByDescriptionNameAndUser(pageable,searchtext, user);
		}
		if(user.getRole().equals("ROLE_PROFESSOR")) {
			marks = marksRepository.searchByDescriptionAndName(pageable,searchtext);
		}
				return marks;}
}
