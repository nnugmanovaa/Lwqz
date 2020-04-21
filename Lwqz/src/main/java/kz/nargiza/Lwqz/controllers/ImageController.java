package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.entities.Image;
import kz.nargiza.Lwqz.repositories.ImageRepository;
import kz.nargiza.Lwqz.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/images")
public class ImageController {

    private ImageRepository imageRepository;
    private ImageService imageService;

    @Autowired
    public ImageController(ImageRepository imageRepository, ImageService imageService) {
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity.BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        Image img = new Image(file.getOriginalFilename(), file.getContentType(),
                this.imageService.compressBytes(file.getBytes()));
        imageRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }


    @GetMapping(path = { "/get/{id}" })
    public Image getImage(@PathVariable Long id) throws IOException {

        final Optional<Image> retrievedImage = imageRepository.findById(id);
        Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
                this.imageService.decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    @GetMapping
    public List<Image> allImages() throws IOException {

        final List<Image> retrievedImages = imageRepository.findAll();
        List<Image> newList = new ArrayList<>();
        for(int i = 0; i<retrievedImages.size(); i++){
            Image img = new Image(retrievedImages.get(i).getName(), retrievedImages.get(i).getType(),
                   this.imageService.decompressBytes(retrievedImages.get(i).getPicByte()));
            newList.add(img);
        }
        System.out.print(newList.size());
        return newList;
    }



}
