package info.myplace.placeapi.place.dto;

import info.myplace.placeapi.place.domain.Place;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.geo.Point;

import java.util.List;

@Getter
@Builder
public class PlaceResponse {

    private final Long id;
    private final String name;
    private final Point point;
    private final String imageUrl;
    private final int recommendCount;
    private final int readCount;
    private final String description;
    private List<TagResponse> tags;

    public static PlaceResponse of(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .point(place.getPoint())
                .imageUrl(place.getImageUrl())
                .recommendCount(place.getRecommendCount())
                .readCount(place.getReadCount())
                .description(place.getDescription())
                .build();
    }
}
