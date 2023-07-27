# education-site-microservices
Example RESTful services for an education site

The two services that make up the education site are student-service and course-service which were using a microservice architectural pattern, and they interact with each another to carry out student-course enrollment procedures.


# default port 
The default port for running student-service is configured as port 8084, and the default port for course-service is port 8085 for the dev profile.

# Init data loading and admin user creation
Need to set app.data.loading.enabled=true in application.properties for both services for initial starting to create admin user which
should be later used to invoke jwt token generation and api user creation

# Admin user credentials
(username=admin, password=admin)

# note
Both services must be up and running to consume enrollment, course deletion apis as those operations involves inter service communication



