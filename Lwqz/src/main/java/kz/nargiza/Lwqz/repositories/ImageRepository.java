package kz.nargiza.Lwqz.repositories;

import kz.nargiza.Lwqz.models.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findById(Long id);

}
