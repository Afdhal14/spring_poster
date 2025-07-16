package spring.Poster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.Poster.service.ImageGenService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImageGenController {

    @Autowired
    private ImageGenService imageGenService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateImage() throws IOException {
        byte[] imageByte =  imageGenService.generateImage();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageByte, headers, HttpStatus.OK);
    }
}