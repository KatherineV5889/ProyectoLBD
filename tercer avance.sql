-- Paquete para gestionar productos
CREATE OR REPLACE PACKAGE gestionar_productos AS
    PROCEDURE actualizar_precio(p_id_producto NUMBER, nuevo_precio NUMBER);
    CURSOR listar_productos_bajo_stock(p_stock_minimo NUMBER) RETURN productos%ROWTYPE;
END gestionar_productos;

CREATE OR REPLACE PACKAGE BODY gestionar_productos AS
    PROCEDURE actualizar_precio(p_id_producto NUMBER, nuevo_precio NUMBER) IS
    BEGIN
        UPDATE productos
        SET precio_producto = nuevo_precio
        WHERE id_producto = p_id_producto;
    END;
    
    CURSOR listar_productos_bajo_stock(p_stock_minimo NUMBER) RETURN productos%ROWTYPE IS
        SELECT *
        FROM productos
        WHERE stock_producto < p_stock_minimo;
END gestionar_productos;

-- Paquete para gestionar clientes
CREATE OR REPLACE PACKAGE gestionar_clientes AS
    FUNCTION obtener_datos_cliente(p_id_cliente NUMBER) RETURN VARCHAR2;
    PROCEDURE actualizar_direccion(p_id_cliente NUMBER, nueva_direccion VARCHAR2);
    CURSOR cur_listar_clientes_frecuentes RETURN clientes%ROWTYPE;
END gestionar_clientes;

CREATE OR REPLACE PACKAGE BODY gestionar_clientes AS
    FUNCTION obtener_datos_cliente(p_id_cliente NUMBER) RETURN VARCHAR2 IS
        v_datos_cliente VARCHAR2(500);
    BEGIN
        SELECT nombre || ' ' || apellido || ' - ' ||
               email || ' - ' || telefono || ' - ' ||
               direccion INTO v_datos_cliente
        FROM clientes
        WHERE id_cliente = p_id_cliente;
        RETURN v_datos_cliente;
    END;

    PROCEDURE actualizar_direccion(p_id_cliente NUMBER, nueva_direccion VARCHAR2) IS
    BEGIN
        UPDATE clientes
        SET direccion = nueva_direccion
        WHERE id_cliente = p_id_cliente;
    END;

    CURSOR cur_listar_clientes_frecuentes IS
        SELECT *
        FROM clientes c
        WHERE c.id_cliente IN (
            SELECT id_cliente
            FROM ventas
            GROUP BY id_cliente
            HAVING COUNT(id_ventas) > 5
        );
END gestionar_clientes;

-- Paquete para gestionar ventas
CREATE OR REPLACE PACKAGE gestionar_ventas AS
    FUNCTION calcular_total_venta(p_id_venta NUMBER) RETURN NUMBER;
    PROCEDURE registrar_venta(
        p_id_cliente NUMBER,
        p_id_empleado NUMBER,
        p_id_producto NUMBER,
        p_id_tienda NUMBER,
        p_cantidad NUMBER
    );
    CURSOR listar_ventas_cliente(p_id_cliente NUMBER) RETURN ventas%ROWTYPE;
END gestionar_ventas;

CREATE OR REPLACE PACKAGE BODY gestionar_ventas AS
    FUNCTION calcular_total_venta(p_id_venta NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT total INTO v_total
        FROM ventas
        WHERE id_ventas = p_id_venta;
        RETURN v_total;
    END;

    PROCEDURE registrar_venta(
        p_id_cliente NUMBER,
        p_id_empleado NUMBER,
        p_id_producto NUMBER,
        p_id_tienda NUMBER,
        p_cantidad NUMBER
    ) IS
        v_precio_producto NUMBER;
        v_total NUMBER;
    BEGIN
        SELECT precio_producto INTO v_precio_producto
        FROM productos
        WHERE id_producto = p_id_producto;

        v_total := v_precio_producto * p_cantidad;

        INSERT INTO ventas (id_cliente, id_empleado, id_producto,
                            id_tienda, fecha, cantidad, total)
                            
        VALUES (p_id_cliente, p_id_empleado, p_id_producto,
                p_id_tienda, SYSDATE, p_cantidad, v_total);

        UPDATE productos
        SET stock_producto = stock_producto - p_cantidad
        WHERE id_producto = p_id_producto;
    END;

    CURSOR listar_ventas_cliente(p_id_cliente NUMBER) RETURN ventas%ROWTYPE IS
        SELECT *
        FROM ventas
        WHERE id_cliente = p_id_cliente;
END gestionar_ventas;

-- Paquete para gestionar inventarios
CREATE OR REPLACE PACKAGE gestionar_inventarios AS
    FUNCTION verificar_stock(p_id_producto NUMBER, p_id_tienda NUMBER) RETURN NUMBER;
    PROCEDURE actualizar_inventario(p_id_producto NUMBER, p_id_tienda NUMBER, nueva_cantidad NUMBER);
    CURSOR listar_inventarios_bajos RETURN inventarios%ROWTYPE;
END gestionar_inventarios;

CREATE OR REPLACE PACKAGE BODY gestionar_inventarios AS
    FUNCTION verificar_stock(p_id_producto NUMBER, p_id_tienda NUMBER) RETURN NUMBER IS
        v_cantidad NUMBER;
    BEGIN
        SELECT cantidad INTO v_cantidad
        FROM inventarios
        WHERE id_producto = p_id_producto AND id_tienda = p_id_tienda;
        RETURN v_cantidad;
    END;

    PROCEDURE actualizar_inventario(p_id_producto NUMBER, p_id_tienda NUMBER, nueva_cantidad NUMBER) IS
    BEGIN
        UPDATE inventarios
        SET cantidad = nueva_cantidad
        WHERE id_producto = p_id_producto AND id_tienda = p_id_tienda;
    END;

    CURSOR listar_inventarios_bajos RETURN inventarios%ROWTYPE IS
        SELECT *
        FROM inventarios
        WHERE cantidad < 10;
END gestionar_inventarios;

-- Paquete para gestionar empleados
CREATE OR REPLACE PACKAGE gestionar_empleados AS
    FUNCTION obtener_empleado(p_id_empleado NUMBER) RETURN VARCHAR2;
    PROCEDURE asignar_empleado_tienda(p_id_empleado NUMBER, p_id_tienda NUMBER);
    CURSOR listar_empleados_tienda(p_id_tienda NUMBER) RETURN empleados%ROWTYPE;
END gestionar_empleados;

CREATE OR REPLACE PACKAGE BODY gestionar_empleados AS
    FUNCTION obtener_empleado(p_id_empleado NUMBER) RETURN VARCHAR2 IS
        v_empleado VARCHAR2(200);
    BEGIN
        SELECT nombre || ' ' || apellido || ' - ' || puesto INTO v_empleado
        FROM empleados
        WHERE id_empleados = p_id_empleado;
        RETURN v_empleado;
    END;

    PROCEDURE asignar_empleado_tienda(p_id_empleado NUMBER, p_id_tienda NUMBER) IS
    BEGIN
        UPDATE empleados
        SET id_tienda = p_id_tienda
        WHERE id_empleados = p_id_empleado;
    END;

    CURSOR cur_listar_empleados_tienda(p_id_tienda NUMBER) RETURN empleados%ROWTYPE IS
        SELECT *
        FROM empleados
        WHERE id_tienda = p_id_tienda;
END gestionar_empleados;
