package info.myplace.placeapi.place.documentation;

import info.myplace.placeapi.Documentation;
import info.myplace.placeapi.place.application.PlaceService;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static info.myplace.placeapi.place.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.장소_리스트_조회_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_삭제_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_생성_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_수정_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_조회_요청;
import static info.myplace.placeapi.place.PlaceSteps.초막골_생태공원;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@DisplayName("장소 관리 문서화")
class PlaceDocumentation extends Documentation {

    PlaceResponse 수리산_산림욕장_응답 = PlaceResponse.builder()
            .id(1L)
            .name(수리산_산림욕장.getName())
            .imageUrl(수리산_산림욕장.getImageUrl())
            .latitude(수리산_산림욕장.getLatitude())
            .longitude(수리산_산림욕장.getLongitude())
            .recommendCount(0)
            .readCount(0)
            .description(수리산_산림욕장.getDescription())
            .tags(Arrays.asList(
                    TagResponse.builder().id(1L).name("산림욕장").build(),
                    TagResponse.builder().id(2L).name("산책").build()
            ))
            .build();
    PlaceResponse 초막골_생태공원_응답 = PlaceResponse.builder()
            .id(2L)
            .name(초막골_생태공원.getName())
            .imageUrl(초막골_생태공원.getImageUrl())
            .latitude(초막골_생태공원.getLatitude())
            .longitude(초막골_생태공원.getLongitude())
            .recommendCount(0)
            .readCount(0)
            .description(초막골_생태공원.getDescription())
            .tags(Arrays.asList(
                    TagResponse.builder().id(1L).name("생태공원").build(),
                    TagResponse.builder().id(2L).name("산책").build()
            ))
            .build();

    @MockBean
    private PlaceService placeService;

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // given
        when(placeService.createPlace(any())).thenReturn(수리산_산림욕장_응답);

        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("name").description("장소명"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("latitude").description("위도"),
                fieldWithPath("longitude").description("경도"),
                fieldWithPath("description").description("장소 설명"),
                fieldWithPath("tags[].name").description("태그명")
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("장소 ID"),
                fieldWithPath("name").description("장소명"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("latitude").description("위도"),
                fieldWithPath("longitude").description("경도"),
                fieldWithPath("recommendCount").description("추천수"),
                fieldWithPath("readCount").description("조회수"),
                fieldWithPath("description").description("장소 설명"),
                fieldWithPath("tags[].id").description("태그 ID"),
                fieldWithPath("tags[].name").description("태그명")
        };

        // when
        장소_생성_요청(
                given("place/create", requestFields(requestFieldDescriptors), responseFields(responseFieldDescriptors)),
                수리산_산림욕장
        );
    }

    @Test
    @DisplayName("장소를 조회한다")
    void getPlace() {
        // given
        when(placeService.getPlace(anyLong())).thenReturn(수리산_산림욕장_응답);

        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("id").description("장소 ID")
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("장소 ID"),
                fieldWithPath("name").description("장소명"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("latitude").description("위도"),
                fieldWithPath("longitude").description("경도"),
                fieldWithPath("recommendCount").description("추천수"),
                fieldWithPath("readCount").description("조회수"),
                fieldWithPath("description").description("장소 설명"),
                fieldWithPath("tags[].id").description("태그 ID"),
                fieldWithPath("tags[].name").description("태그명")
        };

        // when
        장소_조회_요청(
                given("place/get", pathParameters(pathParameterDescriptors), responseFields(responseFieldDescriptors)),
                수리산_산림욕장_응답
        );
    }

    @Test
    @DisplayName("장소 리스트를 조회한다")
    void getPlaces() {
        // given
        when(placeService.getPlaces()).thenReturn(Arrays.asList(수리산_산림욕장_응답, 초막골_생태공원_응답));

        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("[].id").description("장소 ID"),
                fieldWithPath("[].name").description("장소명"),
                fieldWithPath("[].imageUrl").description("이미지 경로"),
                fieldWithPath("[].latitude").description("위도"),
                fieldWithPath("[].longitude").description("경도"),
                fieldWithPath("[].recommendCount").description("추천수"),
                fieldWithPath("[].readCount").description("조회수"),
                fieldWithPath("[].description").description("장소 설명"),
                fieldWithPath("[].tags[].id").description("태그 ID"),
                fieldWithPath("[].tags[].name").description("태그명")
        };

        // when
        장소_리스트_조회_요청(given("place/getList", responseFields(responseFieldDescriptors)));
    }

    @Test
    @DisplayName("장소를 수정한다")
    void updatePlace() {
        // given
        ReflectionTestUtils.setField(초막골_생태공원_응답, "id", 1L);
        when(placeService.updatePlace(anyLong(), any())).thenReturn(초막골_생태공원_응답);

        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("id").description("장소 ID")
        };
        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("name").description("장소명"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("latitude").description("위도"),
                fieldWithPath("longitude").description("경도"),
                fieldWithPath("description").description("장소 설명"),
                fieldWithPath("tags[].name").description("태그명")
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("장소 ID"),
                fieldWithPath("name").description("장소명"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("latitude").description("위도"),
                fieldWithPath("longitude").description("경도"),
                fieldWithPath("recommendCount").description("추천수"),
                fieldWithPath("readCount").description("조회수"),
                fieldWithPath("description").description("장소 설명"),
                fieldWithPath("tags[].id").description("태그 ID"),
                fieldWithPath("tags[].name").description("태그명")
        };

        // when
        장소_수정_요청(
                given("place/update", pathParameters(pathParameterDescriptors), requestFields(requestFieldDescriptors), responseFields(responseFieldDescriptors)),
                수리산_산림욕장_응답,
                초막골_생태공원
        );
    }

    @Test
    @DisplayName("장소를 삭제한다")
    void deletePlace() {
        // given
        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("id").description("장소 ID")
        };

        // when
        장소_삭제_요청(
                given("place/delete", pathParameters(pathParameterDescriptors)),
                수리산_산림욕장_응답
        );
    }
}
