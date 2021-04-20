package info.myplace.placeapi.place.documentation;

import info.myplace.placeapi.Documentation;
import info.myplace.placeapi.place.application.PlaceService;
import info.myplace.placeapi.place.dto.PlaceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;

import static info.myplace.placeapi.place.acceptance.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_생성_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

class PlaceDocumentation extends Documentation {

    @MockBean
    private PlaceService placeService;

    @Test
    void createPlace() {
        // given
        PlaceResponse placeResponse = PlaceResponse.builder()
                .id(1L)
                .name(수리산_산림욕장.getName())
                .point(수리산_산림욕장.getPoint())
                .imageUrl(수리산_산림욕장.getImageUrl())
                .recommendCount(0)
                .readCount(0)
                .description(수리산_산림욕장.getDescription())
                .build();
        when(placeService.createPlace(any())).thenReturn(placeResponse);

        FieldDescriptor[] requestFields = {
                fieldWithPath("name").description("장소명"),
                fieldWithPath("point.x").description("위도"),
                fieldWithPath("point.y").description("경도"),
                fieldWithPath("imageUrl").description("이미지 경로"),
                fieldWithPath("description").description("장소 설명")
        };
        FieldDescriptor[] responseFields = {
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
                given("place-create", null, requestFields, responseFields),
                수리산_산림욕장
        );
    }
}
