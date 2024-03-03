package com.thetestingacademy.tests.integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TCIntegration1 extends BaseTest {

    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking
    //

    // How to pass the data to one testcase to another.

//    @Test
//    public void createToken(){
//
//    }


    @Test(groups = "integration", priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreatingBooking(ITestContext iTestContext) {
        // Post
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        validatableResponse = response.then().log().all();
        BookingRespons bookingRespons = payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingRespons.getBookingid()).isNotNull();
        iTestContext.setAttribute("bookingid", bookingRespons.getBookingid());
        iTestContext.setAttribute("token", getToken());
    }


    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        // GET Req
        System.out.println(iTestContext.getAttribute("bookingid"));
        Assert.assertTrue(true);
    }

    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        // PUT/ PATCH
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        System.out.println("Booking Id Generated -> "+ bookingId);
        System.out.println("Token Generated -> "+token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);
        response = RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatePayload()).put();

        validatableResponse = response.then().log().all();
        Booking booking = payloadManager.bookingResponsePUTReqJava(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Amit");

    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        Integer bookingId = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");
        // DELETE Req.
        System.out.println(iTestContext.getAttribute("bookingid"));
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId).cookie("");
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }


}
