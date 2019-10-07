package com.example.spe_logistic.utilities;

public class Utilities {

    // ESTADOS_INVENTARIO TABLE
    public static final String ESTADOS_INVENTARIO        = "estados_inventario";
    public static final String ESTADOS_INVENTARIO_ID     = "id";
    public static final String ESTADOS_INVENTARIO_NOMBRE = "nombre";
    public static final String DROP_ESTADOS_INVENTARIO   = "DROP TABLE IF EXISTS "+ESTADOS_INVENTARIO;
    public static final String CREATE_ESTADOS_INVENTARIO = "CREATE TABLE "+ESTADOS_INVENTARIO+" ("+ ESTADOS_INVENTARIO_ID+" INTEGER PRIMATY KEY, "+
                                                                                                    ESTADOS_INVENTARIO_NOMBRE+" TEXT)";
    //public static final String ESTADOS_INVENTARIO_REGS = "INSERT INTO "+ESTADOS_INVENTARIO+ " ('nombre') SELECT 'Disponible' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_INVENTARIO+" WHERE nombre = 'Disponible');"+
    //                                                     "INSERT INTO "+ESTADOS_INVENTARIO+ " ('nombre') SELECT 'Reservado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_INVENTARIO+" WHERE nombre = 'Reservado');"+
    //                                                     "INSERT INTO "+ESTADOS_INVENTARIO+ " ('nombre') SELECT 'Alistado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_INVENTARIO+" WHERE nombre = 'Alistado');"+
    //                                                     "INSERT INTO "+ESTADOS_INVENTARIO+ " ('nombre') SELECT 'Despachado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_INVENTARIO+" WHERE nombre = 'Despachado');";

    // DESPACHOS TABLE
    public static final String DESPACHOS        = "despachos";
    public static final String DESPACHOS_ID     = "id";
    public static final String DESPACHOS_FECHA  = "fecha";
    public static final String DESPACHOS_GUIA   = "guia";
    public static final String DESPACHOS_PESO   = "peso";
    public static final String DESPACHOS_CAJAS  = "cajas";
    public static final String DROP_DESPACHOS   = "DROP TABLE IF EXISTS "+DESPACHOS;
    public static final String CREATE_DESPACHOS = "CREATE TABLE "+DESPACHOS+" ("+   DESPACHOS_ID+" INTEGER PRIMATY KEY, "+
                                                                                    DESPACHOS_FECHA+" TEXT, "+
                                                                                    DESPACHOS_GUIA+" TEXT, "+
                                                                                    DESPACHOS_PESO+" INTEGER, "+
                                                                                    DESPACHOS_CAJAS+"INTEGER)";

    // ENVIOS TABLE
    public static final String ENVIOS                        = "envios";
    public static final String ENVIOS_ID                     = "id";
    public static final String ENVIOS_NOMBRE_DESTINATARIO    = "nombre_destinatario";
    public static final String ENVIOS_DIRECCION_DESTINATARIO = "direccion_destinatario";
    public static final String ENVIOS_CIUDAD_DESTINATARIO_ID = "ciudad_destinatario_id";
    public static final String ENVIOS_TELEFONO_DESTINATARIO  = "telefono_destinatario";
    public static final String ENVIOS_EMAIL_DESTINATARIO     = "email_destinatario";
    public static final String ENVIOS_FACTURA                = "factura";
    public static final String ENVIOS_ARCHIVO                = "archivo";
    public static final String ENVIOS_DESPACHO_ID            = "despacho_id";
    public static final String ENVIOS_FECHA_ALISTADO         = "fecha_alistado";
    public static final String ENVIOS_FECHA_EMPACADO         = "fecha_empacado";
    public static final String ENVIOS_CLIENTE_ID             = "cliente_id";
    public static final String ENVIOS_ESTADO_ID              = "estado_id";
    public static final String DROP_ENVIOS                   = "DROP TABLE IF EXISTS "+ENVIOS;
    public static final String CREATE_ENVIOS                 = "CREATE TABLE "+ENVIOS+" ("+ ENVIOS_ID+" INTEGER PRIMATY KEY, "+
                                                                                            ENVIOS_NOMBRE_DESTINATARIO+" TEXT, "+
                                                                                            ENVIOS_DIRECCION_DESTINATARIO+" TEXT, "+
                                                                                            ENVIOS_CIUDAD_DESTINATARIO_ID+" INTEGER, "+
                                                                                            ENVIOS_TELEFONO_DESTINATARIO+" TEXT, "+
                                                                                            ENVIOS_EMAIL_DESTINATARIO+" TEXT, "+
                                                                                            ENVIOS_FACTURA+" TEXT, "+
                                                                                            ENVIOS_ARCHIVO+" TEXT, "+
                                                                                            ENVIOS_DESPACHO_ID+" INTEGER, "+
                                                                                            ENVIOS_FECHA_ALISTADO+" TEXT, "+
                                                                                            ENVIOS_FECHA_EMPACADO+" TEXT, "+
                                                                                            ENVIOS_CLIENTE_ID+" INTEGER, "+
                                                                                            ENVIOS_ESTADO_ID+"INTEGER)";

