package br.inatel.DAO;

import br.inatel.Model.Personagens.Varinha;

import java.sql.*;
import java.util.ArrayList;

public class VarinhaDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("🪄 Conexão mágica! Arsenal de varinhas acessível!");
        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao acessar arsenal de varinhas: " + exc.getMessage());
        }
    }

    // Método otimizado para mostrar TODOS os atributos de TODAS as varinhas
    public ArrayList<Varinha> selectAllVarinhas() {
        connectToDb();

        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha ORDER BY idSerial";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🪄✨ === ARSENAL COMPLETO DE VARINHAS MÁGICAS === ✨🪄");
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                // Mostrando TODOS os dados de cada varinha
                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor da Varinha: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("═══════════════════════════════════════════");
                System.out.println();

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas no arsenal: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao consultar arsenal completo: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para buscar varinha específica por ID Serial
    public Varinha selectVarinhaById(int idSerial) {
        connectToDb();
        Varinha varinha = null;
        String sql = "SELECT * FROM Varinha WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idSerial);
            rs = pst.executeQuery();

            if (rs.next()) {
                varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔍 Varinha encontrada no arsenal:");
                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com ID Serial: " + idSerial);
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinha por ID: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinha;
    }

    // Método para buscar varinhas por cor
    public ArrayList<Varinha> selectVarinhasByCor(String cor) {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha WHERE varinhaCor = ? ORDER BY idSerial";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cor);
            rs = pst.executeQuery();

            System.out.println("🎨 Varinhas da cor: " + cor);
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("─────────────────────────────────────────");

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas " + cor + " encontradas: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas por cor: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para buscar varinhas por status
    public ArrayList<Varinha> selectVarinhasByStatus(String status) {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha WHERE statusVarinha = ? ORDER BY idSerial";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, status);
            rs = pst.executeQuery();

            System.out.println("⚡ Varinhas com status: " + status);
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("─────────────────────────────────────────");

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas com status '" + status + "': " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas por status: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    // Método para buscar varinhas disponíveis (não atribuídas)
    public ArrayList<Varinha> selectVarinhasDisponiveis() {
        connectToDb();
        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha WHERE statusVarinha = 'Disponível' ORDER BY varinhaCor, idSerial";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🆓 Varinhas Disponíveis no Arsenal:");
            System.out.println();

            while (rs.next()) {
                Varinha varinha = new Varinha(
                        rs.getString("varinhaCor"),
                        rs.getString("statusVarinha")
                );

                System.out.println("🔢 ID Serial: " + rs.getInt("idSerial"));
                System.out.println("🎨 Cor: " + rs.getString("varinhaCor"));
                System.out.println("⚡ Status: " + rs.getString("statusVarinha"));
                System.out.println("─────────────────────────────────────────");

                varinhas.add(varinha);
            }

            System.out.println("📊 Total de varinhas disponíveis: " + varinhas.size());

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao buscar varinhas disponíveis: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return varinhas;
    }

    public boolean insertVarinha(Varinha varinha) {
        connectToDb();
        String sql = "INSERT INTO Varinha (varinhaCor, statusVarinha) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getVarinhaCor());
            pst.setString(2, varinha.getStatusVarinha());

            pst.execute();
            System.out.println("🪄 Nova varinha forjada no arsenal mágico!");
            return true;

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao forjar varinha: " + exc.getMessage());
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

    public boolean updateVarinha(int id, Varinha varinha) {
        connectToDb();
        String sql = "UPDATE Varinha SET varinhaCor = ?, statusVarinha = ? WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getVarinhaCor());
            pst.setString(2, varinha.getStatusVarinha());
            pst.setInt(3, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Varinha reencantada no arsenal mágico!");
                return true;
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com o ID Serial especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao reencatar varinha: " + exc.getMessage());
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

    public boolean deleteVarinha(int id) {
        connectToDb();
        String sql = "DELETE FROM Varinha WHERE idSerial = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💔 Varinha removida do arsenal mágico permanentemente!");
                return true;
            } else {
                System.out.println("🔍 Nenhuma varinha encontrada com o ID Serial especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("⚡ Erro ao remover varinha do arsenal: " + exc.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return false;
    }

    // Método original renomeado para compatibilidade
    public ArrayList<Varinha> selectVarinha() {
        return selectAllVarinhas();
    }
}