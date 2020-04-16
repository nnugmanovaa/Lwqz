package kz.nargiza.Lwqz.services;

import kz.nargiza.Lwqz.models.entities.Image;

import java.util.Optional;

public interface ImageService {
    Optional<Image> findById(Long id);
}
