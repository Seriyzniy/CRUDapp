package ru.my.spring.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.my.spring.crud.myModel.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
}
