package ru.my.spring.crud.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.my.spring.crud.myModel.Mood;
import ru.my.spring.crud.myModel.Person;
import ru.my.spring.crud.repositories.PersonRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {
	private final PersonRepository personRepository;
	
	@Autowired
	public PeopleService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public Person findOne(int id) {
		Optional<Person> optional = personRepository.findById(id);
		return optional.orElse(null);
	}
	
	@Transactional
	public void save(Person person) {
		person.setCreateAt(new Date());
		person.setMood(Mood.HAPPY);
		personRepository.save(person);
	}
	
	@Transactional
	public void update(int id, Person person) {
		person.setId(id);
		person.setCreateAt(new Date());
		personRepository.save(person);
	}
	
	@Transactional
	public void delete(int id) {
		personRepository.deleteById(id);
	}
	
}
