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
        ArrayList<Magia> magiasExistentes = new ArrayList<>();
        String sql = "SELECT * FROM Magia";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("🔮 Lista de magias no grimório:");

            while (rs.next()) {
                Magia magiaAux = new Magia(
                        rs.getInt("idMagia"),
                        rs.getString("nomeMagia"),
                        rs.getString("descricaoMagia"),
                        rs.getInt("Padrinhos_idPadrinhos")
                );

                System.out.println("--------------------");
                System.out.println("🆔 ID: " + magiaAux.getIdMagia());
                System.out.println("✨ Nome: " + magiaAux.getNomeMagia());
                System.out.println("📖 Descrição: " + magiaAux.getDescricaoMagia());
                System.out.println("🧙 ID Padrinho: " + magiaAux.getPadrinhos_idPadrinhos());

                magiasExistentes.add(magiaAux);
            }
            System.out.println("🔮 Total de magias: " + magiasExistentes.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao consultar magias: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return magiasExistentes;
    }

    public boolean updateMagia(int idMagia, Magia magia) {
        connectToDb();
        String sql = "UPDATE Magia SET nomeMagia = ?, descricaoMagia = ?, Padrinhos_idPadrinhos = ? WHERE idMagia = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, magia.getNomeMagia());
            pst.setString(2, magia.getDescricaoMagia());
            pst.setInt(3, magia.getPadrinhos_idPadrinhos());
            pst.setInt(4, idMagia);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🔮 Magia #" + idMagia + " reformulada no grimório com sucesso!");
                return true;
            } else {
                System.out.println("📜 Nenhuma magia encontrada com o ID: " + idMagia);
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
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

    public boolean deleteMagia(int idMagia) {
        connectToDb();
        String sql = "DELETE FROM Magia WHERE idMagia = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idMagia);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑️ Magia #" + idMagia + " apagada do grimório permanentemente!");
                return true;
            } else {
                System.out.println("📜 Nenhuma magia encontrada com o ID: " + idMagia);
                return false;
            }

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao apagar magia: " + exc.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
    }

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
                System.out.println("✨ " + nome);
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
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return nomes;
    }

    public ArrayList<String> selectAllDescricoesMagias() {
        connectToDb();
        ArrayList<String> descricoes = new ArrayList<>();
        String sql = "SELECT descricaoMagia FROM Magia";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("📖 Descrições das Magias:");
            System.out.println("--------------------");

            while (rs.next()) {
                String descricao = rs.getString("descricaoMagia");
                descricoes.add(descricao);
                System.out.println("📝 " + descricao);
            }

            System.out.println("--------------------");
            System.out.println("✨ Total de descrições: " + descricoes.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao consultar descrições: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return descricoes;
    }

    public ArrayList<Integer> selectAllIdsPadrinhos() {
        connectToDb();
        ArrayList<Integer> idsPadrinhos = new ArrayList<>();
        String sql = "SELECT Padrinhos_idPadrinhos FROM Magia";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🧙‍♂️ IDs dos Padrinhos:");
            System.out.println("--------------------");

            while (rs.next()) {
                int idPadrinho = rs.getInt("Padrinhos_idPadrinhos");
                idsPadrinhos.add(idPadrinho);
                System.out.println("🆔 " + idPadrinho);
            }

            System.out.println("--------------------");
            System.out.println("✨ Total de padrinhos: " + idsPadrinhos.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro ao consultar IDs: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
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
            JOIN Padrinhos AS P ON M.Padrinhos_idPadrinhos = P.idPadrinhos
            JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca
            """;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("🔮 Relação Magias-Padrinhos-Crianças:");
            System.out.println("══════════════════════════════════════");

            while (rs.next()) {
                String linha = "🧙 " + rs.getString("nomePadrinho") +
                        " 👶 " + rs.getString("nomeCrianca") +
                        " ✨ " + rs.getString("nomeMagia") +
                        " 📖 " + rs.getString("descricaoMagia");
                resultados.add(linha);
                System.out.println(linha);
            }

            System.out.println("📊 Total de relações: " + resultados.size());

        } catch (SQLException exc) {
            System.out.println("💥 Erro na consulta: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return resultados;
    }

    public Magia selectMagiaById(int idMagia) {
        connectToDb();
        Magia magia = null;
        String sql = "SELECT * FROM Magia WHERE idMagia = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idMagia);
            rs = pst.executeQuery();

            if (rs.next()) {
                magia = new Magia(
                        rs.getInt("idMagia"),
                        rs.getString("nomeMagia"),
                        rs.getString("descricaoMagia"),
                        rs.getInt("Padrinhos_idPadrinhos")
                );

                System.out.println("🔮 Magia encontrada:");
                System.out.println("🆔 ID: " + magia.getIdMagia());
                System.out.println("✨ Nome: " + magia.getNomeMagia());
                System.out.println("📖 Descrição: " + magia.getDescricaoMagia());
                System.out.println("🧙 ID Padrinho: " + magia.getPadrinhos_idPadrinhos());
            } else {
                System.out.println("📜 Nenhuma magia encontrada com ID: " + idMagia);
            }
        } catch (SQLException exc) {
            System.out.println("💥 Erro na busca: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException exc) {
                System.out.println("💥 Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return magia;
    }
}