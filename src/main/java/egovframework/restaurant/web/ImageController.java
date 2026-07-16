package egovframework.restaurant.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;

import egovframework.restaurant.service.MenuFileService;
import egovframework.restaurant.vo.MenuFileVO;



@Controller
public class ImageController {

    @Autowired
    private MenuFileService menuFileService;

    @RequestMapping("/menu/image.do")
    public ResponseEntity<byte[]> showMenuImage(
            @RequestParam("menuId") int menuId)
            throws IOException {

        MenuFileVO menuFile = menuFileService.selectMenuFile(menuId);

        if (menuFile == null) {
            return ResponseEntity.notFound().build();
        }

        File imageFile = new File(
                menuFile.getFilePath(),
                menuFile.getSavedName());

        if (!imageFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

        if (menuFile.getContentType() != null
                && !menuFile.getContentType().isBlank()) {

            mediaType = MediaType.parseMediaType(
                    menuFile.getContentType());
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageBytes);
    }
    
    @RequestMapping("/admin/menu/image/download.do")
    public ResponseEntity<byte[]> downloadMenuImage(
            @RequestParam("menuId") int menuId)
            throws IOException {

        MenuFileVO menuFile = menuFileService.selectMenuFile(menuId);

        if (menuFile == null) {
            return ResponseEntity.notFound().build();
        }

        File downloadFile = new File(
                menuFile.getFilePath(),
                menuFile.getSavedName());

        if (!downloadFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileBytes = Files.readAllBytes(downloadFile.toPath());

        String originalName = menuFile.getOriginalName();

        String encodedFileName = URLEncoder.encode(
                originalName,
                StandardCharsets.UTF_8)
                .replace("+", "%20");

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename*=UTF-8''" + encodedFileName);

        headers.setContentLength(fileBytes.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileBytes);
    }
}