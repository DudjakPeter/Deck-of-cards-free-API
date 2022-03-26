# Deck-of-cards-free-API
JAVA tests to verify functionality for free (public) API "Deck of cards": https://deckofcardsapi.com/

Covered functionality "Draw a Card" located in endpoint: [Draw a card endpoint](https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2)
   - where <<deck_id>> should be replaced by valid deck_id; 
   - to create a shuffled deck and draw cards from that deck <<deck_id>> should be replaced by "new"
    
## Technical overview
- Technology: **Java**
- For unit testing was used: **JUnit5**
- Tests are developed by using: **RestAssured framework**
- Dependency management tool: **Maven**
- Plugin for run unit tests: **Surefire**
- Matcher library used in tests: **Hamcrest**

Version of plugins used for this project can be found in file pom.xml.

All plugins were added from [mvn repository](https://mvnrepository.com/)

## Test overview
All tests are located in /src/test/java/ folder of the project.
- **BeforeAfter.java**  is simple list of all global variables and @BeforeAll, @BeforeEach, @AfterAll, @AfterEach statements, that are being used in all tests
- **DrawCardTest.java** is set of Smoke tests to verify basic (and positive) functionality of endpoint with reasonable checks/assertions
- **DrawCardCountTest.java** is set of Regression tests to verify extended and negative behaviour of query parameter "count" with reasonable checks/assertions
- **DrawCardDeckIdTest.java** is set of Regression tests to verify extended and negative behaviour of <<deck_id>> with reasonable checks/assertions

All tests are tagged with @Tag "Smoke" or @Tag "Regression"

Note: test file **ReturnCardTest** is just small check of return functionality (endpoint), but is not included in Smoke or Regression suite. Also is excluded from maven execution.

## Running tests
1. Clone this repository
2. Navigate to directory with cloned repository
3. In command line execute command:
```
mvn clean test
```
