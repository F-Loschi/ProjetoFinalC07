package br.inatel.Model.Personagens;

public class Magia {
    private int idMagia;
    private String nomeMagia;
    private String descricaoMagia;
    private int Padrinhos_idPadrinhos;

    public Magia(String nomeMagia, String descricaoMagia, int padrinhos_idPadrinhos) {
        this.nomeMagia = nomeMagia;
        this.descricaoMagia = descricaoMagia;
        Padrinhos_idPadrinhos = padrinhos_idPadrinhos;
    }

    public Magia(int idMagia, String nomeMagia, String descricaoMagia, int Padrinhos_idPadrinhos) {
        this.idMagia = idMagia;
        this.nomeMagia = nomeMagia;
        this.descricaoMagia = descricaoMagia;
        this.Padrinhos_idPadrinhos = Padrinhos_idPadrinhos;
    }

    public String getNomeMagia() {
        return nomeMagia;
    }

    public String getDescricaoMagia() {
        return descricaoMagia;
    }

    public int getPadrinhos_idPadrinhos() {
        return Padrinhos_idPadrinhos;
    }

    public int getIdMagia() {
        return idMagia;
    }
}
