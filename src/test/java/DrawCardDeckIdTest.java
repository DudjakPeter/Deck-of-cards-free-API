import io.restassured.*;
import org.apache.commons.lang3.*;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import java.util.*;

/**
 * @author Peter Dudjak
 */

@DisplayName("Tests for deck_id parameter verification")
public class DrawCardDeckIdTest extends BeforeAfter {

    @Test
    @Tag("Regression")
    @DisplayName("Send valid request to API with not existing deck id and returns error message")
    public void deck_is_not_existing_and_return404_error() {
        String deck_id = RandomStringUtils.randomAlphanumeric(12);
        RestAssured
                .given().queryParam("count", 2)
                .get("/"+deck_id+"/draw/")
                .then().assertThat().statusCode(404).
                body("success", Matchers.equalTo(false)).
                body("error", Matchers.equalTo("Deck ID does not exist."));
    }

}
