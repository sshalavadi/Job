package edu.gatech.seclass.jobcompare6300.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.security.InvalidParameterException;

@Entity(tableName = "locations", indices = {
        @Index(value = {"city", "state"}, unique = true)
})
public class Location {

    @PrimaryKey(autoGenerate = true)
    public long id;
    private String state;
    private String city;

    @ColumnInfo(name = "living_cost_index")
    private int livingCostIndex;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLivingCostIndex() {
        return livingCostIndex;
    }

    public void setLivingCostIndex(int livingCostIndex) {
        if( livingCostIndex < 0 ) {
            throw new InvalidParameterException("livingCostIndex need to be >= 0");
        }
        this.livingCostIndex = livingCostIndex;
    }

}
