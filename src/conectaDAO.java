
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class conectaDAO {

    Connection conn;
    PreparedStatement st;

    public boolean connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exemplo_senac", "root", "root");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }

    public int salvar(ProdutosDTO prod) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO ProdutosDTO (nome, valor) VALUES(?,?)");
            st.setString(1, prod.getNome());
            st.setInt(2, prod.getValor());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public List<ProdutosDTO> getProdutosDTO() {

        List<ProdutosDTO> listaProdutosDTO = new ArrayList<>();
        String sql = "SELECT * FROM podcast";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO p = new ProdutosDTO();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));

                listaProdutosDTO.add(p);
            }
            return listaProdutosDTO;

            //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"
        } catch (Exception e) {
            return null;
        }
    }
}