    // ESTADOS_ENVIOS TABLE
    public static final String ESTADOS_ENVIOS        = "estados_envios";
    public static final String ESTADOS_ENVIOS_ID     = "id";
    public static final String ESTADOS_ENVIOS_NOMBRE = "nombre";
    public static final String DROP_ESTADOS_ENVIOS   = "DROP TABLE IF EXISTS "+ESTADOS_ENVIOS;
    public static final String CREATE_ESTADOS_ENVIOS = "CREATE TABLE "+ESTADOS_ENVIOS+" ("+ ESTADOS_ENVIOS_ID+" INTEGER PRIMATY KEY, "+
                                                                                            ESTADOS_ENVIOS_NOMBRE+" TEXT)";
    //public static final String ESTADOS_ENVIOS_REGS = "INSERT INTO "+ESTADOS_ENVIOS+ " ('nombre') SELECT 'Reservado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_ENVIOS+" WHERE nombre = 'Reservado');"+
    //                                                 "INSERT INTO "+ESTADOS_ENVIOS+ " ('nombre') SELECT 'Alistado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_ENVIOS+" WHERE nombre = 'Alistado');"+
    //                                                 "INSERT INTO "+ESTADOS_ENVIOS+ " ('nombre') SELECT 'Despachado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_ENVIOS+" WHERE nombre = 'Despachado');";



    // HISTORIAL_INVENTARIO TABLE
    public static final String HISTORIAL_INVENTARIO               = "historial_inventario";
    public static final String HISTORIAL_INVENTARIO_ID            = "id";
    public static final String HISTORIAL_INVENTARIO_INVENTARIO_ID = "inventario_id";
    public static final String HISTORIAL_INVENTARIO_DESCRIPCION   = "descripcion";
    public static final String DROP_HISTORIAL_INVENTARIO          = "DROP TABLE IF EXISTS "+HISTORIAL_INVENTARIO;
    public static final String CREATE_HISTORIAL_INVENTARIO        = "CREATE TABLE "+HISTORIAL_INVENTARIO+" ("+  HISTORIAL_INVENTARIO_ID+" INTEGER PRIMATY KEY, "+
                                                                                                                HISTORIAL_INVENTARIO_INVENTARIO_ID+" INTEGER, "+
                                                                                                                HISTORIAL_INVENTARIO_DESCRIPCION+" TEXT)";

    // INVENTARIO TABLE
    public static final String INVENTARIO               = "inventario";
    public static final String INVENTARIO_ID            = "id";
    public static final String INVENTARIO_REFERENCIA_ID = "referencia_id";
    public static final String INVENTARIO_ESTADO_ID     = "estado_id";
    public static final String INVENTARIO_POSICION      = "posicion";
    public static final String INVENTARIO_SERIAL        = "serial";
    public static final String INVENTARIO_FECHA_INGRESO = "fecha_ingreso";
    public static final String INVENTARIO_ENVIO_ID      = "envio_id";
    public static final String DROP_INVENTARIO          = "DROP TABLE IF EXISTS "+INVENTARIO;
    public static final String CREATE_INVENTARIO        = "CREATE TABLE "+INVENTARIO+" ("+  INVENTARIO_ID+" INTEGER PRIMATY KEY, "+
                                                                                            INVENTARIO_REFERENCIA_ID+" INTEGER, "+
                                                                                            INVENTARIO_ESTADO_ID+" INTEGER, "+
                                                                                            INVENTARIO_POSICION+" TEXT, "+
                                                                                            INVENTARIO_SERIAL+" TEXT, "+
                                                                                            INVENTARIO_FECHA_INGRESO+" TEXT, "+
                                                                                            INVENTARIO_ENVIO_ID+"INTEGER)";

