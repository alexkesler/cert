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
}
