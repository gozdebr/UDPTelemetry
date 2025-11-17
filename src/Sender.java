import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Random;

public class Sender {
    public static void main(String[] args) {
        final String DEST_IP = "127.0.0.1";
        final int DEST_PORT = 9876;
        final int NUM_MESSAGES = 1000;

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName(DEST_IP);
            Random random = new Random();

            for (int i = 1; i <= NUM_MESSAGES; i++) {
                byte[] sendData = new byte[250];

                //Gönderim zamanını al
                long sendTime = System.nanoTime();

                //Zamanı ilk 8 byte'a yaz (big-endian)
                ByteBuffer buffer = ByteBuffer.wrap(sendData);
                buffer.putLong(sendTime);

                //Kalan 242 byte'a rastgele veri ekle
                byte[] randomData = new byte[242];
                random.nextBytes(randomData);
                buffer.put(randomData);

                //UDP paketi gönder
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, DEST_PORT);
                socket.send(sendPacket);

                System.out.println("[" + i + "] Veri gönderildi @ " + sendTime + " ns");

                // Gecikme ekle (isteğe bağlı, alıcının yetişmesi için)
                Thread.sleep(5); // 5 ms
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
