package api.Resources;

import api.Utils.Credentials;
import api.Utils.Specification;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DownloadResource {

    @Test(invocationCount = Specification.ITERATION_COUNT)
    public void downloadResource(ITestContext context) {
        String project_id = (String) context.getAttribute("resource_id_download");
        project_id = project_id.substring(1, project_id.length() - 1);

        Specification.installSpecification(Specification.requestSpecification(Credentials.URL), Specification.responseSpecOk200());
        Response response = given()
                .header("Authorization",Credentials.token)
                .when()
                .get(Credentials.URL + "resources/" + project_id + "/download")
                .then().assertThat().body(notNullValue())
                .and().log().all()
                .extract().response();

        File sourceFile = new File("/Users/aleksandrhazov/Desktop/FOS.rtf");
        byte[] downloadedFile = response.asByteArray();

        try(FileOutputStream fos = new FileOutputStream(sourceFile)) {
            fos.write(downloadedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
