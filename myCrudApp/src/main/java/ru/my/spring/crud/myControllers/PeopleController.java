package ru.my.spring.crud.myControllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.my.spring.crud.myModel.Person;
import ru.my.spring.crud.services.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {
	private PeopleService myPersonService;
	
	@Autowired
	public PeopleController(PeopleService myPersonService) {
		this.myPersonService = myPersonService;
	}
	//_____________________________
	// 			CRUD part 1
	//_____________________________
	@GetMapping()
	public String showAll(Model myModel) {
		myModel.addAttribute("allpeople", myPersonService.findAll());
		return "people/showall";
	}
	@GetMapping("/{id}")
	public String showOne(@PathVariable("id") int id, Model myModel) {
		myModel.addAttribute("oneperson", myPersonService.findOne(id));
		return "people/showone";
	}
	//_____________________________
	// 			CRUD part 2
	//_____________________________
	@GetMapping("/new")
	public String getCreatePersonForm(Model model) {
		model.addAttribute("newPerson", new Person());
		return "people/new";
	}
	
	@PostMapping()
	public String addPersonInDateBase(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "people/new";
		
		myPersonService.save(person);
		return "redirect:/people";
	}
	//_____________________________
	// 			CRUD part 3
	//_____________________________
	@GetMapping("/{id}/edit")
	public String getEditPersonForm(Model model, @PathVariable("id") int id) {
		model.addAttribute("persForId", myPersonService.findOne(id));
		return "people/edit";
	}
	
	@PatchMapping("/{id}")
	public String updatePersonInDateBase(@ModelAttribute("persForId") @Valid Person person, 
			BindingResult bindingResult, @PathVariable("id") int id) {
		
		if(bindingResult.hasErrors())
			return "people/edit";
		
		myPersonService.update(id, person);
		return "redirect:/people";
	}
	
	@DeleteMapping("/{id}")
	public String deletePersonInDateBase(@PathVariable("id") int id) {
		myPersonService.delete(id);
		return "redirect:/people";
	}
	
}
