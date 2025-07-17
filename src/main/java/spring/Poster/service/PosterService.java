package spring.Poster.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Service
public class PosterService {

    private final LinkedList<byte[]> imageQueue = new LinkedList<>();
    private static final int MAX_IMAGES = 3;

    public void savePosterAsBytes(byte[] imageBytes) {
        if (imageQueue.size() >= MAX_IMAGES) {
            imageQueue.removeFirst(); // Remove the oldest
        }
        imageQueue.addLast(imageBytes); // Add the newest
    }

    public byte[] getLatestPoster() {
        return imageQueue.isEmpty() ? null : imageQueue.getLast();
    }

    public List<byte[]> getAllPosters() {
        return new LinkedList<>(imageQueue);
    }

    public int getStoredCount() {
        return imageQueue.size();
    }
}
