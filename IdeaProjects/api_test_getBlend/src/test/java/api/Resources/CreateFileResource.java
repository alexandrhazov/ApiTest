package api.Resources;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateFileResource {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void successCreateTextResourcesTest(ITestContext context) {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        File myObj = new File("/Users/aleksandrhazov/Desktop/test.rtf");

        Response response = given()
                .header("Authorization",Credentials.token)
                .header("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .and()
                .multiPart(myObj)
                .when()
                .post(Credentials.URL + "resources/file")
                .then().log().all()
                .extract().response();
        Assertions.assertNotNull(response.jsonPath().getString("results"));
        Assertions.assertTrue(response.jsonPath().getString("results").matches(".*(rsc-).*"));
        Assertions.assertEquals(Integer.parseInt(response.jsonPath().getString("status.code")), 0);
        context.setAttribute("resource_id_download",response.jsonPath().getString("results"));

    }
}
