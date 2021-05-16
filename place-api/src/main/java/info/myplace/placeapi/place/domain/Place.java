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
@Builder
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
    @Builder.Default
    private int recommendCount = 0;

    @Column
    @Builder.Default
    private int readCount = 0;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private List<Tag> tags = new ArrayList<>();

    public void update(Place place) {
        this.name = place.getName();
        this.imageUrl = place.getImageUrl();
        this.location.setLatitude(place.getLocation().getLatitude());
        this.location.setLongitude(place.getLocation().getLongitude());
        this.description = place.getDescription();
        this.tags = place.getTags();
    }

    public void addTag(Tag tag) {
        tag.setPlace(this);
    }
}
