package br.com.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file") // Irá setar o diretório definido (properties: file.upload-dir) no atributo uploadDir. Atributo prefix significa: ler somente propriedades que começa com file.
public class FileStorageConfig {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
