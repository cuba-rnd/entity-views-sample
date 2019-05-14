# entity-views-sample
This is a sample project that uses strongly typed entity views instead of XML descriptions. 

Entity views concept is at its early development stage, so the code is not production ready, we need to implement a lot of things to align entity views internals (e.g. metadata) and CUBA code, therefore we use custom service instead of CUBA's DataManager to get entities.

To run the sample application, just clone it and run ```gradlew startDb createDb setupTomcat deploy start``` in the application folder or simply import it to CUBA Studio. We use simple CRUD form for Departments management to demonstrate how entity views concept works.  

Classes to pay attention to:  
```com.company.entityviewssample.service.DepartmentsServiceBean``` - shows how DataManenger works with entity views
```com.company.entityviewssample.web.department.DepartmentBrowse``` - shows how to work with custom service for entities manipulation
```com.company.entityviewssample.web.department.DepartmentEdit``` - replaces standard data context with the class that supports entity views. 

Entity views support component source code is [here](https://github.com/cuba-rnd/entity-views).
