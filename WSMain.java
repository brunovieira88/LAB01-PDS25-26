public class WSMain {

    public static void main(String[] args) {
        //Word Search Solver
        WSSolver solver = new WSSolver();
        solver.readWorldSearch("sdl_01.txt");
        solver.solve();
        solver.showSolution();
        solver.showGraphSolution();

        //Word Search Generator
        WSGenerator generator = new WSGenerator(10);

    }
}
