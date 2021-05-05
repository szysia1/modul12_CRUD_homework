import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PostsDELETETest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @Test
    public void deletePostTest() {
        given()
                .when()
                .delete(BASE_URL+"/"+POSTS+"/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
