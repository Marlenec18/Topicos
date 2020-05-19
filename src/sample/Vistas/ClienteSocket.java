package sample.Vistas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteSocket {
    private InetAddress direccionServidor;
    private Socket cliente;
    private byte[] ip = new byte[]{(byte)187,(byte)134,(byte)16,(byte)127};

    public void conectarCliente(){
        try{
            direccionServidor = InetAddress.getByAddress(ip);
            cliente = new Socket(direccionServidor,5000);

            //Leemos el flujo de entrada hacia el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            System.out.println(entrada.readLine());

            //Escribimos el flujo de
        }catch (Exception e ){

        }
    }

}
