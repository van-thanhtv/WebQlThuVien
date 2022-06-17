package Dao;

import entitys.TDaiLyEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaiLyDao {
    private EntityManager em;
    public DaiLyDao() {
        this.em = JpaUtil.getEntityManager();
    }
    public TDaiLyEntity create(TDaiLyEntity entity) throws Exception {
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

    public TDaiLyEntity update(TDaiLyEntity entity) throws Exception {
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
    public TDaiLyEntity delete(TDaiLyEntity entity) throws Exception{
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
    public List<TDaiLyEntity> all() {
        String jpql = "SELECT obj from TDaiLyEntity obj";
        TypedQuery<TDaiLyEntity> query = this.em.createQuery(jpql, TDaiLyEntity.class);
        List<TDaiLyEntity> list = query.getResultList();
        return list;
    }

    public TDaiLyEntity findByID(int id) {
        TDaiLyEntity entity = this.em.find(TDaiLyEntity.class, id);
        return entity;
    }
}
