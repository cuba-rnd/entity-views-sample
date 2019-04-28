package com.company.entityviewssample.service;

import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;

import java.util.List;
import java.util.UUID;

public interface DepartmentsService {
    String NAME = "entityviewssample_DepartmentsService";

    List<ReadonlyDepartmentAndManager> getReadonlyDepartmentData();

    List<MutableDepartment> getDepartmentsForEdit();

    MutableDepartment getDepartment(UUID id);
}