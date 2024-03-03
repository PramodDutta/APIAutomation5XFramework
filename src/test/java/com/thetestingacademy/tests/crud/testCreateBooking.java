package com.thetestingacademy.tests.crud;


import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static org.assertj.core.api.Assertions.*;

public class testCreateBooking extends BaseTest {


    @Test
    @Owner("Promode")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testCreateBooking() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();


        validatableResponse = response.then().log().all();
        BookingRespons bookingRespons = payloadManager.bookingResponseJava(response.asString());

        // Validatable Default
        validatableResponse.statusCode(200);

        // Assert J
        assertThat(bookingRespons.getBookingid()).isNotNull();
        assertThat(bookingRespons.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingRespons.getBooking().getFirstname()).isEqualTo("Pramod");

        // TestNG Assertions
        assertActions.verifyStatusCode(response);

    }

    @Test
    @Owner("Promode")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#2 - Verify that the Booking not created when Payload is missing")
    public void testCreateBookingNegative() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body("{}").post();
        validatableResponse = response.then().log().all();
        // TestNG Assertions
        assertActions.verifyStatusCodeInvalidReq(response);
    }


}
