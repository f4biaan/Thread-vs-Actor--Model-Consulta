package MatrizThreads;

import java.util.ArrayList;
import java.util.List;
import MatrizThreads.TaskCalElement;
public class Matriz {
    private int [][] values;

    public Matriz(int[][] values) {
        this.values = values;
    }

    public Matriz multiplyWithThreads(Matriz matriz2) throws InterruptedException {
        List<TaskCalElement> threads = new ArrayList<>();
        if (values.length == matriz2.values[0].length) {
            // output matriz de salida
            int[][] output = new int[values.length][matriz2.values[0].length];
            //threads = new ArrayList<>();
            for (var f = 0; f < output.length; f++) {
                for (var c = 0; c < output[0].length; c++) {
                    TaskCalElement thread = new TaskCalElement(this, matriz2, f, c);
                    thread.start();
                    threads.add(thread);
                }
            }
            setWaitThreads(threads);
            for (var t : threads) {
                output[t.getRowIndex()][t.getColumIndex()] = t.getElement();
            }
            // Retorna un objeto
            return new Matriz(output);
        } else {
            throw new IllegalArgumentException("No se puede multiplicar");
        }
    }
    private void setWaitThreads(List<TaskCalElement> threads) throws InterruptedException {
        for (var t : threads) {
            t.join();
        }
    }

    public int[] getFila(int FilaIndex) {
        if (FilaIndex >= 0 && FilaIndex < values.length) {
            return values[FilaIndex];
        } else {
            throw new IllegalArgumentException("Indice no válido");
        }
    }

    public int[] getColumna(int ColumnaIndex) {
        int[] output = new int[values.length];
        if (ColumnaIndex < values[0].length) {
            for (var i = 0; i < values.length; i++) {
                output[i] = values[i][ColumnaIndex];
            }
        }
        return output;
    }

    public String toString() {
        String output = "";
        for (var fila : values) {
            output += "{";
            for (var value : fila) {
                output += value + "\t";
            }
            output += "}\n";
        }
        return "{\n" + output + "}";
    }
}
