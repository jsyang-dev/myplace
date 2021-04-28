package info.myplace.placeapi.place.documentation;

import info.myplace.placeapi.Documentation;
import info.myplace.placeapi.place.application.PlaceService;
import info.myplace.placeapi.place.dto.PlaceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;

import static info.myplace.placeapi.place.acceptance.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_리스트_조회_요청;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_생성_요청;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_조회_요청;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.초막골_생태공원;
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

    PlaceResponse placeResponse1 = PlaceResponse.builder()
            .id(1L)
            .name(수리산_산림욕장.getName())
            .point(수리산_산림욕장.getPoint())
            .imageUrl(수리산_산림욕장.getImageUrl())
            .recommendCount(0)
            .readCount(0)
            .description(수리산_산림욕장.getDescription())
            .build();

    @MockBean
    private PlaceService placeService;

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // given
        when(placeService.createPlace(any())).thenReturn(placeResponse1);

        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("name").description("장소명"),
                fieldWithPath("point.x").description("위도"),
                fieldWithPath("point.y").description("경도"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("description").description("장소 설명")
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("장소 ID"),
                fieldWithPath("name").description("장소명"),
                fieldWithPath("point.x").description("위도"),
                fieldWithPath("point.y").description("경도"),
                fieldWithPath("recommendCount").description("추천수"),
                fieldWithPath("readCount").description("조회수"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("description").description("장소 설명")
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
        when(placeService.getPlace(anyLong())).thenReturn(placeResponse1);

        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("id").description("장소 ID")
        };
        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("id").description("장소 ID"),
                fieldWithPath("name").description("장소명"),
                fieldWithPath("point.x").description("위도"),
                fieldWithPath("point.y").description("경도"),
                fieldWithPath("recommendCount").description("추천수"),
                fieldWithPath("readCount").description("조회수"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("description").description("장소 설명")
        };

        // when
        장소_조회_요청(
                given("place/get", pathParameters(pathParameterDescriptors), responseFields(responseFieldDescriptors)),
                placeResponse1
        );
    }

    @Test
    @DisplayName("장소 리스트를 조회한다")
    void getPlaces() {
        // given
        PlaceResponse placeResponse2 = PlaceResponse.builder()
                .id(2L)
                .name(초막골_생태공원.getName())
                .point(초막골_생태공원.getPoint())
                .imageUrl(초막골_생태공원.getImageUrl())
                .recommendCount(0)
                .readCount(0)
                .description(초막골_생태공원.getDescription())
                .build();
        when(placeService.getPlaces()).thenReturn(Arrays.asList(placeResponse1, placeResponse2));

        FieldDescriptor[] responseFieldDescriptors = {
                fieldWithPath("[].id").description("장소 ID"),
                fieldWithPath("[].name").description("장소명"),
                fieldWithPath("[].point.x").description("위도"),
                fieldWithPath("[].point.y").description("경도"),
                fieldWithPath("[].recommendCount").description("추천수"),
                fieldWithPath("[].readCount").description("조회수"),
                fieldWithPath("[].imageUrl").description("이미지 경로"),
                fieldWithPath("[].description").description("장소 설명")
        };

        // when
        장소_리스트_조회_요청(given("place/getList", responseFields(responseFieldDescriptors)));
    }
}
