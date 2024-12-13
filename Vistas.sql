-- Vista de productos menos vendidos
CREATE OR REPLACE VIEW productos_menos_vendidos AS
SELECT 
    p.id_producto,
    p.nombre AS nombre_producto,
    p.descripcion AS descripcion_producto,
    p.precio_producto,
    p.stock_producto,
    NVL(SUM(v.cantidad), 0) AS total_vendido
FROM 
    productos p
LEFT JOIN 
    ventas v ON p.id_producto = v.id_producto
GROUP BY 
    p.id_producto, p.nombre, p.descripcion, p.precio_producto, p.stock_producto
ORDER BY 
    total_vendido ASC;

-- Vista de categorías por cada proveedor
CREATE OR REPLACE VIEW categorias_por_proveedor AS
SELECT 
    pr.id_proveedor,
    pr.nombre AS nombre_proveedor,
    c.id_categoria,
    c.nombre AS nombre_categoria
FROM 
    productos p
INNER JOIN 
    categorias c ON p.id_categoria = c.id_categoria
INNER JOIN 
    proveedor pr ON p.id_proveedor = pr.id_proveedor
GROUP BY 
    pr.id_proveedor, pr.nombre, c.id_categoria, c.nombre
ORDER BY 
    pr.id_proveedor, c.id_categoria;
