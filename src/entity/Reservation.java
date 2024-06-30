package entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String totalPrice;
    private String guestName;
    private String guestCitizenId;
    private String guestMail;
    private String guestPhone;
    private String reservationNote;

//    public Reservation(){
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestCitizenId() {
        return guestCitizenId;
    }

    public void setGuestCitizenId(String guestCitizenId) {
        this.guestCitizenId = guestCitizenId;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getReservationNote() {
        return reservationNote;
    }

    public void setReservationNote(String reservationNote) {
        this.reservationNote = reservationNote;
    }
}
