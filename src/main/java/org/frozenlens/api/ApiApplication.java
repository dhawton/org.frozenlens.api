package org.frozenlens.api;

import org.frozenlens.api.data.entity.Album;
import org.frozenlens.api.data.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	// @TODO REMOVE BEFORE PRODUCTION, THIS IS FOR TESTING ONLY!
	@RestController
	@RequestMapping("/albums")
	public class AlbumController {
		@Autowired
		private AlbumRepository albumRepository;

		@GetMapping
		public Iterable<Album> getAlbums() {
			return this.albumRepository.findAll();
		}
	}

}
