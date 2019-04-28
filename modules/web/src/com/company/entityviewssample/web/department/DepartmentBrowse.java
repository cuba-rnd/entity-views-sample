package com.company.entityviewssample.web.department;

import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;
import com.company.entityviewssample.service.DepartmentsService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.queryconditions.Condition;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.DataLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.entityviewssample.entity.Department;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UiController("entityviewssample_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
@LoadDataBeforeShow
public class DepartmentBrowse extends StandardLookup<ReadonlyDepartmentAndManager> {

    @Inject
    private DepartmentsService departmentsService;

    @Inject
    private Logger log;

    @Install(to = "departmentsDl", target = Target.DATA_LOADER)
    private List<ReadonlyDepartmentAndManager> departmentsDlLoadDelegate(LoadContext<ReadonlyDepartmentAndManager> loadContext) {
        return departmentsService.getReadonlyDepartmentData();
    }





}