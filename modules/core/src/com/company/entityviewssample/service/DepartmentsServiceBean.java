package com.company.entityviewssample.service;

import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Service(DepartmentsService.NAME)
public class DepartmentsServiceBean implements DepartmentsService {

    @Inject
    DataManager dataManager;

    @Override
    public List<ReadonlyDepartmentAndManager> getReadonlyDepartmentData(LoadContext<ReadonlyDepartmentAndManager> loadContext) {
        return dataManager.load(ReadonlyDepartmentAndManager.class).list();
    }

    @Override
    public MutableDepartment createDepartment() {
        return dataManager.create(MutableDepartment.class);
    }

    @Override
    public MutableDepartment getDepartment(UUID id) {
        return dataManager.load(MutableDepartment.class).id(id).one();
    }

    @Override
    public void removeDepartment(Entity entity) {
        dataManager.remove(entity);
    }
}
