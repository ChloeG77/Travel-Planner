package com.laioffer.travel_planner_backend.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

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
    public Place deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode placeNode = jsonParser.getCodec().readTree(jsonParser);
        Place place = new Place();

        place.setPlaceId(placeNode.get("place_id").textValue());
        place.setAddress(placeNode.get("formatted_address").textValue());
        place.setPhoneNumber(placeNode.get("formatted_phone_number").textValue());
        place.setName(placeNode.get("name").textValue());
        place.setUrl(placeNode.get("url").textValue());
        place.setRating(placeNode.get("rating").doubleValue());

        JsonNode locNode = placeNode.get("geometry").get("location");
        place.setLatitude(locNode.get("lat").doubleValue());
        place.setLatitude(locNode.get("lng").doubleValue());

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

        List<String> openingHours = new ArrayList<>();
        for (final JsonNode hour : placeNode.get("opening_hours")) {
            openingHours.add(hour.textValue());
        }
        place.setOpeningHours(openingHours);

        List<String> reviews = new ArrayList<>();
        for (final JsonNode review : placeNode.get("reviews")) {
            openingHours.add(review.get("text").textValue());
        }
        place.setReview(reviews);

        return place;
    }
}
