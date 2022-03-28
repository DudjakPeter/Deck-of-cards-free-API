import io.restassured.*;
import org.apache.commons.lang3.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import java.util.*;

/**
 * @author Peter Dudjak
 */

@DisplayName("Tests for count parameter verification")
public class DrawCardCountTest extends BeforeAfter {

    int randomCount = new Random().nextInt((60 - 53 )+1)+53;

    @Test
    @Tag("Regression")
    @DisplayName("Send valid request to API with card count more than 52")
    public void count_more_than_52cards_and_verify_error_message() {
        RestAssured
                .given().queryParam("count", randomCount)
                .get("/new/draw/")
                .then().assertThat().statusCode(200).
                body("success", Matchers.equalTo(false)).
                body("remaining", Matchers.equalTo(0)).
                body("error", Matchers.equalTo("Not enough cards remaining to draw "+randomCount+" additional"));
    }

    @Test
    @Tag("Regression")
    @DisplayName("Send valid request to API with count equal to 0 and verify cards array is empty")
    public void count_equal_0_and_verify_empty_card_array() {
        RestAssured
                .given().queryParam("count", 0)
                .get("/new/draw/")
                .then().assertThat().statusCode(200).
                body("success", Matchers.equalTo(true)).
                body("deck_id", Matchers.anything()).
                body("remaining", Matchers.equalTo(52)).
                assertThat().body("cards", Matchers.notNullValue()).
                body("cards", Matchers.hasSize(0));
    }

    @Test
    @Tag("Regression")
    @DisplayName("Send valid request to API with count random string and verify 500 error code")
    public void count_invalid_value_and_return500_error() {
        String count = RandomStringUtils.randomAlphanumeric(8);
        RestAssured
                .given().queryParam("count", count)
                .get("/new/draw/")
                .then().assertThat().statusCode(500);
    }

    @Test
    @Tag("Regression")
    @DisplayName("Send valid request to API with missing parameter count")
    public void count_missing_value_and_return200_and_verify_parameters() {
        RestAssured
                .get("/new/draw/")
                .then().assertThat().statusCode(200).
                body("success", Matchers.equalTo(true)).
                body("deck_id", Matchers.anything()).
                body("cards.code", Matchers.anything()).
                body("remaining", Matchers.equalTo(51));
    }

    @Test
    @Tag("Regression")
    @DisplayName("Send valid request to API with empty value for parameter count and verify 500 error code")
    public void count_empty_value_and_return500_error() {
        RestAssured
                .given().queryParam("count")
                .get("/new/draw/")
                .then().assertThat().statusCode(500);
    }
}
