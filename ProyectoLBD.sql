--Procedimientos Almacenados

CREATE OR REPLACE PROCEDURE agregar_tienda(
    p_nombre IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_ciudad IN VARCHAR2
) AS
BEGIN
    INSERT INTO tiendas (nombre, direccion, ciudad)
    VALUES (p_nombre, p_direccion, p_ciudad);
END;
/

CREATE OR REPLACE PROCEDURE agregar_empleado(
    p_id_tienda IN NUMBER,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_puesto IN VARCHAR2
) AS
BEGIN
    INSERT INTO empleados (id_tienda, nombre, apellido, puesto)
    VALUES (p_id_tienda, p_nombre, p_apellido, p_puesto);
END;
/

CREATE OR REPLACE PROCEDURE agregar_cliente(
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_email IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_direccion IN VARCHAR2
) AS
BEGIN
    INSERT INTO clientes (nombre, apellido, email, telefono, direccion)
    VALUES (p_nombre, p_apellido, p_email, p_telefono, p_direccion);
END;
/

CREATE OR REPLACE PROCEDURE agregar_categoria(
    p_nombre IN VARCHAR2
) AS
BEGIN
    INSERT INTO categorias (nombre)
    VALUES (p_nombre);
END;
/

CREATE OR REPLACE PROCEDURE agregar_producto(
    p_id_categoria IN NUMBER,
    p_nombre IN VARCHAR2,
    p_precio_producto IN NUMBER,
    p_stock_producto IN NUMBER,
    p_descripcion IN VARCHAR2
) AS
BEGIN
    INSERT INTO productos (id_categoria, nombre, precio_producto, stock_producto, descripcion)
    VALUES (p_id_categoria, p_nombre, p_precio_producto, p_stock_producto, p_descripcion);
END;
/

CREATE OR REPLACE PROCEDURE agregar_inventario(
    p_id_tienda IN NUMBER,
    p_total_productos IN NUMBER
) AS
BEGIN
    INSERT INTO inventarios (id_tienda, total_productos)
    VALUES (p_id_tienda, p_total_productos);
END;
/

CREATE OR REPLACE PROCEDURE agregar_venta(
    p_id_cliente IN NUMBER,
    p_id_empleado IN NUMBER,
    p_id_producto IN NUMBER,
    p_id_tienda IN NUMBER,
    p_fecha IN DATE,
    p_cantidad IN NUMBER,
    p_total IN NUMBER
) AS
BEGIN
    INSERT INTO ventas (id_cliente, id_empleado, id_producto, id_tienda, fecha, cantidad, total)
    VALUES (p_id_cliente, p_id_empleado, p_id_producto, p_id_tienda, p_fecha, p_cantidad, p_total);
END;
/

CREATE OR REPLACE PROCEDURE agregar_proveedor(
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_ciudad IN VARCHAR2,
    p_telefono IN VARCHAR2
) AS
BEGIN
    INSERT INTO proveedor (nombre, descripcion, ciudad, telefono)
    VALUES (p_nombre, p_descripcion, p_ciudad, p_telefono);
END;
/

CREATE OR REPLACE PROCEDURE agregar_pedido(
    p_id_proveedor IN NUMBER,
    p_fecha_pedido IN DATE,
    p_total_unidades IN NUMBER,
    p_id_productos IN NUMBER
) AS
BEGIN
    INSERT INTO pedidos (id_proveedor, fecha_pedido, total_unidades, id_productos)
    VALUES (p_id_proveedor, p_fecha_pedido, p_total_unidades, p_id_productos);
END;
/

CREATE OR REPLACE PROCEDURE modificar_tienda(
    p_id_tienda IN NUMBER,
    p_nombre IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_ciudad IN VARCHAR2
) AS
BEGIN
    UPDATE tiendas
    SET nombre = p_nombre,
        direccion = p_direccion,
        ciudad = p_ciudad
    WHERE id_tienda = p_id_tienda;
END;
/

