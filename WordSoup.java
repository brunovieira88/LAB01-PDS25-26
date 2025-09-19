public class WordSoup { 
    //ATRIBUTOS
    private int size;
    private Word palavras[];
    private Direction direcao;

    //GETTERS E SETTERS
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public Word[] getPalavras() {
        return palavras;
    }
    public void setPalavras(Word[] palavras) {
        this.palavras = palavras;
    }
    public Direction getDirecao() {
        return direcao;
    }
    public void setDirecao(Direction direcao) {
        this.direcao = direcao;
    }
}
