package br.com.osterloh.service;

import br.com.osterloh.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService {

    List<Person> persons = new ArrayList<>();

    @Override
    public Person createPerson(Person person) {
        var id = new AtomicLong().incrementAndGet();
        person.setId(id);

        if (Objects.isNull(person.getEmail()) || person.getEmail().isBlank()) {
            throw new IllegalArgumentException("The Person E-Mail is null or empty!");
        }

        return person;
    }

    @Override
    public List<Person> findAllPersons() {

        if (persons.isEmpty()) {
            throw new RuntimeException("Não possui pessoas cadastradas!");
        }

        return persons;
    }

    @Override
    public Person findPersonById(Long id) {

        if (persons.isEmpty() || id >= persons.size()) {
            throw new IllegalArgumentException("Não possui pessoas cadastradas ou não encontrou com este ID!");
        }

        return persons.get(id.intValue());
    }

    @Override
    public void removePersonById(Long id) {
        Person person = findPersonById(id);

        persons.remove(person);
    }

    @Override
    public void setPersons(List<Person> personList){
        persons.addAll(personList);
    }
}
