import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FifeTask {

    @Test
    public void testID() {
        String s = "{\"id\":11, \"name\": \"PetName\" , \"status\" : \"available\"}";

        given()
                .contentType("application/json")
                .body(s)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(11))
                .body("name", equalTo("PetName")) // Проверяем, что поле "name" равно "PetName"
                .body("status", equalTo("available"));

    }
    @Test
    public void testGet() {
        RestAssured
                .get("https://petstore.swagger.io/v2/pet/11")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", equalTo(11))
                .body("name", equalTo("PetName"))
                .body("status", equalTo("available"));
    }
    @Test
    public void testInvalidID() {
        RestAssured
                .get("https://petstore.swagger.io/v2/pet/11")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testPut() {
        String s = "{\"id\":11}";
        given().
                contentType("application/json").
                body(s).
                when().
                put("https://petstore.swagger.io/v2/pet").
                then().
                statusCode(200).
                body("id", equalTo(11));
    }

    @Test
    public void testPutRequest() {

        String requestBody = "{\"id\": 11, \"name\": \"PetNameAnother\" , \"status\" : \"sold\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("PetNameAnother"))
                .body("status", equalTo("sold"))
                .extract()
                .response();

    }

    @Test
    public void testDeleteRequestError() {

        Response response;
        response = given()
                .when()
                .delete("https://petstore.swagger.io/v2/pet/1")
                .then()
                .statusCode(404)
                .extract()
                .response();
    }
}
