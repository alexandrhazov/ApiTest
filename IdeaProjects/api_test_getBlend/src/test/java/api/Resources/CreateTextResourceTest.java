package api.Resources;


import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.junit.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class CreateTextResourceTest {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void successCreateTextResourcesTest(ITestContext context) {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());

         Response response = given()
                 .header("Authorization",Credentials.token)
                 .header("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                 .and()
                 .multiPart("text","test text")
                 .when()
                 .post(Credentials.URL + "resources/text")
                 .then().log().all()
                 .extract().response();
         Assertions.assertNotNull(response.jsonPath().getString("results"));
         Assertions.assertTrue(response.jsonPath().getString("results").matches(".*(rsc-).*"));
         Assertions.assertEquals(Integer.parseInt(response.jsonPath().getString("status.code")), 0);
         context.setAttribute("resource_id",response.jsonPath().getString("results"));
    }
}
