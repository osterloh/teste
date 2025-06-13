package br.com.osterloh.service;

import br.com.osterloh.model.Person;

import java.util.List;

public interface IPersonService {

    Person createPerson(Person person);

    List<Person> findAllPersons();

    Person findPersonById(Long id);

    void removePersonById(Long id);

    void setPersons(List<Person> persons);

}
