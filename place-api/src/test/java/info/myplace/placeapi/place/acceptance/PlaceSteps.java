package info.myplace.placeapi.place.acceptance;

import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceSteps {

    private static final String URI_PLACES = "/places";

    public static final PlaceRequest 수리산_산림욕장 = PlaceRequest.builder()
            .name("수리산 산림욕장")
            .point(new Point(37.356683, 126.915901))
            .imageUrl("/image/surisan.jpg")
            .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
            .build();

    public static final PlaceRequest 초막골_생태공원 = PlaceRequest.builder()
            .name("초막골 생태공원")
            .point(new Point(37.353632, 126.918564))
            .imageUrl("/image/chomakgol.jpg")
            .description("경기도 군포시에 있는 생태를 테마로 한 도시공원")
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

    public static ExtractableResponse<Response> 장소_조회_요청(RequestSpecification given, PlaceResponse placeResponse) {
        return given
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(URI_PLACES + "/{id}", placeResponse.getId())
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장소_리스트_조회_요청(RequestSpecification given) {
        return given
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(URI_PLACES)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 장소_수정_요청(RequestSpecification given, PlaceResponse placeResponse, PlaceRequest placeRequest) {
        return given
                .body(placeRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(URI_PLACES + "/{id}", placeResponse.getId())
                .then().log().all()
                .extract();
    }

    public static void 장소_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static void 장소_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(PlaceResponse.class)).isNotNull();
    }

    public static void 장소_리스트_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 장소_리스트_포함됨(ExtractableResponse<Response> response, List<PlaceResponse> placeResponses) {
        List<Long> placeIds = new ArrayList<>(response.jsonPath().getList(".", PlaceResponse.class))
                .stream()
                .map(PlaceResponse::getId)
                .collect(Collectors.toList());
        List<Long> expectedPlaceIds = placeResponses.stream()
                .map(PlaceResponse::getId)
                .collect(Collectors.toList());

        assertThat(placeIds).containsAll(expectedPlaceIds);
    }

    public static void 장소_수정됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
