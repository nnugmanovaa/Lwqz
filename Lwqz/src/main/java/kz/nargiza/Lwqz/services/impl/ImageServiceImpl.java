package kz.nargiza.Lwqz.services.impl;

import kz.nargiza.Lwqz.models.entities.Image;
import kz.nargiza.Lwqz.repositories.ImageRepository;
import kz.nargiza.Lwqz.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }
}
