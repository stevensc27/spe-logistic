package com.example.spe_logistic.utilities;

public class Utilities {

    // ESTADOS_INVENTARIO TABLE
    public static final String ESTADOS_INVENTARIO        = "estados_inventario";
    public static final String ESTADOS_INVENTARIO_ID     = "id";
    public static final String ESTADOS_INVENTARIO_NOMBRE = "nombre";

    // DESPACHOS TABLE
    public static final String DESPACHOS       = "despachos";
    public static final String DESPACHOS_ID    = "id";
    public static final String DESPACHOS_FECHA = "fecha";
    public static final String DESPACHOS_GUIA  = "guia";
    public static final String DESPACHOS_PESO  = "peso";
    public static final String DESPACHOS_CAJAS = "cajas";

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
    public static final String ENVIOS_ESTADO_ID              = "estado_id";

    // ESTADOS_ENVIOS TABLE
    public static final String ESTADOS_ENVIOS        = "estados_envios";
    public static final String ESTADOS_ENVIOS_ID     = "id";
    public static final String ESTADOS_ENVIOS_NOMBRE = "nombre";




    // CAMBIOS TABLE
    public static final String CAMBIOS        = "cambios";
    public static final String CAMBIOS_ID     = "id";
    public static final String CAMBIOS_NOMBRE = "nombre";

    // HISTORIAL_INVENTARIO TABLE
    public static final String HISTORIAL_INVENTARIO               = "historial_inventario";
    public static final String HISTORIAL_INVENTARIO_ID            = "id";
    public static final String HISTORIAL_INVENTARIO_INVENTARIO_ID = "inventario_id";
    public static final String HISTORIAL_INVENTARIO_CAMBIO_ID     = "cambio_id";

    // INVENTARIO TABLE
    public static final String INVENTARIO               = "inventario";
    public static final String INVENTARIO_ID            = "id";
    public static final String INVENTARIO_REFERENCIA_ID = "referencia_id";
    public static final String INVENTARIO_ESTADO_ID     = "estado_id";
    public static final String INVENTARIO_POSICION      = "posicion";
    public static final String INVENTARIO_SERIAL        = "serial";
    public static final String INVENTARIO_FECHA_INGRESO = "fecha_ingreso";
    public static final String INVENTARIO_ENVIO_ID      = "envio_id";

    // HISTORIAL_ENVIOS TABLE
    public static final String HISTORIAL_ENVIOS             = "historial_envios";
    public static final String HISTORIAL_ENVIOS_ID          = "id";
    public static final String HISTORIAL_ENVIOS_FECHA       = "fecha";
    public static final String HISTORIAL_ENVIOS_DESCRIPCION = "descripcion";
    public static final String HISTORIAL_ENVIOS_ENVIO_ID    = "envio_id";

    // DETALLE_HISTORIAL_ENVIOS TABLE
    public static final String DETALLE_HISTORIAL_ENVIOS                     = "detalle_historial_envios";
    public static final String DETALLE_HISTORIAL_ENVIOS_ID                  = "id";
    public static final String DETALLE_HISTORIAL_ENVIOS_HISTORIAL_ENVIOS_ID = "historial_envios_id";
    public static final String DETALLE_HISTORIAL_ENVIOS_REFERENCIA_ID       = "referencia_id";
    public static final String DETALLE_HISTORIAL_ENVIOS_DESCRIPCION         = "descripcion";

    // CIUDADES TABLE
    public static final String CIUDADES        = "ciudades";
    public static final String CIUDADES_ID     = "id";
    public static final String CIUDADES_NOMBRE = "nombre";




    // REFERENCIAS TABLE
    public static final String REFERENCIAS                  = "referencias";
    public static final String REFERENCIAS_ID               = "id";
    public static final String REFERENCIAS_NOMBRE           = "nombre";
    public static final String REFERENCIAS_VALOR            = "valor";
    public static final String REFERENCIAS_CODIGO_BARRAS    = "codigo_barras";
    public static final String REFERENCIAS_UNIDADES_EMPAQUE = "unidades_empaque";
    public static final String REFERENCIAS_CLIENTE_ID       = "cliente_id";

    // CLIENTES TABLE
    public static final String CLIENTES                = "clientes";
    public static final String CLIENTES_ID             = "id";
    public static final String CLIENTES_RAZON_SOCIAL   = "razon_social";
    public static final String CLIENTES_NIT            = "nit";
    public static final String CLIENTES_PASSWORD       = "password";
    public static final String CLIENTES_DIRECCIONES_ID = "direccion_id";

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

    // TIPOS_VIAS TABLE
    public static final String TIPOS_VIAS        = "tipos_vias";
    public static final String TIPOS_VIAS_ID     = "id";
    public static final String TIPOS_VIAS_NOMBRE = "nombre";

    // ESTADOS_RECOGIDAS TABLE
    public static final String ESTADOS_RECOGIDAS        = "estados_recogidas";
    public static final String ESTADOS_RECOGIDAS_ID     = "id";
    public static final String ESTADOS_RECOGIDAS_NOMBRE = "nombre";




    // HISTORIAL_REFERENCIAS TABLE
    public static final String HISTORIAL_REFERENCIAS               = "historial_referencias";
    public static final String HISTORIAL_REFERENCIAS_ID            = "id";
    public static final String HISTORIAL_REFERENCIAS_FECHA         = "fecha";
    public static final String HISTORIAL_REFERENCIAS_REFERENCIA_ID = "referencia_id";
    public static final String HISTORIAL_REFERENCIAS_DESCRIPCION   = "descripcion";

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

}
