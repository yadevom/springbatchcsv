package com.credificar.demo.config;

import com.credificar.demo.models.dao.MyCustomReader;
import com.credificar.demo.models.dao.MyCustomWriter;
import com.credificar.demo.models.entities.Otorgamiento;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * El archivo de configuración por lotes Spring Boot almacena las configuraciones del trabajo por lotes
 * y del paso por lotes.
 * Un archivo csv se lee y analiza usando la clase FlatFileItemReader.
 * Los nombres de las columnas y el número de líneas que se saltan se especifican en la clase FlatFileItemReader.
 * El MultiResourceItemReader se usa en el lote de spring boot para construir un ItemReader.
 * MultiResourceItemReader lee todos los archivos csv en un directorio determinado y
 * proporciona pasos por lotes para ejecutarlos.
 *
 * El paso Spring Boot recibe datos de archivos CSV y los convierte en un objeto Java Bean mediante la interfaz ItemReader.
 * La interfaz ItemProcessor se utiliza para procesar el objeto Java Bean.
 * El objeto java bean se guarda en la base de datos MySQL utilizando el repositorio JPA en la interfaz ItemWriter.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    MyCustomReader myCustomReader;

    @Autowired
    MyCustomWriter myCustomWriter;


    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("MyJob")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("MyStep")
                .<Otorgamiento, Otorgamiento> chunk(1)
                .reader(reader())
                .writer(myCustomWriter)
                .build();
    }

    @Bean
    public ItemReader<Otorgamiento> reader() {
        Resource[] resources = null;
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            resources = patternResolver.getResources("data/*.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultiResourceItemReader<Otorgamiento> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(myCustomReader);
        return reader;
    }

}
