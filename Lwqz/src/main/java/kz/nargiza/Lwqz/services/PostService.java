package kz.nargiza.Lwqz.services;

import kz.nargiza.Lwqz.models.entities.Image;
import kz.nargiza.Lwqz.models.entities.Post;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post addPost(String title, String description, int price,
                 String username, String category, String cityName, Image image) throws SystemServiceException;

    List<Post> findAll() ;

    List<Post> findAllFavorites();

    List<Post> findByCategory(Long id) throws SystemServiceException;

    List<Post> findByCity(String cityName) throws SystemServiceException;

    Post findById(Long id) throws SystemServiceException;

    void deletePost(Long postId) throws SystemServiceException;

    Post update(Post post) throws SystemServiceException;

    Post update(Long id, String title, String description, int price) throws SystemServiceException;

    Post addFavorite(Long postId) throws SystemServiceException;

    Optional<Post> findByImageName(String imageName);
}
