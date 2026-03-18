
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        conectaDAO conecta = new conectaDAO();

        try {
            if (conecta.connectDB()) {

                PreparedStatement stmt = conecta.getConnection().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Produto vendido!");

                conecta.desconectar();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarVendidos() {

        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        conectaDAO conecta = new conectaDAO();

        try {
            if (conecta.connectDB()) {

                PreparedStatement stmt = conecta.getConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ProdutosDTO p = new ProdutosDTO();

                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setValor(rs.getInt("valor"));
                    p.setStatus(rs.getString("status"));

                    lista.add(p);
                }

                conecta.desconectar();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }

        return lista;
    }

}
