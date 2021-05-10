package info.myplace.placeapi.place.dto;

import info.myplace.placeapi.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {

    private String name;
    private Point point;
    private String imageUrl;
    private String description;
    private List<TagRequest> tags;

    public Place toPlace() {
        return Place.builder()
                .name(name)
                .point(point)
                .imageUrl(imageUrl)
                .description(description)
                .build();
    }
}
