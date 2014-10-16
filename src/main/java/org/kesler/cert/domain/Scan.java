package org.kesler.cert.domain;

import java.util.Date;

public class Scan implements Comparable<Scan>{

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

    @Override
    public int compareTo(Scan another) {
        if(another==null) return 1;
        Scan anotherScan = (Scan)another;
        Date thisStoreDate = this.getStoreDate();
        Date anotherStoreDate = anotherScan.getStoreDate();
        if (thisStoreDate==null&&anotherStoreDate==null) return 0;
        if (thisStoreDate!=null&&anotherStoreDate==null) return 1;
        if (thisStoreDate==null&&anotherStoreDate!=null) return -1;
        if (thisStoreDate.equals(anotherStoreDate)) return 0;
        if (thisStoreDate.before(anotherStoreDate)) return -1;
        else return 1;
    }

}
