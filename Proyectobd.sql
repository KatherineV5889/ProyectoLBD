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
    nombre VARCHAR2(100),
    precio_producto NUMBER(10, 2),
    stock_producto NUMBER(10),
    descripcion VARCHAR2(200),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);


CREATE TABLE inventarios (
    id_inventario NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_tienda NUMBER(10),
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
    FOREIGN KEY (id_tienda) REFERENCES tiendas(id_tienda)
);

CREATE TABLE pedidos (
    id_pedido NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_proveedor NUMBER(10),
    fecha_pedido DATE,
    total_unidades NUMBER(10), 
    id_productos NUMBER(10),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
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
CREATE OR REPLACE PACKAGE cliente_ops AS
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
END cliente_ops;

-- Trigger
CREATE OR REPLACE TRIGGER actualizar_inventario
AFTER INSERT ON ventas
FOR EACH ROW
BEGIN
    UPDATE productos
    SET stock_producto = stock_producto - :NEW.cantidad
    WHERE id_producto = :NEW.id_producto;
END;


CREATE OR REPLACE TRIGGER validar_stock_negativo
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
    v_producto productos%ROWTYPE;
BEGIN
    OPEN c_stock_bajo;
    LOOP
        FETCH c_stock_bajo INTO v_producto;
        EXIT WHEN c_stock_bajo%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Producto: ' || v_producto.nombre || ' - Stock: ' || v_producto.stock_producto);
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
