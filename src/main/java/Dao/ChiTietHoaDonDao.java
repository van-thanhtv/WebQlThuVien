package Dao;

import entitys.TChiTienHoaDonEntity;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ChiTietHoaDonDao {
    private EntityManager em;
    public ChiTietHoaDonDao() {
        this.em = JpaUtil.getEntityManager();
    }
    public TChiTienHoaDonEntity create(TChiTienHoaDonEntity entity) throws Exception {
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

    public TChiTienHoaDonEntity update(TChiTienHoaDonEntity entity) throws Exception {
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
    public TChiTienHoaDonEntity delete(TChiTienHoaDonEntity entity) throws Exception{
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
    public int deleteList(List<TChiTienHoaDonEntity> entitys) throws Exception{
        int i =0;
        try {
            this.em.getTransaction().begin();
            for (TChiTienHoaDonEntity entity : entitys) {
                em.remove(em.contains(entity) ? entity : em.merge(entity));;
                i++;
            }
            this.em.getTransaction().commit();
            return i;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<TChiTienHoaDonEntity> all() {
        String jpql = "SELECT obj from TChiTienHoaDonEntity obj";
        TypedQuery<TChiTienHoaDonEntity> query = this.em.createQuery(jpql, TChiTienHoaDonEntity.class);
        List<TChiTienHoaDonEntity> list = query.getResultList();
        return list;
    }
    public List<TChiTienHoaDonEntity> findHD(int id) {
        String jpql = "SELECT obj from TChiTienHoaDonEntity obj where obj.soHoaDon = :id";
        TypedQuery<TChiTienHoaDonEntity> query = this.em.createQuery(jpql, TChiTienHoaDonEntity.class);
        query.setParameter("id", id);
        List<TChiTienHoaDonEntity> list = query.getResultList();
        return list;
    }

    public TChiTienHoaDonEntity findByID(int id) {
        TChiTienHoaDonEntity entity = this.em.find(TChiTienHoaDonEntity.class, id);
        return entity;
    }
}
