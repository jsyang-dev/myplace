package info.myplace.placeapi.place.acceptance;

import info.myplace.placeapi.AcceptanceTest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static info.myplace.placeapi.place.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.장소_생성_요청;
import static info.myplace.placeapi.place.PlaceSteps.태그_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.태그_추가_요청;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("태그 관리 인수 테스트")
class TagAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("태그를 추가한다")
    void addTag() {
        // given
        PlaceResponse placeResponse = 장소_생성_요청(given(), 수리산_산림욕장).as(PlaceResponse.class);

        // when
        ExtractableResponse<Response> response = 태그_추가_요청(given(), placeResponse, 태그_산림욕장);

        // then
        태그_추가됨(response);
    }

    private void 태그_추가됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
