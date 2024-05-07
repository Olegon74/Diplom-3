package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    private final static String REGISTER_USER_PATH = "api/auth/register";
    private final static String LOGIN_USER_PATH = "api/auth/login";
    private final static String DELETE_USER_PATH = "api/auth/user";


    @Step("User creation")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER_USER_PATH)
                .then();

    }

    @Step("User deletion")
    public void deleteUser(String accessToken) {
        given()
                .header("authorization", accessToken)
                .spec(getSpec())
                .when()
                .delete(DELETE_USER_PATH);
    }

    public ValidatableResponse login(Credentials credentials) {
        return
                given()
                        .spec(getSpec())
                        .body(credentials)
                        .when()
                        .post(LOGIN_USER_PATH)
                        .then();

    }

    @Step("Getting access token")
    public String getAccessToken(ValidatableResponse validatableResponse) {
        return validatableResponse.extract().path("accessToken");
    }

    @Step("Removing possible users after tests")
    public void deletingUsersAfterTests(String accessToken) {
        if (accessToken != null) {
            deleteUser(accessToken);
        } else {
            given().spec(getSpec())
                    .when()
                    .delete(DELETE_USER_PATH);
        }
    }
}






