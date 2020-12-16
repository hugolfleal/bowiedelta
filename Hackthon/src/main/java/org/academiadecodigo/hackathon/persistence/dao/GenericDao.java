package org.academiadecodigo.hackathon.persistence.dao;

import org.academiadecodigo.hackathon.persistence.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericDao<T extends Model> {

    protected Class<T> modelType;

    @PersistenceContext
    protected EntityManager em;

    public GenericDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<T> findAll() {

        CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);
        return em.createQuery(criteriaQuery).getResultList();

        // Using JPA
        // return em.createQuery( "from " + modelType.getSimpleName(), modelType).getResultList();
    }

    public T findById(Integer id) {
        return em.find(modelType, id);
    }

    public T saveOrUpdate(T modelObject) {
        return em.merge(modelObject);
    }

    public void delete(Integer id) {
        em.remove(em.find(modelType, id));
    }


}
