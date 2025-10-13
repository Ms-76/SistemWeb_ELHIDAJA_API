package com.elhidaja.apiselhidaja.configuration.deserializadorEspacios;

import com.elhidaja.apiselhidaja.util.deserializador.EliminarEspaciosInicioFInal;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class JaksonConfig {

      @Bean
    public Jackson2ObjectMapperBuilderCustomizer customStringDeserializer() {
        return builder -> {
            SimpleModule stringModule = new SimpleModule();
            stringModule.addDeserializer(String.class, new EliminarEspaciosInicioFInal());

            builder.modules(stringModule, new JavaTimeModule());
         
            builder.featuresToDisable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
  /**
   * @Bean
   *       public Jackson2ObjectMapperBuilderCustomizer customStringDeserializer()
   *       {
   *       return builder -> {
   *       SimpleModule module = new SimpleModule();
   *       module.addDeserializer(String.class, new
   *       EliminarEspaciosInicioFInal());
   *       builder.modules(module);
   *       };
   *       }
   */
  /**
   * @Bean
   *       public ObjectMapper objectMapper() {
   *       ObjectMapper mapper = new ObjectMapper();
   * 
   *       SimpleModule module = new SimpleModule();
   *       module.addDeserializer(String.class, new
   *       EliminarEspaciosInicioFInal());
   *       mapper.registerModule(module);
   *       return mapper;
   *       }
   */
  /**
   * @PostConstruct
   *                public void setUp() {
   *                SimpleModule module = new SimpleModule();
   *                module.addDeserializer(String.class, new
   *                EliminarEspaciosInicioFInal());
   *                objectMapper.registerModule(module);
   *                }
   * 
   */

}
