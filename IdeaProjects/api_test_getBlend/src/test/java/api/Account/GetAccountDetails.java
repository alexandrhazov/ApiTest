package api.Account;


import api.Utils.Specification;
import api.Utils.Credentials;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetAccountDetails {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void getAccountDetailsTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        Response response = given()
                .header("Authorization", Credentials.token)
                .when()
                .get( Credentials.URL + "account")
                .then().log().all()
                .body("status.code", equalTo(0))
                .body("results.account_email", notNullValue())
                .body("results.account_username", notNullValue())
                .body("results.account_id", notNullValue())
                .extract().response();
    }
}
