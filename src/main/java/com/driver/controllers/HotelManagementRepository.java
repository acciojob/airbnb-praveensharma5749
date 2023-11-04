package com.driver.controllers;
import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.HashMap;
import java.util.UUID;
public class HotelManagementRepository {
    private HashMap<String, Hotel> hotelDb = new HashMap<>();
    private HashMap<Integer, User> userDb = new HashMap<>();
    private HashMap<String, Booking> bookingDb = new HashMap<>();
    private HashMap<Integer, Integer> noOfBookingsByUser = new HashMap<>();

    public HashMap<String, Hotel> getHotelDb() {
        return hotelDb;
    }

    public void setHotelDb(HashMap<String, Hotel> hotelDb) {
        this.hotelDb = hotelDb;
    }

    public HashMap<Integer, User> getUserDb() {
        return userDb;
    }

    public void setUserDb(HashMap<Integer, User> userDb) {
        this.userDb = userDb;
    }

    public HashMap<String, Booking> getBookingDb() {
        return bookingDb;
    }

    public void setBookingDb(HashMap<String, Booking> bookingDb) {
        this.bookingDb = bookingDb;
    }

    public HashMap<Integer, Integer> getNoOfBookingsByUser() {
        return noOfBookingsByUser;
    }

    public void setNoOfBookingsByUser(HashMap<Integer, Integer> noOfBookingsByUser) {
        this.noOfBookingsByUser = noOfBookingsByUser;
    }

    public void addHotel(Hotel hotel) {
        hotelDb.put(hotel.getHotelName(), hotel);
    }

    public void addUser(User user) {
        userDb.put(user.getaadharCardNo(), user);
    }

    public void addBooking(Booking booking) {
        String bookingId = generateBookingId();
        booking.setBookingId(bookingId);
        bookingDb.put(bookingId, booking);
    }

    private String generateBookingId(){
        return UUID.randomUUID().toString();
    }

    public void addBookingCountByUser(Integer aadharCardNo) {
        noOfBookingsByUser.put(aadharCardNo, noOfBookingsByUser.getOrDefault(aadharCardNo, 0) + 1);
    }
}
