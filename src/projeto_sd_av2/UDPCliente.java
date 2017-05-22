package projeto_sd_av2;
/**
 *
 * @author Diego Harrison
 */

//import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

public class UDPCliente {
    
    public static void main(String[] argv) throws SocketException, UnknownHostException, IOException{
 
        try ( //Esse aqui vai criar o stream do teclado
        //BufferedReader cadeiaUsuario = new BufferedReader(new inputStreamReader(System.in));
        //Declarando o socket cliente
                DatagramSocket clienteSocket = new DatagramSocket()) {
            BufferedReader cadeiaUsuario =  new BufferedReader(new InputStreamReader(System.in) {
                @Override
                public int read(char[] cbuf, int off, int len) throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
                @Override
                public void close() throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });     //Obtendo endereço ip com dns
            InetAddress enderecoIP = InetAddress.getByName("localhost");
            byte[] enviaDados = new byte[1024];
            byte[] recebeDados = new byte[1024];
            //Lê a linha do teclado
            String sentenca = cadeiaUsuario.readLine();
            enviaDados = sentenca.getBytes();
            //Agora ele cria pacote com o dado, o endereco do servidor e sua porta
            DatagramPacket enviaPacote = new DatagramPacket(enviaDados, enviaDados.length, enderecoIP, 9876);
            //envia o pacote
            clienteSocket.send(enviaPacote);
            //Agora ele declara o pacote a ser recebido
            
            DatagramPacket recebePacote =  new DatagramPacket(recebeDados, recebeDados.length);
            //Recebendo o pacote do servidor
            
            clienteSocket.receive(recebePacote);
            //separa somente o dado recebido
            
            String sentencaModificada = new String(recebePacote.getData());
            //Monstrando
            System.out.println("Pacote UDP enviado com sucesso: " + sentencaModificada);
            //Fechando a conexão com o cliente
        }
        
    }
    
    
    
}
