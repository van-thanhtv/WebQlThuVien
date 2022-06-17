package Dao;

import entitys.TSachEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SachDao {
    private EntityManager em;
    public SachDao() {
        this.em = JpaUtil.getEntityManager();
    }
    public TSachEntity create(TSachEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
        return entity;
    }

    public TSachEntity update(TSachEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
        return entity;
    }
    public TSachEntity delete(TSachEntity entity) throws Exception{
        try {
            this.em.getTransaction().begin();
            this.em.remove(entity);
            this.em.getTransaction().commit();
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<TSachEntity> all() {
        String jpql = "SELECT obj from TSachEntity obj";
        TypedQuery<TSachEntity> query = this.em.createQuery(jpql, TSachEntity.class);
        List<TSachEntity> list = query.getResultList();
        return list;
    }

    public TSachEntity findByID(int id) {
        TSachEntity entity = this.em.find(TSachEntity.class, id);
        return entity;
    }
}
