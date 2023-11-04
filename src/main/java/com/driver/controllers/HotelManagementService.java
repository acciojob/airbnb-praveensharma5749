package com.driver.controllers;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import java.util.HashMap;
import java.util.List;
public class HotelManagementService {
    HotelManagementRepository hotelManagementRepository = new HotelManagementRepository();

    public String addHotel(Hotel hotel) {
        if(hotel == null || hotel.getHotelName() == null) {
            return "FAILURE";
        }

        if(hotelManagementRepository.getHotelDb().containsKey(hotel.getHotelName())) {
            return "FAILURE";
        }

        hotelManagementRepository.addHotel(hotel);
        return "SUCCESS";
    }

//    public Integer addUser(User user) {
//        hotelManagementRepository.addUser(user);
//        return user.getaadharCardNo();
//    }

    public Integer addUser(User user) {
        hotelManagementRepository.addUser(user);
        return user.getaadharCardNo();

    }

    public String getHotelWithMostFacilities(){
        HashMap<String, Hotel> hotelDb = hotelManagementRepository.getHotelDb();
        String hotelNameWithMaxFacilities = "";
        int maxFacilties = 0;

        for(String hotelName : hotelDb.keySet()) {
            Hotel hotel = hotelDb.get(hotelName);

            int currentFacilities = hotel.getFacilities().size();

            if(currentFacilities > maxFacilties) {
                maxFacilties = currentFacilities;
                hotelNameWithMaxFacilities = hotelName;
            } else if(currentFacilities == maxFacilties) {
                if(hotelName.compareTo(hotelNameWithMaxFacilities) < 0){
                    hotelNameWithMaxFacilities = hotelName;
                }
            }
        }

        return hotelNameWithMaxFacilities;
    }

    public int bookARoom(Booking booking) {
        hotelManagementRepository.addBooking(booking);

        Hotel hotel = hotelManagementRepository.getHotelDb().get(booking.getHotelName());
        if(booking.getNoOfRooms() > hotel.getAvailableRooms()) {
            return -1;
        }

        int totalAmount = booking.getNoOfRooms() * hotel.getPricePerNight();

        booking.setAmountToBePaid(totalAmount);
        hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());

        hotelManagementRepository.addBookingCountByUser(booking.getBookingAadharCard());
        hotelManagementRepository.addHotel(hotel);

        return totalAmount;
    }

    public int getBookings(Integer aadharCard) {
        return hotelManagementRepository.getNoOfBookingsByUser().get(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelManagementRepository.getHotelDb().get(hotelName);

        List<Facility> hotelFacility = hotel.getFacilities();


        for(Facility facility : newFacilities) {
            if(!hotelFacility.contains(facility)) {
                hotelFacility.add(facility);
            }
        }

        hotel.setFacilities(hotelFacility);
        hotelManagementRepository.addHotel(hotel);

        return hotel;
    }
}
