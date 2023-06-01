package com.streams;

import java.util.ArrayList;import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaStreamsApplication.class, args);
		System.err.println("Started");
		List<Person> people = getPeople();
		
/*		List<Person> females = new ArrayList<>();
		
		// Imperative approach ❌
		
		for(Person person : people) {
			if(person.getGender().equals(Gender.FEMALE)) {
				females.add(person);
			}
		}
		
		females.forEach(System.err::println);	*/
		
		// Declarative approach ✅
		
		// Filter
		
		List<Person> females = people.stream()
			.filter(person -> person.getGender().equals(Gender.FEMALE))
			.collect(Collectors.toList());
		
//		females.forEach(System.err::println);
		
		//	Sort
		List<Person> sortedPeople = people.stream()
			.sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
			.collect(Collectors.toList());
//		sortedPeople.forEach(System.err::println);
		
		// All match
		boolean allMatch = people.stream()
			.allMatch(person -> person.getAge() > 8);
		
//		System.err.println(allMatch);
		
		// Any Match
		boolean anyMatch = people.stream()
				.anyMatch(person -> person.getAge() > 8);
//		System.err.println(anyMatch);
		
		// Non Match
		boolean noneMatch = people.stream()
				.noneMatch(person -> person.getName().equals("Jamie Goa"));
//		System.err.println(noneMatch);
		
	    // Max
		people.stream()
			.max(Comparator.comparing(Person::getAge));
//			.ifPresent(System.out::println);
		
		// Min
		people.stream()
		.min(Comparator.comparing(Person::getAge));
//		.ifPresent(System.out::println);
		
		// Group
		Map<Gender, List<Person>> groupByGender = people.stream()
			.collect(Collectors.groupingBy(Person::getGender));
		
//		groupByGender.forEach((gender, people1) -> {
//			System.err.println(gender);
//			people1.forEach(System.err::println);
//		});
		Optional<String> oldestFemaleAge = people.stream()
			.filter(person -> person.getGender().equals(Gender.FEMALE))
			.max(Comparator.comparing(Person::getAge))
			.map(Person::getName);
//		oldestFemaleAge.ifPresent(name -> System.err.println(name));
		oldestFemaleAge.ifPresent(System.err::println);
	}
	
	public static List<Person> getPeople() {
		return List.of(
				new Person("Antonio", 20, Gender.MALE),
		        new Person("Alina Smith", 33, Gender.FEMALE),
		        new Person("Helen White", 57, Gender.FEMALE),
		        new Person("Alex Boz", 14, Gender.MALE),
		        new Person("Jamie Goa", 99, Gender.MALE),
		        new Person("Anna Cook", 7, Gender.FEMALE),
		        new Person("Zelda Brown", 120, Gender.FEMALE)
				);
	}

}
