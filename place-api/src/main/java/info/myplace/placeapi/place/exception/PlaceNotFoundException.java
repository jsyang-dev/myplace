package info.myplace.placeapi.place.exception;

public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException(Long id) {
        super("유효한 장소가 존재하지 않습니다: id - " + id);
    }
}
