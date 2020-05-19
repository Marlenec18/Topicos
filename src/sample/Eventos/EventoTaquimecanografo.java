package sample.Eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EventoTaquimecanografo implements EventHandler<KeyEvent> {

    Button[] arBotones1;
    Button[] arBotones2;
    Button[] arBotones3;
    Button[] arBotones4;
    Button[] arBotones5;
    Button[] arBotones6;
    Boolean ban;
    int pos1 = -4 , pos2 = -4 , pos3 = -4 , pos4 = -4 , pos5 = -4 , pos6 = -4;

    public EventoTaquimecanografo(Button[] arreglo1,Button[] arreglo2,Button[] arreglo3,Button[] arreglo4,Button[] arreglo5,Button[] arreglo6){
        arBotones1 = arreglo1;
        arBotones2 = arreglo2;
        arBotones3 = arreglo3;
        arBotones4 = arreglo4;
        arBotones5 = arreglo5;
        arBotones6 = arreglo6;
        ban = true;
    }

    @Override
    public void handle(KeyEvent event) {
        Object source = event.getSource();

        //R1
        if( event.getCode() == KeyCode.ESCAPE)
            pos1 = 0;
        if( event.getCode() == KeyCode.HELP)
            pos1 = 1;
        if( event.getCode() == KeyCode.F2)
            pos1 = 2;
        if( event.getCode() == KeyCode.F3)
            pos1 = 3;
        if( event.getCode() == KeyCode.F4)
            pos1 = 4;
        if( event.getCode() == KeyCode.F5)
            pos1 = 5;
        if( event.getCode() == KeyCode.MUTE)
            pos1 = 6;
        if( event.getCode() == KeyCode.VOLUME_DOWN)
            pos1 = 7;
        if( event.getCode() == KeyCode.VOLUME_UP)
            pos1 = 8;
        if( event.getCode() == KeyCode.TRACK_PREV)
            pos1 = 9;
        if( event.getCode() == KeyCode.PAUSE)
            pos1 = 10;
        if( event.getCode() == KeyCode.TRACK_NEXT)
            pos1 = 11;
        if( event.getCode() == KeyCode.F12)
            pos1 = 12;
        if( event.getCode() == KeyCode.INSERT)
            pos1 = 13;
        if( event.getCode() == KeyCode.DELETE)
            pos1 = 14;

        //R2
        if( event.getCode() == KeyCode.BACK_QUOTE)
            pos2 = 0;
        if( event.getCode() == KeyCode.DIGIT1)
            pos2 = 1;
        if( event.getCode() == KeyCode.DIGIT2)
            pos2 = 2;
        if( event.getCode() == KeyCode.DIGIT3)
            pos2 = 3;
        if( event.getCode() == KeyCode.DIGIT4)
            pos2 = 4;
        if( event.getCode() == KeyCode.DIGIT5)
            pos2 = 5;
        if( event.getCode() == KeyCode.DIGIT6)
            pos2 = 6;
        if( event.getCode() == KeyCode.DIGIT7)
            pos2 = 7;
        if( event.getCode() == KeyCode.DIGIT8)
            pos2 = 8;
        if( event.getCode() == KeyCode.DIGIT9)
            pos2 = 9;
        if( event.getCode() == KeyCode.DIGIT0)
            pos2 = 10;
        if( event.getCode() == KeyCode.EQUALS)
            pos2 = 12;
        if( event.getCode() == KeyCode.BACK_SPACE)
            pos2 = 13;



        if( event.getCode() == KeyCode.TAB)
            pos3 = 0;
        if( event.getCode() == KeyCode.Q)
            pos3 = 1;
        if( event.getCode() == KeyCode.W)
            pos3 = 2;
        if( event.getCode() == KeyCode.E)
            pos3 = 3;
        if( event.getCode() == KeyCode.R)
            pos3 = 4;
        if( event.getCode() == KeyCode.T)
            pos3 = 5;
        if( event.getCode() == KeyCode.Y)
            pos3 = 6;
        if( event.getCode() == KeyCode.U)
            pos3 = 7;
        if( event.getCode() == KeyCode.I)
            pos3 = 8;
        if( event.getCode() == KeyCode.O)
            pos3 = 9;
        if( event.getCode() == KeyCode.P)
            pos3 = 10;
        if( event.getCode() == KeyCode.DEAD_ACUTE)
            pos3 = 11;
        if( event.getCode() == KeyCode.PLUS)
            pos3 = 12;
        if( event.getCode() == KeyCode.ENTER)
            pos3 = 13;

        if( event.getCode() == KeyCode.CAPS)
            pos4 = 0;
        if( event.getCode() == KeyCode.A)
            pos4 = 1;
        if( event.getCode() == KeyCode.S)
            pos4 = 2;
        if( event.getCode() == KeyCode.D)
            pos4 = 3;
        if( event.getCode() == KeyCode.F)
            pos4 = 4;
        if( event.getCode() == KeyCode.G)
            pos4 = 5;
        if( event.getCode() == KeyCode.H)
            pos4 = 6;
        if( event.getCode() == KeyCode.J)
            pos4 = 7;
        if( event.getCode() == KeyCode.K)
            pos4 = 8;
        if( event.getCode() == KeyCode.L)
            pos4 = 9;
        if( event.getCode() == KeyCode.BRACELEFT)
            pos4 = 11;
        if( event.getCode() == KeyCode.BRACERIGHT)
            pos4 = 12;

        if( event.getCode() == KeyCode.SHIFT)
            pos5 = 0;
        if( event.getCode() == KeyCode.PERIOD)
            pos5 = 1;
        if( event.getCode() == KeyCode.Z)
            pos5 = 2;
        if( event.getCode() == KeyCode.X)
            pos5 = 3;
        if( event.getCode() == KeyCode.C)
            pos5 = 4;
        if( event.getCode() == KeyCode.V)
            pos5 = 5;
        if( event.getCode() == KeyCode.B)
            pos5 = 6;
        if( event.getCode() == KeyCode.N)
            pos5 = 7;
        if( event.getCode() == KeyCode.M)
            pos5 = 8;
        if( event.getCode() == KeyCode.COMMA)
            pos5 = 9;
        if( event.getCode() == KeyCode.PERIOD)
            pos5 = 10;
        if( event.getCode() == KeyCode.MINUS)
            pos5 = 11;
        if( event.getCode() == KeyCode.SHIFT)
            pos5 = 12;


        if( event.getCode() == KeyCode.CONTROL)
            pos6 = 0;
        if( event.getCode() == KeyCode.FINAL)
            pos6 = 1;
        if( event.getCode() == KeyCode.WINDOWS)
            pos6 = 2;
        if( event.getCode() == KeyCode.ALT)
            pos6 = 3;
        if( event.getCode() == KeyCode.SPACE)
            pos6 = 4;
        if( event.getCode() == KeyCode.ALT_GRAPH)
            pos6 = 5;
        if( event.getCode() == KeyCode.LEFT)
            pos6 = 7;
        if( event.getCode() == KeyCode.UP)
            pos6 = 8;
        if( event.getCode() == KeyCode.DOWN)
            pos6 = 9;
        if( event.getCode() == KeyCode.RIGHT)
            pos6 = 10;

        if(pos1 !=-4) {
            if (ban) {
                arBotones1[pos1].setStyle("-fx-base: #5D74D4;");
            } else {
                arBotones1[pos1].setStyle("-fx-base: #9997E8;");
            }
        }
        
        if(pos2 !=-4) {
            if (ban) {
                arBotones2[pos2].setStyle("-fx-base:  #5D74D4;");
            } else {
                arBotones2[pos2].setStyle("-fx-base: #9997E8;");
            }
        }
        
        if(pos3 !=-4) {
            if (ban) {
                arBotones3[pos3].setStyle("-fx-base: #5D74D4;");
            } else {
                arBotones3[pos3].setStyle("-fx-base: #9997E8;");
            }
        }
               
        if(pos4 !=-4) {
            if (ban) {
                arBotones4[pos4].setStyle("-fx-base: #5D74D4;");
            } else {
                arBotones4[pos4].setStyle("-fx-base: #9997E8;");
            }
        }
                    
        if (pos5 !=-4) {
            if (ban) {
                if (pos5 == 12) {
                    arBotones5[0].setStyle("-fx-base: #5D74D4;");
                    arBotones5[12].setStyle("-fx-base: #5D74D4;");
                } else {
                    arBotones5[pos5].setStyle("-fx-base: #5D74D4;");
                }
            } else {
                arBotones5[pos5].setStyle("-fx-base: #9997E8;");
                arBotones5[0].setStyle("-fx-base: #9997E8;");
                arBotones5[12].setStyle("-fx-base: #9997E8;");

            }
        }
            if(pos6 !=-4) {
                if (ban) {
                    if (pos6 == 0) {
                        arBotones6[0].setStyle("-fx-base: #5D74D4;");
                        arBotones6[6].setStyle("-fx-base: #5D74D4;");
                    } else {
                        arBotones6[pos6].setStyle("-fx-base: #5D74D4;");
                    }
                } else {
                    arBotones6[pos6].setStyle("-fx-base: #9997E8;");
                    arBotones6[0].setStyle("-fx-base: #9997E8;");
                    arBotones6[6].setStyle("-fx-base: #9997E8;");
                }
            }
        ban = !ban;
        pos1 = -4;
        pos2 = -4;
        pos3 = -4;
        pos4 = -4;
        pos5 = -4;
        pos6 = -4;

    }
}