package entity;

public class Hotel {
    private int id;
    private String hotel_name;
    private String hotel_city;
    private String hotel_region;
    private String hotel_address;
    private String hotel_phone;
    private String hotel_star;
    private boolean hotel_carpark;
    private boolean hotel_spa;
    private boolean hotel_room_service;
    private boolean hotel_pool;
    private boolean hotel_wifi;
    private boolean hotel_fitness;
    private boolean hotel_concierge;



    public Hotel(int id, String hotel_name, String hotel_city, String hotel_region, String hotel_address, String hotel_phone, String hotel_star, boolean hotel_carpark, boolean hotel_spa, boolean hotel_room_service, boolean hotel_pool, boolean hotel_wifi, boolean hotel_fitness, boolean hotel_concierge) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.hotel_city = hotel_city;
        this.hotel_region = hotel_region;
        this.hotel_address = hotel_address;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.hotel_carpark = hotel_carpark;
        this.hotel_spa = hotel_spa;
        this.hotel_room_service = hotel_room_service;
        this.hotel_pool = hotel_pool;
        this.hotel_wifi = hotel_wifi;
        this.hotel_fitness = hotel_fitness;
        this.hotel_concierge = hotel_concierge;
    }

    public Hotel(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_region() {
        return hotel_region;
    }

    public void setHotel_region(String hotel_region) {
        this.hotel_region = hotel_region;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isHotel_carpark() {
        return hotel_carpark;
    }

    public void setHotel_carpark(boolean hotel_carpark) {
        this.hotel_carpark = hotel_carpark;
    }

    public boolean isHotel_spa() {
        return hotel_spa;
    }

    public void setHotel_spa(boolean hotel_spa) {
        this.hotel_spa = hotel_spa;
    }

    public boolean isHotel_room_service() {
        return hotel_room_service;
    }

    public void setHotel_room_service(boolean hotel_room_service) {
        this.hotel_room_service = hotel_room_service;
    }

    public boolean isHotel_pool() {
        return hotel_pool;
    }

    public void setHotel_pool(boolean hotel_pool) {
        this.hotel_pool = hotel_pool;
    }

    public boolean isHotel_wifi() {
        return hotel_wifi;
    }

    public void setHotel_wifi(boolean hotel_wifi) {
        this.hotel_wifi = hotel_wifi;
    }

    public boolean isHotel_fitness() {
        return hotel_fitness;
    }

    public void setHotel_fitness(boolean hotel_fitness) {
        this.hotel_fitness = hotel_fitness;
    }

    public boolean isHotel_concierge() {
        return hotel_concierge;
    }

    public void setHotel_concierge(boolean hotel_concierge) {
        this.hotel_concierge = hotel_concierge;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_city='" + hotel_city + '\'' +
                ", hotel_region='" + hotel_region + '\'' +
                ", hotel_address='" + hotel_address + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                ", hotel_carpark=" + hotel_carpark +
                ", hotel_spa=" + hotel_spa +
                ", hotel_room_service=" + hotel_room_service +
                ", hotel_pool=" + hotel_pool +
                ", hotel_wifi=" + hotel_wifi +
                ", hotel_fitness=" + hotel_fitness +
                ", hotel_concierge=" + hotel_concierge +
                '}';
    }
}
