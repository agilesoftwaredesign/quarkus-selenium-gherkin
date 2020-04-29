package de.agilesoftwaredesign;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FormResourceTest {
    @Test
    public void loadFormWithNoArgument() {
        given().log().all().when().get("/forms").then().log().all().statusCode(200).body("data.items.size()",
                is(not(empty())));
    }
}