    // HISTORIAL_ENVIOS TABLE
    public static final String HISTORIAL_ENVIOS             = "historial_envios";
    public static final String HISTORIAL_ENVIOS_ID          = "id";
    public static final String HISTORIAL_ENVIOS_FECHA       = "fecha";
    public static final String HISTORIAL_ENVIOS_DESCRIPCION = "descripcion";
    public static final String HISTORIAL_ENVIOS_ENVIO_ID    = "envio_id";
    public static final String DROP_HISTORIAL_ENVIOS        = "DROP TABLE IF EXISTS "+HISTORIAL_ENVIOS;
    public static final String CREATE_HISTORIAL_ENVIOS      = "CREATE TABLE "+HISTORIAL_ENVIOS+" ("+HISTORIAL_ENVIOS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                    HISTORIAL_ENVIOS_FECHA+" TEXT, "+
                                                                                                    HISTORIAL_ENVIOS_DESCRIPCION+" TEXT, "+
                                                                                                    HISTORIAL_ENVIOS_ENVIO_ID+"INTEGER)";

    // DETALLE_HISTORIAL_ENVIOS TABLE
    public static final String DETALLE_HISTORIAL_ENVIOS                     = "detalle_historial_envios";
    public static final String DETALLE_HISTORIAL_ENVIOS_ID                  = "id";
    public static final String DETALLE_HISTORIAL_ENVIOS_HISTORIAL_ENVIOS_ID = "historial_envios_id";
    public static final String DETALLE_HISTORIAL_ENVIOS_REFERENCIA_ID       = "referencia_id";
    public static final String DETALLE_HISTORIAL_ENVIOS_DESCRIPCION         = "descripcion";
    public static final String DROP_DETALLE_HISTORIAL_ENVIOS                = "DROP TABLE IF EXISTS "+DETALLE_HISTORIAL_ENVIOS;
    public static final String CREATE_DETALLE_HISTORIAL_ENVIOS              = "CREATE TABLE "+  DETALLE_HISTORIAL_ENVIOS+" ("+DETALLE_HISTORIAL_ENVIOS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                DETALLE_HISTORIAL_ENVIOS_HISTORIAL_ENVIOS_ID+" INTEGER, "+
                                                                                                DETALLE_HISTORIAL_ENVIOS_REFERENCIA_ID+" INTEGER, "+
                                                                                                DETALLE_HISTORIAL_ENVIOS_DESCRIPCION+"TEXT)";

    // CIUDADES TABLE
    public static final String CIUDADES        = "ciudades";
    public static final String CIUDADES_ID     = "id";
    public static final String CIUDADES_NOMBRE = "nombre";
    public static final String DROP_CIUDADES   = "DROP TABLE IF EXISTS "+CIUDADES;
    public static final String CREATE_CIUDADES = "CREATE TABLE "+CIUDADES+" ("+ CIUDADES_ID+" INTEGER PRIMATY KEY, "+
                                                                                CIUDADES_NOMBRE+" TEXT)";
    //public static final String CIUDADES_REGS = "INSERT INTO "+CIUDADES+ " ('nombre') SELECT 'Sabaneta' WHERE NOT EXISTS (SELECT 1 FROM "+ CIUDADES+" WHERE nombre = 'Sabaneta');"+
    //                                           "INSERT INTO "+CIUDADES+ " ('nombre') SELECT 'Medellin' WHERE NOT EXISTS (SELECT 1 FROM "+ CIUDADES+" WHERE nombre = 'Medellin');"+
    //                                           "INSERT INTO "+CIUDADES+ " ('nombre') SELECT 'Bello' WHERE NOT EXISTS (SELECT 1 FROM "+ CIUDADES+" WHERE nombre = 'Bello');";




