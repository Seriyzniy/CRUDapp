package ru.my.spring.crud.myModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Persons")
public class Person {
	@Id
	@Column(name = "p_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "p_name")
	@NotEmpty(message = "Name can`t not be empty!")
	@Size(min = 2, max = 30, message = "Name can be length between 2 and 30 character!")
	private String name;
	
	@Column(name = "p_email")
	@NotEmpty(message = "Email can`t not be empty!")
	@Email(message = "Email should be valid format!")
	private String email;
	
	@Column(name = "p_age")
	@Min(value = 0, message = "Age can`t be negative value!")
	private int age;
	
	@Column(name = "p_adress")
	@Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Format will be 'Country, City, Index(6 numbers)'")
	private String adress;
	
	@Column(name = "date_of_birthday")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateBirth;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@Enumerated(EnumType.STRING)
	private Mood mood;
	
	public Person() {};
	public Person(String name, int age, String email, String adress) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.adress = adress;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getAge() {
		return age;
	}
	public String getAdress() {
		return adress;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public Date getCreateAt() {
		return createAt;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public Mood getMood() {
		return mood;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String em) {
		this.email = em;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setAdress(String adr) {
		this.adress = adr;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
	}
}
