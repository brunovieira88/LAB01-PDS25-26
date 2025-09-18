import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WSSolver {
    //ATRIBUTOS
    private char[][] puzzle = new char[12][12];
    private int[] coords;
    private String[] listaPalavras;

    
    public static boolean isSquare(String[][] a){
        return a.length == a[0].length && a.length < 40;
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
        try(Scanner sc = new Scanner(file)){
            String lineFile;
            int i = 0;
            while(sc.hasNextLine()){
                lineFile = sc.nextLine();
                if(isUpper(lineFile.trim())){
                    System.out.println(lineFile.toCharArray());
                    char[] arrayLine = lineFile.toCharArray();
                    puzzle[i] = arrayLine;
                    i++;
                }
                else{
                    listaPalavras = lineFile.trim().split(";");
                }

            }
            return true;
        }catch(FileNotFoundException e ){
            System.out.println("Ficheiro não encontrado!");
            return false;
        }

    }




    @Override
    public String toString() {
        return "WorldSolverReader [puzzle=" + Arrays.deepToString(puzzle) + ", listaPalavras="
                + Arrays.toString(listaPalavras) + "]";
    }


    public int[] encontrarPalavra(String word){
        for (int i = 0; i < 12; i++){ //linhas
            for(int j = 0; j < 12;j++){ //colunas
                if (puzzle[i][j] == word.charAt(0)){
                    verificar8direcoes(i,j,word);
                    return coords;

                }

            }
        }
        System.out.println("Palavra não encontrada!");
        return null;
    }

    private void verificar8direcoes(int i, int j, String word){
        int[][] direcoes = {
            {1, 0},   // Direita
            {-1, 0},  // Esquerda
            {0, 1},   // Para baixo
            {0, -1},  // Para cima
            {1, 1},   // Diagonal para baixo à direita
            {-1, -1}, // Diagonal para cima à esquerda
            {1, -1},  // Diagonal para cima à direita
            {-1, 1}   // Diagonal para baixo à esquerda
        };
        if(i == 0 || j == 0){
            verificarDireita(i, j, word);
            verificarBaixo(i, j, word);
        }
        else if(i == 0 || j == 12){
            verificarEsquerda(i, j, word);
            verificarBaixo(i, j, word);
        }
        else if (i == 12 || j == 0){
            verificarDireita(i, j, word);
            verificarCima(i, j, word);
        }
        else if (i == 12 || j == 12){
            verificarEsquerda(i, j, word);
            verificarCima(i, j, word);
        }

        else {
            verificarDireita(i, j, word);
            verificarBaixo(i, j, word);
            verificarEsquerda(i, j, word);
            verificarCima(i, j, word);
        }
        


    }
    private boolean verificarDireita(int i, int j, String word) {
        if (j + word.length() > 12) return false; // Evita sair fora da matriz
        for (int x = 0; x < word.length(); x++) {
            if (puzzle[i][j + x] != word.charAt(x)) {
                return false;
            }
        }
        System.out.println(puzzle[i][j]);
        return true;
    }

    private boolean verificarEsquerda(int i, int j, String word) {
        if (j - word.length() + 1 < 0) return false; // Evita sair fora da matriz
        for (int x = 0; x < word.length(); x++) {
            if (puzzle[i][j - x] != word.charAt(x)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarBaixo(int i, int j, String word) {
        if (i + word.length() > 12) return false; // Evita sair fora da matriz
        for (int y = 0; y < word.length(); y++) {
            if (puzzle[i + y][j] != word.charAt(y)) {
                return false;
            }
        }
        return true;
    }

    private boolean verificarCima(int i, int j, String word) {
        if (i - word.length() + 1 < 0) return false; // Evita sair fora da matriz
        for (int y = 0; y < word.length(); y++) {
            if (puzzle[i - y][j] != word.charAt(y)) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        WSSolver c = new WSSolver();
        c.readWorldSearch("sdl_01.txt");
        c.encontrarPalavra("STACK");
    }



}
