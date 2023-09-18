package baseEntities;

import groovy.cli.internal.CliBuilderInternal;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;
import utils.configuration.ReadProperties;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    @BeforeTest
    public void setUpApi() {
        RestAssured.baseURI = ReadProperties.getUrl();

        String finalToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDkyNzIxNTg1MjU4YWE2MjA5OWJlOTQ0NDlkZTEzMTAxNTVkNGY2NDg4ZTYxNDkwYjg1NDBjMDcwN2M3NzMzZjU2ZjU4ZWY1N2FkMTVlOGUiLCJpYXQiOjE2OTUwNDE0OTMuMDE3NjQxLCJuYmYiOjE2OTUwNDE0OTMuMDE3NjQzLCJleHAiOjE4NTI4OTQyOTMuMDEwMDY4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.FvhB0o2k-Q2MNCySbMUzcRA4a-VkgxvrfSQSVss3eWO9ZAwsLVNqb5FLwzf8Vr5VqK_FwgPSaAJ-MprYgUZO2VF_lxestR8kRRW5yi_o_7UEgp7mvy4_luF0ajQ6xNu-jFSWyNeVLIn9IU-bFjCanvKt_X3jpFWoQdvoSALM4AEwLglkYFxb_qQsoQn6Pa0xfBP9LKIH76EA3qBknOhg4gfSuoJuGbPbldac46-Vil1Y0KCnkk7iqATvptQoTo7D-krTKHNTvY5madCoBpWZuD1vzcIiQi_ieR744kTWKSEC6skZ21J33MUT-cFdhs2gSxKf_HgHuW4Hu6ljBiCoo0cZLr1n5FXpP2OHX8obUtAq0jjWlLg4gnpZgpoks8lVht2xCSAc3FQlYtncR7w68wHZNQnqNLaZ1rS9IKBoa5sOH8qElSu8eCNlflM7WVCVXn0kT8JPe4aeI9C2yfeecS29jdmEJpbnyJlS6-EsZQ65SYXUQqFPOLtRNoCMeDuzk7QqyfGOZ5r7TRdT3-kmZfo9ktp2lKmdRCMRRsNzF165R9wfMozvH8ziRHYk7Pdcv3A2yKUNDjo6TKklt7S4wNVRKnc7Ty5fG7H2avf9CB-CVQrqNvLgDZk4Er25zMgXNnguueJM7MAnNATMUeB7HnzJwcpwo-tvRVFqbzWSou8";
        RestAssured.requestSpecification = given().auth().oauth(ReadProperties.email(),ReadProperties.password(),
                        "Authorization", "Bearer" + finalToken)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);

                //(ReadProperties.email(), ReadProperties.password())
               // .header()
                //.header("Authorization", "Bearer" +finalToken);
    }
}