package sample.Eventos;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Vistas.Buscaminas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class EventoBuscaminas implements EventHandler{

    private int nr,nc,nm, C1 = 0, btnid = 0;
    private TextField txtNumRows, txtNumCols;
    private GridPane gdpCampo;
    private Button[][] arbtnCeldas;
    private String[][] arCeldas;
    private VBox vbox;
    private RandomAccessFile fl;

    public EventoBuscaminas(TextField txtNR,TextField txtNC,Button[][] arbtnCELDAS , GridPane gdpCAMPO,VBox vBOX){
        this.txtNumRows = txtNR;
        this.txtNumCols = txtNC;
        this.arbtnCeldas = arbtnCELDAS;
        this.gdpCampo = gdpCAMPO;
        this.vbox = vBOX;


    }

    @Override
    public void handle(Event event) {
        nr = Integer.parseInt(txtNumRows.getText());
        nc = Integer.parseInt(txtNumCols.getText());
            btnid = 0;
            int nmc = 0;
            nm = (int) ((nr * nc) * .25);
            arCeldas = new String[nr][nc];
            while (nmc != nm) {
                int M1 = (int) Math.floor(Math.random() * nr);
                int M2 = (int) Math.floor(Math.random() * nc);
                for (int i = 0; i < nr; i++) {
                    for (int j = 0; j < nc; j++) {
                        if (arCeldas[i][j] != "1") {
                            if (M1 == i && M2 == j) {
                                arCeldas[i][j] = "1";
                                nmc = nmc + 1;
                            } else {
                                arCeldas[i][j] = "0";
                            }
                        }
                    }
                }
            }
        try
        {
            fl = new RandomAccessFile("Buscaminas.txt", "rw");
            fl.seek(0);
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nc; j++) {
                    fl.write(Integer.parseInt(arCeldas[i][j]));
                }
            }


            }
        catch (Exception e){
            e.printStackTrace();
        }
            if (arbtnCeldas != null)
                vbox.getChildren().remove(gdpCampo);

            arbtnCeldas = new Button[nr][nc];
            gdpCampo = new GridPane();
            gdpCampo.setPadding(new Insets(15));
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nc; j++) {
                    arbtnCeldas[i][j] = new Button(arCeldas[i][j]);
                    arbtnCeldas[i][j].setPrefSize(50, 50);
                    arbtnCeldas[i][j].setId("btnNormal");
                    //arbtnCeldas[i][j].setId("" + btnid);
                    arbtnCeldas[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new BuscarMina(nr, nc, arbtnCeldas, gdpCampo, vbox, i, j, arCeldas, nm));
                    gdpCampo.add(arbtnCeldas[i][j], j, i);
                    btnid = btnid + 1;
                    //arbtnCeldas[i][j].setText("");
                }
            }
            gdpCampo.setAlignment(Pos.CENTER);
            vbox.getChildren().add(gdpCampo);
            }

        }

