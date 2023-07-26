# education-site-microservices
Example RESTful services for an education site

The two services that make up the education site are student-service and course-service which were using a microservice architectural pattern, and they interact with one another to carry out student-course enrollment procedures.


#default port for running student-service defined as port=8084 and default port for course-service is port=8085 for spring dev profile

#init data loading and admin user creation
Need to set app.data.loading.enabled=true in application.properties for both services for initial starting to create admin user which
should be later used to invoke jwt token generation and api user creation

Admin user credentials(username=admin, password=admin)



