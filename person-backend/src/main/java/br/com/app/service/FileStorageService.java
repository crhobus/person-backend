package br.com.app.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.config.FileStorageConfig;
import br.com.app.exception.FileNotFoundException;
import br.com.app.exception.FileStorageException;

@Service
public class FileStorageService {

    private final Path fileStorageLocation; // Local de armazenamento dos arquivos

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize(); // Obtém o caminho do diretório definido no application.properties
        try {
            Files.createDirectories(this.fileStorageLocation); // Cria o diretório se não existir
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalida path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName); // Define o caminho do arquivo no destino
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
            throw new FileNotFoundException("File not found " + fileName, e);
        }

    }
}
