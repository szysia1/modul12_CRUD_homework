import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailsEndWithPlTest {
    @Test
    public void EmailsEndWithPlCount() {
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        long numberOfEmailsEndWithPl = emails.stream()
                .filter(email -> email.endsWith(".pl"))
                .count();
        System.out.println(numberOfEmailsEndWithPl);

        assertEquals(0, numberOfEmailsEndWithPl);
    }

    @Test
    public void EmailsEndWithPlMap() {
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");

        boolean anyMatchEndsWithPl = emails.stream()
                .anyMatch(email -> email.endsWith(".pl"));

        assertEquals(false, anyMatchEndsWithPl);
    }

    @Test
    public void readAllUsers() {
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        List<String> emails = json.getList("email");
        List<String> emailsEndWithPl = new ArrayList<>();

        for (String email : emails) {
            if (email.endsWith(".pl")) {
                emailsEndWithPl.add(email);
            }
        }
        Integer numberOfEmails = emailsEndWithPl.size();

        assertEquals(0, numberOfEmails);
    }

}