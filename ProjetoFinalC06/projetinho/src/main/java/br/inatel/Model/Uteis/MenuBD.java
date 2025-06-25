package br.inatel.Model.Uteis;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável pelo menu principal do sistema dos Padrinhos Mágicos
 * Gerencia a interface de usuário e navegação entre funcionalidades
 */
public class MenuBD {

    // ==================== CONSTANTES ====================
    private static final String SEPARADOR = "=".repeat(50);
    private static final String TITULO_PRINCIPAL = "🧚‍♀️ MENU PADRINHOS MÁGICOS 🧚‍♂️";
    private static final String MENSAGEM_OPCAO_INVALIDA = "⚠️ Opção inválida! Digite apenas números válidos.";
    private static final String MENSAGEM_PAUSAR = "\nPressione ENTER para continuar...";

    // ==================== ENUMS ====================
    private enum OpcaoMenuPrincipal {
        GERENCIAR_CRIANCAS(1, "👶 Gerenciar Crianças"),
        GERENCIAR_PADRINHOS(2, "🧚 Gerenciar Padrinhos"),
        GERENCIAR_ANTI_FADAS(3, "🦹 Gerenciar Anti-Fadas"),
        GERENCIAR_GENERAL_FADA(4, "⭐ Gerenciar General Fada"),
        GERENCIAR_VARINHAS(5, "🪄 Gerenciar Varinhas"),
        GERENCIAR_DESEJOS(6, "✨ Gerenciar Desejos"),
        GERENCIAR_RELACIONAMENTOS(7, "🔗 Relacionar Desejos e Crianças"),
        SAIR(8, "🚪 Sair");

        private final int codigo;
        private final String descricao;

