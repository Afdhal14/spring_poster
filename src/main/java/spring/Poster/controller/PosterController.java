package spring.Poster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.Poster.service.PosterService;

import java.util.List;

@RestController
public class PosterController {

    @Autowired
    private PosterService posterService;

    @PostMapping("/upload-poster")
    public ResponseEntity<String> uploadPoster(@RequestParam("poster") MultipartFile file)
    {
        try{
            byte[] posterBytes = file.getBytes();
            posterService.savePosterAsBytes(posterBytes);
            return  ResponseEntity.ok("Poster Uploaded Successfully");
        }
        catch(Exception e)
        {
            return ResponseEntity.internalServerError().body("Failed to upload poster.");
        }
    }

    @GetMapping("/latest-poster")
    public ResponseEntity<byte[]> getLatestPoster() {
        byte[] latest = posterService.getLatestPoster();
        if (latest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(latest);
    }

    @GetMapping("/all-posters")
    public ResponseEntity<List<byte[]>> getAllPosters() {
        return ResponseEntity.ok(posterService.getAllPosters());
    }

    @GetMapping("/poster-count")
    public ResponseEntity<Integer> getPosterCount() {
        return ResponseEntity.ok(posterService.getStoredCount());
    }
}
