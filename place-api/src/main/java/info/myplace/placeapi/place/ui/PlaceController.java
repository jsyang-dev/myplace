package info.myplace.placeapi.place.ui;

import info.myplace.placeapi.place.application.PlaceService;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceResponse> createPlace(PlaceRequest placeRequest) {
        PlaceResponse placeResponse = placeService.createPlace(placeRequest);
        return ResponseEntity.created(URI.create("/places/" + placeResponse.getId())).build();
    }
}
