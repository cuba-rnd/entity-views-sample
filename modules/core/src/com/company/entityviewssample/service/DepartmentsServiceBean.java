package com.company.entityviewssample.service;

import com.company.entityviewssample.entity.Department;
import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;
import com.haulmont.addons.cuba.entity.views.ViewsSupportDataManagerBean;
import com.haulmont.cuba.core.entity.contracts.Id;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(DepartmentsService.NAME)
public class DepartmentsServiceBean implements DepartmentsService {

    @Inject
    ViewsSupportDataManagerBean dataManager;

    @Override
    public List<ReadonlyDepartmentAndManager> getReadonlyDepartmentData() {
        return dataManager.load(ReadonlyDepartmentAndManager.class).list();
    }

    @Override
    public List<MutableDepartment> getDepartmentsForEdit() {
        return dataManager.load(MutableDepartment.class).list();
    }

    @Override
    public MutableDepartment getDepartment(UUID id) {
        if (id == null) {
            return dataManager.create(MutableDepartment.class);
        }
        return dataManager.load(MutableDepartment.class).id(id).one();

    }
}