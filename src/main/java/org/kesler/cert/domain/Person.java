package org.kesler.cert.domain;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private String name;
    private String surName;
    private String parentName;

    private String paspSer;
    private String paspNum;
    private Date paspIssueDate;
    private String paspIssueOGV;

    private String snils;

    private Set<Cert> certs;
    private Set<CertRight> rights;

    public Person() {
        certs = new HashSet<Cert>();
        rights = new HashSet<CertRight>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public Set<Cert> getCerts() { return certs; }

    public Set<CertRight> getRights() { return rights; }

    public String getPaspSer() { return paspSer; }
    public void setPaspSer(String paspSer) { this.paspSer = paspSer; }

    public String getPaspNum() { return paspNum; }
    public void setPaspNum(String paspNum) { this.paspNum = paspNum; }

    public Date getPaspIssueDate() { return paspIssueDate; }
    public void setPaspIssueDate(Date paspIssueDate) { this.paspIssueDate = paspIssueDate; }

    public String getPaspIssueOGV() { return paspIssueOGV; }
    public void setPaspIssueOGV(String paspIssueOGV) { this.paspIssueOGV = paspIssueOGV; }

    public String getSnils() { return snils; }
    public void setSnils(String snils) { this.snils = snils; }



}

