package br.com.auctionMatics.main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StatusConsultaCRUD {

    public void insereOuAtualiza(Integer numLeilao, String statusConsulta) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            StatusConsulta emp = new StatusConsulta();
            emp.setNumleilao(numLeilao);
            emp.setStatusconsulta(statusConsulta);;
            session.saveOrUpdate(emp);
            tx.commit(); // Flush happens automatically
        }catch (RuntimeException e) {
            tx.rollback();
            throw e; // or display error message
        }

       // HibernateUtil.shutdown();
    }
    
    public String buscaStatusConsulta(Integer numleilao) {

        Session session = HibernateUtil.getSessionFactory().openSession();
/*
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        System.out.println(em.createQuery("SELECT * FROM br.com.auctionMatics.main.DadosLotes").getResultList());
*/
        Query q = session.createQuery("from StatusConsulta where numleilao = :numleilao")
                .setParameter("numleilao", numleilao);
        List<StatusConsulta> list = q.getResultList();

        String result;
        try {
        	
        	result = list.get(0).getStatusconsulta();
        	
        }catch(Exception e) {
        	result = "0";
        }

        return result;
        	

        
         
        		
        
        //HibernateUtil.shutdown();
    }

    public static void main(String[] args){

        StatusConsultaCRUD dlcrud = new StatusConsultaCRUD();
        System.out.println("StatusConsultaCRUD.main" + "- Resultado da QUERY: "+ dlcrud.buscaStatusConsulta(382));
        


    }
}