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

    private final PlaceRequest placeRequest = PlaceRequest.builder()
            .name("수리산 산림욕장")
            .point(new Point(37.356683, 126.915901))
            .imageUrl("/image/surisan.jpg")
            .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
            .build();

    @Autowired
    private PlaceService placeService;

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // when
        PlaceResponse placeResponse = placeService.createPlace(placeRequest);

        // then
        assertThat(placeResponse.getId()).isNotNull();
        assertThat(placeResponse.getName()).isEqualTo(placeRequest.getName());
        assertThat(placeResponse.getPoint()).isEqualTo(placeRequest.getPoint());
        assertThat(placeResponse.getImageUrl()).isEqualTo(placeRequest.getImageUrl());
        assertThat(placeResponse.getRecommendCount()).isZero();
        assertThat(placeResponse.getReadCount()).isZero();
        assertThat(placeResponse.getDescription()).isEqualTo(placeRequest.getDescription());
    }

    @Test
    @DisplayName("장소를 생성한다")
    void getPlace() {
        // given
        PlaceResponse createdPlaceResponse = placeService.createPlace(placeRequest);

        // when
        PlaceResponse placeResponse = placeService.getPlace(createdPlaceResponse.getId());

        // then
        assertThat(placeResponse.getId()).isEqualTo(createdPlaceResponse.getId());
        assertThat(placeResponse.getName()).isEqualTo(createdPlaceResponse.getName());
        assertThat(placeResponse.getPoint()).isEqualTo(createdPlaceResponse.getPoint());
        assertThat(placeResponse.getImageUrl()).isEqualTo(createdPlaceResponse.getImageUrl());
        assertThat(placeResponse.getRecommendCount()).isEqualTo(createdPlaceResponse.getRecommendCount());
        assertThat(placeResponse.getReadCount()).isEqualTo(createdPlaceResponse.getReadCount());
        assertThat(placeResponse.getDescription()).isEqualTo(createdPlaceResponse.getDescription());
    }
}
