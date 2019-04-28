alter table ENTITYVIEWSSAMPLE_EMPLOYEE add constraint FK_ENTITYVIEWSSAMPLE_EMPLOYEE_ON_DEPARTMENT foreign key (DEPARTMENT_ID) references ENTITYVIEWSSAMPLE_DEPARTMENT(ID);
create index IDX_ENTITYVIEWSSAMPLE_EMPLOYEE_ON_DEPARTMENT on ENTITYVIEWSSAMPLE_EMPLOYEE (DEPARTMENT_ID);