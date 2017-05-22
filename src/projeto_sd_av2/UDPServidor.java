package projeto_sd_av2;
/**
 *
 * @author Diego Harrison
 */
import java.net.*;  //Essa classe foi importada para socket, servidorsocket e clientsocker.

public class UDPServidor {
    public static void main(String[] argv[]) throws Exception{
        //Cria socket do servidor com a porta 9876
        DatagramSocket servidorSocket = new DatagramSocket(9876);        
        byte[] dadosRecebidos = new byte[1024];
        byte[] dadosEnviados = new byte[1024];
        
        while(true){
        //Declara o pacote a ser recebido        
        DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);        
        //Recebe o pacote do cliente
        servidorSocket.receive(pacoteRecebido);        
        //Pega os dados, o endereço IP e a porta do cliente
        //Para poder mandar a mensagem de volta
        String sentenca = new String(pacoteRecebido.getData());
            InetAddress enderecoIP = pacoteRecebido.getAddress();
            int porta = pacoteRecebido.getPort();
        //Transforma em maiúsculas
        String sentencaCapturada = sentenca.toUpperCase();
        dadosEnviados = sentencaCapturada.getBytes();
        
        //Monta o pacote com endereço IP e porta
        
        DatagramPacket pacoteEnviado =  new DatagramPacket(dadosEnviados,
        dadosEnviados.length, enderecoIP, porta);
        
        //Enviando aos clientes
        servidorSocket.send(pacoteEnviado);
        
        }
        
    }
    
}
