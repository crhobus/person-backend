package br.com.app.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/*
 * Conversor para aplicação suportar yaml
 */
public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper(), MediaType.parseMediaType("application/x-yaml")); // Quando chegar a extensão no cabeçalho, a aplicação já irá processar esse tipo de media type.
    }

}
