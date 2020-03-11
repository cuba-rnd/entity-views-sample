package com.company.entityviewssample.web.department;

import com.company.entityviewssample.entity.Department;
import com.company.entityviewssample.entity.MutableDepartment;
import com.haulmont.addons.cuba.entity.projections.gui.model.impl.DataContextProjectionSupportImpl;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.model.impl.DataContextImpl;
import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("entityviewssample_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
@LoadDataBeforeShow
public class DepartmentEdit extends StandardEditor<MutableDepartment> {

    @Inject
    private InstanceLoader<Department> instanceLoader;

    @Subscribe
    private void onInit(InitEvent event) {
        DataContextImpl dataContext = new DataContextProjectionSupportImpl(AppContext.getApplicationContext());
        instanceLoader.setDataContext(dataContext);
        getScreenData().setDataContext(dataContext);
    }

}
