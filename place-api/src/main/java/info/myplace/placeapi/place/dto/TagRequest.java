package info.myplace.placeapi.place.dto;

import info.myplace.placeapi.place.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    private String name;

    public Tag toTag() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
