package info.myplace.placeapi.place.documentation;

import info.myplace.placeapi.Documentation;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;

import static info.myplace.placeapi.place.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.태그_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.태그_제거_요청;
import static info.myplace.placeapi.place.PlaceSteps.태그_추가_요청;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

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

    @Test
    @DisplayName("태그를 추가한다")
    void addTag() {
        // given
        FieldDescriptor[] requestFieldDescriptors = {
                fieldWithPath("name").description("태그명")
        };

        // when
        태그_추가_요청(
                given("tag/add", requestFields(requestFieldDescriptors)),
                수리산_산림욕장_응답,
                태그_산림욕장
        );
    }

    @Test
    @DisplayName("태그를 제거한다")
    void removeTag() {
        // given
        ParameterDescriptor[] pathParameterDescriptors = {
                parameterWithName("placeId").description("장소 ID")
        };

        ParameterDescriptor[] requestParameterDescriptors = {
                parameterWithName("tagName").description("태그명")
        };

        // when
        태그_제거_요청(
                given("tag/remove", pathParameters(pathParameterDescriptors), requestParameters(requestParameterDescriptors)),
                수리산_산림욕장_응답,
                태그_산림욕장
        );
    }
}
