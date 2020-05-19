package sample.Eventos;


import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class BuscarMina implements EventHandler <MouseEvent> {
    private int nr, nc, nm, nmc, i, j, E1 = 0, ci, f = 0, cj, minas = 0, c1 = 0, c2 = 0, E2 = 0, ci2, cj2, cminas = 0, Cminas, cvacio = 0, celdas, cm, D = 0;
    private GridPane gdpCampo;
    private Button[][] arbtnCeldas;
    private String[][] arCeldas, arPos;
    private VBox vbox;
    private boolean b1 = true;
    Alert alerta = new Alert(Alert.AlertType.ERROR);
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private RandomAccessFile fl;

    public BuscarMina(int NR, int NC, Button[][] arbtnCELDAS, GridPane gdpCAMPO, VBox vBOX, int I, int J, String[][] arceldas, int CMinas) {
        nr = NR;
        i = I;
        j = J;
        nc = NC;
        ci = i;
        cj = j;
        ci2 = i;
        cj2 = j;
        Cminas = CMinas;
        celdas = (nr * nc) - Cminas;
        this.arbtnCeldas = arbtnCELDAS;
        this.gdpCampo = gdpCAMPO;
        this.vbox = vBOX;
        arCeldas = arceldas;
        alerta.setTitle("Buscaminas");
        alerta.setHeaderText("Fin del Juego");
        alerta.setContentText("Has explotado :(");
        alert.setTitle("Buscaminas");
        alert.setHeaderText("Fin del Juego");
        alert.setContentText("Has Ganado :D");

        try {
            fl = new RandomAccessFile("Buscaminas.txt", "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        D = 0;
        arPos = new String[nr][nc];
        for (int a = 0; a < nr; a++) {
            for (int b = 0; b < nc; b++) {
                arPos[a][b] = String.valueOf(D);
                D++;
            }
        }
    }

    @Override
    public void handle(MouseEvent event) {
        /*
        try {
            fl.seek(Long.parseLong(arPos[i][j]));
            System.out.println(fl.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //System.out.println(" I: " + i + " J: " + j);
        if (event.getButton() == MouseButton.PRIMARY) {
            try {
                fl.seek(Long.parseLong(arPos[i][j]));
                if (fl.read() == 0) {
                    arbtnCeldas[i][j].setId("btnVacio");
                    Buscar(i, j);
                }
                fl.seek(Long.parseLong(arPos[i][j]));
                if (fl.read() == 1) {
                    arbtnCeldas[i][j].setId("btnMina");
                    alerta.showAndWait();
                    vbox.getChildren().remove(gdpCampo);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (arbtnCeldas[i][j].getId().equals("btnNormal")) {
                    arbtnCeldas[i][j].setText("");
                    arbtnCeldas[i][j].setId("btnMina");
                } else {
                    if (arbtnCeldas[i][j].getId().equals("btnMina")) {
                        arbtnCeldas[i][j].setId("btnNormal");
                    }
                }

            }
        }

        cminas = 0;
        cvacio = 0;
        for (int a = 0; a < nr; a++) {
            for (int b = 0; b < nc; b++) {
                if (arbtnCeldas[a][b].getId() == "btnMina" && arCeldas[a][b] == "1") {
                    cminas = cminas + 1;
                } else {
                    if (arbtnCeldas[a][b].getId() == "btnVacio" && arCeldas[a][b] == "0") {
                        cvacio = cvacio + 1;
                    }
                }
                /*if
                }*/
            }
        }
        f = 0;
        for (int a = 0; a < nr; a++) {
            for (int b = 0; b < nc; b++) {
                f = f + BuscarVacio(a, b);
            }
        }

        for (int h = 1; h <= f; h++) {
            for (int a = 0; a < nr; a++) {
                for (int b = 0; b < nc; b++) {
                    if (arbtnCeldas[a][b].getId() == "btnVacio" && arbtnCeldas[a][b].getText() == "") {
                        Buscar(a, b);
                    }
                }
            }
        }

        System.out.println("Contador Minas : " + cminas);
        if (cminas == Cminas && cvacio == celdas) {
            alert.showAndWait();
            vbox.getChildren().remove(gdpCampo);
        }

    }

    public void Buscar(int ci2, int cj2) {
        E1 = 0;
        c1 = 0;
        minas = 0;
        ci = ci2;
        cj = cj2;

        while (E1 == 0) {

            switch (c1) {

                case 0:
                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 1: // Izq Arriba
                    ci = ci2 - 1;
                    cj = cj2 - 1;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }

                    break;

                case 2: //Izq
                    ci = ci2;
                    cj = cj2 - 1;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 3: //Izq Abajo
                    ci = ci2 + 1;
                    cj = cj2 - 1;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 4: //Arriba
                    ci = ci2 - 1;
                    cj = cj2;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 5: //Abajo
                    ci = ci2 + 1;
                    cj = cj2;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 6: //Der Arriba
                    ci = ci2 - 1;
                    cj = cj2 + 1;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 7: // Der
                    ci = ci2;
                    cj = cj2 + 1;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 8: // Der Abajo
                    ci = ci2 + 1;
                    cj = cj2 + 1;
                    minas = 0;

                    if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
                        //Izq Arriba
                        if (ci - 1 >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq
                        if (ci >= 0 && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Izq Abajo
                        if (ci + 1 < nr && cj - 1 >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Arriba
                        if (ci - 1 >= 0 && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Abajo
                        if (ci + 1 < nr && cj >= 0)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Arriba
                        if (ci - 1 >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der
                        if (ci >= 0 && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        //Der Abajo
                        if (ci + 1 < nr && cj + 1 < nc)
                            try {
                                fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                                if (fl.read() == 1) {
                                    minas = minas + 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    break;

                case 9:

                    E1 = 1;

            }

            if (ci >= 0 && cj >= 0 && ci < nr && cj < nc) {
                if (arCeldas[ci][cj] == "0" && minas == 0) {
                    arbtnCeldas[ci][cj].setText("");
                    arbtnCeldas[ci][cj].setId("btnVacio");

                } else {
                    if (arCeldas[ci][cj] == "0") {
                        arbtnCeldas[ci][cj].setText("" + minas);
                        arbtnCeldas[ci][cj].setId("btnVacio");
                    }
                }
            }

            c1 = c1 + 1;

        }

    }

    public int BuscarVacio(int ci2, int cj2) {
        E1 = 0;
        minas = 0;
        ci = ci2;
        cj = cj2;

        int contvacio = 0;

        if (ci >= 0 && ci < nr && cj >= 0 && cj < nc) {
            //Izq Arriba
            if (ci - 1 >= 0 && cj - 1 >= 0)
                try {
                    fl.seek(Long.parseLong(arPos[ci - 1][cj - 1]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Izq
            if (ci >= 0 && cj - 1 >= 0)
                try {
                    fl.seek(Long.parseLong(arPos[ci][cj - 1]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Izq Abajo
            if (ci + 1 < nr && cj - 1 >= 0)
                try {
                    fl.seek(Long.parseLong(arPos[ci + 1][cj - 1]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Arriba
            if (ci - 1 >= 0 && cj >= 0)
                try {
                    fl.seek(Long.parseLong(arPos[ci - 1][cj]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Abajo
            if (ci + 1 < nr && cj >= 0)
                try {
                    fl.seek(Long.parseLong(arPos[ci + 1][cj]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Der Arriba
            if (ci - 1 >= 0 && cj + 1 < nc)
                try {
                    fl.seek(Long.parseLong(arPos[ci - 1][cj + 1]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Der
            if (ci >= 0 && cj + 1 < nc)
                try {
                    fl.seek(Long.parseLong(arPos[ci][cj + 1]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            //Der Abajo
            if (ci + 1 < nr && cj + 1 < nc)
                try {
                    fl.seek(Long.parseLong(arPos[ci + 1][cj + 1]));
                    if (fl.read() == 1) {
                        minas = minas + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        if (ci >= 0 && cj >= 0 && ci < nr && cj < nc) {
            if (arCeldas[ci][cj] == "0" && minas == 0) {
                contvacio = 1;
            }
        }

        return contvacio;
    }

}