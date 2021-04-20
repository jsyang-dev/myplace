package info.myplace.placeapi.place.application;

import info.myplace.placeapi.ServiceTest;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관리 테스트")
class PlaceServiceTest extends ServiceTest {

    @Autowired
    private PlaceService placeService;

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // given
        PlaceRequest placeRequest = PlaceRequest.builder()
                .name("수리산 산림욕장")
                .point(new Point(37.356683, 126.915901))
                .imageUrl("/image/surisan.jpg")
                .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
                .build();

        // when
        PlaceResponse placeResponse = placeService.createPlace(placeRequest);

        // then
        assertThat(placeResponse.getId()).isNotNull();
        assertThat(placeResponse.getName()).isEqualTo(placeResponse.getName());
        assertThat(placeResponse.getPoint()).isEqualTo(placeResponse.getPoint());
        assertThat(placeResponse.getImageUrl()).isEqualTo(placeResponse.getImageUrl());
        assertThat(placeResponse.getRecommendCount()).isZero();
        assertThat(placeResponse.getReadCount()).isZero();
        assertThat(placeResponse.getDescription()).isEqualTo(placeResponse.getDescription());
    }
}
