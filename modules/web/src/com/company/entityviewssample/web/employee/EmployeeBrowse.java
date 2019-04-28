package com.company.entityviewssample.web.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.entityviewssample.entity.Employee;

@UiController("entityviewssample_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class EmployeeBrowse extends StandardLookup<Employee> {
}