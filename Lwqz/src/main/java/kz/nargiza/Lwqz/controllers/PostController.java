package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.entities.Image;
import kz.nargiza.Lwqz.models.entities.Post;
import kz.nargiza.Lwqz.models.errors.ErrorCode;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import kz.nargiza.Lwqz.models.responses.SuccessResponse;
import kz.nargiza.Lwqz.repositories.ImageRepository;
import kz.nargiza.Lwqz.repositories.PostRepository;
import kz.nargiza.Lwqz.services.ImageService;
import kz.nargiza.Lwqz.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController extends BaseController {

    private PostService postService;
    private ImageService imageService;
    private PostRepository postRepository;
    private ImageRepository imageRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository,
                          ImageRepository imageRepository, ImageService imageService) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    @GetMapping("favorites")
    public List<Post> findAllFav(){
        return postService.findAllFavorites();
    }

    @PatchMapping("{id}")
    public Post addFav(@PathVariable Long id){
        return this.postService.addFavorite(id);
    }

    @PostMapping
    public Post addPost(@RequestParam String title,
                        @RequestParam String description,
                        @RequestParam int price,
                        @RequestParam String city,
                        Authentication authentication,
                        @RequestParam String category,
                        @RequestParam("file") MultipartFile file) throws IOException {
        Image img = new Image(file.getOriginalFilename(), file.getContentType(),
                imageService.compressBytes(file.getBytes()));
        this.imageRepository.save(img);
        return this.postService.addPost(title, description, price, authentication.getName(), category, city, img);
    }


    @GetMapping
    public List<Post> loadAll(){
        return this.postService.findAll();
    }

    @GetMapping("category/{id}")
    public List<Post> loadById(@PathVariable Long id){
        return this.postService.findByCategory(id);
    }

    @GetMapping("{id}")
    public Post findPostById(@PathVariable Long id){
        return this.postService.findById(id);
    }

    @GetMapping("city/{cityName}")
    public List<Post> findByCityName(@PathVariable String cityName){
        return  this.postService.findByCity(cityName);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        this.postService.deletePost(id);
        this.imageRepository.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("post with id " + id + " deleted").build(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public Post updatePost(@Valid @RequestBody Post post,
                           @PathVariable Long id){
        Optional<Post> newPost = postRepository.findByIdAndActiveIsTrue(id);

        if(newPost.isPresent()){
            Post post1 = newPost.get();
            if (Objects.nonNull(post.getTitle())) {
                post1.setTitle(post.getTitle());
            }
            if (Objects.nonNull(post.getDescription())) {
                post1.setDescription(post.getDescription());
            }
            if (Objects.nonNull(post.getPrice())) {
                post1.setPrice(post.getPrice());
            }
            return postRepository.save(post1);
        }

        throw SystemServiceException.builder().errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .message("Post with such id not found")
                .build();
    }
}
