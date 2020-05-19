package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.Componentes.ConsumidorThread;
import sample.Componentes.ProductorThread;
import sample.Modelos.Conexion;
import sample.Vistas.*;

public class Main extends Application {

    MenuBar mnbProyecto;
    Menu menCompetencia1, menCompetencia2,mensalir;
    MenuItem mitPractica1, mitPractica2, mitPractica3, mitPractica4, mitBye;
    Scene escena;
    BorderPane brpPrincipal;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        /*
        AnchorPane anchor = new AnchorPane();

        Button btn1 = new Button("Btn1");
        Button btn2 = new Button("Btn2");
        Button btn3 = new Button("Btn3");
        Button btn4 = new Button("Btn4");

        HBox hbox = new HBox();
        VBox vbox = new VBox();

        vbox.getChildren().addAll(btn1,btn2);
        hbox.getChildren().addAll(btn3,btn4);
        vbox.getChildren().addAll(hbox);

        Label lb1 = new Label("Hola :)");
        vbox.getChildren().addAll(lb1);
        */
        brpPrincipal = new BorderPane();
        mnbProyecto = new MenuBar();
        brpPrincipal.setTop(mnbProyecto);

        menCompetencia1 = new Menu("1er. Competencia");
        menCompetencia2 = new Menu("2da. Competencia");
        mensalir = new Menu("Salir");

        mitPractica1 = new MenuItem("Buscaminas");
        mitPractica1.setOnAction(event -> OpcionMenu(1));
        mitPractica2 = new MenuItem("Taquimecanografo");
        mitPractica2.setOnAction(event -> OpcionMenu(2));
        mitPractica3 = new MenuItem("CRUD Productos");
        mitPractica3.setOnAction(event -> OpcionMenu(3));
        mitPractica4 = new MenuItem("Pista Atletismo");
        mitPractica4.setOnAction(event -> OpcionMenu(4));
        mitBye = new MenuItem("Bye");
        mitBye.setOnAction(event -> OpcionMenu(20));
        menCompetencia1.getItems().addAll(mitPractica1,mitPractica2);
        menCompetencia2.getItems().addAll(mitPractica3, mitPractica4);
        mensalir.getItems().add(mitBye);

        //Pasarlos al menu bar
        primaryStage.setMaximized(true);
        mnbProyecto.getMenus().addAll(menCompetencia1,menCompetencia2,mensalir);
        escena = new Scene(brpPrincipal, 500, 475);
        escena.getStylesheets().add("sample/Estilos/estilos_principal.css");

        //Creamos la conexion a la dase de datos
        Conexion.crearConexion();

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Topicos");
        primaryStage.setScene(escena);
        primaryStage.show();

        /*new Hilo("Sonic").start();
        new Hilo("Rubensin").start();
        new Hilo("Hulk").start();
        new Hilo("El prisas").start();
        new Hilo("Limas").start();*/
/*
        RecursoCompartido objRec = new RecursoCompartido();
        new ProductorThread(objRec).start();
        new ConsumidorThread(objRec).start();
*/
        //new ServidorSocket().iniciarServidor();
        //new ClienteSocket().conectarCliente();
    }

    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new Buscaminas();
                break;
            case 2 :
                new Taquimecanografo();
                break;
            case 3:
                new CRUDProductos();
                break;
            case 4:
                new PistaAtletismo();
                break;
            case 20:
                System.exit(0);
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
