package info.myplace.placeapi.place.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagResponse {

    private Long id;
    private String name;
}
