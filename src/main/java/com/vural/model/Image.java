package com.vural.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Created by vural on 27-Apr-17.
 */
public class Image implements Serializable {

    private String imageId;
    private String name;
    private String description;
    private byte[] base64ImageFile;
    private String imageString;
    private LocalDate date;
    private LocalTime time;
    private Long size;
    private int viewCount;
    private String url;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getBase64ImageFile() {
        return base64ImageFile;
    }

    public void setBase64ImageFile(byte[] base64ImageFile) throws UnsupportedEncodingException {
        this.base64ImageFile = base64ImageFile;
        imageString = new String(base64ImageFile, "UTF-8");
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
