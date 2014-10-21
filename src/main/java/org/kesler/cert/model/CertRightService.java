package org.kesler.cert.model;

import org.kesler.cert.domain.CertRight;

import java.util.Collection;

public interface CertRightService {
    public Collection<CertRight> getAllCertRights();
    public void addcertRight(CertRight certRight);
    public void updateCertRight(CertRight certRight);
    public void removeCertRight(CertRight certRight);
}