    // REFERENCIAS TABLE
    public static final String REFERENCIAS                  = "referencias";
    public static final String REFERENCIAS_ID               = "id";
    public static final String REFERENCIAS_NOMBRE           = "nombre";
    public static final String REFERENCIAS_VALOR            = "valor";
    public static final String REFERENCIAS_CODIGO_BARRAS    = "codigo_barras";
    public static final String REFERENCIAS_UNIDADES_EMPAQUE = "unidades_empaque";
    public static final String REFERENCIAS_CLIENTE_ID       = "cliente_id";
    public static final String DROP_REFERENCIAS             = "DROP TABLE IF EXISTS "+REFERENCIAS;
    public static final String CREATE_REFERENCIAS           = "CREATE TABLE "+REFERENCIAS+" ("+ REFERENCIAS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                REFERENCIAS_NOMBRE+" TEXT, "+
                                                                                                REFERENCIAS_VALOR+" INTEGER, "+
                                                                                                REFERENCIAS_CODIGO_BARRAS+" TEXT, "+
                                                                                                REFERENCIAS_UNIDADES_EMPAQUE+" INTEGER, "+
                                                                                                REFERENCIAS_CLIENTE_ID+"INTEGER)";

    // CLIENTES TABLE
    public static final String CLIENTES                = "clientes";
    public static final String CLIENTES_ID             = "id";
    public static final String CLIENTES_RAZON_SOCIAL   = "razon_social";
    public static final String CLIENTES_NIT            = "nit";
    public static final String CLIENTES_PASSWORD       = "password";
    public static final String CLIENTES_DIRECCION_ID   = "direccion_id";
    public static final String DROP_CLIENTES           = "DROP TABLE IF EXISTS "+CLIENTES;
    public static final String CREATE_CLIENTES         = "CREATE TABLE "+CLIENTES+" ("+ CLIENTES_ID+" INTEGER PRIMATY KEY, "+
                                                                                        CLIENTES_RAZON_SOCIAL+" TEXT, "+
                                                                                        CLIENTES_NIT+" TEXT, "+
                                                                                        CLIENTES_PASSWORD+" TEXT, "+
                                                                                        CLIENTES_DIRECCION_ID+"INTEGER)";

    // DIRECCIONES TABLE
    public static final String DIRECCIONES                  = "direcciones";
    public static final String DIRECCIONES_ID               = "id";
    public static final String DIRECCIONES_TIPO_VIA_ID      = "tipo_via_id";
    public static final String DIRECCIONES_NUMERO_1         = "numero_1";
    public static final String DIRECCIONES_LETRA_1          = "letra_1";
    public static final String DIRECCIONES_ORIENTACION_1_ID = "orientacion_1_id";
    public static final String DIRECCIONES_NUMERO_2         = "numero_2";
    public static final String DIRECCIONES_LETRA_2          = "letra_2";
    public static final String DIRECCIONES_ORIENTACION_2_ID = "orientacion_2_id";
    public static final String DIRECCIONES_NUMERO_3         = "numero_3";
    public static final String DIRECCIONES_CIUDAD_ID        = "ciudad_id";
    public static final String DROP_DIRECCIONES             = "DROP TABLE IF EXISTS "+DIRECCIONES;
    public static final String CREATE_DIRECCIONES           = "CREATE TABLE "+DIRECCIONES+" ("+ DIRECCIONES_ID+" INTEGER PRIMATY KEY, "+
                                                                                                DIRECCIONES_TIPO_VIA_ID+" INTEGER, "+
                                                                                                DIRECCIONES_NUMERO_1+" INTEGER, "+
                                                                                                DIRECCIONES_LETRA_1+" TEXT, "+
                                                                                                DIRECCIONES_ORIENTACION_1_ID+" INTEGER, "+
                                                                                                DIRECCIONES_NUMERO_2+" INTEGER, "+
                                                                                                DIRECCIONES_LETRA_2+" TEXT, "+
                                                                                                DIRECCIONES_ORIENTACION_2_ID+" INTEGER, "+
                                                                                                DIRECCIONES_NUMERO_3+" INTEGER, "+
                                                                                                DIRECCIONES_CIUDAD_ID+"INTEGER)";

    // TIPOS_VIAS TABLE
    public static final String TIPOS_VIAS        = "tipos_vias";
    public static final String TIPOS_VIAS_ID     = "id";
    public static final String TIPOS_VIAS_NOMBRE = "nombre";
    public static final String DROP_TIPOS_VIAS   = "DROP TABLE IF EXISTS "+TIPOS_VIAS;
    public static final String CREATE_TIPOS_VIAS = "CREATE TABLE "+TIPOS_VIAS+" ("+ TIPOS_VIAS_ID+" INTEGER PRIMATY KEY, "+
                                                                                    TIPOS_VIAS_NOMBRE+" TEXT)";
    //public static final String TIPOS_VIAS_REGS = "INSERT INTO "+TIPOS_VIAS+ "(nombre) values ('Calle')";

