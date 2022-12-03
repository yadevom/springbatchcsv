CREATE TABLE otorgamiento (
    oagiId bigint NOT NULL,
    escrowNumber bigint default NULL,
    escrowApplicationNumber bigint default NULL,
    nameThirdParty varchar(255),
    nit varchar(255),
    idtyId bigint default NULL,
    thptId bigint default NULL,
    grantNumber varchar(255),
    PRIMARY KEY (`oagiId`)
) AUTO_INCREMENT=1;


CREATE TABLE CREDIFICAR_BATCH.OTORGAMIENTO (
    OAIGI_ID NUMERIC(8,0) NOT NULL,
    ESCROW_NUMBER NUMERIC(12,0),
    ESCROW_APPLICATION_NUMBER NUMERIC(12,0),
    NAME_THIRD_PARTY VARCHAR(50),
    NIT VARCHAR(50),
    IDTY_ID NUMERIC(8,0),
    THPT_ID NUMERIC(8,0),
    GRANT_NUMBER VARCHAR(50),
    CONSTRAINT PK_OTORGAMIENTO PRIMARY KEY (OAIGI_ID)
);