package MatrizThreads;

public class TaskCalElement extends Thread {
    private Matriz m1;
    private Matriz m2;
    private int rowIndex;
    private int columIndex;
    private int element;

    public TaskCalElement(Matriz m1, Matriz m2, int rowIndex, int columIndex) {
        this.m1 = m1;
        this.m2 = m2;
        this.rowIndex = rowIndex;
        this.columIndex = columIndex;
    }

    @Override
    public void run() {
        element = calcularValor(m1.getFila(rowIndex), m2.getColumna(columIndex));
    }

    private int calcularValor (int[] fila, int[] columna) {
        int aux = 0;
        for(var i = 0; i < fila.length; i++){
            aux += fila[i] * columna[i];
        }
        return aux;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumIndex() {
        return columIndex;
    }

    public int getElement() {
        return element;
    }
}
