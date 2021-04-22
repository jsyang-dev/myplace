package info.myplace.placeapi.place.acceptance;

import info.myplace.placeapi.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static info.myplace.placeapi.place.acceptance.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_생성_요청;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_생성됨;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_조회_요청;
import static info.myplace.placeapi.place.acceptance.PlaceSteps.장소_조회됨;

@DisplayName("장소 관리 인수 테스트")
class PlaceAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // when
        ExtractableResponse<Response> response = 장소_생성_요청(given(), 수리산_산림욕장);

        // then
        장소_생성됨(response);
    }

    @Test
    @DisplayName("장소를 조회한다")
    void getPlace() {
        // given
        ExtractableResponse<Response> createResponse = 장소_생성_요청(given(), 수리산_산림욕장);

        // when
        ExtractableResponse<Response> response = 장소_조회_요청(given(), createResponse);

        // then
        장소_조회됨(response);
    }
}
