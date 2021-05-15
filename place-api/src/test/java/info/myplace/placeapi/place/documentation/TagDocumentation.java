package info.myplace.placeapi.place.documentation;

import info.myplace.placeapi.Documentation;
import info.myplace.placeapi.place.application.PlaceService;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.Arrays;

import static info.myplace.placeapi.place.PlaceSteps.산림욕장_태그;
import static info.myplace.placeapi.place.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.태그_추가_요청;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

@DisplayName("태그 관리 문서화")
class TagDocumentation extends Documentation {

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

    @MockBean
    private PlaceService placeService;

    @Test
    @DisplayName("태그를 추가한다")
    void addTag() {
        // given
        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("name").description("태그명")
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
        태그_추가_요청(
                given("tag/add", requestFields(requestFieldDescriptors), responseFields(responseFieldDescriptors)),
                수리산_산림욕장_응답,
                산림욕장_태그
        );
    }
}
