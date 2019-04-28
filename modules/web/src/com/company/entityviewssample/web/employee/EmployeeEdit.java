package com.company.entityviewssample.web.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.entityviewssample.entity.Employee;

@UiController("entityviewssample_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {
}