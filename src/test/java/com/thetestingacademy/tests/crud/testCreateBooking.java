package com.thetestingacademy.tests.crud;


import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class testCreateBooking extends BaseTest {


    @Test
    @Owner("Promode")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Booking can be Created")
    public void testCreateBooking() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        Response response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        ValidatableResponse validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        System.out.println("Booking Id :" + jsonPath.getString("bookingid"));
        validatableResponse.statusCode(200);


    }


}
