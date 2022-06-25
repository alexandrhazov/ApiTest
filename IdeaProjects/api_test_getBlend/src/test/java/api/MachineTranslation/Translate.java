package api.MachineTranslation;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class Translate {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void translateTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        Response response = given()
                .header("Authorization", Credentials.token)
                .queryParam("source_language", "en-us")
                .queryParam("target_language", "ru-ru")
                .queryParam("source_content", "Hello, this is GetBlendTest")
                .when()
                .get( Credentials.URL + "mt/translate/text")
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.TranslatedText", equalTo("Привет, это GetBlendTest"))
                .extract().response();
    }
}
