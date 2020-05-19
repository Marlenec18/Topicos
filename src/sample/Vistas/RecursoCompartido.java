package sample.Vistas;

public class RecursoCompartido {
    //Recurso compartido por los hilos
    public int[] arreglo = new int[5];
    public int tope = 0;
    public boolean detenerHilo = false;

    //Metodo llamado por el hilo productor
    public synchronized void llenarRecurso(int valor){
        try {

            while (detenerHilo){
                System.out.println("Se detuvo el Hilo Productor");
                wait();
                System.out.println("Se reanudo el Hilo Productor");
            }

            System.out.println("Recurso[" + tope + "] = " + valor);
            arreglo[tope] = valor;
            tope++;

            if(tope == arreglo.length){
                detenerHilo = true;
                tope--;
                notify();
            }
        }catch (Exception e){

        }
    }
    //Metodo llamado por el hilo consumidor
    public synchronized void vaciarRecurso(){
        try{
            while(detenerHilo == false){
                System.out.println("Se detuvo el hilo Consumidor");
                wait();
                System.out.println("Se reanudo el hilo consumidor");
            }

            System.out.println("valor = Recurso[" + tope + "] : " + arreglo[tope]);
            tope--;
            if(tope == 0){
                tope++;
                notify();
                detenerHilo = false;
            }
        }catch (Exception e){

        }
    }
}
