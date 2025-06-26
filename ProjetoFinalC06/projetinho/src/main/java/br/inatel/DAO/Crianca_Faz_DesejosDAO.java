package br.inatel.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crianca_Faz_DesejosDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("⭐ Conexão realizada! Portal de desejos ativo!");
        } catch (SQLException exc) {
            System.out.println("💔 Erro ao conectar portal de desejos: " + exc.getMessage());
        }
    }

    public boolean insertCriancaDesejo(int idCrianca, int idDesejo) {
        connectToDb();
        // Alterado para CriancaFazDesejos
        String sql = "INSERT INTO CriancaFazDesejos (Crianca_idCrianca, Desejos_idDesejos) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);
            pst.setInt(2, idDesejo);

            pst.execute();
            System.out.println("⭐ Desejo vinculado à criança! Portal ativo!");
            return true;

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao ativar portal de desejos: " + exc.getMessage());
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

    public ArrayList<String> selectCriancaDesejos() {
        connectToDb();
        ArrayList<String> relacionamentos = new ArrayList<>();
        // Alterado para CriancaFazDesejos
        String sql = "SELECT * FROM CriancaFazDesejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("⭐ Consultando portal de desejos:");

            while (rs.next()) {
                int idCrianca = rs.getInt("Crianca_idCrianca");
                int idDesejo = rs.getInt("Desejos_idDesejos");
                String relacionamento = "Criança ID: " + idCrianca + " | Desejo ID: " + idDesejo;

                System.out.println("🌟 " + relacionamento);
                System.out.println("--------------------");
                relacionamentos.add(relacionamento);
            }
            System.out.println("⭐ Total de desejos vinculados: " + relacionamentos.size());

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao consultar portal de desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return relacionamentos;
    }

    // Demais métodos devem seguir o mesmo padrão de alteração...
    // Todos os outros métodos que contêm "Crianca_Faz_Desejos" devem ser alterados para "CriancaFazDesejos"

    public boolean updateCriancaDesejo(int idCriancaAntiga, int idDesejoAntigo, int idCriancaNova, int idDesejoNovo) {
        connectToDb();
        // Alterado para CriancaFazDesejos
        String sql = "UPDATE CriancaFazDesejos SET Crianca_idCrianca = ?, Desejos_idDesejos = ? WHERE Crianca_idCrianca = ? AND Desejos_idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriancaNova);
            pst.setInt(2, idDesejoNovo);
            pst.setInt(3, idCriancaAntiga);
            pst.setInt(4, idDesejoAntigo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✨ Portal de desejos reconfigurado com sucesso!");
                return true;
            } else {
                System.out.println("🔍 Relacionamento não encontrado para atualização!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao reconfigurar portal: " + exc.getMessage());
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

    public boolean transferirDesejo(int idDesejoTransferir, int idCriancaOrigem, int idCriancaDestino) {
        connectToDb();
        // Alterado para CriancaFazDesejos
        String sql = "UPDATE CriancaFazDesejos SET Crianca_idCrianca = ? WHERE Desejos_idDesejos = ? AND Crianca_idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCriancaDestino);
            pst.setInt(2, idDesejoTransferir);
            pst.setInt(3, idCriancaOrigem);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🎁 Desejo transferido com sucesso entre crianças!");
                return true;
            } else {
                System.out.println("🔍 Desejo não encontrado para transferência!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao transferir desejo: " + exc.getMessage());
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

    public boolean deleteCriancaDesejo(int idCrianca, int idDesejo) {
        connectToDb();
        // Alterado para CriancaFazDesejos
        String sql = "DELETE FROM CriancaFazDesejos WHERE Crianca_idCrianca = ? AND Desejos_idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);
            pst.setInt(2, idDesejo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💫 Desejo desvinculado da criança! Portal fechado!");
                return true;
            } else {
                System.out.println("🔍 Relacionamento não encontrado para remoção!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao desvincular desejo: " + exc.getMessage());
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

    public boolean deleteDesejosPorCrianca(int idCrianca) {
        connectToDb();
        // Alterado para CriancaFazDesejos
        String sql = "DELETE FROM CriancaFazDesejos WHERE Crianca_idCrianca = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idCrianca);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🌟 Todos os desejos da criança ID " + idCrianca + " foram removidos!");
                System.out.println("📊 Total de desejos removidos: " + rowsAffected);
                return true;
            } else {
                System.out.println("🔍 Nenhum desejo encontrado para a criança ID " + idCrianca + "!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao remover desejos da criança: " + exc.getMessage());
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

    public boolean deleteDesejoDeTodas(int idDesejo) {
        connectToDb();
        // Alterado para CriancaFazDesejos
        String sql = "DELETE FROM CriancaFazDesejos WHERE Desejos_idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idDesejo);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("💫 Desejo ID " + idDesejo + " foi removido de todas as crianças!");
                System.out.println("📊 Total de relacionamentos removidos: " + rowsAffected);
                return true;
            } else {
                System.out.println("🔍 Desejo ID " + idDesejo + " não estava vinculado a nenhuma criança!");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💔 Erro ao remover desejo de todas as crianças: " + exc.getMessage());
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
}