package com.stoom.petz.maps.service;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.stoom.petz.models.Address;

public class MapService {

	final String API_KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";

	private static volatile MapService instance = null;

	private MapService() {
	}

	public static MapService getInstance() {
		if (instance == null) {
			synchronized (MapService.class) {
				if (instance == null) {
					instance = new MapService();
				}
			}
		}

		return instance;
	}

	public Address returnAddressWithGeoPosition(Address address) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		GeocodingResult[] results;
		try {
			results = GeocodingApi.geocode(context, address.getNumber() + " " + address.getStreetName() + " "
					+ address.getCity() + ", " + address.getState() + address.getZipcode()).await();

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			address.setLatitude(results[0].geometry.location.lat);
			address.setLongitude(results[0].geometry.location.lng);

			// Invoke .shutdown() after your application is done making requests
			context.shutdown();
		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
}
