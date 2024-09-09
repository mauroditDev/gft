# Price Api

Java Version: 17
Maven Wrapper Version: 3.3.2

## Operations:

1. Build the project so generated sources are created:
`./mvnw clean compile -U`
2. Run tests with Maven using (this includes end-to-end tests): `./mvnw verify`
3. Run the application using: `./mvnw spring-boot:run`

## Useful Information:
* The initialy loaded data is stored in SQL format in the file `./src/main/resources/data.sql`
* All API information is available in `./src/main/resources/prices_api_description.yaml`
* An example of the url for the implemented endpoint to use in Postman (or curl, etc): `http://localhost:8080/price/1?productId=35455&date=2020-06-14T10:00:00`