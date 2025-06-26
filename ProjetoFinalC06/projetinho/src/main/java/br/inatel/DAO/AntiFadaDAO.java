package br.inatel.DAO;

import br.inatel.Model.Personagens.AntiFada;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AntiFadaDAO extends ConnectionDao {
    // AntiFadaDAO.java
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("⚔️ Conexão estabelecida com sucesso! Anti-Fadas prontas para a batalha!");
        } catch (SQLException exc) {
            System.out.println("❌ Erro na conexão das Anti-Fadas: " + exc.getMessage());
        }
    }

    public boolean insertAntiFada(AntiFada antiFada) {
        connectToDb();
        String sql = "INSERT INTO AntiFada (nomeFada, tipoFada, Varinha_idSerial) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, antiFada.getNomeFada());
            pst.setString(2, antiFada.getTipoFada());
            pst.setInt(3, antiFada.getVarinha_idSerial());

            pst.execute();
            System.out.println("⚔️ Nova Anti-Fada registrada no exército das trevas!");
            return true;

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao registrar Anti-Fada: " + exc.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    // Método UPDATE - Atualizar dados da Anti-Fada
    public boolean updateAntiFada(int id, AntiFada antiFada) {
        connectToDb();
        String sql = "UPDATE AntiFada SET nomeFada = ?, tipoFada = ?, Varinha_idSerial = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, antiFada.getNomeFada());
            pst.setString(2, antiFada.getTipoFada());
            pst.setInt(3, antiFada.getVarinha_idSerial());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚡ Anti-Fada " + antiFada.getNomeFada() + " teve seus poderes das trevas atualizados!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada com ID " + id + " não foi encontrada no exército das trevas!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao atualizar poderes da Anti-Fada: " + exc.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    public boolean deleteAntiFada(String nomeFada) {
        connectToDb();
        String sql = "DELETE FROM AntiFada WHERE nomeFada = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeFada);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚔️ Anti-Fada " + nomeFada + " foi banida do exército das trevas!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada " + nomeFada + " não foi encontrada no exército!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao banir Anti-Fada: " + exc.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    public boolean deleteAntiFadaById(int id) {
        connectToDb();
        String sql = "DELETE FROM AntiFada WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("⚔️ Anti-Fada com ID " + id + " foi banida do exército das trevas!");
                return true;
            } else {
                System.out.println("🔍 Anti-Fada com ID " + id + " não foi encontrada no exército!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao banir Anti-Fada: " + exc.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    public ArrayList<AntiFada> selectAntiFada() {
        connectToDb();

        ArrayList<AntiFada> antiFadas = new ArrayList<>();
        String sql = "SELECT * FROM AntiFada";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("⚔️ Listando exército das Anti-Fadas:");
            while (rs.next()) {
                AntiFada antiFadaAux = new AntiFada(
                        rs.getString("nomeFada"),
                        rs.getString("tipoFada"),
                        rs.getInt("Varinha_idSerial")
                );
                System.out.println("👹 Nome: " + antiFadaAux.getNomeFada() + " | Tipo: " + antiFadaAux.getTipoFada());
                System.out.println("--------------------");
                antiFadas.add(antiFadaAux);
            }
            System.out.println("⚔️ Total de Anti-Fadas no exército: " + antiFadas.size());
        } catch (SQLException exc) {
            System.out.println("❌ Erro ao consultar exército das Anti-Fadas: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return antiFadas;
    }

    // Método SELECT - Buscar apenas os NOMES de todas as Anti-Fadas
    public ArrayList<String> selectAllNomesAntiFada() {
        connectToDb();

        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeFada FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📜 Lista de nomes das Anti-Fadas:");
            while (rs.next()) {
                String nome = rs.getString("nomeFada");
                nomes.add(nome);
                System.out.println("👹 " + nome);
            }
            System.out.println("⚔️ Total de nomes listados: " + nomes.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar nomes das Anti-Fadas: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return nomes;
    }

    // Método SELECT - Buscar apenas os TIPOS de todas as Anti-Fadas
    public ArrayList<String> selectAllTiposAntiFada() {
        connectToDb();

        ArrayList<String> tipos = new ArrayList<>();
        String sql = "SELECT tipoFada FROM AntiFada";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🌟 Lista de tipos das Anti-Fadas:");
            while (rs.next()) {
                String tipo = rs.getString("tipoFada");
                tipos.add(tipo);
                System.out.println("🔮 " + tipo);
            }
            System.out.println("⚔️ Total de tipos listados: " + tipos.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("❌ Erro ao buscar tipos das Anti-Fadas: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return tipos;
    }

    public AntiFada selectAntiFadaById(int id) {
        connectToDb();
        AntiFada antiFada = null;
        String sql = "SELECT * FROM AntiFada WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                antiFada = new AntiFada(
                        rs.getString("nomeFada"),
                        rs.getString("tipoFada"),
                        rs.getInt("Varinha_idSerial")
                );
                System.out.println("Anti-Fada encontrada:");
                System.out.println("Nome: " + antiFada.getNomeFada());
                System.out.println("Tipo: " + antiFada.getTipoFada());
                System.out.println("ID Varinha: " + antiFada.getVarinha_idSerial());
            } else {
                System.out.println("Anti-Fada não encontrada com ID: " + id);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao buscar Anti-Fada por ID: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return antiFada;
    }

}