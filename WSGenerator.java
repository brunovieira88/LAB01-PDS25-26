import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WSGenerator {
    private char[][] grid;
    private int size;
    private List<String> words;
    private Random random;


    WSGenerator(int size){
        this.size = size;
        this.grid = new char[size][size];
        this.words = new ArrayList<>();
        this.random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grid[i][j] = '-'; //preencher toda a tabela
            }
        }
    }
    
    public void generateGrid(List<String> words) {
        this.words = new ArrayList<>(words);
        //sort com streams do maior para menor pois maiores mais dificeis de encaixar
        this.words.sort((a,b)->Integer.compare(b.length(), a.length()));
        //chamar metodo para tentar por cada palavra
        for (String word: this.words){
            placeWord(word.toLowerCase().trim());
        }
        fill();
        
    }

    private void placeWord(String word){
        boolean placed = false;
        int attempts=0;
        //so para de tentar se ja for placed ou se exceder demasiadas tentativas(numero a rever)
        while (!placed && attempts <999){
            attempts++;
            //selecionar direcao random
            Direction[] directions = Direction.values();
            Direction dir = directions[random.nextInt(directions.length)];
            int x = dir.getDx();
            int y = dir.getDy();

            //se for 0,0 tenta again porque nao se mexe em 0,0
            if(x == 0 && y == 0) {continue;}
            
            //agora coordenadas iniciais random
            int beginX = random.nextInt(size);
            int beginY = random.nextInt(size);

            if (hasSpace(word, beginX, beginY, x, y)){
                for (int i = 0; i < word.length(); i++){
                    //desloca e mete as letras
                    int pX = beginX + x * i; 
                    int pY = beginY + y * i;
                    grid[pY][pX] = word.charAt(i);
                }
                placed = true;
            }
        }

    }

    private boolean hasSpace(String word, int beginX, int beginY, int x, int y){
        int bX = beginX;
        int bY = beginY;

        for(int i = 0; i<word.length();i++){
            //confirmar se nao sai dos limites da matriz
            if(bX<0 || bX >= size || bY < 0 || bY >= size){
                return false;
            }
            char c = grid[bY][bX];
            //confirmar se esta livre (tem -) ou se ja tem outra letra
            if (c != '-' && c != word.charAt(i)){
                return false;
            }
            //aplicar o deslocamento para testar proximas letras 
            bX += x;
            bY += y;
        }
        return true;
    }

    public void fill() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(grid[i][j]=='-'){
                    //letra aleatoria
                    grid[i][j] = (char)('a' + random.nextInt(26));
                }
            }
        }
    }

    public static void main(String[] args) {
        //TESTE
        WSGenerator generator = new WSGenerator(10);
        List<String> words = new ArrayList<>();
        words.add("Java");
        words.add("Program");
        words.add("Code");
        words.add("Receba");
        generator.generateGrid(words);

        //print da sopa
        for (int i = 0; i < generator.size; i++) {
            for (int j = 0; j < generator.size; j++) {
                System.out.print(generator.grid[i][j] + " ");
            }
            System.out.println();
        }
        for (String word : words){
            System.out.printf("%s\n", word);
        }
    }
    
}
