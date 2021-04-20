package info.myplace.placeapi.place.acceptance;

import info.myplace.placeapi.place.dto.PlaceRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceSteps {

    private static final String URI_PLACES = "/places";

    public static final PlaceRequest 수리산_산림욕장 = PlaceRequest.builder()
            .name("수리산 산림욕장")
            .point(new Point(37.356683, 126.915901))
            .imageUrl("/image/surisan.jpg")
            .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
            .build();

    public static ExtractableResponse<Response> 장소_생성_요청(RequestSpecification given, PlaceRequest placeRequest) {
        return given
                .body(placeRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(URI_PLACES)
                .then().log().all()
                .extract();
    }

    public static void 장소_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }
}
