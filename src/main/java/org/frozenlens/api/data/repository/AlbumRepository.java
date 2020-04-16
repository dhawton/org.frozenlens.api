package org.frozenlens.api.data.repository;

import org.frozenlens.api.data.entity.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
