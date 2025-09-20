import java.util.ArrayList;

public class Soup {
    //ATRIBUTOS
    private char[][] arraySoup;
    private int dimSoup;
    private ArrayList<String> palavrasChave = new ArrayList<>();

    //CONSTRUTOR
    public Soup(ArrayList<String> soup){
        //1. O puzzle é sempre quadrado, com o tamanho máximo de 40x40.
        if(!isSquare(soup)){
            throw new IllegalArgumentException("A puzzle não é quadrado!");
        }
        if(soup.size() > 40){
            throw new IllegalArgumentException("O puzzle tem tamanho superior a 40x40!");
        }
        this.dimSoup = soup.size();
        this.arraySoup = new char[dimSoup][dimSoup];
        this.fillSoup(soup);
    }

    public Soup(char[][] soup){
        this.arraySoup = soup;
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

        public void setPalavrasChave(ArrayList<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
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

    //2. As letras do puzzle estão em maiúscula.
    public static boolean isUpper(String a){
        for(Character c: a.toCharArray()){
            if(Character.isLetter(c)  && Character.isLowerCase(c)){
                return false;
            }
        }
        return true;
    }

    public void addWord(String word){
        //7. Todas as palavras da lista têm de estar no puzzle e apenas uma vez. 
        if(palavrasChave.contains(word)){
            throw new IllegalArgumentException(String.format("[ERRO]A palavra %s já existe na sopa de letras!", word));
        }
        this.palavrasChave.add(word);
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

    public String toStringTerminalOutput(){
        StringBuffer out = new StringBuffer();
        for(char[] linha : arraySoup){
            for(char caracter: linha){
                out.append(String.format("%c ", caracter));
            }
            out.append("\n");
        }
        return out.toString();
    }



}
