package com.credificar.demo.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * En este ejemplo, se crea la clase del modelo de datos del bean Java.
 * La clase de otorgamiento tiene campos para identificación y otros.
 * A la clase Otorgamiento se le asignarán los datos del archivo csv de ejemplo.
 * El  spring boot batch lee datos de un archivo csv y lo asigna a la clase Otorgamiento.
 */
@Entity
@Getter
@Setter
public class Otorgamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long oagiId;
    private Long escrowNumber;
    private Long escrowApplicationNumber;
    private String nameThirdParty;
    private String nit;
    private Long idtyId;
    private Long thptId;
    private String grantNumber;
}
