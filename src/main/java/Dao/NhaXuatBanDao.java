package Dao;

import entitys.TNhaXuatBanEntity;
import entitys.TSachEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class NhaXuatBanDao {
    private EntityManager em;
    public NhaXuatBanDao() {
        this.em= JpaUtil.getEntityManager();
    }
    public TNhaXuatBanEntity create(TNhaXuatBanEntity entity) throws Exception {
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

    public TNhaXuatBanEntity update(TNhaXuatBanEntity entity) throws Exception {
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
    public TNhaXuatBanEntity delete(TNhaXuatBanEntity entity) throws Exception{
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
    public List<TNhaXuatBanEntity> all() {
        String jpql = "SELECT obj from TNhaXuatBanEntity obj";
        TypedQuery<TNhaXuatBanEntity> query = this.em.createQuery(jpql, TNhaXuatBanEntity.class);
        List<TNhaXuatBanEntity> list = query.getResultList();
        return list;
    }

    public TNhaXuatBanEntity findByID(int id) {
        TNhaXuatBanEntity entity = this.em.find(TNhaXuatBanEntity.class, id);
        return entity;
    }
}
