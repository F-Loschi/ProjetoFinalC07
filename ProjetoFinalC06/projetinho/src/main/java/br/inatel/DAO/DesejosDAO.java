package br.inatel.DAO;

import br.inatel.Model.Personagens.Desejos;

import java.sql.*;
import java.util.ArrayList;

public class DesejosDAO extends ConnectionDao {
    // ... outros métodos permanecem iguais ...

    public boolean insertDesejo(Desejos desejo) {
        connectToDb();
        String sql = "INSERT INTO Desejos (descricao, statusDesejo) VALUES (?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricao());
            pst.setBoolean(2, desejo.isStatusDesejo());

            pst.execute();
            System.out.println("🌟 Novo desejo registrado no livro mágico!");
            return true;
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao registrar desejo: " + exc.getMessage());
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

    public boolean updateDesejo(int idDesejos, Desejos desejo) {
        connectToDb();
        String sql = "UPDATE Desejos SET descricao = ?, statusDesejo = ? WHERE idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, desejo.getDescricao());
            pst.setBoolean(2, desejo.isStatusDesejo());
            pst.setInt(3, idDesejos);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✨ Desejo #" + idDesejos + " atualizado com sucesso no livro mágico!");
                return true;
            } else {
                System.out.println("📝 Nenhum desejo encontrado com o ID fornecido: " + idDesejos);
                return false;
            }
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao atualizar desejo: " + exc.getMessage());
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

    public ArrayList<Desejos> selectDesejos() {
        connectToDb();
        ArrayList<Desejos> desejos = new ArrayList<>();
        String sql = "SELECT * FROM Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🌟 Consultando livro mágico de desejos:");

            while (rs.next()) {
                Desejos desejoAux = new Desejos(
                        rs.getInt("idDesejos"),
                        rs.getString("descricao"),
                        rs.getBoolean("statusDesejo")
                );

                String status = desejoAux.isStatusDesejo() ? "✅ Concedido" : "❌ Não concedido";
                System.out.println("📝 ID: " + desejoAux.getIdDesejos() + " | Desejo: " +
                        desejoAux.getDescricao() + " | Status: " + status);
                System.out.println("--------------------");
                desejos.add(desejoAux);
            }
            System.out.println("🌟 Total de desejos no livro: " + desejos.size());
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao consultar livro de desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return desejos;
    }

    public ArrayList<Boolean> selectAllStatusDesejos() {
        connectToDb();
        ArrayList<Boolean> status = new ArrayList<>();
        String sql = "SELECT statusDesejo FROM Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("✨ Lista de status dos desejos:");
            while (rs.next()) {
                boolean statusDesejo = rs.getBoolean("statusDesejo");
                status.add(statusDesejo);
                String statusTexto = statusDesejo ? "Concedido ✅" : "Não concedido ❌";
                System.out.println("🎯 " + statusTexto);
            }
            System.out.println("🌟 Total de status listados: " + status.size());
            System.out.println("--------------------");

        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar status dos desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return status;
    }

    public Desejos selectDesejoById(int idDesejos) {
        connectToDb();
        Desejos desejo = null;
        String sql = "SELECT * FROM Desejos WHERE idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idDesejos);
            rs = pst.executeQuery();

            if (rs.next()) {
                desejo = new Desejos(
                        rs.getInt("idDesejos"),
                        rs.getString("descricao"),
                        rs.getBoolean("statusDesejo")
                );

                System.out.println("📝 Desejo encontrado:");
                System.out.println("🆔 ID: " + desejo.getIdDesejos());
                System.out.println("📄 Descrição: " + desejo.getDescricao());
                System.out.println("🎯 Status: " + (desejo.isStatusDesejo() ? "Concedido ✅" : "Não concedido ❌"));
            } else {
                System.out.println("🔍 Nenhum desejo encontrado com ID: " + idDesejos);
            }
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar desejo por ID: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return desejo;
    }

    public ArrayList<String> selectAllDescricoesDesejos() {
        connectToDb();
        ArrayList<String> descricoes = new ArrayList<>();
        String sql = "SELECT descricao FROM Desejos";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📝 Lista de descrições de desejos:");
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                descricoes.add(descricao);
                System.out.println("✨ " + descricao);
            }
            System.out.println("🌟 Total de descrições listadas: " + descricoes.size());
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao buscar descrições de desejos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return descricoes;
    }

    public boolean deleteDesejo(int idDesejos) {
        connectToDb();
        String sql = "DELETE FROM Desejos WHERE idDesejos = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idDesejos);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑️ Desejo #" + idDesejos + " removido com sucesso!");
                return true;
            } else {
                System.out.println("🔍 Nenhum desejo encontrado com ID: " + idDesejos);
                return false;
            }
        } catch (SQLException exc) {
            System.out.println("📖 Erro ao remover desejo: " + exc.getMessage());
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