package prac2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerSocket extends ServerSocket {

    private MySocket socket;

    public MyServerSocket(int port) throws IOException {
        //Creamos el socket y lo enlazamos al puerto
        super(port);
    }

    @Override
    public MySocket accept() throws IOException {
        //Espera a que haya una conexión, entonces crea y devulelve el socket 
        try {
             this.socket = new MySocket(super.accept());
           
        } catch (IOException e) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE,null,e);
        }        
        return socket;
    }
    
    @Override
    public void close(){
        try{
            //Cierra la conexión del socket
            super.close();
        }catch(IOException e){
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE,null,e);
        }
    }
}
