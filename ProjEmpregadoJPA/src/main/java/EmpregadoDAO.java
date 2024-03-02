import jakarta.persistence.*;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Date;
import java.util.List;

public class EmpregadoDAO {
    private static EntityManagerFactory emf;
    public void iniciar(){emf = Persistence.createEntityManagerFactory("default");}
    public void encerrar(){emf.close();}
    public int inserirEmp(int codEmpr, String nome, String trabalho, Float gerente, Date contratacao,
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
            em.persist(emp1);
            em.getTransaction().commit();
            return 1;}
//        }catch(PersistenceException e){
//            if(e.getCause().getCause() instanceof EntityExistsException){
//                return 2;
//            }else if(e.getCause().getCause() instanceof ConstraintViolationException){
//                return 3;
//            }else{
//                return -1;
//            }
//        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            em.close();
        }
    }
    public int alterarNome(int codEmpr, String nome){
        EntityManager em = emf.createEntityManager();
        nome = nome.toUpperCase();
        try {
            em.getTransaction().begin();
            Empregado emp= em.find(Empregado.class, codEmpr);
            emp.setNome(nome);
            em.getTransaction().commit();
            return 1;
        }catch(NullPointerException n){
            return 2;
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            em.close();
        }
    }
    public int alterarSalario(int codEmpr, double salario){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empregado emp = em.find(Empregado.class, codEmpr);
            emp.setSalario(salario);
            em.getTransaction().commit();
            return 1;
        }catch(NullPointerException n){
            return 2;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            em.close();
        }
    }


    public int excluirEmp(int codEmpr){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empregado emp = em.find(Empregado.class,codEmpr);
            if(emp !=null) {
                em.remove(emp);
                em.getTransaction().commit();
                return 1;
            }else{
                return 0;
            }
        }catch(PersistenceException r){
            if(r.getCause().getCause() instanceof ConstraintViolationException){
                return 2;
            }else{
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
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
