package com.company.entityviewssample.web.department;

import com.company.entityviewssample.entity.MutableDepartment;
import com.company.entityviewssample.entity.ReadonlyDepartmentAndManager;
import com.company.entityviewssample.service.DepartmentsService;
import com.haulmont.addons.cuba.entity.projections.scan.ProjectionsConfiguration;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Install;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.screen.StandardLookup;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.Target;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.List;

@UiController("entityviewssample_Department.browse")
@UiDescriptor("department-browse.xml")
@LookupComponent("departmentsTable")
@LoadDataBeforeShow
public class DepartmentBrowse extends StandardLookup<ReadonlyDepartmentAndManager> {

    @Inject
    private DepartmentsService departmentsService;

    @Inject
    private ProjectionsConfiguration viewsConfiguration;

    @Inject
    private GroupTable<ReadonlyDepartmentAndManager> departmentsTable;

    @Inject
    private CollectionContainer<ReadonlyDepartmentAndManager> departmentsDc;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private Dialogs dialogs;

    @Inject
    private MetadataTools metadataTools;

    @Install(to = "departmentsDl", target = Target.DATA_LOADER)
    private List<ReadonlyDepartmentAndManager> departmentsDlLoadDelegate(LoadContext<ReadonlyDepartmentAndManager> loadContext) {
        return departmentsService.getReadonlyDepartmentData(loadContext);
    }

    @Subscribe
    private void onInit(InitEvent event) {
        departmentsDc.setView(viewsConfiguration.getViewByProjection(ReadonlyDepartmentAndManager.class));
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
                    .withMessage("Do you want to delete department \""+ metadataTools.getInstanceName(selected)+"\"?")
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
