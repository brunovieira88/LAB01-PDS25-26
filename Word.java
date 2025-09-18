public class Word {

    private String nome;
    private Integer coordenadas[];
    private direcao dir;
    private sentido sen;
    private enum direcao {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    };

    private enum sentido{
        NORMAL,
        INVERSO
    };

    Word (String nome, Integer coordenadas[], int direcao, int sentido){
        this.nome = nome;
        if (coordenadas[0] >= 0 && coordenadas[0] <= 40 && coordenadas[1] != null){ //ver depois porque na 2a alinea o tamanho arbitrario
            this.coordenadas = coordenadas;
        }
        this.dir = Word.direcao.values()[direcao];
        this.sen = Word.sentido.values()[sentido];
    }

    public Word(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Integer[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public direcao getDir() {
        return dir;
    }

    public void setDir(direcao dir) {
        this.dir = dir;
    }

    public sentido getSen() {
        return sen;
    }

    public void setSen(sentido sen) {
        this.sen = sen;
    }

    

}
