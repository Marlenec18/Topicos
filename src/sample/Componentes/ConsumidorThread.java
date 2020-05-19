package sample.Componentes;

import sample.Vistas.RecursoCompartido;

public class ConsumidorThread extends Thread{
    RecursoCompartido objRec;

    public ConsumidorThread(RecursoCompartido objRec){
        this.objRec = objRec;
    }

    @Override
    public void run() {
        super.run();
        for(int i = 1; i <= 50; i++){
            objRec.vaciarRecurso();
        }
    }
}

