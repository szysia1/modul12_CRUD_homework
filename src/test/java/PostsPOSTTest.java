import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostsPOSTTest {
    private static Faker faker;
    private Integer fakeUserId;
    private String fakeTitle;
    private String fakeBody;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        fakeUserId = faker.number().numberBetween(1, 100);
        fakeBody = faker.shakespeare().romeoAndJulietQuote();
        fakeTitle = faker.rickAndMorty().quote();
    }

    @Test
    public void CreateNewPostTest() {


        JSONObject post = new JSONObject();
        post.put("userId", fakeUserId);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .post(BASE_URL+"/"+POSTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeUserId, json.get("userId"));
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));

        System.out.println(response.asString());
    }

}
