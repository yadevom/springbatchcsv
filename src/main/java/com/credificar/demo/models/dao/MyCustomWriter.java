package com.credificar.demo.models.dao;

import java.util.List;

import com.credificar.demo.models.entities.Otorgamiento;
import com.credificar.demo.repositories.OtorgamientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * La interfaz ItemWriter se utiliza para guardar datos de un archivo CSV en una base de datos relacional como MySQL.
 * Los datos serán almacenados por ItemWriter en el Repositorio JPA.
 * El repositorio JPA almacena datos en la base de datos.
 */
@Slf4j
@Component
public class MyCustomWriter implements ItemWriter<Otorgamiento> {

    @Autowired
    OtorgamientoRepository otorgamientoRepository;

    @Override
    public void write(List<? extends Otorgamiento> list) throws Exception {
        for (Otorgamiento data : list) {
            log.info(
                    "MyCustomWriter : Writing data : "
                            + data.getOagiId()+" : "
                            + data.getEscrowNumber()+" : "
                            + data.getEscrowApplicationNumber()+" : "
                            + data.getNameThirdParty()+" : "
                            + data.getNit()+" : "
                            + data.getIdtyId()+" : "
                            + data.getThptId()+" : "
                            + data.getGrantNumber()
            );
            otorgamientoRepository.save(data);
        }
    }
}
