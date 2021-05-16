package info.myplace.placeapi.place;

import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

public class PlaceSteps {

    private static final String URI_PLACES = "/places";
    private static final String URI_TAGS = "/tags";

    public static final PlaceRequest 수리산_산림욕장 = PlaceRequest.builder()
            .name("수리산 산림욕장")
            .imageUrl("/image/surisan.jpg")
            .latitude(37.356683)
            .longitude(126.915901)
            .description("경기도 안양시와 군포시 경계에 있는 산림욕장")
            .build();

    public static final PlaceRequest 초막골_생태공원 = PlaceRequest.builder()
            .name("초막골 생태공원")
            .imageUrl("/image/chomakgol.jpg")
            .latitude(37.353632)
            .longitude(126.918564)
            .description("경기도 군포시에 있는 생태를 테마로 한 도시공원")
            .build();

    public static final TagRequest 태그_산림욕장 = TagRequest.builder()
            .name("산림욕장")
            .build();

    public static final TagRequest 태그_산책 = TagRequest.builder()
            .name("산책")
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

    public static ExtractableResponse<Response> 장소_삭제_요청(RequestSpecification given, PlaceResponse placeResponse) {
        return given
                .when()
                .delete(URI_PLACES + "/{id}", placeResponse.getId())
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 태그_추가_요청(RequestSpecification given, PlaceResponse placeResponse, TagRequest tagRequest) {
        return given
                .body(tagRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(URI_PLACES + "/{placeId}" + URI_TAGS, placeResponse.getId())
                .then().log().all()
                .extract();
    }
}
