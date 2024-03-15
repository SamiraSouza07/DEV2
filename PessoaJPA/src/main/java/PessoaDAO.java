import jakarta.persistence.*;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class PessoaDAO {
    EntityManagerFactory emf;
    public void iniciar(){
        emf = Persistence.createEntityManagerFactory("default");
    }
    public void fechar(){
        emf.close();
    }
    public int inserir(int id, String nome, int idade, double altura, double peso, String genero, String estado, int musica, int cinema, int leitura, int esportes){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Pessoa pessoa = new Pessoa(id,nome,idade,altura,peso,genero,estado,musica,cinema,leitura,esportes);
            em.persist(pessoa);
            em.getTransaction().commit();
            return 1;
        }catch(RollbackException r){
            return -1;
        }finally {
            em.close();
        }
    }
    public int excluir(int id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class,id);
            if(pessoa!=null){
                em.remove(pessoa);
                em.getTransaction().commit();
                return 1;
            }else{
                return 0;
            }
        }catch (PersistenceException p){
            if(p.getCause().getCause() instanceof ConstraintViolationException){
                return 2;
            }else{
                return -1;
            }
        }
        catch(NullPointerException n){
            return 0;
        }
    }

    public int alterar(int id, String campo, String valorNovo){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class,id);
            if(pessoa!=null) {
                if (campo.equalsIgnoreCase("nome")) {
                    pessoa.setNome(valorNovo);
                } else if (campo.equalsIgnoreCase("genero")) {
                    pessoa.setGenero(valorNovo);
                } else if (campo.equalsIgnoreCase("estado")) {
                    pessoa.setEstado(valorNovo);
                } else {
                    int valorNovoInt = Integer.parseInt(valorNovo);
                    if (campo.equalsIgnoreCase("idade")) {
                        pessoa.setIdade(valorNovoInt);
                    } else if (campo.equalsIgnoreCase("musica")) {
                        pessoa.setMusica(valorNovoInt);
                    } else if (campo.equalsIgnoreCase("cinema")) {
                        pessoa.setCinema(valorNovoInt);
                    } else if (campo.equalsIgnoreCase("leitura")) {
                        pessoa.setLeitura(valorNovoInt);
                    } else if (campo.equalsIgnoreCase("esportes")) {
                        pessoa.setEsportes(valorNovoInt);
                    } else {
                        double valorNovoDouble = Double.parseDouble(valorNovo);
                        if (campo.equalsIgnoreCase("altura")) {
                            pessoa.setAltura(valorNovoDouble);
                        } else if (campo.equalsIgnoreCase("peso")) {
                            pessoa.setPeso(valorNovoDouble);
                        }
                    }
                }
                em.getTransaction().commit();
                return 1;
            }else{
                return 0;
            }
        }catch(Exception e){
            return -1;
        }
    }


    public List buscarPorFiltro(String campo, String valor){
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select pessoa from Pessoa pessoa order by pessoa.id");
            if(campo.equalsIgnoreCase("id")) {
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.id = :valor order by pessoa.id");
                query.setParameter("valor", Integer.parseInt(valor));
            }else if(campo.equalsIgnoreCase("idade")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.idade = :valor order by pessoa.id");
                query.setParameter("valor", Integer.parseInt(valor));
            }else if(campo.equalsIgnoreCase("musica")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.musica = :valor order by pessoa.id");
                query.setParameter("valor", Integer.parseInt(valor));
            }
            else if(campo.equalsIgnoreCase("cinema")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.cinema = :valor order by pessoa.id");
                query.setParameter("valor", Integer.parseInt(valor));
            }
            else if(campo.equalsIgnoreCase("leitura")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.leitura = :valor order by pessoa.id");
                query.setParameter("valor", Integer.parseInt(valor));
            }
            else if(campo.equalsIgnoreCase("esportes")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.esportes = :valor order by pessoa.id");
                query.setParameter("valor", Integer.parseInt(valor));
            }
            else if(campo.equalsIgnoreCase("nome")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.nome = :valor order by pessoa.id");
                query.setParameter("valor", valor);
            }
            else if(campo.equalsIgnoreCase("genero")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.genero = :valor order by pessoa.id");
                query.setParameter("valor", valor);
            }
            else if(campo.equalsIgnoreCase("estado")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.estado = :valor order by pessoa.id");
                query.setParameter("valor", valor);
            }
            else if(campo.equalsIgnoreCase("altura")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.altura = :valor order by pessoa.id");
                query.setParameter("valor", Double.parseDouble(valor));
            }
            else if(campo.equalsIgnoreCase("peso")){
                query = em.createQuery("select pessoa from Pessoa pessoa where pessoa.peso = :valor order by pessoa.id");
                query.setParameter("valor", Double.parseDouble(valor));
            }
            List<Pessoa> pessoas = query.getResultList();
            return pessoas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }
}
