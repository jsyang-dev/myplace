package info.myplace.placeapi.place.application;

import info.myplace.placeapi.ServiceTest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagRequest;
import info.myplace.placeapi.place.dto.TagResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static info.myplace.placeapi.place.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.초막골_생태공원;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("장소 관리 서비스 테스트")
class PlaceServiceTest extends ServiceTest {

    @Autowired
    private PlaceService placeService;

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // when
        PlaceResponse placeResponse = placeService.createPlace(수리산_산림욕장);

        // then
        assertThat(placeResponse.getId()).isNotNull();
        assertThat(placeResponse.getName()).isEqualTo(수리산_산림욕장.getName());
        assertThat(placeResponse.getImageUrl()).isEqualTo(수리산_산림욕장.getImageUrl());
        assertThat(placeResponse.getLatitude()).isEqualTo(수리산_산림욕장.getLatitude());
        assertThat(placeResponse.getLongitude()).isEqualTo(수리산_산림욕장.getLongitude());
        assertThat(placeResponse.getRecommendCount()).isZero();
        assertThat(placeResponse.getReadCount()).isZero();
        assertThat(placeResponse.getDescription()).isEqualTo(수리산_산림욕장.getDescription());

        List<String> tagNames = placeResponse.getTags()
                .stream()
                .map(TagResponse::getName)
                .collect(Collectors.toList());
        List<String> expectedTagNames = 수리산_산림욕장.getTags().stream()
                .map(TagRequest::getName)
                .collect(Collectors.toList());
        assertThat(tagNames).containsAll(expectedTagNames);
    }

    @Test
    @DisplayName("장소를 조회한다")
    void getPlace() {
        // given
        PlaceResponse createdPlaceResponse = placeService.createPlace(수리산_산림욕장);

        // when
        PlaceResponse placeResponse = placeService.getPlace(createdPlaceResponse.getId());

        // then
        assertThat(placeResponse.getId()).isEqualTo(createdPlaceResponse.getId());
        assertThat(placeResponse.getName()).isEqualTo(createdPlaceResponse.getName());
        assertThat(placeResponse.getImageUrl()).isEqualTo(createdPlaceResponse.getImageUrl());
        assertThat(placeResponse.getLatitude()).isEqualTo(createdPlaceResponse.getLatitude());
        assertThat(placeResponse.getLongitude()).isEqualTo(createdPlaceResponse.getLongitude());
        assertThat(placeResponse.getRecommendCount()).isEqualTo(createdPlaceResponse.getRecommendCount());
        assertThat(placeResponse.getReadCount()).isEqualTo(createdPlaceResponse.getReadCount());
        assertThat(placeResponse.getDescription()).isEqualTo(createdPlaceResponse.getDescription());

        List<String> tagNames = placeResponse.getTags()
                .stream()
                .map(TagResponse::getName)
                .collect(Collectors.toList());
        List<String> expectedTagNames = 수리산_산림욕장.getTags().stream()
                .map(TagRequest::getName)
                .collect(Collectors.toList());
        assertThat(tagNames).containsAll(expectedTagNames);
    }

    @Test
    @DisplayName("장소 리스트를 조회한다")
    void getPlaces() {
        // given
        PlaceResponse createdPlaceResponse1 = placeService.createPlace(수리산_산림욕장);
        PlaceResponse createdPlaceResponse2 = placeService.createPlace(초막골_생태공원);

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
        PlaceResponse createdPlaceResponse = placeService.createPlace(수리산_산림욕장);

        // when
        PlaceResponse placeResponse = placeService.updatePlace(createdPlaceResponse.getId(), 초막골_생태공원);

        // then
        assertThat(placeResponse.getId()).isEqualTo(createdPlaceResponse.getId());
        assertThat(placeResponse.getName()).isEqualTo(초막골_생태공원.getName());
        assertThat(placeResponse.getImageUrl()).isEqualTo(초막골_생태공원.getImageUrl());
        assertThat(placeResponse.getLatitude()).isEqualTo(초막골_생태공원.getLatitude());
        assertThat(placeResponse.getLongitude()).isEqualTo(초막골_생태공원.getLongitude());
        assertThat(placeResponse.getRecommendCount()).isZero();
        assertThat(placeResponse.getReadCount()).isZero();
        assertThat(placeResponse.getDescription()).isEqualTo(초막골_생태공원.getDescription());

        List<String> tagNames = placeResponse.getTags()
                .stream()
                .map(TagResponse::getName)
                .collect(Collectors.toList());
        List<String> expectedTagNames = 초막골_생태공원.getTags().stream()
                .map(TagRequest::getName)
                .collect(Collectors.toList());
        assertThat(tagNames).containsAll(expectedTagNames);
    }

    @Test
    @DisplayName("장소를 삭제한다")
    void deletePlace() {
        // given
        PlaceResponse createdPlaceResponse = placeService.createPlace(수리산_산림욕장);
        PlaceService mockPlaceService = mock(PlaceService.class);

        // when
        mockPlaceService.deletePlace(createdPlaceResponse.getId());

        // then
        verify(mockPlaceService, times(1)).deletePlace(createdPlaceResponse.getId());
    }
}
