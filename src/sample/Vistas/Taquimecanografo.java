package sample.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Eventos.EventoTaquimecanografo;

public class Taquimecanografo extends Stage {
    private Scene escena;
    private ToolBar tlbMenu;
    private Button btnAbrir;
    private TextArea txaTexto, txaEscritura;
    private VBox vboxPrincipal, vboxTeclado;
    private HBox hTeclas1,hTeclas2,hTeclas3,hTeclas4,hTeclas5,hTeclas6;
    private Button [] arBotones1,arBotones2,arBotones3,arBotones4,arBotones5,arBotones6;
    private String[] arTeclas1 = {"esc","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","ins","supr"};
    private String[] arTeclas2 = {"|","1","2","3","4","5","6","7","8","9","0","'","¿","  <- "};
    private String[] arTeclas3 = {" -> ","Q","W","E","R","T","Y","U","I","O","P","´","+"," Enter "};
    private String[] arTeclas4 = {" bloq mayús ","A","S","D","F","G","H","J","K","L","Ñ","{","}"};
    private String[] arTeclas5 = {"Shift","<","Z","X","C","V","B","N","M",",",".","-","Shift"};
    private String[] arTeclas6 = {"ctrl","fn","Windows","Alt","        Espacio       ","clt gr","ctrl","<","arriba","abajo",">"};
    private FileChooser flcArchivos;

    public Taquimecanografo(){
       CrearGUI();
       this.setTitle("Mi Tutor de Mecanografía");
       this.setScene(escena);
       this.show();
    }

    private void CrearGUI() {
        tlbMenu = new ToolBar();
        btnAbrir = new Button();
        btnAbrir.setGraphic(new ImageView("sample/Imagenes/iconfinder_Artboard_12_5740103.png")); // Agregar imagen a Boton
        btnAbrir.setOnAction(event -> AbrirExplorador());
        tlbMenu.getItems().add(btnAbrir);

        txaTexto = new TextArea();
        txaTexto.setPrefRowCount(5);
        txaTexto.setEditable(false); //Bloquear acceso al teclado
        txaEscritura = new TextArea();
        txaEscritura.setPrefRowCount(5);

        hTeclas1 = new HBox();
        hTeclas1.setSpacing(5);
        hTeclas1.setId("hbox-custom");

        hTeclas2 = new HBox();
        hTeclas2.setSpacing(5);
        hTeclas2.setId("hbox-custom");

        hTeclas3 = new HBox();
        hTeclas3.setSpacing(5);
        hTeclas3.setId("hbox-custom");

        hTeclas4 = new HBox();
        hTeclas4.setSpacing(5);
        hTeclas4.setId("hbox-custom");

        hTeclas5 = new HBox();
        hTeclas5.setSpacing(5);
        hTeclas5.setId("hbox-custom");

        hTeclas6 = new HBox();
        hTeclas6.setSpacing(5);
        hTeclas6.setId("hbox-custom");

        arBotones1 = new Button[15];
        arBotones2 = new Button[14];
        arBotones3 = new Button[14];
        arBotones4 = new Button[13];
        arBotones5 = new Button[13];
        arBotones6 = new Button[12];

        for (int i = 0; i < 14; i++) {
            arBotones3[i] = new Button(arTeclas3[i]);
            hTeclas3.getChildren().add(arBotones3[i]);
            arBotones2[i] = new Button(arTeclas2[i]);
            hTeclas2.getChildren().add(arBotones2[i]);

            if( i != 0 && i != 14 ) {
                arBotones3[i].setId("btnNormal");
                arBotones2[i].setId("btnNormal");
            }
            else {
                arBotones3[i].setId("btnLargo");
                arBotones2[i].setId("btnLargo");
            }
        }

        for (int i = 0; i < 15; i++) {
            arBotones1[i] = new Button(arTeclas1[i]);
            hTeclas1.getChildren().add(arBotones1[i]);
            arBotones1[i].setId("btnF");
        }

        for (int i = 0; i < 13; i++) {
            arBotones4[i] = new Button(arTeclas4[i]);
            hTeclas4.getChildren().add(arBotones4[i]);
            arBotones5[i] = new Button(arTeclas5[i]);
            hTeclas5.getChildren().add(arBotones5[i]);

            if( i == 0  ) {
                arBotones4[0].setId("btnLargo");
            }
            if(i == 12 ) {
                arBotones5[i].setId("btnLargo");
            }
            if(i == 13 ) {
                arBotones4[14].setId("btnLargo");
            }
            else {
                arBotones4[i].setId("btnNormal");
                arBotones5[i].setId("btnNormal");
            }
        }

        for (int i = 0; i < 11; i++) {
            arBotones6[i] = new Button(arTeclas6[i]);
            hTeclas6.getChildren().add(arBotones6[i]);
            if(i == 4) {
                arBotones6[i].setId("btnEspacio");
            }
            else {
                arBotones6[i].setId("btnNormal");
            }
        }

        hTeclas1.setAlignment(Pos.CENTER);
        hTeclas2.setAlignment(Pos.CENTER);
        hTeclas3.setAlignment(Pos.CENTER);
        hTeclas4.setAlignment(Pos.CENTER);
        hTeclas5.setAlignment(Pos.CENTER);
        hTeclas6.setAlignment(Pos.CENTER);

        hTeclas1.setSpacing(13.5);
        hTeclas1.setPadding(new Insets(5));
        hTeclas2.setSpacing(5);
        hTeclas2.setPadding(new Insets(5));
        hTeclas3.setSpacing(5);
        hTeclas3.setPadding(new Insets(5));
        hTeclas4.setSpacing(8.5);
        hTeclas4.setPadding(new Insets(5));
        hTeclas5.setSpacing(11.5);
        hTeclas5.setPadding(new Insets(5));
        hTeclas6.setSpacing(6);
        hTeclas6.setPadding(new Insets(5));

        EventoTaquimecanografo objEvento = new EventoTaquimecanografo(arBotones1 , arBotones2 , arBotones3 , arBotones4 , arBotones5 , arBotones6);
        txaEscritura.setOnKeyPressed( objEvento);

        txaEscritura.setOnKeyReleased(objEvento);

        vboxPrincipal = new VBox();
        vboxTeclado = new VBox();
        vboxTeclado.getChildren().addAll(hTeclas1,hTeclas2,hTeclas3,hTeclas4,hTeclas5,hTeclas6);
        vboxPrincipal.setSpacing(7);
        vboxPrincipal.getChildren().addAll(tlbMenu, txaTexto,txaEscritura,vboxTeclado);
        escena = new Scene(vboxPrincipal,850,600);
        vboxPrincipal.getStylesheets().add("sample/Estilos/estilos_taquimecanografo.css");
    }

    private void AbrirExplorador() {
        flcArchivos = new FileChooser();
        flcArchivos.setTitle("Archivos Taquimecanografo");
        flcArchivos.showOpenDialog(this);
    }
}
