package com.company.entityviewssample.web.department;

import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;
import com.company.entityviewssample.service.DepartmentsService;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.actions.list.RemoveAction;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.entityviewssample.entity.Department;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@UiController("entityviewssample_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
@LoadDataBeforeShow
public class DepartmentBrowse extends StandardLookup<ReadonlyDepartmentAndManager> {

    @Inject
    private DepartmentsService departmentsService;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private GroupTable<ReadonlyDepartmentAndManager> departmentsTable;

    @Inject
    private Dialogs dialogs;

    @Install(to = "departmentsDl", target = Target.DATA_LOADER)
    private List<ReadonlyDepartmentAndManager> departmentsDlLoadDelegate(LoadContext<ReadonlyDepartmentAndManager> loadContext) {
        return departmentsService.getReadonlyDepartmentData(loadContext);
    }

    @Subscribe("departmentsTable.create")
    private void onCreateAction(Action.ActionPerformedEvent event) {
        screenBuilders.editor(MutableDepartment.class, this)
                .newEntity(departmentsService.createDepartment())
                .build().show().addAfterCloseListener(afterCloseEvent -> getScreenData().loadAll());
    }

    @Subscribe("departmentsTable.edit")
    private void onEditAction(Action.ActionPerformedEvent event) {
        ReadonlyDepartmentAndManager selected = departmentsTable.getSingleSelected();
        if (selected != null) {
            screenBuilders.editor(MutableDepartment.class, this)
                    .editEntity(selected.reload(MutableDepartment.class))
                    .build().show().addAfterCloseListener(afterCloseEvent -> getScreenData().loadAll());
        }
    }

    @Subscribe("departmentsTable.remove")
    private void onRemoveAction(Action.ActionPerformedEvent event) {
        ReadonlyDepartmentAndManager selected = departmentsTable.getSingleSelected();
        if (selected != null) {
            dialogs.createOptionDialog()
                    .withCaption("Confirm")
                    .withMessage("Do you want to delete department \""+selected.getName()+"\"?")
                    .withActions(
                            new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> {
                                departmentsService.removeDepartment(selected);
                                getScreenData().loadAll();
                            }),
                            new DialogAction(DialogAction.Type.NO)
                    )
                    .show();
        }
    }


}