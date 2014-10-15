package org.kesler.cert.domain;


import java.util.Date;
import java.util.Set;

public class Cert {
    private CA ca;
    private Person person;
    private Date issueDate;
    private Date expireDate;

    private Set<CertRight> rights;

    private boolean active;

    public CA getCa() {
        return ca;
    }

    public void setCa(CA ca) {
        this.ca = ca;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Set<CertRight> getRights() {
        return rights;
    }

    public void setRights(Set<CertRight> rights) {
        this.rights = rights;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int compareTo(Object another) {
        if(another==null) return 1;
        if(!(another instanceof Cert)) return 1;
        Cert anotherCert = (Cert)another;
        Date thisIssueDate = this.getIssueDate();
        Date anotherIssueDate = anotherCert.getIssueDate();
        if (thisIssueDate==null&&anotherIssueDate==null) return 0;
        if (thisIssueDate!=null&&anotherIssueDate==null) return 1;
        if (thisIssueDate==null&&anotherIssueDate!=null) return -1;
        if (thisIssueDate.equals(anotherIssueDate)) return 0;
        if (thisIssueDate.before(anotherIssueDate)) return -1;
        else return 1;
    }
}
