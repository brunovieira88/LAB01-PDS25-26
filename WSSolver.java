import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WSSolver {
    //ATRIBUTOS
    private Soup sopaLetras;

    public Soup getSopaLetras() {
        return sopaLetras;
    }

    public static boolean isUpper(String a){
        for(Character c: a.toCharArray()){
            if(Character.isLetter(c)  && Character.isLowerCase(c)){
                return false;
            }
        }
        return true;
    }

    //LEITURA DE UM FICHEIRO
    public boolean readWorldSearch(String nameFile){
        File file = new File(nameFile);
        ArrayList<String> leituraPuzzle = new ArrayList<>();
        ArrayList<String> leituraPalavrasPuzzle = new ArrayList<>();
        try(Scanner sc = new Scanner(file)){
            String lineFile;
            while(sc.hasNextLine()){
                lineFile = sc.nextLine();
                if(isUpper(lineFile.trim())){
                    leituraPuzzle.add(lineFile);
                }
                else{
                    leituraPalavrasPuzzle.addAll(Arrays.asList(lineFile.split("[;,\\s]")));
                }

            }
            sopaLetras = new Soup(leituraPuzzle, leituraPalavrasPuzzle);
            return true;

        }catch(FileNotFoundException e ){
            System.out.println("Ficheiro não encontrado!");
            return false;
        }

    }


    public boolean encontrarPalavra(String word){
        for (int i = 0; i < sopaLetras.getDimSoup(); i++){ //linhas
            for(int j = 0; j < this.sopaLetras.getDimSoup();j++){ //colunas
                if (sopaLetras.getArraySoup()[i][j] == word.charAt(0) && verificar8direcoes(i,j,word)){
                    System.out.printf("%s:(%d,%d)\n",word,i + 1,j + 1);//adiciona-se i + 1, j + 1 para obter numero da coluna e nao o indice 
                    return true;
                }

            }
        }
        System.out.println("Palavra não encontrada!");
        return false;
    }

    private boolean verificar8direcoes(int i, int j, String word){
        if(verificarCima(i, j, word) || verificarDireita(i, j, word) || verificarEsquerda(i, j, word) || verificarBaixo(i, j, word)){
            return true;
        }
        if(verificarDireitaCima(i, j, word) || verificarDireitaBaixo(i, j, word)
        || verificarEsquerdaCima(i, j, word) || verificarEsquerdaBaixo(i, j, word)){
            return true;
        } /* ESTA SEPARADO POIS ASSIM TEMOS MAIOR PERFORMANCE SE AS PALAVRAS NAO FOREM DIAGONAIS
        PORQUE A MAORIA DAS PALAVRAS SÃO HORIZONTAIS OU VERTICAIS*/

        return false;
    }


    private boolean verificarDireita(int i, int j, String word) {
        if (j + word.length() > sopaLetras.getDimSoup()) return false; // Evita sair fora da matriz
        for (int x = 0; x < word.length(); x++) {
            if (sopaLetras.getArraySoup()[i][j + x] != word.charAt(x)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarEsquerda(int i, int j, String word) {
        if (j - word.length() + 1 < 0) return false; // Evita sair fora da matriz
        for (int x = 0; x < word.length(); x++) {
            if (sopaLetras.getArraySoup()[i][j - x] != word.charAt(x)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarBaixo(int i, int j, String word) {
        if (i + word.length() > sopaLetras.getDimSoup()) return false; // Evita sair fora da matriz
        for (int y = 0; y < word.length(); y++) {
            if (sopaLetras.getArraySoup()[i + y][j] != word.charAt(y)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarCima(int i, int j, String word) {
        if (i - word.length() + 1 < 0) return false; // Evita sair fora da matriz
        for (int y = 0; y < word.length(); y++) {
            if (sopaLetras.getArraySoup()[i - y][j] != word.charAt(y)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarDireitaCima(int i, int j, String word){
        if ((j + word.length() > sopaLetras.getDimSoup()) || (i - word.length() + 1 < 0)) return false;
        for(int k = 0; k < word.length();k++){
            if(sopaLetras.getArraySoup()[i - k][j + k] != word.charAt(k)){
                return false;
            }
        }
        return true;
    }

    private boolean verificarDireitaBaixo(int i, int j, String word){
        if ((j + word.length() > sopaLetras.getDimSoup()) || (i + word.length() > sopaLetras.getDimSoup())) return false;
        for(int k = 0; k < word.length();k++){
            if(sopaLetras.getArraySoup()[i + k][j + k] != word.charAt(k)){
                return false;
            }
        }
        return true;
        
    }

    private boolean verificarEsquerdaCima(int i, int j, String word){
        if ((j - word.length() + 1 < 0) || (i - word.length() + 1 < 0)) return false;
        for(int k = 0; k < word.length();k++){
            if(sopaLetras.getArraySoup()[i - k][j - k] != word.charAt(k)){
                return false;
            }
        }
        return true;

    }

    private boolean verificarEsquerdaBaixo(int i, int j, String word){
        if((j - word.length() + 1 < 0) || (i + word.length() > sopaLetras.getDimSoup())) return false;
        for(int k = 0; k < word.length();k++){
            if(sopaLetras.getArraySoup()[i + k][j - k] != word.charAt(k)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        WSSolver c = new WSSolver();
        c.readWorldSearch("sdl_01.txt");
        c.encontrarPalavra("WORDS");
        System.out.println(c.getSopaLetras().toString());
    }

}
