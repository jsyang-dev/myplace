package info.myplace.placeapi.place.ui;

import info.myplace.placeapi.place.application.PlaceService;
import info.myplace.placeapi.place.dto.PlaceRequest;
import info.myplace.placeapi.place.dto.PlaceResponse;
import info.myplace.placeapi.place.dto.TagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceResponse> createPlace(@RequestBody PlaceRequest placeRequest) {
        PlaceResponse placeResponse = placeService.createPlace(placeRequest);
        return ResponseEntity.created(URI.create("/places/" + placeResponse.getId())).body(placeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponse> getPlace(@PathVariable Long id) {
        PlaceResponse placeResponse = placeService.getPlace(id);
        return ResponseEntity.ok(placeResponse);
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getPlaces() {
        List<PlaceResponse> placeResponses = placeService.getPlaces();
        return ResponseEntity.ok(placeResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlaces(@PathVariable Long id, @RequestBody PlaceRequest placeRequest) {
        placeService.updatePlace(id, placeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaces(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{placeId}/tags")
    public ResponseEntity<Void> createPlace(@PathVariable Long placeId, @RequestBody TagRequest tagRequest) {
        placeService.addTag(placeId, tagRequest);
        return ResponseEntity.ok().build();
    }
}
