import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;

public class WSGenerator {
    private Soup soup;
    private int size;
    private List<String> words;
    private Random random;


    WSGenerator(int size){
        this.size = size;
        char[][] grid = new char[size][size];
        this.words = new ArrayList<>();
        this.random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-'; //preencher toda a tabela
            }
        }
        this.soup = new Soup(grid);
    }
    
    public void generateGrid(List<String> words) {
        this.words = new ArrayList<>(words);
        //sort com streams do maior para menor pois maiores mais dificeis de encaixar
        this.words.sort((a,b)->Integer.compare(b.length(), a.length()));
        //chamar metodo para tentar por cada palavra
        for (String word: this.words){
            placeWord(word.toUpperCase().trim());
        }
        fill();
        soup.setPalavrasChave(new ArrayList<>(words));
    }

    private void placeWord(String word){
        boolean placed = false;
        int attempts=0;
        char[][] grid = soup.getArraySoup();
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
        soup.setArraySoup(grid);

    }

    private boolean hasSpace(String word, int beginX, int beginY, int x, int y){
        int bX = beginX;
        int bY = beginY;
        char[][] grid = soup.getArraySoup();

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
        char[][] grid = soup.getArraySoup();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(grid[i][j]=='-'){
                    //letra aleatoria
                    grid[i][j] = (char)('A' + random.nextInt(26));
                }
            }
        }
        soup.setArraySoup(grid);
    }
    public Soup getSoup() {
        return soup;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Use: java WSGenerator -i <wordlist.txt> -s <size> -o <output.txt>");
            System.exit(1);
        }
        String inputFile = null;
        String outputFile = null;
        int size;
        size = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                inputFile = args[++i];
            } else if (args[i].equals("-s")) {
                size = Integer.parseInt(args[++i]);
                if (size > 40) {
                    System.out.println("Error: Size cannot be greater than 40.");
                    System.exit(1);
                }
            } else if (args[i].equals("-o")){
                outputFile = args[++i];
            }
        }

        if (inputFile == null || outputFile == null) {
            System.out.println("Error: Forgot -w or -s.");
            System.exit(1);;
        }
        try {
            //ler palavras do file 
            List<String> words = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        words.add(line.trim());
                    }
                }
            }

            WSGenerator generator = new WSGenerator(size);
            generator.generateGrid(words);

            //guardar noutro ficheiro 
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
                bw.write(generator.getSoup().toString());
            }

            System.out.println("WordSoup saved with success: " + outputFile);

        } catch (IOException e) {
            System.out.println("There was an error processing the files: " + e.getMessage());
        }
    }
}

