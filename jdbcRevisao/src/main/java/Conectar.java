import java.sql.*;

public class Conectar {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public boolean conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://kesavan.db.elephantsql.com:5432/vzjzymbp", "vzjzymbp", "rrt9ofqoeZbMJ10lIW4WQb5MDm4ZF6m3");
            return true;
        }catch(SQLException | ClassNotFoundException s){
            s.printStackTrace();
            return false;
        }
    }

    public void desconectar(){
        try {
            conn.close();
        }catch (SQLException s){
            s.printStackTrace();
        }
    }

    public boolean inserir(int custid, String name, String address, String city, String state,
                           String zip, int area, String phone,int repid, double creditlimit, String comments ){
        try{
            boolean coneccao = conectar();
            if (coneccao){
                pstmt = conn.prepareStatement("INSERT INTO CUSTOMER (custid, name, address, city, state, zip, area, phone, repid, creditlimit, comments) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                pstmt.setInt(1, custid);
                pstmt.setString(2,name);
                pstmt.setString(3,address);
                pstmt.setString(4,city);
                pstmt.setString(5,state);
                pstmt.setString(6,zip);
                pstmt.setInt(7,area);
                pstmt.setString(8,phone);
                pstmt.setInt(9,repid);
                pstmt.setDouble(10,creditlimit);
                pstmt.setString(11,comments);
                pstmt.execute();
                return true;
            }
        }catch (SQLException s){
            s.printStackTrace();
            return false;
        }finally {
            desconectar();
        }
        return false;
    }

    public boolean excluir(int custid){
        try{
            boolean coneccao = conectar();
            if(coneccao){
                pstmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE custid = ?");
                pstmt.setInt(1,custid);
                pstmt.execute();
                return true;
            }
        }catch (SQLException s){
            s.printStackTrace();
            return false;
        }finally {
            desconectar();
        }
        return false;
    }

    public boolean alterar(int custid, String novoValor){
        try{
            boolean coneccao = conectar();
            if(coneccao){
                pstmt = conn.prepareStatement("UPDATE CUSTOMER SET name = ? WHERE custid = ?");
                pstmt.setString(1, novoValor);
                pstmt.setInt(2,custid);
                pstmt.execute();
                return true;
            }
        }catch (SQLException s){
            s.printStackTrace();
            return false;
        }finally {
            desconectar();
        }
        return false;
    }


    public ResultSet consultar(){
        try{
            boolean coneccao = conectar();
            if(coneccao){
                pstmt = conn.prepareStatement("SELECT * FROM customer ORDER BY custid");
                rs = pstmt.executeQuery();
                return rs;
            }
        }catch (SQLException s){
            s.printStackTrace();
        }finally {
            desconectar();
        }
        return null;
    }


}
