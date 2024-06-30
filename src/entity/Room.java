package entity;

public class Room {
    private int room_id;
    private int room_hotelId;
    private int room_pensionId;
    private int room_seasonId;
    private int room_stock;
    private String adultPrice;
    private String childPrice;
    private int room_bed;
    private int room_size;
    private boolean room_tv;
    private boolean room_minibar;
    private boolean room_game_console;
    private boolean room_safebox;
    private boolean room_projection;
    private RoomType room_type;
    private Hotel hotel;
    private Pension pension;
    private Season season;

    public Room() {
    }
    public enum RoomType {
        Single,
        Double,
        JuniorSuite,
        Suite
    }

    public Room(int room_id, int room_hotelId, int room_pensionId, int room_seasonId, int room_stock, String adultPrice, String childPrice, int room_bed, int room_size, boolean room_tv, boolean room_minibar, boolean room_game_console, boolean room_safebox, boolean room_projection) {
        this.room_id = room_id;
        this.room_hotelId = room_hotelId;
        this.room_pensionId = room_pensionId;
        this.room_seasonId = room_seasonId;
        this.room_stock = room_stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.room_bed = room_bed;
        this.room_size = room_size;
        this.room_tv = room_tv;
        this.room_minibar = room_minibar;
        this.room_game_console = room_game_console;
        this.room_safebox = room_safebox;
        this.room_projection = room_projection;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_hotelId() {
        return room_hotelId;
    }

    public void setRoom_hotelId(int room_hotelId) {
        this.room_hotelId = room_hotelId;
    }

    public int getRoom_pensionId() {
        return room_pensionId;
    }

    public void setRoom_pensionId(int room_pensionId) {
        this.room_pensionId = room_pensionId;
    }

    public int getRoom_seasonId() {
        return room_seasonId;
    }

    public void setRoom_seasonId(int room_seasonId) {
        this.room_seasonId = room_seasonId;
    }

    public int getRoom_stock() {
        return room_stock;
    }

    public void setRoom_stock(int room_stock) {
        this.room_stock = room_stock;
    }

    public String getRoomPriceAdult() {
        return adultPrice;
    }

    public void setRoomPriceAdult(String roomPriceAdult) {
        this.adultPrice = roomPriceAdult;
    }

    public String getRoomPriceChild() {
        return childPrice;
    }

    public void setRoomPriceChild(String roomPriceChild) {
        this.childPrice = roomPriceChild;
    }

    public int getRoom_bed() {
        return room_bed;
    }

    public void setRoom_bed(int room_bed) {
        this.room_bed = room_bed;
    }

    public int getRoom_size() {
        return room_size;
    }

    public void setRoom_size(int room_size) {
        this.room_size = room_size;
    }

    public boolean isRoom_tv() {
        return room_tv;
    }

    public void setRoom_tv(boolean room_tv) {
        this.room_tv = room_tv;
    }

    public boolean isRoom_minibar() {
        return room_minibar;
    }

    public void setRoom_minibar(boolean room_minibar) {
        this.room_minibar = room_minibar;
    }

    public boolean isRoom_game_console() {
        return room_game_console;
    }

    public void setRoom_game_console(boolean room_game_console) {
        this.room_game_console = room_game_console;
    }

    public boolean isRoom_safebox() {
        return room_safebox;
    }

    public void setRoom_safebox(boolean room_safebox) {
        this.room_safebox = room_safebox;
    }

    public boolean isRoom_projection() {
        return room_projection;
    }

    public void setRoom_projection(boolean room_projection) {
        this.room_projection = room_projection;
    }
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public int getHotelId() {
        return room_hotelId;
    }

    public void setHotelId(int hotelId) {
        this.room_hotelId = hotelId;
    }

    public int getPensionId() {
        return room_pensionId;
    }

    public void setPensionId(int pensionId) {
        this.room_pensionId = pensionId;
    }

    public int getSeasonId() {
        return room_seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.room_seasonId = seasonId;
    }
    public RoomType getRoomType() {
        return room_type;
    }

    public void setRoomType(RoomType roomType) {
        this.room_type = roomType;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_hotelId=" + room_hotelId +
                ", room_pensionId=" + room_pensionId +
                ", room_seasonId=" + room_seasonId +
                ", room_stock=" + room_stock +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                ", room_bed=" + room_bed +
                ", room_size=" + room_size +
                ", room_tv=" + room_tv +
                ", room_minibar=" + room_minibar +
                ", room_game_console=" + room_game_console +
                ", room_safebox=" + room_safebox +
                ", room_projection=" + room_projection +
                '}';
    }
}