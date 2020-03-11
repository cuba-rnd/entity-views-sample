package com.company.entityviewssample.entity;

import com.haulmont.addons.cuba.entity.projections.BaseProjection;
import com.haulmont.chile.core.annotations.MetaProperty;

import java.util.Date;
import java.util.UUID;

public interface ReadonlyDepartmentAndManager extends BaseProjection<Department, UUID> {

    String getName();

    Date getFoundationDate();

    EmployeeFullName getManager();

    interface EmployeeFullName extends BaseProjection<Employee, UUID> {

        String getFirstName();

        String getLastName();

        @MetaProperty
        default String getFullName() {
            return String.format("%s %s", getFirstName(), getLastName());
        }

    }

}
