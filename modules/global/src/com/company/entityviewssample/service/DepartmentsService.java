package com.company.entityviewssample.service;

import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.LoadContext;

import java.util.List;
import java.util.UUID;

public interface DepartmentsService {
    String NAME = "entityviewssample_DepartmentsService";

    List<ReadonlyDepartmentAndManager> getReadonlyDepartmentData(LoadContext<ReadonlyDepartmentAndManager> loadContext);

    MutableDepartment createDepartment();

    MutableDepartment getDepartment(UUID id);

    void removeDepartment(Entity entity);
}