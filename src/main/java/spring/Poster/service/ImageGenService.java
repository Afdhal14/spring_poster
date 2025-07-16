package spring.Poster.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.Poster.model.Prompt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ImageGenService {

    private final String flaskUrl = "http://localhost:5000/generate";
    private final Prompt prompt = new Prompt();
    private final Random rand = new Random();

    public byte[] generateImage() throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String randomPrompt = prompt.quoteBackgroundPrompts[rand.nextInt(prompt.quoteBackgroundPrompts.length)] +"motivational poster background, cinematic lighting";

        Map<String,String> body = new HashMap<>();
        body.put("prompt", randomPrompt);

        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(body,headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl,requestEntity,Map.class);

        String imageUrl = response.getBody().get("image_url").toString();
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        return baos.toByteArray();
    }
}

