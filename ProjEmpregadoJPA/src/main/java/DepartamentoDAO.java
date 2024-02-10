import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Date;
import java.util.List;

public class DepartamentoDAO {
    private static EntityManagerFactory emf;
    public void iniciar(){emf = Persistence.createEntityManagerFactory("default");}
    public void encerrar(){emf.close();}

    public List consultarDept(){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createQuery("select dept from Departamento dept");
            List<Departamento> departamentos = query.getResultList();
            return departamentos;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    public Departamento buscarPorCod(int codDept){
        EntityManager em = emf.createEntityManager();
        try {
            Departamento dept = em.find(Departamento.class, codDept);
            return dept;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }
}
