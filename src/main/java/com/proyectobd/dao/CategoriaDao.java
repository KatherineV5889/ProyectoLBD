package com.proyectobd.dao;

import com.proyectobd.domain.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoriaDao{
    
    @PersistenceContext
    private EntityManager entityManager;

    public void agregarCategoria(String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("agregar_categoria");
        query.registerStoredProcedureParameter(1, String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter(1, nombre);
        query.execute();
    }

    public List<Categoria> listarCategorias() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("listar_categorias", Categoria.class);
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);

        query.execute();
        return query.getResultList();
    }

    public void modificarCategoria(Long idCategoria, String nombre) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("modificar_categoria");
        query.registerStoredProcedureParameter(1, Long.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter(1, idCategoria);
        query.setParameter(2, nombre);
        query.execute();
    }

    public void eliminarCategoria(Long idCategoria) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_categoria");
        query.registerStoredProcedureParameter(1, Long.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter(1, idCategoria);
        query.execute();
    }    
    
}
