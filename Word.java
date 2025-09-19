public class Word {

    private String nome;
    private int i,j;
    private Direction direcao;

    Word (String nome, int x ,int y, Direction direction){
        this.nome = nome;
        if (x >= 0 && x <= 40 && y != 0){ //ver depois porque na 2a alinea o tamanho arbitrario
            this.i = x;
            this.j = y;
        }
        this.direcao = direction;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Direction getDirecao() {
        return direcao;
    }

    public void setDirecao(Direction direcao) {
        this.direcao = direcao;
    }

    //TO STRING
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        //adiciona-se i + 1, j + 1 para obter numero da coluna e nao o indice
        out.append(String.format("%-20s %-8d %d,%-5d %s", nome,nome.length(),i + 1,j + 1,this.getDirecao()));
        return out.toString();
    }

    
}
