import io.restassured.*;
import io.restassured.response.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;

import java.util.*;

/**
 * @author Peter Dudjak
 */

public class ReturnCardTest extends BeforeAfter {

    @Test
    @Tag("ReturnTest")
    @DisplayName("Test for verification that cards are returned to deck")
    public void return_card_and_return200() {
        Response response =
                RestAssured
                        .given().queryParam("count", randomCount)
                        .get("/new/draw/")
                        .then().assertThat().statusCode(200).
                        body("remaining", Matchers.equalTo(52-randomCount)).
                        extract().response();
        String deck_id = response.path("deck_id");

        RestAssured
                .get("/"+deck_id+"/return/")
                .then().assertThat().statusCode(200).
                body("deck_id", Matchers.equalTo(deck_id)).
                body("remaining", Matchers.not(52-randomCount)).
                body("remaining", Matchers.equalTo(52));
    }

}
