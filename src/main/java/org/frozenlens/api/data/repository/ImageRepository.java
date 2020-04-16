package org.frozenlens.api.data.repository;

import org.frozenlens.api.data.entity.Album;
import org.frozenlens.api.data.entity.Image;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByAlbum(Album album, Sort sort);
}
