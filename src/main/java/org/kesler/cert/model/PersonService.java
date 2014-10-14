package org.kesler.cert.model;

import org.kesler.cert.domain.Person;

import java.util.Collection;

public interface PersonService {
    public Collection<Person>  getAllPersonas();
    public void addPersona(Person person);
    public void updatePersona(Person person);
    public void removePersona(Person person);
}