        OpcaoMenuPrincipal(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoMenuPrincipal fromCodigo(int codigo) {
            for (OpcaoMenuPrincipal opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoCriancas {
        LISTAR_CRIANCA(1, "📋 Listar todas as crianças"),
        BUSCAR_CRIANCA(2, "🔍 Buscar criança por ID"),
        INSERIR_CRIANCA(3, "➕ Inserir nova criança"),
        EDITAR_CRIANCA(4, "✏️ Editar criança"),
        EXCLUIR_CRIANCA(5, "❌ Excluir criança");

        private final int codigo;
        private final String descricao;

        OpcaoCriancas(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoCriancas fromCodigo(int codigo) {
            for (OpcaoCriancas opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoVarinhas {
        LISTAR_TODAS(1, "📋 Listar todas as varinhas"),
        BUSCAR_POR_ID(2, "🔍 Buscar varinha por ID Serial"),
        BUSCAR_POR_COR(3, "🎨 Buscar varinhas por cor"),
        BUSCAR_POR_STATUS(4, "⚡ Buscar varinhas por status"),
        VARINHAS_DISPONIVEIS(5, "🆓 Listar varinhas disponíveis"),
        VARINHAS_EM_USO(6, "🧚‍♀️ Listar varinhas em uso"),
        INVENTARIO_RESUMIDO(7, "📋 Inventário resumido"),
        ESTATISTICAS(8, "📊 Estatísticas do arsenal"),
        INSERIR(9, "➕ Inserir nova varinha"),
        EDITAR(10, "✏️ Editar varinha"),
        EXCLUIR(11, "❌ Excluir varinha");

        private final int codigo;
        private final String descricao;

        OpcaoVarinhas(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoVarinhas fromCodigo(int codigo) {
            for (OpcaoVarinhas opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoPadrinhos {
        LISTAR_PADRINHOS(1, "📋 Listar todos os padrinhos"),
        BUSCAR_PADRINHO(2, "🔍 Buscar padrinho por ID"),
        INSERIR_PADRINHO(3, "➕ Inserir novo padrinho"),
        EDITAR_PADRINHO(4, "✏️ Editar padrinho"),
        EXCLUIR_PADRINHO(5, "❌ Excluir padrinho");

        private final int codigo;
        private final String descricao;

        OpcaoPadrinhos(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoPadrinhos fromCodigo(int codigo) {
            for (OpcaoPadrinhos opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoAntiFadas {
        LISTAR_ANTI_FADAS(1, "📋 Listar todas as anti-fadas"),
        BUSCAR_ANTI_FADA(2, "🔍 Buscar anti-fada por ID"),
        INSERIR_ANTI_FADA(3, "➕ Inserir nova anti-fada"),
        EDITAR_ANTI_FADA(4, "✏️ Editar anti-fada"),
        EXCLUIR_ANTI_FADA(5, "❌ Excluir anti-fada");

        private final int codigo;
        private final String descricao;

        OpcaoAntiFadas(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoAntiFadas fromCodigo(int codigo) {
            for (OpcaoAntiFadas opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoGeneralFada {
        LISTAR_GENERAL_FADAS(1, "📋 Listar todos os general fadas"),
        BUSCAR_GENERAL_FADA(2, "🔍 Buscar general fada por ID"),
        INSERIR_GENERAL_FADA(3, "➕ Inserir novo general fada"),
        EDITAR_GENERAL_FADA(4, "✏️ Editar general fada"),
        EXCLUIR_GENERAL_FADA(5, "❌ Excluir general fada");

        private final int codigo;
        private final String descricao;

        OpcaoGeneralFada(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoGeneralFada fromCodigo(int codigo) {
            for (OpcaoGeneralFada opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoDesejos {
        LISTAR_DESEJOS(1, "📋 Listar todos os desejos"),
        BUSCAR_DESEJO(2, "🔍 Buscar desejo por ID"),
        BUSCAR_POR_TIPO(3, "🎯 Buscar desejos por tipo"),
        BUSCAR_POR_STATUS(4, "⚡ Buscar desejos por status"),
        DESEJOS_PENDENTES(5, "⏳ Listar desejos pendentes"),
        DESEJOS_REALIZADOS(6, "✅ Listar desejos realizados"),
        ESTATISTICAS(7, "📊 Estatísticas dos desejos"),
        INSERIR_DESEJO(8, "➕ Inserir novo desejo"),
        EDITAR_DESEJO(9, "✏️ Editar desejo"),
        EXCLUIR_DESEJO(10, "❌ Excluir desejo");

        private final int codigo;
        private final String descricao;

        OpcaoDesejos(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoDesejos fromCodigo(int codigo) {
            for (OpcaoDesejos opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    private enum OpcaoRelacionamentos {
        LISTAR_RELACIONAMENTOS(1, "📋 Listar todos os relacionamentos"),
        BUSCAR_POR_CRIANCA(2, "👶 Buscar relacionamentos por criança"),
        BUSCAR_POR_DESEJO(3, "✨ Buscar relacionamentos por desejo"),
        RELACIONAMENTOS_ATIVOS(4, "🔗 Listar relacionamentos ativos"),
        RELACIONAMENTOS_FINALIZADOS(5, "✅ Listar relacionamentos finalizados"),
        CRIAR_RELACIONAMENTO(6, "➕ Criar novo relacionamento"),
        EDITAR_RELACIONAMENTO(7, "✏️ Editar relacionamento"),
        FINALIZAR_RELACIONAMENTO(8, "🏁 Finalizar relacionamento"),
        EXCLUIR_RELACIONAMENTO(9, "❌ Excluir relacionamento");

        private final int codigo;
        private final String descricao;

        OpcaoRelacionamentos(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static OpcaoRelacionamentos fromCodigo(int codigo) {
            for (OpcaoRelacionamentos opcao : values()) {
                if (opcao.codigo == codigo) {
                    return opcao;
                }
            }
            return null;
        }
    }

    // ==================== ATRIBUTOS ====================
    private final Scanner scanner;
    private boolean executando;

    // ==================== CONSTRUTOR ====================
    public MenuBD() {
        this.scanner = new Scanner(System.in);
        this.executando = true;
    }

    // ==================== METODO PRINCIPAL ====================
    /*
      Inicia o sistema e exibe o menu principal
     */
    public void iniciar() {
        exibirMensagemBoasVindas();

        while (executando) {
            try {
                exibirMenuPrincipal();
                int opcao = lerOpcaoInt();
                processarOpcaoMenuPrincipal(opcao);

                if (executando) {
                    pausar();
                }
            } catch (Exception e) {
                System.err.println("❌ Erro inesperado: " + e.getMessage());
                pausar();
            }
        }

        fecharRecursos();
    }

    // ==================== MÉTODOS DE EXIBIÇÃO DE MENU ====================
    /**
     * Exibe mensagem de boas-vindas
     */
    private void exibirMensagemBoasVindas() {
        System.out.println("🌟 Bem-vindo ao Sistema dos Padrinhos Mágicos! 🌟");
        System.out.println("Conectando ao banco de dados...\n");
    }

    /**
     * Exibe o menu principal do sistema
     */
    private void exibirMenuPrincipal() {
        System.out.println("\n" + SEPARADOR);
        System.out.println(TITULO_PRINCIPAL);
        System.out.println(SEPARADOR);

        for (OpcaoMenuPrincipal opcao : OpcaoMenuPrincipal.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
    }

    /*
     Processa a opção selecionada no menu principal
     */
    private void processarOpcaoMenuPrincipal(int opcao) {
        OpcaoMenuPrincipal opcaoEnum = OpcaoMenuPrincipal.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida! Escolha entre 1 e " + OpcaoMenuPrincipal.values().length + ".");
            return;
        }

        switch (opcaoEnum) {
            case GERENCIAR_CRIANCAS:
                gerenciarCriancas();
                break;
            case GERENCIAR_PADRINHOS:
                gerenciarPadrinhos();
                break;
            case GERENCIAR_ANTI_FADAS:
                gerenciarAntiFadas();
                break;
            case GERENCIAR_GENERAL_FADA:
                gerenciarGeneralFada();
                break;
            case GERENCIAR_VARINHAS:
                gerenciarVarinhas();
                break;
            case GERENCIAR_DESEJOS:
                gerenciarDesejos();
                break;
            case GERENCIAR_RELACIONAMENTOS:
                gerenciarRelacionamentos();
                break;
            case SAIR:
                encerrarSistema();
                break;
        }
    }

    // ==================== MÉTODOS DE GERENCIAMENTO ====================
    /**
     * Gerencia operações relacionadas às crianças
     */
    private void gerenciarCriancas() {
        System.out.println("\n👶 === GERENCIAR CRIANÇAS ===");

        for (OpcaoCriancas opcao : OpcaoCriancas.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoCriancas opcaoEnum = OpcaoCriancas.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Crianças.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR_CRIANCA:
                listarTodasCriancas();
                break;
            case BUSCAR_CRIANCA:
                buscarCriancaPorId();
                break;
            case INSERIR_CRIANCA:
                inserirCrianca();
                break;
            case EDITAR_CRIANCA:
                editarCrianca();
                break;
            case EXCLUIR_CRIANCA:
                excluirCrianca();
                break;
        }
    }

    private void gerenciarVarinhas() {
        System.out.println("\n🪄 === GERENCIAR VARINHAS ===");

        for (OpcaoVarinhas opcao : OpcaoVarinhas.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        processarOpcaoVarinhas(opcao);
    }

    private void gerenciarPadrinhos() {
        System.out.println("\n🧚 === GERENCIAR PADRINHOS ===");

        for (OpcaoPadrinhos opcao : OpcaoPadrinhos.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoPadrinhos opcaoEnum = OpcaoPadrinhos.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Padrinhos.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR_PADRINHOS:
                listarTodosPadrinhos();
                break;
            case BUSCAR_PADRINHO:
                buscarPadrinhoPorId();
                break;
            case INSERIR_PADRINHO:
                inserirPadrinho();
                break;
            case EDITAR_PADRINHO:
                editarPadrinho();
                break;
            case EXCLUIR_PADRINHO:
                excluirPadrinho();
                break;
        }
    }

    private void gerenciarAntiFadas() {
        System.out.println("\n🦹 === GERENCIAR ANTI-FADAS ===");

        for (OpcaoAntiFadas opcao : OpcaoAntiFadas.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoAntiFadas opcaoEnum = OpcaoAntiFadas.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Anti-Fadas.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR_ANTI_FADAS:
                listarTodasAntiFadas();
                break;
            case BUSCAR_ANTI_FADA:
                buscarAntiFadaPorId();
                break;
            case INSERIR_ANTI_FADA:
                inserirAntiFada();
                break;
            case EDITAR_ANTI_FADA:
                editarAntiFada();
                break;
            case EXCLUIR_ANTI_FADA:
                excluirAntiFada();
                break;
        }
    }

    private void gerenciarGeneralFada() {
        System.out.println("\n⭐ === GERENCIAR GENERAL FADA ===");

        for (OpcaoGeneralFada opcao : OpcaoGeneralFada.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoGeneralFada opcaoEnum = OpcaoGeneralFada.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em General Fada.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR_GENERAL_FADAS:
                listarTodosGeneralFadas();
                break;
            case BUSCAR_GENERAL_FADA:
                buscarGeneralFadaPorId();
                break;
            case INSERIR_GENERAL_FADA:
                inserirGeneralFada();
                break;
            case EDITAR_GENERAL_FADA:
                editarGeneralFada();
                break;
            case EXCLUIR_GENERAL_FADA:
                excluirGeneralFada();
                break;
        }
    }

    private void gerenciarDesejos() {
        System.out.println("\n✨ === GERENCIAR DESEJOS ===");

        for (OpcaoDesejos opcao : OpcaoDesejos.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoDesejos opcaoEnum = OpcaoDesejos.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Desejos.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR_DESEJOS:
                listarTodosDesejos();
                break;
            case BUSCAR_DESEJO:
                buscarDesejoPorId();
                break;
            case BUSCAR_POR_TIPO:
                buscarDesejosPorTipo();
                break;
            case BUSCAR_POR_STATUS:
                buscarDesejosPorStatus();
                break;
            case DESEJOS_PENDENTES:
                listarDesejosPendentes();
                break;
            case DESEJOS_REALIZADOS:
                listarDesejosRealizados();
                break;
            case ESTATISTICAS:
                mostrarEstatisticasDesejos();
                break;
            case INSERIR_DESEJO:
                inserirDesejo();
                break;
            case EDITAR_DESEJO:
                editarDesejo();
                break;
            case EXCLUIR_DESEJO:
                excluirDesejo();
                break;
        }
    }

    private void gerenciarRelacionamentos() {
        System.out.println("\n🔗 === GERENCIAR RELACIONAMENTOS ===");

        for (OpcaoRelacionamentos opcao : OpcaoRelacionamentos.values()) {
            System.out.println(opcao.getCodigo() + ". " + opcao.getDescricao());
        }

        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcaoInt();

        OpcaoRelacionamentos opcaoEnum = OpcaoRelacionamentos.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Relacionamentos.");
            return;
        }

        switch (opcaoEnum) {
            case LISTAR_RELACIONAMENTOS:
                listarTodosRelacionamentos();
                break;
            case BUSCAR_POR_CRIANCA:
                buscarRelacionamentosPorCrianca();
                break;
            case BUSCAR_POR_DESEJO:
                buscarRelacionamentosPorDesejo();
                break;
            case RELACIONAMENTOS_ATIVOS:
                listarRelacionamentosAtivos();
                break;
            case RELACIONAMENTOS_FINALIZADOS:
                listarRelacionamentosFinalizados();
                break;
            case CRIAR_RELACIONAMENTO:
                criarRelacionamento();
                break;
            case EDITAR_RELACIONAMENTO:
                editarRelacionamento();
                break;
            case FINALIZAR_RELACIONAMENTO:
                finalizarRelacionamento();
                break;
            case EXCLUIR_RELACIONAMENTO:
                excluirRelacionamento();
                break;
        }
    }

    /**
     * Processa as opções do menu de varinhas
     */
    private void processarOpcaoVarinhas(int opcao) {
        OpcaoVarinhas opcaoEnum = OpcaoVarinhas.fromCodigo(opcao);

        if (opcaoEnum == null) {
            System.out.println("⚠️ Opção inválida em Varinhas.");
            return;
        }

        // Criar instância do DAO
        br.inatel.DAO.VarinhaDAO varinhaDAO = new br.inatel.DAO.VarinhaDAO();

        switch (opcaoEnum) {
            case LISTAR_TODAS:
                varinhaDAO.selectAllVarinhas();
                break;
            case BUSCAR_POR_ID:
                buscarVarinhaPorId(varinhaDAO);
                break;
            case BUSCAR_POR_COR:
                buscarVarinhasPorCor(varinhaDAO);
                break;
            case BUSCAR_POR_STATUS:
                buscarVarinhasPorStatus(varinhaDAO);
                break;
            case VARINHAS_DISPONIVEIS:
                varinhaDAO.selectVarinhasDisponiveis();
                break;
            case VARINHAS_EM_USO:
                varinhaDAO.selectVarinhasEmUso();
                break;
            case INVENTARIO_RESUMIDO:
                varinhaDAO.showInventarioResumo();
                break;
            case ESTATISTICAS:
                varinhaDAO.showEstatisticasArsenal();
                break;
            case INSERIR:
                inserirVarinha(varinhaDAO);
                break;
            case EDITAR:
                editarVarinha(varinhaDAO);
                break;
            case EXCLUIR:
                excluirVarinha(varinhaDAO);
                break;
        }
    }


    // ==================== OPERAÇÕES ESPECÍFICAS DE CRIANÇAS ====================
    private void listarTodasCriancas() {
        System.out.println("\n📋 === LISTA DE CRIANÇAS ===");
        // TODO: Implementar lógica de listagem
        System.out.println("Implementar: Listar todas as crianças do banco de dados");
    }

    private void buscarCriancaPorId() {
        System.out.println("\n🔍 === BUSCAR CRIANÇA POR ID ===");
        System.out.print("Digite o ID da criança: ");
        int id = lerOpcaoInt();
        // TODO: Implementar busca por ID
        System.out.println("Implementar: Buscar criança com ID: " + id);
    }

    private void inserirCrianca() {
        System.out.println("\n➕ === INSERIR NOVA CRIANÇA ===");
        // TODO: Implementar inserção
        System.out.println("Implementar: Formulário para inserir nova criança");
    }

    private void editarCrianca() {
        System.out.println("\n✏️ === EDITAR CRIANÇA ===");
        // TODO: Implementar edição
        System.out.println("Implementar: Formulário para editar criança existente");
    }

    private void excluirCrianca() {
        System.out.println("\n❌ === EXCLUIR CRIANÇA ===");
        System.out.print("Digite o ID da criança a ser excluída: ");
        int id = lerOpcaoInt();
        // TODO: Implementar exclusão com confirmação
        System.out.println("Implementar: Excluir criança com ID: " + id);
    }

    // ==================== OPERAÇÕES ESPECÍFICAS DE VARINHAS ====================
    private void buscarVarinhaPorId(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.print("Digite o ID Serial da varinha: ");
        int id = lerOpcaoInt();
        varinhaDAO.selectVarinhaById(id);
    }

    private void buscarVarinhasPorCor(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.print("Digite a cor da varinha: ");
        String cor = lerTextoValidado("Cor não pode ser vazia!");
        varinhaDAO.selectVarinhasByCor(cor);
    }

    private void buscarVarinhasPorStatus(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("Status disponíveis: Disponível, Em Uso, Manutenção");
        System.out.print("Digite o status da varinha: ");
        String status = lerTextoValidado("Status não pode ser vazio!");
        varinhaDAO.selectVarinhasByStatus(status);
    }

    private void inserirVarinha(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("\n➕ === INSERIR NOVA VARINHA ===");
        System.out.print("Digite a cor da varinha: ");
        String cor = lerTextoValidado("Cor não pode ser vazia!");
        System.out.print("Digite o status da varinha (Disponível/Em Uso/Manutenção): ");
        String status = lerTextoValidado("Status não pode ser vazio!");

        // Assumindo que existe uma classe Varinha no pacote br.inatel.Model.Personagens
        try {
            br.inatel.Model.Personagens.Varinha varinha =
                    new br.inatel.Model.Personagens.Varinha(cor, status);

            if (varinhaDAO.insertVarinha(varinha)) {
                System.out.println("✅ Varinha inserida com sucesso!");
            } else {
                System.out.println("❌ Erro ao inserir varinha.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar varinha: " + e.getMessage());
        }
    }

    private void editarVarinha(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("\n✏️ === EDITAR VARINHA ===");
        System.out.print("Digite o ID Serial da varinha a ser editada: ");
        int id = lerOpcaoInt();

        // Primeiro, buscar e mostrar a varinha atual
        br.inatel.Model.Personagens.Varinha varinhaAtual = varinhaDAO.selectVarinhaById(id);

        if (varinhaAtual != null) {
            System.out.print("Nova cor da varinha: ");
            String novaCor = lerTextoValidado("Cor não pode ser vazia!");
            System.out.print("Novo status da varinha (Disponível/Em Uso/Manutenção): ");
            String novoStatus = lerTextoValidado("Status não pode ser vazio!");

            try {
                br.inatel.Model.Personagens.Varinha varinhaEditada =
                        new br.inatel.Model.Personagens.Varinha(novaCor, novoStatus);

                if (varinhaDAO.updateVarinha(id, varinhaEditada)) {
                    System.out.println("✅ Varinha editada com sucesso!");
                } else {
                    System.out.println("❌ Erro ao editar varinha.");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro ao criar varinha editada: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Varinha não encontrada com o ID fornecido.");
        }
    }

    private void excluirVarinha(br.inatel.DAO.VarinhaDAO varinhaDAO) {
        System.out.println("\n❌ === EXCLUIR VARINHA ===");
        System.out.print("Digite o ID Serial da varinha a ser excluída: ");
        int id = lerOpcaoInt();

        // Mostrar a varinha antes de excluir
        br.inatel.Model.Personagens.Varinha varinha = varinhaDAO.selectVarinhaById(id);

        if (varinha != null) {
            System.out.print("Tem certeza que deseja excluir esta varinha? (S/N): ");
            String confirmacao = lerTexto().toUpperCase();

            if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
                if (varinhaDAO.deleteVarinha(id)) {
                    System.out.println("✅ Varinha excluída com sucesso!");
                } else {
                    System.out.println("❌ Erro ao excluir varinha.");
                }
            } else {
                System.out.println("ℹ️ Operação cancelada.");
            }
        } else {
            System.out.println("❌ Varinha não encontrada com o ID fornecido.");
        }
    }

    // ==================== OPERAÇÕES ESPECÍFICAS DE PADRINHOS ====================
    private void listarTodosPadrinhos() {
        System.out.println("\n📋 === LISTA DE PADRINHOS ===");
        // TODO: Implementar lógica de listagem
        System.out.println("Implementar: Listar todos os padrinhos do banco de dados");
    }

    private void buscarPadrinhoPorId() {
        System.out.println("\n🔍 === BUSCAR PADRINHO POR ID ===");
        System.out.print("Digite o ID do padrinho: ");
        int id = lerOpcaoInt();
        // TODO: Implementar busca por ID
        System.out.println("Implementar: Buscar padrinho com ID: " + id);
    }

    private void inserirPadrinho() {
        System.out.println("\n➕ === INSERIR NOVO PADRINHO ===");
        // TODO: Implementar inserção
        System.out.println("Implementar: Formulário para inserir novo padrinho");
    }

    private void editarPadrinho() {
        System.out.println("\n✏️ === EDITAR PADRINHO ===");
        // TODO: Implementar edição
        System.out.println("Implementar: Formulário para editar padrinho existente");
    }

    private void excluirPadrinho() {
        System.out.println("\n❌ === EXCLUIR PADRINHO ===");
        System.out.print("Digite o ID do padrinho a ser excluído: ");
        int id = lerOpcaoInt();
        // TODO: Implementar exclusão com confirmação
        System.out.println("Implementar: Excluir padrinho com ID: " + id);
    }

    // ==================== OPERAÇÕES ESPECÍFICAS DE ANTI-FADAS ====================
    private void listarTodasAntiFadas() {
        System.out.println("\n📋 === LISTA DE ANTI-FADAS ===");
        // TODO: Implementar lógica de listagem
        System.out.println("Implementar: Listar todas as anti-fadas do banco de dados");
    }

    private void buscarAntiFadaPorId() {
        System.out.println("\n🔍 === BUSCAR ANTI-FADA POR ID ===");
        System.out.print("Digite o ID da anti-fada: ");
        int id = lerOpcaoInt();
        // TODO: Implementar busca por ID
        System.out.println("Implementar: Buscar anti-fada com ID: " + id);
    }

    private void inserirAntiFada() {
        System.out.println("\n➕ === INSERIR NOVA ANTI-FADA ===");
        // TODO: Implementar inserção
        System.out.println("Implementar: Formulário para inserir nova anti-fada");
    }

    private void editarAntiFada() {
        System.out.println("\n✏️ === EDITAR ANTI-FADA ===");
        // TODO: Implementar edição
        System.out.println("Implementar: Formulário para editar anti-fada existente");
    }

    private void excluirAntiFada() {
        System.out.println("\n❌ === EXCLUIR ANTI-FADA ===");
        System.out.print("Digite o ID da anti-fada a ser excluída: ");
        int id = lerOpcaoInt();
        // TODO: Implementar exclusão com confirmação
        System.out.println("Implementar: Excluir anti-fada com ID: " + id);
    }

    // ==================== OPERAÇÕES ESPECÍFICAS DE GENERAL FADA ====================
    private void listarTodosGeneralFadas() {
        System.out.println("\n📋 === LISTA DE GENERAL FADAS ===");
        // TODO: Implementar lógica de listagem
        System.out.println("Implementar: Listar todos os general fadas do banco de dados");
    }

    private void buscarGeneralFadaPorId() {
        System.out.println("\n🔍 === BUSCAR GENERAL FADA POR ID ===");
        System.out.print("Digite o ID do general fada: ");
        int id = lerOpcaoInt();
        // TODO: Implementar busca por ID
        System.out.println("Implementar: Buscar general fada com ID: " + id);
    }

    private void inserirGeneralFada() {
        System.out.println("\n➕ === INSERIR NOVO GENERAL FADA ===");
        // TODO: Implementar inserção
        System.out.println("Implementar: Formulário para inserir novo general fada");
    }

    private void editarGeneralFada() {
        System.out.println("\n✏️ === EDITAR GENERAL FADA ===");
        // TODO: Implementar edição
        System.out.println("Implementar: Formulário para editar general fada existente");
    }

    private void excluirGeneralFada() {
        System.out.println("\n❌ === EXCLUIR GENERAL FADA ===");
        System.out.print("Digite o ID do general fada a ser excluído: ");
        int id = lerOpcaoInt();
        // TODO: Implementar exclusão com confirmação
        System.out.println("Implementar: Excluir general fada com ID: " + id);
    }

    // ==================== OPERAÇÕES ESPECÍFICAS DE DESEJOS ====================
    private void listarTodosDesejos() {
        System.out.println("\n📋 === LISTA DE DESEJOS ===");
        // TODO: Implementar lógica de listagem
        System.out.println("Implementar: Listar todos os desejos do banco de dados");
    }

    private void buscarDesejoPorId() {
        System.out.println("\n🔍 === BUSCAR DESEJO POR ID ===");
        System.out.print("Digite o ID do desejo: ");
        int id = lerOpcaoInt();
        // TODO: Implementar busca por ID
        System.out.println("Implementar: Buscar desejo com ID: " + id);
    }

    private void buscarDesejosPorTipo() {
        System.out.println("\n🎯 === BUSCAR DESEJOS POR TIPO ===");
        System.out.print("Digite o tipo do desejo: ");
        String tipo = lerTextoValidado("Tipo não pode ser vazio!");
        // TODO: Implementar busca por tipo
        System.out.println("Implementar: Buscar desejos do tipo: " + tipo);
    }

    private void buscarDesejosPorStatus() {
        System.out.println("\n⚡ === BUSCAR DESEJOS POR STATUS ===");
        System.out.print("Digite o status do desejo: ");
        String status = lerTextoValidado("Status não pode ser vazio!");
        // TODO: Implementar busca por status
        System.out.println("Implementar: Buscar desejos com status: " + status);
    }

    private void listarDesejosPendentes() {
        System.out.println("\n⏳ === DESEJOS PENDENTES ===");
        // TODO: Implementar listagem de desejos pendentes
        System.out.println("Implementar: Listar desejos pendentes");
    }

    private void listarDesejosRealizados() {
        System.out.println("\n✅ === DESEJOS REALIZADOS ===");
        // TODO: Implementar listagem de desejos realizados
        System.out.println("Implementar: Listar desejos realizados");
    }

    private void mostrarEstatisticasDesejos() {
        System.out.println("\n📊 === ESTATÍSTICAS DOS DESEJOS ===");
        // TODO: Implementar estatísticas
        System.out.println("Implementar: Mostrar estatísticas dos desejos");
    }

    private void inserirDesejo() {
        System.out.println("\n➕ === INSERIR NOVO DESEJO ===");
        // TODO: Implementar inserção
        System.out.println("Implementar: Formulário para inserir novo desejo");
    }

    private void editarDesejo() {
        System.out.println("\n✏️ === EDITAR DESEJO ===");
        // TODO: Implementar edição
        System.out.println("Implementar: Formulário para editar desejo existente");
    }

    private void excluirDesejo() {
        System.out.println("\n❌ === EXCLUIR DESEJO ===");
        System.out.print("Digite o ID do desejo a ser excluído: ");
        int id = lerOpcaoInt();
        // TODO: Implementar exclusão com confirmação
        System.out.println("Implementar: Excluir desejo com ID: " + id);
    }

    // ==================== OPERAÇÕES ESPECÍFICAS DE RELACIONAMENTOS ====================
    private void listarTodosRelacionamentos() {
        System.out.println("\n📋 === LISTA DE RELACIONAMENTOS ===");
        //  listagem
        System.out.println("Implementar: Listar todos os relacionamentos do banco de dados");
    }

    private void buscarRelacionamentosPorCrianca() {
        System.out.println("\n👶 === BUSCAR RELACIONAMENTOS POR CRIANÇA ===");
        System.out.print("Digite o ID da criança: ");
        int id = lerOpcaoInt();
        //  busca por criança
        System.out.println("Implementar: Buscar relacionamentos da criança com ID: " + id);
    }

    private void buscarRelacionamentosPorDesejo() {
        System.out.println("\n✨ === BUSCAR RELACIONAMENTOS POR DESEJO ===");
        System.out.print("Digite o ID do desejo: ");
        int id = lerOpcaoInt();
        // busca por desejo
        System.out.println("Implementar: Buscar relacionamentos do desejo com ID: " + id);
    }

    private void listarRelacionamentosAtivos() {
        System.out.println("\n🔗 === RELACIONAMENTOS ATIVOS ===");
        // listagem de relacionamentos ativos
        System.out.println("Implementar: Listar relacionamentos ativos");
    }

    private void listarRelacionamentosFinalizados() {
        System.out.println("\n✅ === RELACIONAMENTOS FINALIZADOS ===");
        // listagem de relacionamentos finalizados
        System.out.println("Implementar: Listar relacionamentos finalizados");
    }

    private void criarRelacionamento() {
        System.out.println("\n➕ === CRIAR NOVO RELACIONAMENTO ===");
        // criação
        System.out.println("Implementar: Formulário para criar novo relacionamento");
    }

    private void editarRelacionamento() {
        System.out.println("\n✏️ === EDITAR RELACIONAMENTO ===");
        //  edição
        System.out.println("Implementar: Formulário para editar relacionamento existente");
    }

    private void finalizarRelacionamento() {
        System.out.println("\n🏁 === FINALIZAR RELACIONAMENTO ===");
        System.out.print("Digite o ID do relacionamento a ser finalizado: ");
        int id = lerOpcaoInt();
        // finalização
        System.out.println("Implementar: Finalizar relacionamento com ID: " + id);
    }

    private void excluirRelacionamento() {
        System.out.println("\n❌ === EXCLUIR RELACIONAMENTO ===");
        System.out.print("Digite o ID do relacionamento a ser excluído: ");
        int id = lerOpcaoInt();
        //  exclusão com confirmação
        System.out.println("Implementar: Excluir relacionamento com ID: " + id);
    }

    // ==================== MÉTODOS UTILITÁRIOS ====================
    /**
     * Lê uma opção inteira do usuário com validação
     *
     * @return número inteiro válido
     */
    private int lerOpcaoInt() {
        while (true) {
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // limpa buffer
                return opcao;
            } catch (InputMismatchException e) {
                System.out.print(MENSAGEM_OPCAO_INVALIDA + " Digite novamente: ");
                scanner.nextLine(); // limpa buffer inválido
            }
        }
    }

    /**
     * Lê uma string do usuário
     *
     * @return string lida, sem espaços extras
     */
    private String lerTexto() {
        return scanner.nextLine().trim();
    }

    /**
     * Lê uma string do usuário com validação de entrada não vazia
     *
     * @param mensagemErro mensagem a ser exibida se a entrada for vazia
     * @return string válida e não vazia
     */
    private String lerTextoValidado(String mensagemErro) {
        String texto;
        do {
            texto = lerTexto();
            if (texto.isEmpty()) {
                System.out.print("⚠️ " + mensagemErro + " Tente novamente: ");
            }
        } while (texto.isEmpty());
        return texto;
    }

    /**
     * Pausa a execução até o usuário pressionar ENTER
     */
    private void pausar() {
        System.out.println(MENSAGEM_PAUSAR);
        scanner.nextLine();
    }

    /**
     * Encerra o sistema de forma controlada
     */
    private void encerrarSistema() {
        System.out.println("✨ Encerrando o sistema dos Padrinhos Mágicos...");
        System.out.println("Até a próxima aventura mágica! 🪄");
        executando = false;
    }

    /**
     * Fecha recursos utilizados pela classe
     */
    private void fecharRecursos() {
        if (scanner != null) {
            scanner.close();
        }
    }
}