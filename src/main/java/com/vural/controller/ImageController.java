package com.vural.controller;

import com.vural.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by vural on 27-Apr-17.
 */

@Controller
@PropertySource("classpath:config.properties")
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static List<Image> images = new ArrayList<>();
    private Image image;

    @Value("${max_image_number}")
    private int maxImageNumber;

    @GetMapping(value = "/images")
    public String result(ModelMap modelMap) {
        logger.info("request with /images");
        sortingImages();
        modelMap.addAttribute("images", images);
        return "images";
    }

    @RequestMapping(value = "/")
    public String defaultPage(ModelMap modelMap) {
        logger.info("request with /");
        modelMap.addAttribute("imageObject", new Image());
        return "upload";
    }

    @RequestMapping(value = "/upload")
    public String uploadImage(ModelMap modelMap) {
        logger.info("request with /upload");
        modelMap.addAttribute("imageObject", new Image());
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             @ModelAttribute("imageObject") final Image imageObject){

        if (file == null || file.isEmpty()) {
            logger.warn("image file is null or empty");
            return "upload";
        }

        if (images.size() >= maxImageNumber){
            findLargestImageAndDelete();
        }
        image = imageObject;
        setImageValues(file);
        images.add(image);
        image = null;

        return "/upload";
    }

    @RequestMapping(value = "/updateImageViewCount")
    public String updateImageViewCount(@RequestParam(value = "imageId") String imageId, @RequestParam(value = "imageViewCount") Integer imageViewCount){
        images.stream().filter(image -> image.getImageId().equals(imageId)).findFirst().get().setViewCount(imageViewCount);
        return "/images";
    }

    private void sortingImages() {
        images = images.stream()
                .sorted(Comparator.comparing(Image::getDate).reversed().thenComparing(Image::getTime))
                .collect(Collectors.toList());
        logger.info("image objects were sorted");
    }

    private void setImageValues(MultipartFile file) {
        image.setName(file.getOriginalFilename());
        try {
            image.setBase64ImageFile(Base64.getEncoder().encode(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setDate(LocalDate.now());
        image.setTime(LocalTime.now());
        image.setSize(file.getSize());
        image.setUrl("data:image/jpg;base64," + image.getImageString());
        image.setImageId(UUID.randomUUID().toString());
        logger.info("image has id "+image.getImageId()+" that values were set.");

    }

    private void findLargestImageAndDelete(){
        images = images.stream()
                .sorted(Comparator.comparing(Image::getSize).reversed())
                .collect(Collectors.toList());
        Image i = images.remove(0);
        logger.info("largest image was deleted " + i.getImageId() + " " + i.getName());
    }



}
