import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Queue;

public class Principal {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//            Persistence.createEntityManagerFactory("default");

    public static void main(String[] args){
        EntityManager em = emf.createEntityManager();
        //mostrar um registro
//        em.getTransaction().begin();//iniciando a transação
//        Departamento dept2 = em.find(Departamento.class,40);
//        System.out.println(dept2);
//        em.getTransaction().commit();//commitando a transação

        //inserirndo
//        Departamento dept1 = new Departamento();
//        dept1.setNumDept(75);
//        dept1.setdName("Marketing");
//        dept1.setLoc("São Paulo");
//
//        em.getTransaction().begin();
//        em.persist(dept1);
//        em.getTransaction().commit();
//
//        //Alterando
//        em.getTransaction().begin();
//        Departamento dept2 = em.find(Departamento.class,75);
//        dept2.setdName("Administratvo");
//        em.getTransaction().commit();

//        //Excluindo
//        em.getTransaction().begin();
//        Departamento dept2 = em.find(Departamento.class,75);
//        em.remove(dept2);
//        em.getTransaction().commit();

        //consultando
//        Query query = em.createQuery("select dept3 from Departamento dept3 order by dept3.numDept");
//        List<Departamento> departamentos = query.getResultList();
//
//        for(Departamento depart : departamentos){
//            System.out.println(depart);
//        }

//        String cidade = "NEW YORK";
//        Query query = em.createQuery("select dept4 from Departamento dept4 where dept4.loc = :local order by dept4.numDept");
//        query.setParameter("local",cidade);
//        List<Departamento> departamentos = query.getResultList();
//
//        for(Departamento depart :departamentos){
//            System.out.println(depart);
//        }



    }
}
