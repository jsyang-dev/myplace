package info.myplace.placeapi.place.dto;

import info.myplace.placeapi.place.domain.Location;
import info.myplace.placeapi.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {

    private String name;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String description;

    public Place toPlace() {
        return Place.builder()
                .name(name)
                .imageUrl(imageUrl)
                .location(Location.builder().latitude(latitude).longitude(longitude).build())
                .description(description)
                .build();
    }
}
