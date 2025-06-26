package br.inatel.Model.Uteis;

import br.inatel.DAO.*;
import br.inatel.Model.Personagens.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuBD {
    private Scanner scanner;
    private AntiFadaDAO antiFadaDAO;
    private CriancaDAO criancaDAO;
    private Crianca_Faz_DesejosDAO criancaDesejoDAO;
    private DesejosDAO desejosDAO;
    private GeneralFadaDAO generalFadaDAO;
    private MagiaDAO magiaDAO;
    private PadrinhosDAO padrinhosDAO;
    private VarinhaDAO varinhaDAO;

    public MenuBD() {
        scanner = new Scanner(System.in);
        antiFadaDAO = new AntiFadaDAO();
        criancaDAO = new CriancaDAO();
        criancaDesejoDAO = new Crianca_Faz_DesejosDAO();
        desejosDAO = new DesejosDAO();
        generalFadaDAO = new GeneralFadaDAO();
        magiaDAO = new MagiaDAO();
        padrinhosDAO = new PadrinhosDAO();
        varinhaDAO = new VarinhaDAO();
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n================== MENU DO BANCO DE DADOS ==================");
            System.out.println("\n================== MENU PRINCIPAL ==================");
            System.out.println("1. Gerenciar Crianças");
            System.out.println("2. Gerenciar Padrinhos");
            System.out.println("3. Gerenciar Fadas (Generais)");
            System.out.println("4. Gerenciar Anti-Fadas");
            System.out.println("5. Gerenciar Varinhas");
            System.out.println("6. Gerenciar Magias");
            System.out.println("7. Gerenciar Desejos");
            System.out.println("8. Gerenciar Relacionamentos");
            System.out.println("9. Consultas Especiais");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    menuCriancas();
                    break;
                case 2:
                    menuPadrinhos();
                    break;
                case 3:
                    menuGenerais();
                    break;
                case 4:
                    menuAntiFadas();
                    break;
                case 5:
                    menuVarinhas();
                    break;
                case 6:
                    menuMagias();
                    break;
                case 7:
                    menuDesejos();
                    break;
                case 8:
                    menuRelacionamentos();
                    break;
                case 9:
                    menuConsultasEspeciais();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void menuCriancas() {
        int opcao;
        do {
            System.out.println("\n=== MENU CRIANÇAS ===");
            System.out.println("1. Cadastrar nova criança");
            System.out.println("2. Listar todas as crianças");
            System.out.println("3. Buscar criança por ID");
            System.out.println("4. Atualizar criança");
            System.out.println("5. Remover criança");
            System.out.println("6. Atualizar status de padrinho");
            System.out.println("7. Listar nomes das crianças");
            System.out.println("8. Listar idades das crianças");
            System.out.println("9. Listar endereços das crianças");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCrianca();
                    break;
                case 2:
                    listarCriancas();
                    break;
                case 3:
                    buscarCriancaPorId();
                    break;
                case 4:
                    atualizarCrianca();
                    break;
                case 5:
                    removerCrianca();
                    break;
                case 6:
                    atualizarStatusPadrinho();
                    break;
                case 7:
                    listarNomesCriancas();
                    break;
                case 8:
                    listarIdadesCriancas();
                    break;
                case 9:
                    listarEnderecosCriancas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarCrianca() {
        System.out.println("\n--- Cadastrar Nova Criança ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Tem padrinho? (true/false): ");
        boolean temPadrinho = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Crianca crianca = new Crianca(nome, idade, sexo, temPadrinho, endereco);
        boolean sucesso = criancaDAO.insertCrianca(crianca);

        if (sucesso) {
            System.out.println("Criança cadastrada com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar criança.");
        }
    }

    private void listarCriancas() {
        ArrayList<Crianca> criancas = criancaDAO.selectCrianca();
        if (criancas.isEmpty()) {
            System.out.println("Nenhuma criança cadastrada.");
        }
    }

    private void buscarCriancaPorId() {
        System.out.print("\nDigite o ID da criança: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Crianca crianca = criancaDAO.selectCriancaById(id);
        if (crianca == null) {
            System.out.println("Criança não encontrada com o ID: " + id);
        }
    }

    private void atualizarCrianca() {
        System.out.print("\nDigite o ID da criança a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Tem padrinho? (true/false): ");
        boolean temPadrinho = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Crianca criancaAtualizada = new Crianca(nome, idade, sexo, temPadrinho, endereco);
        boolean sucesso = criancaDAO.updateCrianca(id, criancaAtualizada);

        if (sucesso) {
            System.out.println("Criança atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar criança.");
        }
    }

    private void removerCrianca() {
        System.out.print("\nDigite o ID da criança a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = criancaDAO.deleteCriancaById(id);

        if (sucesso) {
            System.out.println("Criança removida com sucesso!");
        } else {
            System.out.println("Falha ao remover criança.");
        }
    }

    private void atualizarStatusPadrinho() {
        System.out.print("\nDigite o ID da criança: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tem padrinho? (true/false): ");
        boolean temPadrinho = scanner.nextBoolean();
        scanner.nextLine();

        boolean sucesso = criancaDAO.updateTemPadrinho(id, temPadrinho);

        if (sucesso) {
            System.out.println("Status de padrinho atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar status de padrinho.");
        }
    }

    private void listarNomesCriancas() {
        ArrayList<String> nomes = criancaDAO.selectAllNomesCriancas();
        if (nomes.isEmpty()) {
            System.out.println("Nenhuma criança cadastrada.");
        }
    }

    private void listarIdadesCriancas() {
        ArrayList<Integer> idades = criancaDAO.selectAllIdadesCriancas();
        if (idades.isEmpty()) {
            System.out.println("Nenhuma criança cadastrada.");
        }
    }

    private void listarEnderecosCriancas() {
        ArrayList<String> enderecos = criancaDAO.selectAllEnderecosCriancas();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhuma criança cadastrada.");
        }
    }

    private void menuPadrinhos() {
        int opcao;
        do {
            System.out.println("\n=== MENU PADRINHOS ===");
            System.out.println("1. Cadastrar novo padrinho");
            System.out.println("2. Listar todos os padrinhos");
            System.out.println("3. Buscar padrinho por ID");
            System.out.println("4. Atualizar padrinho");
            System.out.println("5. Remover padrinho");
            System.out.println("6. Listar padrinhos por tipo");
            System.out.println("7. Listar padrinhos de uma criança");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPadrinho();
                    break;
                case 2:
                    listarPadrinhos();
                    break;
                case 3:
                    buscarPadrinhoPorId();
                    break;
                case 4:
                    atualizarPadrinho();
                    break;
                case 5:
                    removerPadrinho();
                    break;
                case 6:
                    listarPadrinhosPorTipo();
                    break;
                case 7:
                    listarPadrinhosPorCrianca();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarPadrinho() {
        System.out.println("\n--- Cadastrar Novo Padrinho ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID da Varinha: ");
        int varinhaId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da Criança: ");
        int criancaId = scanner.nextInt();
        scanner.nextLine();

        Padrinhos padrinho = new Padrinhos(nome, tipo, varinhaId, criancaId);
        boolean sucesso = padrinhosDAO.insertPadrinho(padrinho);

        if (sucesso) {
            System.out.println("Padrinho cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar padrinho.");
        }
    }

    private void listarPadrinhos() {
        ArrayList<Padrinhos> padrinhos = padrinhosDAO.selectAllPadrinhos();
        if (padrinhos.isEmpty()) {
            System.out.println("Nenhum padrinho cadastrado.");
        }
    }

    private void buscarPadrinhoPorId() {
        System.out.print("\nDigite o ID do padrinho: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Padrinhos padrinho = padrinhosDAO.selectPadrinhoById(id);
        if (padrinho == null) {
            System.out.println("Padrinho não encontrado com o ID: " + id);
        }
    }

    private void atualizarPadrinho() {
        System.out.print("\nDigite o ID do padrinho a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID da Varinha: ");
        int varinhaId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID da Criança: ");
        int criancaId = scanner.nextInt();
        scanner.nextLine();

        Padrinhos padrinhoAtualizado = new Padrinhos(nome, tipo, varinhaId, criancaId);
        boolean sucesso = padrinhosDAO.updatePadrinho(id, padrinhoAtualizado);

        if (sucesso) {
            System.out.println("Padrinho atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar padrinho.");
        }
    }

    private void removerPadrinho() {
        System.out.print("\nDigite o ID do padrinho a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = padrinhosDAO.deletePadrinho(id);

        if (sucesso) {
            System.out.println("Padrinho removido com sucesso!");
        } else {
            System.out.println("Falha ao remover padrinho.");
        }
    }

    private void listarPadrinhosPorTipo() {
        System.out.print("\nDigite o tipo de padrinho: ");
        String tipo = scanner.nextLine();

        ArrayList<Padrinhos> padrinhos = padrinhosDAO.selectPadrinhosByTipo(tipo);
        if (padrinhos.isEmpty()) {
            System.out.println("Nenhum padrinho encontrado do tipo: " + tipo);
        }
    }

    private void listarPadrinhosPorCrianca() {
        System.out.print("\nDigite o ID da criança: ");
        int idCrianca = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Padrinhos> padrinhos = padrinhosDAO.selectPadrinhosByCrianca(idCrianca);
        if (padrinhos.isEmpty()) {
            System.out.println("Nenhum padrinho encontrado para a criança com ID: " + idCrianca);
        }
    }

    private void menuGenerais() {
        int opcao;
        do {
            System.out.println("\n=== MENU GENERAIS DAS FADAS ===");
            System.out.println("1. Cadastrar novo General");
            System.out.println("2. Listar todos os Generais");
            System.out.println("3. Buscar General por ID");
            System.out.println("4. Atualizar General");
            System.out.println("5. Remover General");
            System.out.println("6. Listar nomes dos Generais");
            System.out.println("7. Listar tipos de Generais");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarGeneral();
                    break;
                case 2:
                    listarGenerais();
                    break;
                case 3:
                    buscarGeneralPorId();
                    break;
                case 4:
                    atualizarGeneral();
                    break;
                case 5:
                    removerGeneral();
                    break;
                case 6:
                    listarNomesGenerais();
                    break;
                case 7:
                    listarTiposGenerais();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarGeneral() {
        System.out.println("\n--- Cadastrar Novo General ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID da Varinha: ");
        int varinhaId = scanner.nextInt();
        scanner.nextLine();

        GeneralFada general = new GeneralFada(nome, tipo, varinhaId);
        boolean sucesso = generalFadaDAO.insertGeneralFada(general);

        if (sucesso) {
            System.out.println("General cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar General.");
        }
    }

    private void listarGenerais() {
        ArrayList<GeneralFada> generais = generalFadaDAO.selectGeneralFada();
        if (generais.isEmpty()) {
            System.out.println("Nenhum General cadastrado.");
        }
    }

    private void buscarGeneralPorId() {
        System.out.print("\nDigite o ID do General: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        GeneralFada general = generalFadaDAO.selectGeneralFadaById(id);
        if (general == null) {
            System.out.println("General não encontrado com o ID: " + id);
        }
    }

    private void atualizarGeneral() {
        System.out.print("\nDigite o ID do General a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID da Varinha: ");
        int varinhaId = scanner.nextInt();
        scanner.nextLine();

        GeneralFada generalAtualizado = new GeneralFada(nome, tipo, varinhaId);
        boolean sucesso = generalFadaDAO.updateGeneralFada(id, generalAtualizado);

        if (sucesso) {
            System.out.println("General atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar General.");
        }
    }

    private void removerGeneral() {
        System.out.print("\nDigite o ID do General a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = generalFadaDAO.deleteGeneralFada(id);

        if (sucesso) {
            System.out.println("General removido com sucesso!");
        } else {
            System.out.println("Falha ao remover General.");
        }
    }

    private void listarNomesGenerais() {
        ArrayList<String> nomes = generalFadaDAO.selectAllNomes();
        if (nomes.isEmpty()) {
            System.out.println("Nenhum General cadastrado.");
        }
    }

    private void listarTiposGenerais() {
        ArrayList<String> tipos = generalFadaDAO.selectAllTipos();
        if (tipos.isEmpty()) {
            System.out.println("Nenhum General cadastrado.");
        }
    }

    private void menuAntiFadas() {
        int opcao;
        do {
            System.out.println("\n=== MENU ANTI-FADAS ===");
            System.out.println("1. Cadastrar nova Anti-Fada");
            System.out.println("2. Listar todas as Anti-Fadas");
            System.out.println("3. Buscar Anti-Fada por ID");
            System.out.println("4. Atualizar Anti-Fada");
            System.out.println("5. Remover Anti-Fada");
            System.out.println("6. Listar nomes das Anti-Fadas");
            System.out.println("7. Listar tipos de Anti-Fadas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAntiFada();
                    break;
                case 2:
                    listarAntiFadas();
                    break;
                case 3:
                    buscarAntiFadaPorId();
                    break;
                case 4:
                    atualizarAntiFada();
                    break;
                case 5:
                    removerAntiFada();
                    break;
                case 6:
                    listarNomesAntiFadas();
                    break;
                case 7:
                    listarTiposAntiFadas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarAntiFada() {
        System.out.println("\n--- Cadastrar Nova Anti-Fada ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID da Varinha: ");
        int varinhaId = scanner.nextInt();
        scanner.nextLine();

        AntiFada antiFada = new AntiFada(nome, tipo, varinhaId);
        boolean sucesso = antiFadaDAO.insertAntiFada(antiFada);

        if (sucesso) {
            System.out.println("Anti-Fada cadastrada com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar Anti-Fada.");
        }
    }

    private void listarAntiFadas() {
        ArrayList<AntiFada> antiFadas = antiFadaDAO.selectAntiFada();
        if (antiFadas.isEmpty()) {
            System.out.println("Nenhuma Anti-Fada cadastrada.");
        }
    }

    private void buscarAntiFadaPorId() {
        System.out.print("\nDigite o ID da Anti-Fada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AntiFada antiFada = antiFadaDAO.selectAntiFadaById(id);
        if (antiFada == null) {
            System.out.println("Anti-Fada não encontrada com o ID: " + id);
        }
    }

    private void atualizarAntiFada() {
        System.out.print("\nDigite o ID da Anti-Fada a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        System.out.print("ID da Varinha: ");
        int varinhaId = scanner.nextInt();
        scanner.nextLine();

        AntiFada antiFadaAtualizada = new AntiFada(nome, tipo, varinhaId);
        boolean sucesso = antiFadaDAO.updateAntiFada(id, antiFadaAtualizada);

        if (sucesso) {
            System.out.println("Anti-Fada atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar Anti-Fada.");
        }
    }

    private void removerAntiFada() {
        System.out.print("\nDigite o ID da Anti-Fada a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = antiFadaDAO.deleteAntiFadaById(id);

        if (sucesso) {
            System.out.println("Anti-Fada removida com sucesso!");
        } else {
            System.out.println("Falha ao remover Anti-Fada.");
        }
    }

    private void listarNomesAntiFadas() {
        ArrayList<String> nomes = antiFadaDAO.selectAllNomesAntiFada();
        if (nomes.isEmpty()) {
            System.out.println("Nenhuma Anti-Fada cadastrada.");
        }
    }

    private void listarTiposAntiFadas() {
        ArrayList<String> tipos = antiFadaDAO.selectAllTiposAntiFada();
        if (tipos.isEmpty()) {
            System.out.println("Nenhuma Anti-Fada cadastrada.");
        }
    }

    private void menuVarinhas() {
        int opcao;
        do {
            System.out.println("\n=== MENU VARINHAS ===");
            System.out.println("1. Cadastrar nova Varinha");
            System.out.println("2. Listar todas as Varinhas");
            System.out.println("3. Buscar Varinha por ID");
            System.out.println("4. Buscar Varinhas por cor");
            System.out.println("5. Buscar Varinhas por status");
            System.out.println("6. Listar Varinhas disponíveis");
            System.out.println("7. Atualizar Varinha");
            System.out.println("8. Remover Varinha");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVarinha();
                    break;
                case 2:
                    listarVarinhas();
                    break;
                case 3:
                    buscarVarinhaPorId();
                    break;
                case 4:
                    buscarVarinhasPorCor();
                    break;
                case 5:
                    buscarVarinhasPorStatus();
                    break;
                case 6:
                    listarVarinhasDisponiveis();
                    break;
                case 7:
                    atualizarVarinha();
                    break;
                case 8:
                    removerVarinha();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarVarinha() {
        System.out.println("\n--- Cadastrar Nova Varinha ---");
        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        Varinha varinha = new Varinha(cor, status);
        boolean sucesso = varinhaDAO.insertVarinha(varinha);

        if (sucesso) {
            System.out.println("Varinha cadastrada com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar Varinha.");
        }
    }

    private void listarVarinhas() {
        ArrayList<Varinha> varinhas = varinhaDAO.selectAllVarinhas();
        if (varinhas.isEmpty()) {
            System.out.println("Nenhuma Varinha cadastrada.");
        }
    }

    private void buscarVarinhaPorId() {
        System.out.print("\nDigite o ID da Varinha: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Varinha varinha = varinhaDAO.selectVarinhaById(id);
        if (varinha == null) {
            System.out.println("Varinha não encontrada com o ID: " + id);
        }
    }

    private void buscarVarinhasPorCor() {
        System.out.print("\nDigite a cor da Varinha: ");
        String cor = scanner.nextLine();

        ArrayList<Varinha> varinhas = varinhaDAO.selectVarinhasByCor(cor);
        if (varinhas.isEmpty()) {
            System.out.println("Nenhuma Varinha encontrada com a cor: " + cor);
        }
    }

    private void buscarVarinhasPorStatus() {
        System.out.print("\nDigite o status da Varinha: ");
        String status = scanner.nextLine();

        ArrayList<Varinha> varinhas = varinhaDAO.selectVarinhasByStatus(status);
        if (varinhas.isEmpty()) {
            System.out.println("Nenhuma Varinha encontrada com o status: " + status);
        }
    }

    private void listarVarinhasDisponiveis() {
        ArrayList<Varinha> varinhas = varinhaDAO.selectVarinhasDisponiveis();
        if (varinhas.isEmpty()) {
            System.out.println("Nenhuma Varinha disponível.");
        }
    }

    private void atualizarVarinha() {
        System.out.print("\nDigite o ID da Varinha a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        Varinha varinhaAtualizada = new Varinha(cor, status);
        boolean sucesso = varinhaDAO.updateVarinha(id, varinhaAtualizada);

        if (sucesso) {
            System.out.println("Varinha atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar Varinha.");
        }
    }

    private void removerVarinha() {
        System.out.print("\nDigite o ID da Varinha a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = varinhaDAO.deleteVarinha(id);

        if (sucesso) {
            System.out.println("Varinha removida com sucesso!");
        } else {
            System.out.println("Falha ao remover Varinha.");
        }
    }

    private void menuMagias() {
        int opcao;
        do {
            System.out.println("\n=== MENU MAGIAS ===");
            System.out.println("1. Cadastrar nova Magia");
            System.out.println("2. Listar todas as Magias");
            System.out.println("3. Buscar Magia por ID");
            System.out.println("4. Atualizar Magia");
            System.out.println("5. Remover Magia");
            System.out.println("6. Listar nomes das Magias");
            System.out.println("7. Listar descrições das Magias");
            System.out.println("8. Listar IDs dos Padrinhos das Magias");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarMagia();
                    break;
                case 2:
                    listarMagias();
                    break;
                case 3:
                    buscarMagiaPorId();
                    break;
                case 4:
                    atualizarMagia();
                    break;
                case 5:
                    removerMagia();
                    break;
                case 6:
                    listarNomesMagias();
                    break;
                case 7:
                    listarDescricoesMagias();
                    break;
                case 8:
                    listarIdsPadrinhosMagias();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarMagia() {
        System.out.println("\n--- Cadastrar Nova Magia ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("ID do Padrinho: ");
        int padrinhoId = scanner.nextInt();
        scanner.nextLine();

        Magia magia = new Magia(nome, descricao, padrinhoId);
        boolean sucesso = magiaDAO.insertMagia(magia);

        if (sucesso) {
            System.out.println("Magia cadastrada com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar Magia.");
        }
    }

    private void listarMagias() {
        ArrayList<Magia> magias = magiaDAO.selectMagia();
        if (magias.isEmpty()) {
            System.out.println("Nenhuma Magia cadastrada.");
        }
    }

    private void buscarMagiaPorId() {
        System.out.print("\nDigite o ID da Magia: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Magia magia = magiaDAO.selectMagiaById(id);
        if (magia == null) {
            System.out.println("Magia não encontrada com o ID: " + id);
        }
    }

    private void atualizarMagia() {
        System.out.print("\nDigite o ID da Magia a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("ID do Padrinho: ");
        int padrinhoId = scanner.nextInt();
        scanner.nextLine();

        Magia magiaAtualizada = new Magia(nome, descricao, padrinhoId);
        boolean sucesso = magiaDAO.updateMagia(id, magiaAtualizada);

        if (sucesso) {
            System.out.println("Magia atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar Magia.");
        }
    }

    private void removerMagia() {
        System.out.print("\nDigite o ID da Magia a ser removida: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = magiaDAO.deleteMagia(id);

        if (sucesso) {
            System.out.println("Magia removida com sucesso!");
        } else {
            System.out.println("Falha ao remover Magia.");
        }
    }

    private void listarNomesMagias() {
        ArrayList<String> nomes = magiaDAO.selectAllNomesMagias();
        if (nomes.isEmpty()) {
            System.out.println("Nenhuma Magia cadastrada.");
        }
    }

    private void listarDescricoesMagias() {
        ArrayList<String> descricoes = magiaDAO.selectAllDescricoesMagias();
        if (descricoes.isEmpty()) {
            System.out.println("Nenhuma Magia cadastrada.");
        }
    }

    private void listarIdsPadrinhosMagias() {
        ArrayList<Integer> ids = magiaDAO.selectAllIdsPadrinhos();
        if (ids.isEmpty()) {
            System.out.println("Nenhuma Magia cadastrada.");
        }
    }

    private void menuDesejos() {
        int opcao;
        do {
            System.out.println("\n=== MENU DESEJOS ===");
            System.out.println("1. Cadastrar novo Desejo");
            System.out.println("2. Listar todos os Desejos");
            System.out.println("3. Atualizar Desejo");
            System.out.println("4. Remover Desejo");
            System.out.println("5. Listar descrições dos Desejos");
            System.out.println("6. Listar status dos Desejos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarDesejo();
                    break;
                case 2:
                    listarDesejos();
                    break;
                case 3:
                    atualizarDesejo();
                    break;
                case 4:
                    removerDesejo();
                    break;
                case 5:
                    listarDescricoesDesejos();
                    break;
                case 6:
                    listarStatusDesejos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarDesejo() {
        System.out.println("\n--- Cadastrar Novo Desejo ---");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Status (true/false): ");
        boolean status = scanner.nextBoolean();
        scanner.nextLine();

        Desejos desejo = new Desejos(descricao, status);
        boolean sucesso = desejosDAO.insertDesejo(desejo);

        if (sucesso) {
            System.out.println("Desejo cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar Desejo.");
        }
    }

    private void listarDesejos() {
        ArrayList<Desejos> desejos = desejosDAO.selectDesejos();
        if (desejos.isEmpty()) {
            System.out.println("Nenhum Desejo cadastrado.");
        }
    }

    private void atualizarDesejo() {
        System.out.print("\nDigite o ID do Desejo a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite os novos dados:");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Status (true/false): ");
        boolean status = scanner.nextBoolean();
        scanner.nextLine();

        Desejos desejoAtualizado = new Desejos(descricao, status);
        boolean sucesso = desejosDAO.updateDesejo(id, desejoAtualizado);

        if (sucesso) {
            System.out.println("Desejo atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar Desejo.");
        }
    }

    private void removerDesejo() {
        System.out.print("\nDigite o ID do Desejo a ser removido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = desejosDAO.deleteDesejo(id);

        if (sucesso) {
            System.out.println("Desejo removido com sucesso!");
        } else {
            System.out.println("Falha ao remover Desejo.");
        }
    }

    private void listarDescricoesDesejos() {
        ArrayList<String> descricoes = desejosDAO.selectAllDescricoesDesejos();
        if (descricoes.isEmpty()) {
            System.out.println("Nenhum Desejo cadastrado.");
        }
    }

    private void listarStatusDesejos() {
        ArrayList<Boolean> status = desejosDAO.selectAllStatusDesejos();
        if (status.isEmpty()) {
            System.out.println("Nenhum Desejo cadastrado.");
        }
    }

    private void menuRelacionamentos() {
        int opcao;
        do {
            System.out.println("\n=== MENU RELACIONAMENTOS ===");
            System.out.println("1. Vincular Criança a Desejo");
            System.out.println("2. Desvincular Criança de Desejo");
            System.out.println("3. Listar relacionamentos Criança-Desejo");
            System.out.println("4. Transferir Desejo entre Crianças");
            System.out.println("5. Remover todos os desejos de uma Criança");
            System.out.println("6. Remover Desejo de todas as Crianças");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    vincularCriancaDesejo();
                    break;
                case 2:
                    desvincularCriancaDesejo();
                    break;
                case 3:
                    listarRelacionamentosCriancaDesejo();
                    break;
                case 4:
                    transferirDesejo();
                    break;
                case 5:
                    removerDesejosCrianca();
                    break;
                case 6:
                    removerDesejoDeTodas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void vincularCriancaDesejo() {
        System.out.print("\nDigite o ID da Criança: ");
        int idCrianca = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID do Desejo: ");
        int idDesejo = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = criancaDesejoDAO.insertCriancaDesejo(idCrianca, idDesejo);

        if (sucesso) {
            System.out.println("Relacionamento criado com sucesso!");
        } else {
            System.out.println("Falha ao criar relacionamento.");
        }
    }

    private void desvincularCriancaDesejo() {
        System.out.print("\nDigite o ID da Criança: ");
        int idCrianca = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID do Desejo: ");
        int idDesejo = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = criancaDesejoDAO.deleteCriancaDesejo(idCrianca, idDesejo);

        if (sucesso) {
            System.out.println("Relacionamento removido com sucesso!");
        } else {
            System.out.println("Falha ao remover relacionamento.");
        }
    }

    private void listarRelacionamentosCriancaDesejo() {
        ArrayList<String> relacionamentos = criancaDesejoDAO.selectCriancaDesejos();
        if (relacionamentos.isEmpty()) {
            System.out.println("Nenhum relacionamento cadastrado.");
        }
    }

    private void transferirDesejo() {
        System.out.print("\nDigite o ID do Desejo a ser transferido: ");
        int idDesejo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID da Criança de origem: ");
        int idOrigem = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID da Criança de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = criancaDesejoDAO.transferirDesejo(idDesejo, idOrigem, idDestino);

        if (sucesso) {
            System.out.println("Desejo transferido com sucesso!");
        } else {
            System.out.println("Falha ao transferir desejo.");
        }
    }


    private void removerDesejosCrianca() {
        System.out.print("\nDigite o ID da Criança: ");
        int idCrianca = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = criancaDesejoDAO.deleteDesejosPorCrianca(idCrianca);

        if (sucesso) {
            System.out.println("Desejos removidos com sucesso!");
        } else {
            System.out.println("Falha ao remover desejos.");
        }
    }

    private void removerDesejoDeTodas() {
        System.out.print("\nDigite o ID do Desejo: ");
        int idDesejo = scanner.nextInt();
        scanner.nextLine();

        boolean sucesso = criancaDesejoDAO.deleteDesejoDeTodas(idDesejo);

        if (sucesso) {
            System.out.println("Desejo removido de todas as crianças com sucesso!");
        } else {
            System.out.println("Falha ao remover desejo.");
        }
    }

    private void menuConsultasEspeciais() {
        int opcao;
        do {
            System.out.println("\n=== MENU CONSULTAS ESPECIAIS ===");
            System.out.println("1. Listar Crianças com seus Desejos");
            System.out.println("2. Listar Padrinhos com suas Crianças e Desejos");
            System.out.println("3. Listar Magias com seus Padrinhos e Crianças");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarCriancasComDesejos();
                    break;
                case 2:
                    listarPadrinhosComCriancasEDesejos();
                    break;
                case 3:
                    listarMagiasComPadrinhosECriancas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarCriancasComDesejos() {
        ArrayList<String> resultados = criancaDAO.selectCriancasComDesejos();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum relacionamento encontrado.");
        }
    }

    private void listarPadrinhosComCriancasEDesejos() {
        ArrayList<String> resultados = padrinhosDAO.selectPadrinhosComCriancasEDesejos();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum relacionamento encontrado.");
        }
    }

    private void listarMagiasComPadrinhosECriancas() {
        ArrayList<String> resultados = magiaDAO.selectMagiasComPadrinhosECriancas();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum relacionamento encontrado.");
        }
    }
}