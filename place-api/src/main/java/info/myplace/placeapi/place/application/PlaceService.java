package info.myplace.placeapi.place.application;

import info.myplace.placeapi.place.domain.Place;
import info.myplace.placeapi.place.domain.PlaceRepository;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagRequest;
import info.myplace.placeapi.place.exception.PlaceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponse createPlace(PlaceRequest placeRequest) {
        Place place = placeRepository.save(placeRequest.toPlace());
        return PlaceResponse.of(place);
    }

    @Transactional(readOnly = true)
    public PlaceResponse getPlace(Long id) {
        Place place = findPlaceById(id);
        return PlaceResponse.of(place);
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> getPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(PlaceResponse::of)
                .collect(Collectors.toList());
    }

    public void updatePlace(Long id, PlaceRequest placeRequest) {
        Place place = findPlaceById(id);
        place.update(placeRequest.toPlace());
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    public void addTag(Long placeId, TagRequest tagRequest) {
        Place place = findPlaceById(placeId);
        place.addTag(tagRequest.toTag());
    }

    public void removeTag(Long placeId, String tagName) {
        Place place = findPlaceById(placeId);
        place.removeTag(tagName);
    }

    private Place findPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(() -> new PlaceNotFoundException(id));
    }
}
