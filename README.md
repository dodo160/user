**User Service**

Spring Boot application for user management.

The application provides a simple REST API for creating, retrieving, and deleting users. Application users are stored in the database in the User table.

Anyone can create and retrieve users, but only an authorized user can delete them.

**The project also demonstrates two different authentication approaches using Spring Security:**

dbUserDetails – authentication using users stored in the database

inMemoryUserDetails – authentication using a user defined in application.properties

**Security**

The project supports two different authentication mechanisms.

The mechanism that is used depends on the active Spring profile.

**Supported profiles:**

dbUserDetails

inMemoryUserDetails

**dbUserDetails**

The dbUserDetails profile uses users stored directly in the database.

The user must exist in the User table and must provide valid credentials.

After successful authentication, the user can perform the protected operation:

DELETE /user/{id}

**inMemoryUserDetails**

The inMemoryUserDetails profile does not use users stored in the database for authentication.

The security user is defined directly in:

application.properties

For example:

spring.security.user.name=admin
spring.security.user.password=admin

This user can perform the protected operation:

DELETE /user/{id}

Users stored in the User database table are not used for authentication when this profile is active.
