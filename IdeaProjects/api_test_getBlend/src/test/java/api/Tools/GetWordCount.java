package api.Tools;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class GetWordCount {

    //Related to Create Text Resource wordcount
    Integer wordcount = 5;

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void GetWordCountTest(ITestContext context) {
        String project_id = (String) context.getAttribute("resource_id");
        project_id = project_id.substring(1, project_id.length() - 1);

        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        Response response = given()
                .header("Authorization", Credentials.token)
                .when()
                .get(Credentials.URL + "tools/wordcount?resources=" + project_id)
                .then().log().all()
                .body("results.resources", notNullValue())
                .body("results.resources[0].wordcount", equalTo(wordcount))
                .body("status.code", equalTo(0))
                .extract().response();
    }
}
