import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Date;
import java.util.List;

public class EmpregadoDAO {
    private static EntityManagerFactory emf;
    public void iniciar(){emf = Persistence.createEntityManagerFactory("default");}
    public void encerrar(){emf.close();}
    public boolean inserirEmp(int codEmpr, String nome, String trabalho, Float gerente, Date contratacao,
                           double salario, Float comissao, int departamento){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empregado emp1 = new Empregado();
            emp1.setCodEmpr(codEmpr);
            emp1.setNome(nome);
            emp1.setTrabalho(trabalho);
            emp1.setGerente(gerente);
            emp1.setContratacao(contratacao);
            emp1.setSalario(salario);
            emp1.setComissao(comissao);
            emp1.setDepartamento(departamento);
            //Empregado emp1 = new Empregado(codEmpr,nome,trabalho,gerente,contratacao,salario,comissao,departamento);
            em.persist(emp1);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }
    public boolean alterarNome(int codEmpr, String nome){
        EntityManager em = emf.createEntityManager();
        nome = nome.toUpperCase();
        try {
            em.getTransaction().begin();
            Empregado emp1 = em.find(Empregado.class, codEmpr);
            emp1.setNome(nome);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }
    public boolean alterarSalario(int codEmpr, double salario){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empregado emp1 = em.find(Empregado.class, codEmpr);
            emp1.setSalario(salario);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }


    public boolean excluirEmp(int codEmpr){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empregado emp1 = em.find(Empregado.class, codEmpr);
            em.remove(emp1);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }

    public List consultarTodos(){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select emp from Empregado emp");
            List<Empregado> empregados = query.getResultList();
            return empregados;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public Empregado buscarPorCod(int codEmpr){
        EntityManager em = emf.createEntityManager();
        try {
            Empregado emp1 = em.find(Empregado.class, codEmpr);
            return emp1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public List<Empregado> buscarPorTrabalho(String trabalho){
        EntityManager em = emf.createEntityManager();
        trabalho = trabalho.toUpperCase();
        try {
            Query query = em.createQuery("select emp from Empregado emp where emp.trabalho = :trabalho order by emp.codEmpr");
            query.setParameter("trabalho", trabalho);
            List<Empregado> empregados = query.getResultList();
            return empregados;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public List consultarTodosCodigos(){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select codEmpr from Empregado emp");
            List<Empregado> empregados = query.getResultList();
            return empregados;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }




}
