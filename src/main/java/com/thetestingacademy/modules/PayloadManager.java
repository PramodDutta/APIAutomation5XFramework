package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.Bookingdates;

public class PayloadManager {

    // JAVA -> JSON

    public String createPayloadGSON(){
        Faker faker = new Faker();
        Booking booking = new Booking();
        String expectFirstName = faker.name().firstName();


        booking.setFirstname(expectFirstName);
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Object -> JSON String (GSON)
        Gson gson = new Gson();
        String jsonStringBooking =  gson.toJson(booking);
        System.out.println(jsonStringBooking);
        return jsonStringBooking;
    }


    public void createPayloadJackSon(){ // Incomplete

    }public void updatePayload(){

    }




}
