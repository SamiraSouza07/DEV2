import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="emp")
public class Empregado {
    @Id
    @Column(name = "empno")
    private int codEmpr;

    @Column(name = "ename")
    private String nome;

    @Column(name = "job")
    private String trabalho;

    @Column(name = "mgr")
    private Float gerente;

    @Column(name = "hiredate")
    private Date contratacao;

    @Column(name = "sal")
    private double salario;

    @Column(name = "comm")
    private Float comissao;

    @Column(name = "deptno")
    private int departamento;


    public Empregado(){};
    public Empregado(int codEmpr, String nome, String trabalho, Float gerente, Date contratacao,
                     double salario, Float comissao, int departamento){
        this.codEmpr = codEmpr;
        this.nome = nome;
        this.trabalho = trabalho;
        this.gerente = gerente;
        this.contratacao = contratacao;
        this.salario = salario;
        this.comissao = comissao;
        this.departamento = departamento;
    }

    public int getCodEmpr() {
        return this.codEmpr;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTrabalho() {
        return this.trabalho;
    }

    public Float getGerente() {
        return this.gerente;
    }

    public Date getContratacao() {
        return this.contratacao;
    }

    public double getSalario() {
        return this.salario;
    }

    public Float getComissao() {
        return this.comissao;
    }

    public int getDepartamento() {
        return this.departamento;
    }

    public void setCodEmpr(int codEmpr) {
        this.codEmpr = codEmpr;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTrabalho(String trabalho) {
        this.trabalho = trabalho;
    }

    public void setGerente(Float gerente) {
        this.gerente = gerente;
    }

    public void setContratacao(Date contratacao) {
        this.contratacao = contratacao;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setComissao(Float comissao) {
        this.comissao = comissao;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Código empregado: "+this.codEmpr+"\nNome: "+this.nome+"\nTrabalho: "+this.trabalho
                +"\nGerente: "+this.gerente+"\nData de contratação: "+this.contratacao
                +"\nSalário: "+this.salario+"\nComissão: "+this.comissao+"\nCódigo departamento: "+this.departamento;
    }


}
