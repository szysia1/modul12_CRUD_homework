import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostsGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @Test
    public void readAllPosts() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        System.out.println(response.asString());

    }

    @Test
    public void readOnePost() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(1, (Integer) json.get("userId"));
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", json.get("title"));
        assertEquals(, json.get("body"));
    }
}


{   "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        }