package api.Discover.SupportedLanguages;

import api.Discover.SupportedLanguages.utils.Result;
import api.Utils.Credentials;
import api.Utils.Specification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SupportedLanguages {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void supportedLanguagesTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());

        List<Result> result =
                given()
                .header("Authorization", Credentials.token)
                .when()
                .get( Credentials.URL + "discover/languages")
                .then().log().all()
                        .body("status.code", equalTo(0))
                        .extract().body().jsonPath().getList("results", Result.class);

        Assertions.assertThat(result.stream().map(Result::getName).collect(Collectors.toList())).containsExactlyInAnyOrder
                        (Credentials.supportedLanguages);
    }
}
