package sample.Vistas;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Eventos.BuscarMina;
import sample.Eventos.EventoBuscaminas;


public class Buscaminas extends Stage implements EventHandler {
    private Label lblNumRows, lblNumCol;
    private TextField txtNumRows, txtNumCols;
    private Button btnMinar, btnBomba;
    private GridPane gdpCampo;
    private Button[][] arbtnCeldas; //reserva cantidad de espacios para botones
    private Scene escena;
    private HBox hbox;
    private VBox vbox;
    private int nr = 1 ,nc = 1;
    private BuscarMina ObjB;

    public Buscaminas(){
        CrearGUI();
        this.setTitle("Mi Buscaminas :D");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        hbox = new HBox();
        txtNumRows = new TextField();
        txtNumCols = new TextField();
        lblNumRows = new Label("No. Rows");
        lblNumCol = new Label("No. Cols");
        btnMinar = new Button("Minar Campo");
        btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventoBuscaminas(txtNumRows,txtNumCols , arbtnCeldas,gdpCampo,vbox)); //Evento en otra clase independiente

        //btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED,this); //Evento en la misma clase
        /*btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { // Evento no reutilizable
            @Override
            public void handle(MouseEvent event) {

            }
        });*/
        /*btnMinar.setOnAction(event -> {
            System.out.println("4rto Evento");
        });
        */



        hbox.getChildren().addAll(lblNumRows,txtNumRows,lblNumCol,txtNumCols, btnMinar);
        vbox.getChildren().addAll(hbox);
        escena  = new Scene(vbox, 600, 500);
        escena.getStylesheets().add("sample/Estilos/estilos_buscaminas.css");//Estilo

    }

    private void Evento() {
        System.out.println("Funciona");
        arbtnCeldas[0][0].setStyle("btnVacio");
    }

    @Override
    public void handle(Event event) {
        nr = Integer.parseInt(txtNumRows.getText());
        nc = Integer.parseInt(txtNumCols.getText());

        if (arbtnCeldas != null)
            vbox.getChildren().remove(gdpCampo);

        arbtnCeldas = new Button[nr][nc];
        gdpCampo = new GridPane();
        gdpCampo.setPadding(new Insets(15));
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                arbtnCeldas[i][j] = new Button(i + "-" + j);
                arbtnCeldas[i][j].setPrefSize(50, 50);
                arbtnCeldas[i][j].setId("btnNormal");
                gdpCampo.add(arbtnCeldas[i][j], j, i);
            }
        }
        vbox.getChildren().add(gdpCampo);
    }

}
