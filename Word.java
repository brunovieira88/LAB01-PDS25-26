public class Word {

    private String nome;
    private int i,j;
    private Direction direcao;

    Word (String nome, int x ,int y, Direction direction){
        this.nome = nome;
        this.i = x;
        this.j = y;
        this.direcao = direction;
    }


    public String getNome() {
        return nome;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Direction getDirecao() {
        return direcao;
    }


    //TO STRING
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        //adiciona-se i + 1, j + 1 para obter numero da coluna e nao o indice
        out.append(String.format("%-20s %-8d %d,%-6d %s", nome,nome.length(),i + 1,j + 1,this.getDirecao()));
        return out.toString();
    }

    
}
