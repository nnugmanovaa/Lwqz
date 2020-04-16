package kz.nargiza.Lwqz.services.impl;

import kz.nargiza.Lwqz.models.entities.*;
import kz.nargiza.Lwqz.models.errors.ErrorCode;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import kz.nargiza.Lwqz.repositories.CategoryRepository;
import kz.nargiza.Lwqz.repositories.PostRepository;
import kz.nargiza.Lwqz.services.CategoryService;
import kz.nargiza.Lwqz.services.CityService;
import kz.nargiza.Lwqz.services.PostService;
import kz.nargiza.Lwqz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserService userService;
    private CategoryService categoryService;
    private CityService cityService;


    @Override
    public Post addPost(String title, String description, int price,
                        String username, String category, String cityName, Image image) throws SystemServiceException {
        User user = this.userService.findByUsername(username);
        Category categoryName = this.categoryService.findByName(category);
        City city = this.cityService.findByName(cityName);
        if (Objects.nonNull(user) && Objects.nonNull(categoryName) && Objects.nonNull(city)) {
            Post post = Post.builder()
                    .title(title)
                    .description(description)
                    .price(price)
                    .image(image)
                    .author(user)
                    .city(city)
                    .category(categoryName)
                    .build();
            return postRepository.save(post);
        }
        throw SystemServiceException.builder()
                .message("no user with such id")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAllByActiveIsTrue();
    }

    @Override
    public List<Post> findAllFavorites() {
        return  postRepository.findAllByActiveIsTrueAndFavoriteIsTrue();
    }

    @Override
    public List<Post> findByCategory(Long id) throws SystemServiceException {
        return postRepository.findAllByCategoryIdAndActiveIsTrue(id);
    }

    @Override
    public List<Post> findByCity(String cityName) throws SystemServiceException {
        return  postRepository.findByCityNameAndActiveIsTrue(cityName);
    }

    @Override
    public Post findById(Long id) throws SystemServiceException {
        return postRepository.findByIdAndActiveIsTrue(id).orElseThrow(() -> SystemServiceException.builder()
                .message("No post with such id")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build());
    }

    @Override
    public void deletePost(Long postId) throws SystemServiceException {
        Post post = findById(postId);
        post.setActive(false);
        update(post);
    }

    @Override
    public Post update(Post post) throws SystemServiceException {
        if (Objects.nonNull(post.getId())) {
            return postRepository.save(post);
        }

        throw SystemServiceException.builder()
                .message("This post is still noT persisted")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build();
    }

    @Override
    public Post update(Long id, String title, String description, int price) throws SystemServiceException {
        Post post = findById(id);
        post.setTitle(title);
        post.setDescription(description);
        post.setPrice(price);
        return postRepository.save(post);
    }

    @Override
    public Post addFavorite(Long postId) throws SystemServiceException {
        Post post = findById(postId);
        post.setFavorite(true);
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findByImageName(String imageName) {
        return postRepository.findByImageName(imageName);
    }
}
