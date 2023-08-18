package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Usuario;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public Usuario consultarPorNombre(String username) {
		   TypedQuery<Usuario> myQuery=this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombre=:userName", Usuario.class);
	        myQuery.setParameter("userName",username);
	        return myQuery.getSingleResult();
	}
}
