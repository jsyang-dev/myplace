package info.myplace.placeapi.place.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관리 도메인 테스트")
class PlaceTest {

    private final Place place = Place.builder()
            .name("수리산 산림욕장")
            .imageUrl("/image/surisan.jpg")
            .location(Location.builder().latitude(37.356683).longitude(126.915901).build())
            .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
            .build();

    @Test
    @DisplayName("태그를 추가한다")
    void addTag() {
        // given
        Tag tag = Tag.builder()
                .name("산림욕장")
                .build();

        // when
        place.addTag(tag);

        //then
        assertThat(place.getTags().size()).isEqualTo(1);
        assertThat(tag.getPlace()).isEqualTo(place);
    }
}