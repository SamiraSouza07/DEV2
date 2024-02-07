import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dept")
public class Departamento {
    @Id
    @Column(name="deptNo")
    private float numDept;
    private String dName;
    private String loc;

    public float getNumDept() {
        return this.numDept;
    }

    public String getdName() {
        return this.dName;
    }

    public void setNumDept(float numDept) {
        this.numDept = numDept;
    }

    public String getLoc() {
        return this.loc;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String toString() {
        return "Número do departamento: "+this.numDept+"\nNome: "+this.dName+"\n Localização: "+this.loc;
    }
}
