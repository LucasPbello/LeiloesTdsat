
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;




public class conectaDAO {
    
    Connection conn;
    PreparedStatement st;
    
       public boolean connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exemplo_senac","root", "root");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
       public int salvar(ProdutosDTO prod){
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO ProdutosDTO (nome, valor) VALUES(?,?)");
            st.setString(1,prod.getNome());
            st.setInt(2,prod.getValor());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
}