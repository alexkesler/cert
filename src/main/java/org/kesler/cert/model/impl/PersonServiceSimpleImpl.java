package org.kesler.cert.model.impl;

import org.kesler.cert.domain.Person;
import org.kesler.cert.model.PersonService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonServiceSimpleImpl implements PersonService {
    private List<Person> persons = new ArrayList<Person>();

    @Override
    public Collection<Person> getAllPersons() {
        return persons;
    }

    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void removePerson(Person person) {
        persons.remove(person);
    }
}
