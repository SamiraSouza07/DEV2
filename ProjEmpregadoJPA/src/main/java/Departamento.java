import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dept")
public class Departamento {
    @Id
    @Column(name="deptno")
    private int codDept;

    @Column(name="dname")
    private String nomeDept;

    private String loc;

    public int getCodDept() {
        return this.codDept;
    }

    public String getNomeDept() {
        return this.nomeDept;
    }

    public String getLoc() {
        return this.loc;
    }

    public void setCodDept(int codDept) {
        this.codDept = codDept;
    }

    public void setNomeDept(String nomeDept) {
        this.nomeDept = nomeDept;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Cód.: "+this.codDept+" Departamento: "+this.nomeDept;
    }
}
