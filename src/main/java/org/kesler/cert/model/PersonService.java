package org.kesler.cert.model;

import org.kesler.cert.domain.Person;

import java.util.Collection;

public interface PersonService {
    public Collection<Person> getAllPersons();
    public void addPerson(Person person);
    public void updatePerson(Person person);
    public void removePerson(Person person);
}


