package nz.co.gregs.amhan.browser.data.service;

import nz.co.gregs.amhan.browser.data.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}