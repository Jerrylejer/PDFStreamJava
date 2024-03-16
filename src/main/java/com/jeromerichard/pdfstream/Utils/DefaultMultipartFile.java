package com.jeromerichard.pdfstream.Utils;

import jakarta.persistence.Lob;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class DefaultMultipartFile implements MultipartFile {
    private final String filename;
    private final String originalFilename;
    private final String contentType;
    @Lob
    private final byte[] bytes;

    // Class permettant d'instancier un objet de type MultipartFile (ici utilisé pour ma photo par défaut)
    public DefaultMultipartFile(String filename, String originalFilename, String contentType, byte[] bytes) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.bytes = bytes;
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public long getSize() {
        return 0;
    }
    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }
    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }
}
