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

    String wordcount = "100";
    String source_language = "en";
    String target_language = "rus";
    String currency = "USD";

    String quoteURL = "tools/quote?wordcount=" + wordcount + "&source_language=" + source_language +
            "&target_language=" + target_language +"&currency=" + currency;

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void GetQuoteTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());

        Response response = given()
                .header("Authorization", Credentials.token)
                .when()
                .get( Credentials.URL + quoteURL)
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.total.price", greaterThan(0))
                .body("results.total.wordcount", equalTo(wordcount))
                .body("results.currency", equalTo(currency))
                .extract().response();
    }
}

