package com.uniovi.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.service.MarksService;
import com.uniovi.service.UsersService;

@Controller
public class MarksControllers {

	@Autowired // Inyectar el servicio
	private MarksService marksService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/mark/list")
	public String getList(Pageable pageable,Model model,Principal principal, @RequestParam(value = "",required=false) String searchText) {

		/*Set<Mark> consultedList = (Set<Mark>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Mark>();
		}
		model.addAttribute("consultedList", consultedList);*/
		
		// añade la lista de notas al modelo con el nombre markList
		
		
		//Principal ≈ SecurityContextHolder
		String dni = principal.getName();
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		
		if(searchText !=null && !searchText.isEmpty()) {
		marks = marksService.searchMarksByDescriptionAndNameForUser(pageable,searchText, user);	
		}else {
		marks = marksService.getMarksForUser(pageable,user);}
		
		model.addAttribute("markList",marks.getContent());
		model.addAttribute("page",marks);
		return "/mark/list";
	}

	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		marksService.addMark(mark);
		return "redirect:/mark/list";
	}

	@RequestMapping("/mark/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		return "mark/details";
	}

	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		marksService.deleteMark(id);
		return "redirect:/mark/list";
	}

	@RequestMapping("/mark/add")
	public String getMark(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "mark/add";
	}

	@RequestMapping(value = "mark/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		model.addAttribute("usersList", usersService.getUsers());
		return "mark/edit";
	}

	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable long id, @ModelAttribute Mark mark) {
		Mark original = marksService.getMark(id);
		original.setScore(mark.getScore());
		original.setDescription(mark.getDescription());
		marksService.addMark(original);
		return "redirect:/mark/details/" + id;
	}

	@RequestMapping("/mark/list/update")
	public String updateList(Pageable pageable,Model model,Principal principal) {
		String dni = principal.getName();
		User user = usersService.getUserByDni(dni);
		model.addAttribute("markList", marksService.getMarksForUser(pageable,user).getContent());
		return "mark/list :: tableMarks";
	}
	
	@RequestMapping(value="/mark/{id}/resend",method = RequestMethod.GET)
	public String setResendTrue(Model model,@PathVariable Long id) {
		
		marksService.setMarkResend(true, id);
		return "redirect:/mark/list";
		
	}
	
	@RequestMapping(value="/mark/{id}/noresend",method = RequestMethod.GET)
	public String setResendFalse(Model model,@PathVariable Long id) {
		
		marksService.setMarkResend(false, id);
		return "redirect:/mark/list";
		
	}

}
