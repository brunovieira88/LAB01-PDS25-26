package aula01;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WorldSolverReader {
    //ATRIBUTOS
    private char[][] puzzle = new char[12][12];
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
                    if(verificar8direcoes(i,j,word)){
                        int[] coord = {i,j};
                        return coord;
                    }
                }

            }
        }
        System.out.println("Palavra não encontrada!");
        return null;
    }

    private boolean verificar8direcoes(int i, int j, String word){
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

        if(i == 0)
        


    }



    public static void main(String[] args) {
        WorldSolverReader c = new WorldSolverReader();
        c.readWorldSearch("sdl_01.txt");
        System.out.println(c.toString());
    }



}
