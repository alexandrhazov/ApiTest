package api.Tools;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class GetQuote {

    String currency = "USD";
    String source_language = "en";
    String target_language = "rus";

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void GetQuoteTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());

        Response response = given()
                .header("Authorization", Credentials.token)
                .queryParam("wordcount", 100)
                .queryParam("source_language", source_language)
                .queryParam("target_language", target_language)
                .queryParam("currency", currency)
                .when()
                .get( Credentials.URL + "tools/quote")
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.total.price", greaterThan(0))
                .body("results.total.credits", greaterThan(0F))
                .body("results.total.wordcount", equalTo("100"))
                .body("results.currency", equalTo(currency))
                .extract().response();
    }
}

