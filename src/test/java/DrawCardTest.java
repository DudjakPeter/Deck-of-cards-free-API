import io.restassured.*;
import io.restassured.response.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import java.util.*;

/**
 * @author Peter Dudjak
 */

@DisplayName("Tests for verification positive behaviour of application")
public class DrawCardTest extends BeforeAfter {

    @Test
    @Tag("Smoke")
    @DisplayName("Send valid request to API and verify status code and json objects")
    public void draw_card_and_return200_and_verify_parameters() {
            RestAssured
                    .given().queryParam("count", randomCount)
                    .get("/new/draw/")
                    .then().assertThat().statusCode(200).
                    body("success", Matchers.equalTo(true)).
                    body("deck_id", Matchers.anything()).
                    body("remaining", Matchers.equalTo(52-randomCount)).
                    body( "cards", Matchers.iterableWithSize(randomCount));
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Send valid request to API and verify cards array contains all objects")
    public void draw_card_and_verify_card_are_present() {
        Response response =
                RestAssured
                        .given().queryParam("count", randomCount)
                        .get("/new/draw/")
                        .then().assertThat().statusCode(200).
                        body( "cards", Matchers.iterableWithSize(randomCount)).
                        body("cards.code", Matchers.anything()).
                        extract().response();
        for (int i = 0; i< randomCount; i++) {
            Assertions.assertNotNull(response.path("cards["+i+"].code"));
            Assertions.assertNotNull(response.path("cards["+i+"].image"));
            Assertions.assertNotNull(response.path("cards["+i+"].images.svg"));
            Assertions.assertNotNull(response.path("cards["+i+"].images.png"));
            Assertions.assertNotNull(response.path("cards["+i+"].value"));
            Assertions.assertNotNull(response.path("cards["+i+"].suit"));
        }
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Send valid request to API and verify that newly created deck can be accessed")
    public void draw_card_and_verify_deck() {
        int randomCount1 = new Random().nextInt((20 - 1 )+1)+1;
        Response response =
                RestAssured
                        .given().queryParam("count", randomCount1)
                        .get("/new/draw/")
                        .then().assertThat().statusCode(200).
                        body("remaining", Matchers.equalTo(52-randomCount1)).
                        extract().response();
        String deck_id = response.path("deck_id");

        RestAssured
                .given().queryParam("count", randomCount1)
                .get("/"+deck_id+"/draw/")
                .then().assertThat().statusCode(200).
                body("remaining", Matchers.equalTo(52-randomCount1-randomCount1)).
                body("deck_id", Matchers.equalTo(deck_id));
    }

}