import io.restassured.*;
import org.junit.jupiter.api.*;

import java.util.*;

/**
 * @author Peter Dudjak
 */

public class BeforeAfter {
    int randomCount = new Random().nextInt((52 - 1 )+1)+1;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://deckofcardsapi.com";
        RestAssured.basePath = "/api/deck";
    }

    @BeforeEach
    public void init (TestInfo testInfo) {
        System.out.println("Running test: " +testInfo.getDisplayName());
    }

    @AfterAll
    public static void cleanup () {

    }

    @AfterEach
    public void teardown () {
        System.out.println("Finished testing of suite: "+ this.getClass().getSimpleName());
    }
}