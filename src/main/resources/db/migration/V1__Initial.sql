CREATE TABLE albums(
    id BIGINT(21) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    description VARCHAR(255),
    `order` INT(11) UNSIGNED,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE images(
       id BIGINT(21) UNSIGNED NOT NULL AUTO_INCREMENT,
       album_id BIGINT(21) UNSIGNED NOT NULL,
       name VARCHAR(255) NOT NULL,
       location VARCHAR(255),
       description VARCHAR(255),
       `order` INT(11) UNSIGNED,
       url VARCHAR(255),
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       PRIMARY KEY (id),
       FOREIGN KEY (album_id) REFERENCES albums(id) on delete cascade on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8;