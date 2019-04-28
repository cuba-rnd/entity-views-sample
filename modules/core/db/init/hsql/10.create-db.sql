-- begin ENTITYVIEWSSAMPLE_DEPARTMENT
create table ENTITYVIEWSSAMPLE_DEPARTMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FOUNDATION_DATE date,
    MANAGER_ID varchar(36),
    --
    primary key (ID)
)^
-- end ENTITYVIEWSSAMPLE_DEPARTMENT
-- begin ENTITYVIEWSSAMPLE_EMPLOYEE
create table ENTITYVIEWSSAMPLE_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255) not null,
    LAST_NAME varchar(255),
    BIRTH_DATE date not null,
    DEPARTMENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end ENTITYVIEWSSAMPLE_EMPLOYEE
