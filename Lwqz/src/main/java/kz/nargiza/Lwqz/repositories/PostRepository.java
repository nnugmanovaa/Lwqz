package kz.nargiza.Lwqz.repositories;

import javafx.geometry.Pos;
import kz.nargiza.Lwqz.models.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long > {
    List<Post> findAllByActiveIsTrue();

    List<Post> findAllByCategoryIdAndActiveIsTrue(Long id);

    Optional<Post> findByIdAndActiveIsTrue(Long id);

    List<Post> findAllByActiveIsTrueAndFavoriteIsTrue();

    Optional<Post> findByImageName(String imageName);

    List<Post> findByCityNameAndActiveIsTrue(String city);
}
