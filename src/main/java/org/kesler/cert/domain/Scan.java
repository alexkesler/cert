package org.kesler.cert.domain;

import java.util.Date;

public class Scan {

    private Person person;
    private String name;
    private String fileName;
    private Date storeDate;

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public Date getStoreDate() { return storeDate; }
    public void setStoreDate(Date storeDate) { this.storeDate = storeDate; }
}
