package com.company.entityviewssample.web.department;

import com.company.entityviewssample.entity.Department;
import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.service.DepartmentsService;
import com.haulmont.addons.cuba.entity.views.gui.model.impl.DataContextViewSupportImpl;
import com.haulmont.cuba.core.global.EntityStates;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.model.impl.DataContextImpl;
import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.Install;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.Target;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.UUID;

@UiController("entityviewssample_Department.edit")
@UiDescriptor("department-edit.xml")
@EditedEntityContainer("departmentDc")
@LoadDataBeforeShow
public class DepartmentEdit extends StandardEditor<MutableDepartment> {

    @Inject
    private DepartmentsService departmentsService;

    @Inject
    private InstanceLoader<Department> instanceLoader;

    @Inject
    private EntityStates entityStates;

    @Subscribe
    private void onInit(InitEvent event) {
        DataContextImpl dataContext = new DataContextViewSupportImpl(AppContext.getApplicationContext());
        instanceLoader.setDataContext(dataContext);
        getScreenData().setDataContext(dataContext);
    }

    @Install(to = "instanceLoader", target = Target.DATA_LOADER)
    private MutableDepartment dptLoadDelegate(LoadContext<MutableDepartment> loadContext) {
        if (!entityStates.isNew(this.getEditedEntity().getOrigin())) {//Cannot override EntityStates to a version with views support
            return departmentsService.getDepartment((UUID) loadContext.getId());
        } else {
            return getEditedEntity();
        }
    }

}