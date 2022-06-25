package api.MachineTranslation;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class MT_MTPE_ProjectCreation {


    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void mt_mtpe_projectCreationTest(ITestContext context) {

        String source_language = "en-us";
        String target_language = "ru-ru";
        String workflow = "mt-post-edit";
        String notes = "TEST PROJECT DO NOT TAKE";

        //Related to Create Text Resource wordcount
        Integer wordcount = 5;

        //Getting project id of Create Text Resource test
        String sources = (String) context.getAttribute("resource_id");
        sources = sources.substring(1, sources.length() - 1);


        Specification.installSpecification(Specification.requestSpecification(Credentials.URL),
                Specification.responseSpecOk200());

        Response response = given()
                .header("Authorization", Credentials.token)
                .queryParam("source_language", source_language)
                .queryParam("target_language", target_language )
                .queryParam("sources", sources)
                .queryParam("workflow", workflow)
                .queryParam("notes", notes)
                .when()
                .post( Credentials.URL + "projects/translation")
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.wordcount", equalTo(wordcount))
                .extract().response();

    }
}
