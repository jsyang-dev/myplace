package info.myplace.placeapi.place.acceptance;

import info.myplace.placeapi.AcceptanceTest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static info.myplace.placeapi.place.PlaceSteps.수리산_산림욕장;
import static info.myplace.placeapi.place.PlaceSteps.장소_리스트_조회_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_리스트_조회됨;
import static info.myplace.placeapi.place.PlaceSteps.장소_리스트_포함됨;
import static info.myplace.placeapi.place.PlaceSteps.장소_삭제_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_삭제됨;
import static info.myplace.placeapi.place.PlaceSteps.장소_생성_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_생성됨;
import static info.myplace.placeapi.place.PlaceSteps.장소_수정_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_수정됨;
import static info.myplace.placeapi.place.PlaceSteps.장소_조회_요청;
import static info.myplace.placeapi.place.PlaceSteps.장소_조회됨;
import static info.myplace.placeapi.place.PlaceSteps.초막골_생태공원;

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
        PlaceResponse placeResponse = 장소_생성_요청(given(), 수리산_산림욕장).as(PlaceResponse.class);

        // when
        ExtractableResponse<Response> response = 장소_조회_요청(given(), placeResponse);

        // then
        장소_조회됨(response);
    }

    @Test
    @DisplayName("장소 리스트를 조회한다")
    void getPlaces() {
        // given
        PlaceResponse placeResponse1 = 장소_생성_요청(given(), 수리산_산림욕장).as(PlaceResponse.class);
        PlaceResponse placeResponse2 = 장소_생성_요청(given(), 초막골_생태공원).as(PlaceResponse.class);

        // when
        ExtractableResponse<Response> response = 장소_리스트_조회_요청(given());

        // then
        장소_리스트_조회됨(response);
        장소_리스트_포함됨(response, Arrays.asList(placeResponse1, placeResponse2));
    }

    @Test
    @DisplayName("장소를 수정한다")
    void updatePlace() {
        // given
        PlaceResponse placeResponse = 장소_생성_요청(given(), 수리산_산림욕장).as(PlaceResponse.class);

        // when
        ExtractableResponse<Response> response = 장소_수정_요청(given(), placeResponse, 초막골_생태공원);

        // then
        장소_수정됨(response);
    }

    @Test
    @DisplayName("장소를 삭제한다")
    void deletePlace() {
        // given
        PlaceResponse placeResponse = 장소_생성_요청(given(), 수리산_산림욕장).as(PlaceResponse.class);

        // when
        ExtractableResponse<Response> response = 장소_삭제_요청(given(), placeResponse);

        // then
        장소_삭제됨(response);
    }
}
