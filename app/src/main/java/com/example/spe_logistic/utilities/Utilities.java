package com.example.spe_logistic.utilities;

public class Utilities {

    // REFERENCIAS TABLE
    public static final String REFERENCIAS                  = "referencias";
    public static final String REFERENCIAS_ID               = "id";
    public static final String REFERENCIAS_NOMBRE           = "nombre";
    public static final String REFERENCIAS_VALOR            = "valor";
    public static final String REFERENCIAS_CODIGO_BARRAS    = "codigo_barras";
    public static final String REFERENCIAS_UNIDADES_EMPAQUE = "unidades_empaque";
    public static final String REFERENCIAS_CLIENTE_ID       = "cliente_id";

    // HISTORIAL_REFERENCIAS TABLE
    public static final String HISTORIAL_REFERENCIAS               = "historial_referencias";
    public static final String HISTORIAL_REFERENCIAS_ID            = "id";
    public static final String HISTORIAL_REFERENCIAS_FECHA         = "fecha";
    public static final String HISTORIAL_REFERENCIAS_REFERENCIA_ID = "referencia_id";
    public static final String HISTORIAL_REFERENCIAS_DESCRIPCION   = "descripcion";

    // CLIENTES TABLE
    public static final String CLIENTES                = "clientes";
    public static final String CLIENTES_ID             = "id";
    public static final String CLIENTES_RAZON_SOCIAL   = "razon_social";
    public static final String CLIENTES_NIT            = "nit";
    public static final String CLIENTES_DIRECCIONES_ID = "direccion_id";

    // PQRS TABLE
    public static final String PQRS              = "PQRS";
    public static final String PQRS_ID           = "id";
    public static final String PQRS_FECHA        = "fecha";
    public static final String PQRS_CLIENTE_ID   = "cliente_id";
    public static final String PQRS_DESCRIPCION  = "descripcion";
    public static final String PQRS_CATEGORIA_ID = "categoria_id";
    public static final String PQRS_ESTADO_ID    = "estado_id";

    // CATEGORIAS_PQRS TABLE
    public static final String CATEGORIAS_PQRS             = "categorias_pqrs";
    public static final String CATEGORIAS_PQRS_ID          = "id";
    public static final String CATEGORIAS_PQRS_DESCRIPCION = "nombre";

    // ESTADOS_PQRS TABLE
    public static final String ESTADOS_PQRS        = "estados_pqrs";
    public static final String ESTADOS_PQRS_ID     = "id";
    public static final String ESTADOS_PQRS_NOMBRE = "nombre";

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

    // CIUDADES TABLE
    public static final String CIUDADES        = "ciudades";
    public static final String CIUDADES_ID     = "id";
    public static final String CIUDADES_NOMBRE = "nombre";

    // TIPOS_VIAS TABLE
    public static final String TIPOS_VIAS        = "tipos_vias";
    public static final String TIPOS_VIAS_ID     = "id";
    public static final String TIPOS_VIAS_NOMBRE = "nombre";

    // INVENTARIO TABLE
    public static final String INVENTARIO               = "inventario";
    public static final String INVENTARIO_ID            = "id";
    public static final String INVENTARIO_REFERENCIA_ID = "referencia_id";
    public static final String INVENTARIO_ESTADO_ID     = "estado_id";
    public static final String INVENTARIO_POSICION      = "posicion";
    public static final String INVENTARIO_SERIAL        = "serial";
    public static final String INVENTARIO_FECHA_INGRESO = "fecha_ingreso";
    public static final String INVENTARIO_ENVIO_ID      = "envio_id";

    // HISTORIAL_INVENTARIO TABLE
    public static final String HISTORIAL_INVENTARIO               = "historial_inventario";
    public static final String HISTORIAL_INVENTARIO_ID            = "id";
    public static final String HISTORIAL_INVENTARIO_INVENTARIO_ID = "inventario_id";
    public static final String HISTORIAL_INVENTARIO_CAMBIO_ID     = "cambio_id";

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
    public static final String ENVIOS_FECHA_DESPACHADO       = "fecha_despachado";
    public static final String ENVIOS_CLIENTE_ID             = "cliente_id";

    // HISTORIAL_ENVIOS TABLE
    public static final String HISTORIAL_ENVIOS             = "historial_envios";
    public static final String HISTORIAL_ENVIOS_ID          = "id";
    public static final String HISTORIAL_ENVIOS_FECHA       = "fecha";
    public static final String HISTORIAL_ENVIOS_DESCRIPCION = "descripcion";
    public static final String HISTORIAL_ENVIOS_ENVIO_ID    = "envio_id";

    public static final String HISTORIAL_ENVIOS_ENVIO_ID2    = "envio_id";


}
