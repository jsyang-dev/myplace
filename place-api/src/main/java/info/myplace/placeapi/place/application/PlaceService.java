package info.myplace.placeapi.place.application;

import info.myplace.placeapi.place.domain.Place;
import info.myplace.placeapi.place.domain.PlaceRepository;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.exception.PlaceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponse createPlace(PlaceRequest placeRequest) {
        Place place = placeRepository.save(placeRequest.toPlace());
        return PlaceResponse.of(place);
    }

    public PlaceResponse getPlace(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new PlaceNotFoundException(id));
        return PlaceResponse.of(place);
    }

    public List<PlaceResponse> getPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(PlaceResponse::of)
                .collect(Collectors.toList());
    }
}
