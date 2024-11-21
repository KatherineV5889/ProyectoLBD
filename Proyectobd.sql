CREATE TABLE tiendas (
    id_tienda NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    direccion VARCHAR2(200),
    ciudad VARCHAR2(100)
);


CREATE TABLE empleados (
    id_empleados NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_tienda NUMBER(10),
    nombre VARCHAR2(100),
    apellido VARCHAR2(100),
    puesto VARCHAR2(50),
    FOREIGN KEY (id_tienda) REFERENCES tiendas(id_tienda)
);


CREATE TABLE clientes (
    id_cliente NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    apellido VARCHAR2(100),
    email VARCHAR2(100),
    telefono VARCHAR2(15),
    direccion VARCHAR2(200)
);

CREATE TABLE categorias (
    id_categoria NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100)
);

CREATE TABLE productos (
    id_producto NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_categoria NUMBER(10),
    id_proveedor NUMBER(10), 
    nombre VARCHAR2(100),
    precio_producto NUMBER(10, 2),
    stock_producto NUMBER(10),
    descripcion VARCHAR2(200),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);


CREATE TABLE inventarios (
    id_inventario NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_tienda NUMBER(10),
    id_producto NUMBER(10),
    total_productos NUMBER(10),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_tienda) REFERENCES tiendas(id_tienda)
);

CREATE TABLE ventas (
    id_ventas NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cliente NUMBER(10),
    id_empleado NUMBER(10),
    id_producto NUMBER(10),
    id_tienda NUMBER(10),
    fecha DATE,
    cantidad NUMBER(10),
    total NUMBER(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleados),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
    FOREIGN KEY (id_tienda) REFERENCES tiendas(id_tienda)
);


CREATE TABLE proveedor (
    id_proveedor NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100),
    descripcion VARCHAR2(200),
    ciudad VARCHAR2(100),
    telefono VARCHAR2(15),
    id_tienda NUMBER(10),
    FOREIGN KEY (id_tienda) REFERENCES tiendas(id_tienda)
);


CREATE TABLE pedidos (
    id_pedido NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_proveedor NUMBER(10),
    id_tienda NUMBER(10),
    fecha_pedido DATE,
    total_unidades NUMBER(10), 
    id_productos NUMBER(10),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_tienda) REFERENCES tiendas(id_tienda)
);



-- Vistas
CREATE OR REPLACE VIEW productos_stock AS
SELECT id_producto, nombre, stock_producto
FROM productos
WHERE stock_producto < 10;


CREATE OR REPLACE VIEW ventas_por_tienda AS
SELECT id_tienda, SUM(total) AS total_ventas
FROM ventas
GROUP BY id_tienda;


CREATE OR REPLACE VIEW clientes_frecuentes AS
SELECT c.id_cliente, c.nombre, c.apellido, COUNT(v.id_ventas) AS compras_realizadas
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.id_cliente, c.nombre, c.apellido
HAVING COUNT(v.id_ventas) > 5;



-- Funciones
CREATE OR REPLACE FUNCTION calcular_descuento(precio_producto NUMBER, porcentaje_descuento NUMBER)
RETURN NUMBER IS
BEGIN
    RETURN precio_producto - (precio_producto * (porcentaje_descuento / 100));
END;


CREATE OR REPLACE FUNCTION obtener_nombre_cliente(p_id_cliente NUMBER)
RETURN VARCHAR2 IS
    v_nombre VARCHAR2(100);
BEGIN
    SELECT nombre INTO v_nombre
    FROM clientes
    WHERE id_cliente = p_id_cliente;
    RETURN v_nombre;
END;


CREATE OR REPLACE FUNCTION validar_stock(id_producto NUMBER, cantidad NUMBER)
RETURN VARCHAR2 IS
    stock_actual NUMBER;
BEGIN
    SELECT stock_producto INTO stock_actual
    FROM productos
    WHERE id_producto = id_producto;

    IF stock_actual >= cantidad THEN
        RETURN 'Suficiente stock';
    ELSE
        RETURN 'Stock insuficiente';
    END IF;
END;



-- Paquetes
/*CREATE OR REPLACE PACKAGE cliente_ops AS
    FUNCTION obtener_correo(p_id_cliente NUMBER) RETURN VARCHAR2;
    PROCEDURE actualizar_telefono(p_id_cliente NUMBER, nuevo_telefono VARCHAR2);
END cliente_ops;

CREATE OR REPLACE PACKAGE BODY cliente_ops AS
    FUNCTION obtener_correo(p_id_cliente NUMBER) RETURN VARCHAR2 IS
        v_email VARCHAR2(100);
    BEGIN
        SELECT email INTO v_email
        FROM clientes
        WHERE id_cliente = p_id_cliente;
        RETURN v_email;
    END;

    PROCEDURE actualizar_telefono(p_id_cliente NUMBER, nuevo_telefono VARCHAR2) IS
    BEGIN
        UPDATE clientes
        SET telefono = nuevo_telefono
        WHERE id_cliente = p_id_cliente;
    END;
END cliente_ops;*/

-- Trigger
CREATE OR REPLACE TRIGGER actualizar_inventario
AFTER INSERT ON ventas
FOR EACH ROW
BEGIN
    UPDATE productos
    SET stock_producto = stock_producto - :NEW.cantidad
    WHERE id_producto = :NEW.id_producto;
END;


CREATE OR REPLACE TRIGGER validar_stock
BEFORE UPDATE OF stock_producto ON productos
FOR EACH ROW
BEGIN
    IF :NEW.stock_producto < 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El stock no puede ser negativo');
    END IF;
END;


-- Cursores
DECLARE
    CURSOR c_stock_bajo IS
        SELECT id_producto, nombre, stock_producto
        FROM productos
        WHERE stock_producto < 10;
    v_id_producto productos.id_producto%TYPE;
    v_nombre productos.nombre%TYPE;
    v_stock productos.stock_producto%TYPE;
BEGIN
    OPEN c_stock_bajo;
    LOOP
        FETCH c_stock_bajo INTO v_id_producto, v_nombre, v_stock;
        EXIT WHEN c_stock_bajo%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Producto: ' || v_nombre || ' - Stock: ' || v_stock);
    END LOOP;
    CLOSE c_stock_bajo;
END;



DECLARE
    CURSOR c_ventas_cliente IS
        SELECT c.nombre, SUM(v.total) AS total_compras
        FROM clientes c
        JOIN ventas v ON c.id_cliente = v.id_cliente
        GROUP BY c.nombre;
    v_nombre clientes.nombre%TYPE;
    v_total NUMBER;
BEGIN
    OPEN c_ventas_cliente;
    LOOP
        FETCH c_ventas_cliente INTO v_nombre, v_total;
        EXIT WHEN c_ventas_cliente%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Cliente: ' || v_nombre || ' - Total Compras: ' || v_total);
    END LOOP;
    CLOSE c_ventas_cliente;
END;

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