CREATE OR REPLACE PROCEDURE modificar_empleado(
    p_id_empleado IN NUMBER,
    p_id_tienda IN NUMBER,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_puesto IN VARCHAR2
) AS
BEGIN
    UPDATE empleados
    SET id_tienda = p_id_tienda,
        nombre = p_nombre,
        apellido = p_apellido,
        puesto = p_puesto
    WHERE id_empleados = p_id_empleado;
END;
/

CREATE OR REPLACE PROCEDURE modificar_cliente(
    p_id_cliente IN NUMBER,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_email IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_direccion IN VARCHAR2
) AS
BEGIN
    UPDATE clientes
    SET nombre = p_nombre,
        apellido = p_apellido,
        email = p_email,
        telefono = p_telefono,
        direccion = p_direccion
    WHERE id_cliente = p_id_cliente;
END;
/

CREATE OR REPLACE PROCEDURE modificar_producto(
    p_id_producto IN NUMBER,
    p_id_categoria IN NUMBER,
    p_nombre IN VARCHAR2,
    p_precio_producto IN NUMBER,
    p_stock_producto IN NUMBER,
    p_descripcion IN VARCHAR2
) AS
BEGIN
    UPDATE productos
    SET id_categoria = p_id_categoria,
        nombre = p_nombre,
        precio_producto = p_precio_producto,
        stock_producto = p_stock_producto,
        descripcion = p_descripcion
    WHERE id_producto = p_id_producto;
END;
/

CREATE OR REPLACE PROCEDURE modificar_inventario(
    p_id_inventario IN NUMBER,
    p_id_tienda IN NUMBER,
    p_total_productos IN NUMBER
) AS
BEGIN
    UPDATE inventarios
    SET id_tienda = p_id_tienda,
        total_productos = p_total_productos
    WHERE id_inventario = p_id_inventario;
END;
/

CREATE OR REPLACE PROCEDURE modificar_proveedor(
    p_id_proveedor IN NUMBER,
    p_nombre IN VARCHAR2,
    p_descripcion IN VARCHAR2,
    p_ciudad IN VARCHAR2,
    p_telefono IN VARCHAR2
) AS
BEGIN
    UPDATE proveedor
    SET nombre = p_nombre,
        descripcion = p_descripcion,
        ciudad = p_ciudad,
        telefono = p_telefono
    WHERE id_proveedor = p_id_proveedor;
END;
/

CREATE OR REPLACE PROCEDURE modificar_pedido(
    p_id_pedido IN NUMBER,
    p_id_proveedor IN NUMBER,
    p_fecha_pedido IN DATE,
    p_total_unidades IN NUMBER,
    p_id_productos IN NUMBER
) AS
BEGIN
    UPDATE pedidos
    SET id_proveedor = p_id_proveedor,
        fecha_pedido = p_fecha_pedido,
        total_unidades = p_total_unidades,
        id_productos = p_id_productos
    WHERE id_pedido = p_id_pedido;
END;
/


-- Vistas

CREATE OR REPLACE VIEW ventas_por_empleado AS
SELECT 
    id_empleado, 
    SUM(total) AS total_ventas
FROM 
    ventas
GROUP BY 
    id_empleado;

CREATE OR REPLACE VIEW dia_mas_ventas AS
SELECT 
    fecha, 
    SUM(cantidad) AS total_cantidad_ventas,
    SUM(total) AS total_monto_ventas
FROM 
    ventas
GROUP BY 
    fecha
HAVING 
    SUM(cantidad) = (
        SELECT MAX(SUM(cantidad))
        FROM ventas
        GROUP BY fecha
    );

CREATE OR REPLACE VIEW dia_menos_ventas AS
SELECT 
    fecha, 
    SUM(cantidad) AS total_cantidad_ventas,
    SUM(total) AS total_monto_ventas
FROM 
    ventas
GROUP BY 
    fecha
HAVING 
    SUM(cantidad) = (
        SELECT MIN(SUM(cantidad))
        FROM ventas
        GROUP BY fecha
    );


