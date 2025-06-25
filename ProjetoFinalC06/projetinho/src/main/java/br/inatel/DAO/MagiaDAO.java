package br.inatel.DAO;

import br.inatel.Model.Personagens.Magia;

import java.sql.*;
import java.util.ArrayList;

public class MagiaDAO extends ConnectionDao {
    @Override
    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("✨ Conexão encantada! Fluxo mágico ativado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("💥 Erro no fluxo mágico: " + exc.getMessage());
        }
    }

    public boolean insertMagia(Magia magia) {
        connectToDb();
        String sql = "INSERT INTO Magia (nomeMagia, descricaoMagia, Padrinhos_idPadrinhos) VALUES (?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, magia.getNomeMagia());
            pst.setString(2, magia.getDescricaoMagia());
            pst.setInt(3, magia.getPadrinhos_idPadrinhos());

            pst.execute();
            System.out.println("✨ Nova magia inscrita no grimório!");
            return true;

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao inscrever magia: " + exc.getMessage());
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

    public ArrayList<Magia> selectMagia() {
        connectToDb();

        ArrayList<Magia> MagiasExistentes = new ArrayList<>();
        String sql = "SELECT * FROM Magia";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de usuários:");
            while (rs.next()) {
                Magia MagiasAux = new Magia(rs.getString("nomeMagia"), rs.getString("descricaoMagia"), rs.getInt("Padrinhos_idPadrinhos"));
                System.out.println("--------------------");
                System.out.println("Nome: " + MagiasAux.getNomeMagia());
                MagiasExistentes.add(MagiasAux);
            }
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return MagiasExistentes;
    }

    public boolean updateMagia(int id, Magia magia) {
        connectToDb();
        String sql = "UPDATE Magia SET nomeMagia = ?, descricaoMagia = ?, Padrinhos_idPadrinhos = ? WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, magia.getNomeMagia());
            pst.setString(2, magia.getDescricaoMagia());
            pst.setInt(3, magia.getPadrinhos_idPadrinhos());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🔮 Magia reformulada no grimório com sucesso!");
                return true;
            } else {
                System.out.println("📜 Nenhuma magia encontrada com o ID especificado.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao reformular magia: " + exc.getMessage());
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

    public boolean deleteMagia(int id) {
        connectToDb();
        String sql = "DELETE FROM Magia WHERE id = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑️ Magia apagada do grimório permanentemente!");
                return true;
            } else {
                System.out.println("📜 Nenhuma magia encontrada com o ID especificado para remoção.");
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao apagar magia do grimório: " + exc.getMessage());
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

    // Método específico para buscar todos os nomes das magias
    public ArrayList<String> selectAllNomesMagias() {
        connectToDb();

        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT nomeMagia FROM Magia";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📚 Índice de Magias do Grimório:");
            System.out.println("--------------------");

            while (rs.next()) {
                String nome = rs.getString("nomeMagia");
                nomes.add(nome);
                System.out.println("✨ Magia: " + nome);
            }

            System.out.println("--------------------");
            System.out.println("🔮 Total de magias catalogadas: " + nomes.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao consultar nomes das magias: " + exc.getMessage());
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

    // Método específico para buscar todas as descrições das magias
    public ArrayList<String> selectAllDescricoesMagias() {
        connectToDb();

        ArrayList<String> descricoes = new ArrayList<>();
        String sql = "SELECT descricaoMagia FROM Magia";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📖 Descrições das Magias do Grimório:");
            System.out.println("--------------------");

            while (rs.next()) {
                String descricao = rs.getString("descricaoMagia");
                descricoes.add(descricao);
                System.out.println("📝 Descrição: " + descricao);
            }

            System.out.println("--------------------");
            System.out.println("✨ Total de descrições catalogadas: " + descricoes.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao consultar descrições das magias: " + exc.getMessage());
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

    // Método específico para buscar todos os IDs dos Padrinhos
    public ArrayList<Integer> selectAllIdsPadrinhos() {
        connectToDb();

        ArrayList<Integer> idsPadrinhos = new ArrayList<>();
        String sql = "SELECT Padrinhos_idPadrinhos FROM Magia";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧙‍♂️ IDs dos Padrinhos Mágicos:");
            System.out.println("--------------------");

            while (rs.next()) {
                int idPadrinho = rs.getInt("Padrinhos_idPadrinhos");
                idsPadrinhos.add(idPadrinho);
                System.out.println("👨‍🎓 Padrinho ID: " + idPadrinho);
            }

            System.out.println("--------------------");
            System.out.println("✨ Total de padrinhos registrados: " + idsPadrinhos.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao consultar IDs dos Padrinhos: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }

        return idsPadrinhos;
    }

    public ArrayList<String> selectMagiasComPadrinhosECriancas() {
        connectToDb();
        ArrayList<String> resultados = new ArrayList<>();

        String sql = """
                SELECT P.nomePadrinho, C.nomeCrianca, M.nomeMagia, M.descricaoMagia
                FROM Magia AS M
                JOIN Padrinhos AS P ON M.Padrinhos_idPadrinhos = P.id
                JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca
                """;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🔮🧚‍♀️👶 Magias + Padrinhos + Crianças:");
            System.out.println("═══════════════════════════════════════════");

            while (rs.next()) {
                String linha = "Padrinho: " + rs.getString("nomePadrinho") +
                        " | Criança: " + rs.getString("nomeCrianca") +
                        " | Magia: " + rs.getString("nomeMagia") +
                        " | Descrição: " + rs.getString("descricaoMagia");
                resultados.add(linha);
                System.out.println(linha);
            }

            System.out.println("📊 Total de relações encontradas: " + resultados.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao buscar relação Magias+Padrinhos+Crianças: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return resultados;
    }
}
