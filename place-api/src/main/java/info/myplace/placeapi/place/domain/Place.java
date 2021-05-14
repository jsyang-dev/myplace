package info.myplace.placeapi.place.domain;

import info.myplace.placeapi.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @Embedded
    private Location location;

    @Column
    private int recommendCount = 0;

    @Column
    private int readCount = 0;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "place", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    @Builder
    private Place(String name, String imageUrl, double latitude, double longitude, String description, List<Tag> tags) {
        this.name = name;
        this.location = Location.builder().longitude(longitude).latitude(latitude).build();
        this.imageUrl = imageUrl;
        this.description = description;
        this.tags = tags;
    }

    public void update(Place place) {
        this.name = place.getName();
        this.imageUrl = place.getImageUrl();
        this.location.setLatitude(place.getLocation().getLatitude());
        this.location.setLongitude(place.getLocation().getLongitude());
        this.description = place.getDescription();
        this.tags = place.getTags();
    }
}
