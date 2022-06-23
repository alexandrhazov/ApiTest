package api.Resources;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetResourceTest {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void getResource(ITestContext context) {
        String project_id = (String) context.getAttribute("resource_id");
        project_id = project_id.substring(1, project_id.length() - 1);

        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        Response response = given()
                .header("Authorization",Credentials.token)
                .when()
                .get(Credentials.URL + "resources/" + project_id)
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.type", notNullValue())
                .body("results.length", notNullValue())
                .body("results.download_url", CoreMatchers.endsWithIgnoringCase("/download"))
                .extract().response();
    }
}
