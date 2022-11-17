package com.emrys.learnelastcicache.person;

import org.springframework.beans.factory.annotation.Value;

public interface AddressView {
    String getZipCode();
    Person getPerson();

    interface PersonRs {
        String getFirstName();
    }
}