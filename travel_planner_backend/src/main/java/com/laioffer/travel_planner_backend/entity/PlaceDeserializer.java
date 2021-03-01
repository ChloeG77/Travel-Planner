package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.laioffer.travel_planner_backend.external.GoogleMapException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDeserializer extends StdDeserializer<Place> {
    
    private static final long serialVersionUID = -3901703511881311791L;
    
    public PlaceDeserializer() {
        this(null);
    }
    
    public PlaceDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public Place deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        
        JsonNode placeNode = jsonParser.getCodec().readTree(jsonParser);
        
        Place place = new Place();
        
        try {
            
            place.setName(placeNode.get("name").textValue());
            place.setPlaceId(placeNode.get("place_id").textValue());
            place.setAddress(placeNode.get("formatted_address").textValue());
            
            JsonNode locNode = placeNode.get("geometry").get("location");
            place.setLatitude(locNode.get("lat").doubleValue());
            place.setLongitude(locNode.get("lng").doubleValue());
            
            
        } catch (NullPointerException e) {
            throw new GoogleMapException("Crucial place information missing from Google Map API");
        }
        
        try {
            // Address Components
            JsonNode addressComponents = placeNode.get("address_components");
            
            for (final JsonNode component : addressComponents) {
                String type = component.get("types").get(0).textValue();
                switch (type) {
                    case "locality":
                        place.setCityName(component.get("short_name").textValue());
                    case "administrative_area_level_1":
                        place.setState(component.get("short_name").textValue());
                    case "country":
                        place.setCountry(component.get("short_name").textValue());
                    case "postal_code":
                        place.setPostcode(component.get("short_name").textValue());
                }
            }
        } catch (NullPointerException ignored) {
        }
        
        try {
            place.setPhoneNumber(placeNode.get("formatted_phone_number").textValue());
        } catch (NullPointerException ignored) {
        }
        
        try {
            place.setUrl(placeNode.get("url").textValue());
        } catch (NullPointerException ignored) {
        }
        
        try {
            place.setPhotoRef(placeNode.get("photos").get(0).get("photo_reference").textValue());
        } catch (NullPointerException ignored) {
        }
        
        try {
            List<String> openingHours = new ArrayList<>();
            for (final JsonNode hour : placeNode.get("opening_hours").get("weekday_text")) {
                openingHours.add(hour.textValue());
            }
            place.setOpeningHours(openingHours);
        } catch (NullPointerException ignored) {
        }
        
        try {
            List<String> category = new ArrayList<>();
            for (final JsonNode type : placeNode.get("types")) {
                category.add(type.textValue());
            }
            place.setCategory(category);
        } catch (NullPointerException ignored) {
        }
        
        try {
            place.setRating(placeNode.get("rating").doubleValue());
        } catch (NullPointerException ignored) {
        }
        
        try {
            List<String> reviews = new ArrayList<>();
            for (final JsonNode review : placeNode.get("reviews")) {
                reviews.add(review.get("text").textValue());
            }
            place.setReview(reviews);
        } catch (NullPointerException ignored) {
        }
        
        return place;
    }
}
