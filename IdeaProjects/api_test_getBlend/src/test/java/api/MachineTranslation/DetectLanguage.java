package api.MachineTranslation;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DetectLanguage {

    String text = "Guess the language";
    String languageGuess = "English";

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void detectLanguageTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        Response response = given()
                .header("Authorization", Credentials.token)
                .queryParam("source_content", text)
                .when()
                .get( Credentials.URL + "mt/detect/text")
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.language", equalTo(languageGuess))
                .extract().response();
    }
}
