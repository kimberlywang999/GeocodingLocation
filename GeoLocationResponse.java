package com.example.location;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class GeoLocationResponse {

        public final Set<String> addresses = new ConcurrentSkipListSet<>();

        public double latitude;

        public double longitude;

        public GeoLocationResponse addAddress(final String address) {
            if (address != null) {
                this.addresses.add(address);
            }
//            this.addresses.add(address);
            return this;
        }

        public String build() {
            return String.join(",", this.addresses);
        }

    public Set<String> getAddresses() {
        return addresses;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
