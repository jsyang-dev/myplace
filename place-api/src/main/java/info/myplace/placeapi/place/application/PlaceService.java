package info.myplace.placeapi.place.application;

import info.myplace.placeapi.place.domain.Place;
import info.myplace.placeapi.place.domain.PlaceRepository;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponse createPlace(PlaceRequest placeRequest) {
        Place place = placeRepository.save(placeRequest.toPlace());
        return PlaceResponse.of(place);
    }
}
