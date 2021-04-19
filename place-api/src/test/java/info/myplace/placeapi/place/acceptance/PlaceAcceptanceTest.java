package info.myplace.placeapi.place.acceptance;

import info.myplace.placeapi.AcceptanceTest;
import info.myplace.placeapi.place.dto.PlaceRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관리 인수 테스트")
class PlaceAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("장소를 생성한다")
    void createPlace() {
        // given
        PlaceRequest placeRequest = new PlaceRequest();

        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .body(placeRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/places")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }
}
