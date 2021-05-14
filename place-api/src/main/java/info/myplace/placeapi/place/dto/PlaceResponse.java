package info.myplace.placeapi.place.dto;

import info.myplace.placeapi.place.domain.Place;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PlaceResponse {

    private final Long id;
    private final String name;
    private final String imageUrl;
    private final double latitude;
    private final double longitude;
    private final int recommendCount;
    private final int readCount;
    private final String description;
    private List<TagResponse> tags;

    public static PlaceResponse of(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .imageUrl(place.getImageUrl())
                .latitude(place.getLocation().getLatitude())
                .longitude(place.getLocation().getLongitude())
                .recommendCount(place.getRecommendCount())
                .readCount(place.getReadCount())
                .description(place.getDescription())
                .tags(
                        place.getTags()
                                .stream()
                                .map(it -> TagResponse.builder().id(it.getId()).name(it.getName()).build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
