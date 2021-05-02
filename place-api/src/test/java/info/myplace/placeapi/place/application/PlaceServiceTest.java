package info.myplace.placeapi.place.application;

import info.myplace.placeapi.ServiceTest;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관리 테스트")
class PlaceServiceTest extends ServiceTest {

    private final PlaceRequest placeRequest1 = PlaceRequest.builder()
            .name("수리산 산림욕장")
            .point(new Point(37.356683, 126.915901))
            .imageUrl("/image/surisan.jpg")
            .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
            .build();

    public static final PlaceRequest placeRequest2 = PlaceRequest.builder()
            .name("초막골 생태공원")
            .point(new Point(37.353632, 126.918564))
            .imageUrl("/image/chomakgol.jpg")
            .description("경기도 군포시에 있는 생태를 테마로 한 도시공원")
            .build();

    @Autowired
    private PlaceService placeService;

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // when
        PlaceResponse placeResponse = placeService.createPlace(placeRequest1);

        // then
        assertThat(placeResponse.getId()).isNotNull();
        assertThat(placeResponse.getName()).isEqualTo(placeRequest1.getName());
        assertThat(placeResponse.getPoint()).isEqualTo(placeRequest1.getPoint());
        assertThat(placeResponse.getImageUrl()).isEqualTo(placeRequest1.getImageUrl());
        assertThat(placeResponse.getRecommendCount()).isZero();
        assertThat(placeResponse.getReadCount()).isZero();
        assertThat(placeResponse.getDescription()).isEqualTo(placeRequest1.getDescription());
    }

    @Test
    @DisplayName("장소를 조회한다")
    void getPlace() {
        // given
        PlaceResponse createdPlaceResponse = placeService.createPlace(placeRequest1);

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

    @Test
    @DisplayName("장소 리스트를 조회한다")
    void getPlaces() {
        // given
        PlaceResponse createdPlaceResponse1 = placeService.createPlace(placeRequest1);
        PlaceResponse createdPlaceResponse2 = placeService.createPlace(placeRequest2);

        // when
        List<PlaceResponse> placeResponses = placeService.getPlaces();

        // then
        List<Long> placeIds = placeResponses.stream()
                .map(PlaceResponse::getId)
                .collect(Collectors.toList());
        assertThat(placeIds).containsAll(Arrays.asList(createdPlaceResponse1.getId(), createdPlaceResponse2.getId()));
    }

    @Test
    @DisplayName("장소를 수정한다")
    void updatePlace() {
        // given
        PlaceResponse createdPlaceResponse = placeService.createPlace(placeRequest1);

        // when
        PlaceResponse placeResponse = placeService.updatePlace(createdPlaceResponse.getId(), placeRequest2);

        // then
        assertThat(placeResponse.getId()).isEqualTo(createdPlaceResponse.getId());
        assertThat(placeResponse.getName()).isEqualTo(placeRequest2.getName());
        assertThat(placeResponse.getPoint()).isEqualTo(placeRequest2.getPoint());
        assertThat(placeResponse.getImageUrl()).isEqualTo(placeRequest2.getImageUrl());
        assertThat(placeResponse.getRecommendCount()).isZero();
        assertThat(placeResponse.getReadCount()).isZero();
        assertThat(placeResponse.getDescription()).isEqualTo(placeRequest2.getDescription());
    }
}
