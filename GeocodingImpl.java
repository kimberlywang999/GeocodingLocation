package com.example.location;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import java.awt.Desktop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.maps.*;

import com.google.maps.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeocodingImpl {

    //private static final String KEY = "AIzaSyBL8F9ebXIIXBF5IqjkG5Go2aFuJPpc-zQ";
    //private static final String KEY ="AIzaSyAY9JXEt8iscqqalRwCs-KQIwBzb5gAu8A";

    private static final String KEY ="AIzaSyCX2MRvsPy4oWhzETF14lWji_d3zTMe4PU";
    private String response;
    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodingImpl.class);

    public GeocodingImpl() { }


    public GeoLocationResponse reverseGeocode(final double latitude, final double longitude) throws Exception {
        final GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(KEY)
                .build();

        final GeocodingResult[] results;
        final GeoLocationResponse response = new GeoLocationResponse();
        response.setLatitude(latitude);
        response.setLongitude(longitude);
        final LatLng latlng = new LatLng(latitude, longitude);

        try {

              results = GeocodingApi.reverseGeocode(context, latlng).await();
              System.out.println("results::geo.reverseGeocode(40.48, -74.47);" + results + " response\n");


              LatLng latLng1 = new LatLng(latitude, longitude);
              LatLng latLng2 = new LatLng(50.05, -77.05);

//              GeocodingApiRequest req = GeocodingApi.newRequest(context).bounds(latlng,latLng2);
//              final GeocodingResult[] boundsR = req.await();
//              System.out.println("bounds result::" + boundsR);

            DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
            DistanceMatrix rest = req.origins("1 Goodwin drive, North brunswick NJ 08902")
                    .destinations("422 Danbury land, East brunswick NJ")
                    .mode(TravelMode.DRIVING)
                    .avoid(DirectionsApi.RouteRestriction.TOLLS)
                    .language("en-US")
                    .await();

            long distApart = rest.rows[0].elements[0].distance.inMeters;
            
            System.out.println("address result::" + distApart);

//              GeocodingApiRequest addr = GeocodingApi.newRequest(context).address(String.format("%1$s %2$s, %3$s %4$s",
//                      1, "Goodwin drive", "08902", "North Brunswick"));
//              final GeocodingResult[] boundsA = addr.await();
//              System.out.println("address result::" + boundsA[0]);


              if (results != null && results.length > 0) {
                  Arrays.stream(results).map(result -> result.formattedAddress)
                          .forEach(response::addAddress);
                  //response:formattedAddress[
                  // 30 Van Dyke Ave, New Brunswick, NJ 08901, USA,
                  // 32 Van Dyke Ave, New Brunswick, NJ 08901, USA,
                  // 35 Van Dyke Ave, New Brunswick, NJ 08901, USA,
                  // 40 Van Dyke Ave, New Brunswick, NJ 08901, USA,
                  // 505-521 Jersey Ave, New Brunswick, NJ 08901, USA,
                  // Jersey Ave southbound at One-Stop Career Center, New Brunswick, NJ 08901, USA,
                  // Middlesex County, NJ, USA, New Brunswick, NJ 08901, USA,
                  // New Brunswick, NJ, USA, New Jersey, USA, United States] response

                  return response;
              }
              //final Gson gson = new GsonBuilder().setPrettyPrinting().create();
              //this.response = gson.toJson(results[0].addressComponents);
              // System.out.println("results::addressComponents::" +this.response);
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return response;
    }

      private void geocode(final String address) throws Exception {
        final GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(KEY)
                .build();
        final GeocodingResult[] results;
        try {
            results = GeocodingApi.geocode(context, address).await();
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            this.response = gson.toJson(results[0].geometry.location);
        } catch (final Exception e) {
            throw e;
        }

      }

    public static void main (String[] args) throws java.lang.Exception
    {
        GeoLocationResponse resp = new GeoLocationResponse();
        GeocodingImpl geo = new GeocodingImpl();

        //resp = geo.reverseGeocode(40.48, -74.47);
        resp = geo.reverseGeocode(40.48, -74.47);

        //System.out.println("response" + resp.getAddresses() + " response\n");

        //D92%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626%7C40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626&key=AIzaSyAY9JXEt8iscqqalRwCs-KQIwBzb5gAu8A"));
        //URI uri = new URI("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=40.48,-73.47&destinations=45.69%2C-75.94&key=AIzaSyAY9JXEt8iscqqalRwCs-KQIwBzb5gAu8A");

    }
}