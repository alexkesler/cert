package org.kesler.cert.model.impl;

import org.kesler.cert.domain.Person;
import org.kesler.cert.model.PersonService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonServiceSimpleImpl implements PersonService {
    private List<Person> persons = new ArrayList<Person>();

    @Override
    public Collection<Person> getAllPersonas() {
        return persons;
    }

    @Override
    public void addPersona(Person person) {
        persons.add(person);
    }

    @Override
    public void updatePersona(Person person) {

    }

    @Override
    public void removePersona(Person person) {
        persons.remove(person);
    }
}
