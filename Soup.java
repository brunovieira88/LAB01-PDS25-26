import java.util.ArrayList;

public class Soup {
    //ATRIBUTOS
    private char[][] arraySoup;
    private int dimSoup;
    private ArrayList<String> palavrasChave = new ArrayList<>();

    //CONSTRUTOR
    public Soup(ArrayList<String> soup,ArrayList<String> words){
        if(!isSquare(soup)){
            throw new IllegalArgumentException("A puzzle não é quadrado!");//verficar quadrado
        }
        if(soup.size() > 40){
            throw new IllegalArgumentException("O puzzle tem tamanho superior a 40x40!");//tamanho maximo
        }
        this.dimSoup = soup.size();
        this.arraySoup = new char[dimSoup][dimSoup];
        this.fillSoup(soup);
        this.setPalavrasChave(words);
    }

    public Soup(char[][] solution){
        this.arraySoup = solution;
    }


    //GETTERS E SETTERS
    public char[][] getArraySoup() {
        return arraySoup;
    }
    public void setArraySoup(char[][] arraySoup) {
        this.arraySoup = arraySoup;
    }
    public int getDimSoup() {
        return dimSoup;
    }
    public void setDimSoup(int dimSoup) {
        this.dimSoup = dimSoup;
    }

    public ArrayList<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(ArrayList<String> listaPalavras) {
        this.palavrasChave = listaPalavras;
    }


    //METODOS ESPECIAIS
    public boolean isSquare(){
        return arraySoup[0].length == arraySoup.length;
    }

    public static boolean isSquare(ArrayList<String> soup){
        return soup.get(0).length() == soup.size();
    }

    private void fillSoup(ArrayList<String> soup){
        for(int i = 0; i < dimSoup; i++){
            arraySoup[i] = soup.get(i).toCharArray();
        }
    }


    //TO STRING
    public String toString(){
        StringBuffer out = new StringBuffer();
        for(char[] linha : arraySoup){
            for(char caracter: linha){
                out.append(String.format("%c", caracter));
            }
            out.append("\n");
        }
        for (int i = 0; i < palavrasChave.size(); i++) {
            out.append(palavrasChave.get(i).toLowerCase());
            if (i < palavrasChave.size() - 1) {
                out.append(";");
            }
        }
        return out.toString();

    }




}
