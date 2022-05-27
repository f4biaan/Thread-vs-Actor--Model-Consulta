package MatrizThreads;

public class RunMatriz {
    public static void main(String[] args) throws InterruptedException {

        int[][] matriz1Values = {
                {6, 4},
                {3, 7}
        };
        Matriz m1 = new Matriz(matriz1Values);

        int[][] matriz2Values = {
                {0, 6},
                {8, 3}
        };
        Matriz m2 = new Matriz(matriz2Values);

        Matriz result = m1.multiplyWithThreads(m2);

        System.out.println(result);
    }
}
