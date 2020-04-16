package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.entities.Image;
import kz.nargiza.Lwqz.models.entities.Post;
import kz.nargiza.Lwqz.repositories.ImageRepository;
import kz.nargiza.Lwqz.repositories.PostRepository;
import kz.nargiza.Lwqz.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.Deflater;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Value("${file.upload-dir}")
    private String uploadPath;

    private PostService postService;
    private PostRepository postRepository;
    private ImageRepository imageRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository, ImageRepository imageRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
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
                compressBytes(file.getBytes()));
        this.imageRepository.save(img);
        return this.postService.addPost(title, description, price, authentication.getName(), category, city, img);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
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
    public void deletePost(@PathVariable Long id){
      //  this.postService.deletePost(id);
        this.postRepository.deleteById(id);
        this.imageRepository.deleteById(id);
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

        return null;
    }
}
