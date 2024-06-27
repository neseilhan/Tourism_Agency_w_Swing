package entity;

import core.ComboItem;

import java.time.LocalDate;

public class Season {
    private int seasonId, seasonHotelId;
    private LocalDate seasonStartDate, seasonEndDate;
    private String seasonName;

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getHotelId() {
        return seasonHotelId;
    }

    public void setHotelId(int hotelId) {
        this.seasonHotelId = hotelId;
    }

    public LocalDate getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(LocalDate seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public LocalDate getSeasonEndDate() {
        return seasonEndDate;
    }

    public void setSeasonEndDate(LocalDate seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public ComboItem getComboItem() { return new ComboItem(this.getSeasonId(), this.getSeasonName()); }

    @Override
    public String toString() {
        return "Season{" +
                "seasonId=" + seasonId +
                ", hotelId=" + seasonHotelId +
                ", seasonName='" + seasonName + '\'' +
                ", seasonStartDate=" + seasonStartDate +
                ", seasonEndDate=" + seasonEndDate +
                '}';
    }
}