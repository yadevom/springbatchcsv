package com.credificar.demo.models.dao;

import com.credificar.demo.models.entities.Otorgamiento;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Component;

/**
 * La clase FlatFileItemReader se amplía para implementar la interfaz ItemReader.
 * FlatFileItemReader es una clase que lee un archivo CSV y lo convierte en un objeto Java Bean.
 * La interfaz de ItemReader ayuda a leer los datos de un archivo CSV.
 */
@Component
public class MyCustomReader  extends FlatFileItemReader<Otorgamiento> implements ItemReader<Otorgamiento> {

    public MyCustomReader() {
//		setResource(new FileSystemResource("data/input.csv"));
        setLinesToSkip(1);
        setLineMapper(getDefaultLineMapper());
    }

    public DefaultLineMapper<Otorgamiento> getDefaultLineMapper() {
        DefaultLineMapper<Otorgamiento> defaultLineMapper = new DefaultLineMapper<Otorgamiento>();

        DelimitedLineTokenizer delimitedLineTokenizer =new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[] { "oagiId", "escrowNumber", "escrowApplicationNumber", "nameThirdParty", "nit", "idtyId", "thptId", "grantNumber" });
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        BeanWrapperFieldSetMapper<Otorgamiento> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<Otorgamiento>();
        beanWrapperFieldSetMapper.setTargetType(Otorgamiento.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        return defaultLineMapper;
    }
}
