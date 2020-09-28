package br.com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.app.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({ //
        FileStorageConfig.class // Quando inicializar a aplicação, será lido as informações do application.properties e será armazenado na classe FileStorageConfig
})
@EnableAutoConfiguration // Uma das principais características dessa annotation, é permitir que o Application Context do Spring seja automaticamente carregado baseado nos jars e nas configurações que definimos. Ela sempre é feita depois que os beans já foram registrados no Application Context. A grande vantagem é diminuir a responsabilidade na definição das configurações.
@ComponentScan // É usada para dizer ao SpringBoot que ele deve escanear os pacotes para encontrar arquivos de configuração. Ex: WebCongig criado nesse projeto
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        //        String result = bCryptPasswordEncoder.encode("admin123");
        //        System.out.println("My hash:" + result);
    }

}