    // ESTADOS_RECOGIDAS TABLE
    public static final String ESTADOS_RECOGIDAS        = "estados_recogidas";
    public static final String ESTADOS_RECOGIDAS_ID     = "id";
    public static final String ESTADOS_RECOGIDAS_NOMBRE = "nombre";
    public static final String DROP_ESTADOS_RECOGIDAS   = "DROP TABLE IF EXISTS "+ESTADOS_RECOGIDAS;
    public static final String CREATE_ESTADOS_RECOGIDAS = "CREATE TABLE "+ESTADOS_RECOGIDAS+" ("+   ESTADOS_RECOGIDAS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                    ESTADOS_RECOGIDAS_NOMBRE+" TEXT)";
    //public static final String ESTADOS_RECOGIDAS_REGS = "INSERT INTO "+ESTADOS_RECOGIDAS+ " ('nombre') SELECT 'Pendiente' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_RECOGIDAS+" WHERE nombre = 'Pendiente');"+
    //                                                    "INSERT INTO "+ESTADOS_RECOGIDAS+ " ('nombre') SELECT 'En camino' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_RECOGIDAS+" WHERE nombre = 'En camino');"+
    //                                                    "INSERT INTO "+ESTADOS_RECOGIDAS+ " ('nombre') SELECT 'Recogido' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_RECOGIDAS+" WHERE nombre = 'Recogido');"+
    //                                                    "INSERT INTO "+ESTADOS_RECOGIDAS+ " ('nombre') SELECT 'Cancelado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_RECOGIDAS+" WHERE nombre = 'Cancelado');";




    // HISTORIAL_REFERENCIAS TABLE
    public static final String HISTORIAL_REFERENCIAS               = "historial_referencias";
    public static final String HISTORIAL_REFERENCIAS_ID            = "id";
    public static final String HISTORIAL_REFERENCIAS_FECHA         = "fecha";
    public static final String HISTORIAL_REFERENCIAS_REFERENCIA_ID = "referencia_id";
    public static final String HISTORIAL_REFERENCIAS_DESCRIPCION   = "descripcion";
    public static final String DROP_HISTORIAL_REFERENCIAS          = "DROP TABLE IF EXISTS "+HISTORIAL_REFERENCIAS;
    public static final String CREATE_HISTORIAL_REFERENCIAS        = "CREATE TABLE "+HISTORIAL_REFERENCIAS+" ("+HISTORIAL_REFERENCIAS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                                HISTORIAL_REFERENCIAS_FECHA+" TEXT, "+
                                                                                                                HISTORIAL_REFERENCIAS_REFERENCIA_ID+" INTEGER, "+
                                                                                                                HISTORIAL_REFERENCIAS_DESCRIPCION+"TEXT)";

    // PQRS TABLE
    public static final String PQRS              = "PQRS";
    public static final String PQRS_ID           = "id";
    public static final String PQRS_FECHA        = "fecha";
    public static final String PQRS_CLIENTE_ID   = "cliente_id";
    public static final String PQRS_DESCRIPCION  = "descripcion";
    public static final String PQRS_CATEGORIA_ID = "categoria_id";
    public static final String PQRS_ESTADO_ID    = "estado_id";
    public static final String DROP_PQRS         = "DROP TABLE IF EXISTS "+PQRS;
    public static final String CREATE_PQRS       = "CREATE TABLE "+PQRS+" ("+   PQRS_ID+" INTEGER PRIMATY KEY, "+
                                                                                PQRS_FECHA+" TEXT, "+
                                                                                PQRS_CLIENTE_ID+" INTEGER, "+
                                                                                PQRS_DESCRIPCION+" TEXT, "+
                                                                                PQRS_CATEGORIA_ID+" INTEGER, "+
                                                                                PQRS_ESTADO_ID+"INTEGER)";

