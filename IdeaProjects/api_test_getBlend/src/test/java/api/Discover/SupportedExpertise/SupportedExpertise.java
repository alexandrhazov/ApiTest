package api.Discover.SupportedExpertise;

import api.Discover.SupportedExpertise.utils.Result;
import api.Utils.Credentials;
import api.Utils.Specification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SupportedExpertise {


    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void supportedExpertiseTest() {
        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());

        List<Result> result =
                given()
                        .header("Authorization", Credentials.token)
                        .when()
                        .get(Credentials.URL + "discover/expertise")
                        .then().log().all()
                        .body("status.code", equalTo(0))
                        .extract().body().jsonPath().getList("results", Result.class);

        Assertions.assertThat(result.stream().map(Result::getName).collect(Collectors.toList())).containsExactlyInAnyOrder
                ("Automotive / Aerospace",
                        "Finance",
                        "Software / IT",
                        "Legal",
                        "Marketing / Consumer/ Media",
                        "Medical",
                        "Patents",
                        "Scientific / Academic",
                        "Technical / Engineering",
                        "Gaming / Video Games",
                        "Ad-Words / Banners",
                        "Mobile Applications",
                        "Tourism",
                        "Certificates Translation",
                        "Training / Employee Handbooks",
                        "Forex / Crypto");
    }
}
