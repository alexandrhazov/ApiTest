package api.Discover.SupportedLanguagesPairs;


import api.Discover.SupportedLanguagesPairs.utils.Result;
import api.Discover.SupportedLanguagesPairs.utils.Source;
import api.Discover.SupportedLanguagesPairs.utils.Target;
import api.Utils.Credentials;
import api.Utils.Specification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SupportedLanguagesPairs {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void supportedLanguagesPairsTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL),
                Specification.responseSpecOk200());

        List<Result> result =
                given()
                        .header("Authorization", Credentials.token)
                        .when()
                        .get( Credentials.URL + "discover/language_pairs")
                        .then().log().all()
                        .body("status.code", equalTo(0))
                        .extract().body().jsonPath().getList("results", Result.class);

        Assertions.assertThat(result.stream().map(x -> x.getSource().getName()).
                collect(Collectors.toList()))
                .containsExactlyInAnyOrder(Credentials.sourceLanguages);

        Assertions.assertThat(result.stream().map(Result::getTargets)
                .flatMap(Collection::stream).map(Target::getName).distinct())
                .containsExactlyInAnyOrder(Credentials.targetLanguages);
    }
}
