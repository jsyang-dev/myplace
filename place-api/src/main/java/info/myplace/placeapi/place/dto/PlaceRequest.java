package info.myplace.placeapi.place.dto;

import info.myplace.placeapi.place.domain.Place;
import info.myplace.placeapi.place.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<TagRequest> tags;

    public Place toPlace() {
        return Place.builder()
                .name(name)
                .imageUrl(imageUrl)
                .latitude(latitude)
                .longitude(longitude)
                .description(description)
                .tags(
                        tags.stream()
                                .map(it -> Tag.builder().name(it.getName()).build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