    // CATEGORIAS_PQRS TABLE
    public static final String CATEGORIAS_PQRS        = "categorias_pqrs";
    public static final String CATEGORIAS_PQRS_ID     = "id";
    public static final String CATEGORIAS_PQRS_NOMBRE = "nombre";
    public static final String DROP_CATEGORIAS_PQRS   = "DROP TABLE IF EXISTS "+CATEGORIAS_PQRS;
    public static final String CREATE_CATEGORIAS_PQRS = "CREATE TABLE "+CATEGORIAS_PQRS+" ("+   CATEGORIAS_PQRS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                CATEGORIAS_PQRS_NOMBRE+" TEXT)";
    //public static final String CATEGORIAS_PQRS_REGS = "INSERT INTO "+CATEGORIAS_PQRS+ " ('nombre') SELECT 'Peticion' WHERE NOT EXISTS (SELECT 1 FROM "+ CATEGORIAS_PQRS+" WHERE nombre = 'Peticion');"+
    //                                                  "INSERT INTO "+CATEGORIAS_PQRS+ " ('nombre') SELECT 'Queja' WHERE NOT EXISTS (SELECT 1 FROM "+ CATEGORIAS_PQRS+" WHERE nombre = 'Queja');"+
    //                                                  "INSERT INTO "+CATEGORIAS_PQRS+ " ('nombre') SELECT 'Felicitacion' WHERE NOT EXISTS (SELECT 1 FROM "+ CATEGORIAS_PQRS+" WHERE nombre = 'Felicitacion');";

    // ESTADOS_PQRS TABLE
    public static final String ESTADOS_PQRS        = "estados_pqrs";
    public static final String ESTADOS_PQRS_ID     = "id";
    public static final String ESTADOS_PQRS_NOMBRE = "nombre";
    public static final String DROP_ESTADOS_PQRS   = "DROP TABLE IF EXISTS "+ESTADOS_PQRS;
    public static final String CREATE_ESTADOS_PQRS = "CREATE TABLE "+ESTADOS_PQRS+" ("+ ESTADOS_PQRS_ID+" INTEGER PRIMATY KEY, "+
                                                                                        ESTADOS_PQRS_NOMBRE+" TEXT)";
    //public static final String ESTADOS_PQRS_REGS = "INSERT INTO "+ESTADOS_PQRS+ " ('nombre') SELECT 'Nuevo' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_PQRS+" WHERE nombre = 'Nuevo');"+
    //                                               "INSERT INTO "+ESTADOS_PQRS+ " ('nombre') SELECT 'Procesado' WHERE NOT EXISTS (SELECT 1 FROM "+ ESTADOS_PQRS+" WHERE nombre = 'Procesado');";

    // RECOGIDAS TABLE
    public static final String RECOGIDAS                       = "recogidas";
    public static final String RECOGIDAS_ID                    = "id";
    public static final String RECOGIDAS_FECHA                 = "fecha";
    public static final String RECOGIDAS_CLIENTE_ID            = "cliente_id";
    public static final String RECOGIDAS_CANTIDAD_CAJAS        = "cantidad_cajas";
    public static final String RECOGIDAS_ALTO_CAJA             = "alto_caja";
    public static final String RECOGIDAS_ANCHO_CAJA            = "ancho_caja";
    public static final String RECOGIDAS_LARGO_CAJA            = "largo_caja";
    public static final String RECOGIDAS_PESO                  = "peso";
    public static final String RECOGIDAS_DESCRIPCION_CONTENIDO = "descripcion_contenido";
    public static final String RECOGIDAS_VALOR_DECLARADO       = "valor_declarado";
    public static final String RECOGIDAS_ESTADO_ID             = "estado_id";
    public static final String DROP_RECOGIDAS                  = "DROP TABLE IF EXISTS "+RECOGIDAS;
    public static final String CREATE_RECOGIDAS                = "CREATE TABLE "+RECOGIDAS+" ("+RECOGIDAS_ID+" INTEGER PRIMATY KEY, "+
                                                                                                RECOGIDAS_FECHA+" TEXT, "+
                                                                                                RECOGIDAS_CLIENTE_ID+" INTEGER, "+
                                                                                                RECOGIDAS_CANTIDAD_CAJAS+" INTEGER, "+
                                                                                                RECOGIDAS_ALTO_CAJA+" INTEGER, "+
                                                                                                RECOGIDAS_ANCHO_CAJA+" INTEGER, "+
                                                                                                RECOGIDAS_LARGO_CAJA+" INTEGER, "+
                                                                                                RECOGIDAS_PESO+" INTEGER, "+
                                                                                                RECOGIDAS_DESCRIPCION_CONTENIDO+" TEXT, "+
                                                                                                RECOGIDAS_VALOR_DECLARADO+" INTEGER, "+
                                                                                                RECOGIDAS_ESTADO_ID+"INTEGER)";
}
