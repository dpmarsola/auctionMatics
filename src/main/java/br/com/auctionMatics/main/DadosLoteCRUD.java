package br.com.auctionMatics.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DadosLoteCRUD {

    public void insereOuAtualiza(Integer loteID, String marcaModeloVeiculo, String anoVeiculo, String quilometragem, String corVeiculo, String combustivel, String linkLote) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            DadosLotes emp = new DadosLotes();
            emp.setLoteID(loteID);
            emp.setMarcaModeloVeiculo(marcaModeloVeiculo);
            emp.setAnoVeiculo(anoVeiculo);
            emp.setQtdeKilometros(quilometragem);
            emp.setCorVeiculo(corVeiculo);
            emp.setCombustivelVeiculo(combustivel);
            emp.setLinkLote(linkLote);
            session.saveOrUpdate(emp);
            tx.commit(); // Flush happens automatically
        }catch (RuntimeException e) {
            tx.rollback();
            throw e; // or display error message
        }

       // HibernateUtil.shutdown();
    }

    public void buscaLoteByLoteID(String loteID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
/*
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        System.out.println(em.createQuery("SELECT * FROM br.com.auctionMatics.main.DadosLotes").getResultList());
*/
        Query q = session.createQuery("from DadosLotes where loteID = :lote")
                .setParameter("lote", loteID);
        List<DadosLotes> list = q.getResultList();

        for (DadosLotes e : list ) {
            System.out.println("Lote: " + e.getLoteID());
        }

        //HibernateUtil.shutdown();
    }

    public Integer buscaMaxLoteID() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        Integer result = (int) em.createQuery("SELECT max(LoteID) FROM br.com.auctionMatics.main.DadosLotes").getSingleResult();

        return result;

        //HibernateUtil.shutdown();
    }


    public static void main(String[] args){

        DadosLoteCRUD dlcrud = new DadosLoteCRUD();
        dlcrud.buscaMaxLoteID();


    }
}