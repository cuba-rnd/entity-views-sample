package com.company.entityviewssample.entity;

import com.haulmont.addons.cuba.entity.projections.BaseProjection;

import java.util.Date;
import java.util.UUID;

public interface MutableDepartment extends BaseProjection<Department, UUID> {

    String getName();

    void setName(String name);

    Date getFoundationDate();

    void setFoundationDate(Date foundationDate);

}
