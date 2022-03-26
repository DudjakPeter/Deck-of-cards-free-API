import io.restassured.*;
import org.codehaus.groovy.reflection.*;
import org.junit.jupiter.api.*;

import java.util.*;

/**
 * @author Peter Dudjak
 */

public class BeforeAfter {
    int randomCount = new Random().nextInt((52 - 1 )+1)+1;

    @BeforeAll
    public static void beforeAllSteps() {
        RestAssured.baseURI = "https://deckofcardsapi.com";
        RestAssured.basePath = "/api/deck";
    }

    @BeforeEach
    public void beforeEachSteps (TestInfo testInfo) {
        System.out.println("Running test: " +testInfo.getDisplayName());
    }

    @AfterAll
    public static void afterAllSteps (TestInfo testInfo) {
        System.out.println("Finished testing of suite: "+ testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEachSteps () {
    }
}