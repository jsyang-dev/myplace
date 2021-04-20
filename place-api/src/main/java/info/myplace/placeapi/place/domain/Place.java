package info.myplace.placeapi.place.domain;

import info.myplace.placeapi.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private Point point;

    @Column
    private String imageUrl;

    @Column
    @Builder.Default
    private int recommendCount = 0;

    @Column
    @Builder.Default
    private int readCount = 0;

    @Column(columnDefinition = "text")
    private String description;

    @Builder
    private Place(String name, Point point, String imageUrl, String description) {
        this.name = name;
        this.point = point;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
