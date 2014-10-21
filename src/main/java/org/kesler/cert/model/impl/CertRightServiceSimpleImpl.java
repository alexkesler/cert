package org.kesler.cert.model.impl;

import org.kesler.cert.domain.CertRight;
import org.kesler.cert.model.CertRightService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alex on 21.10.14.
 */
public class CertRightServiceSimpleImpl implements CertRightService {
    private List<CertRight> certRights = new ArrayList<CertRight>();
    @Override
    public Collection<CertRight> getAllCertRights() {
        return certRights;
    }

    @Override
    public void addCertRight(CertRight certRight) {
        certRights.add(certRight);
    }

    @Override
    public void updateCertRight(CertRight certRight) {

    }

    @Override
    public void removeCertRight(CertRight certRight) {
        certRights.remove(certRight);
    }
}
