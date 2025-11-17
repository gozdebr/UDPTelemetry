import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class Receiver {
    public static void main(String[] args) {
        final int LISTEN_PORT = 9876;
        final int NUM_MESSAGES = 1000;

        try {
            DatagramSocket socket = new DatagramSocket(LISTEN_PORT);
            System.out.println("UDP Alıcı başlatıldı...");

            for (int i = 1; i <= NUM_MESSAGES; i++) {
                byte[] receiveData = new byte[250];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                socket.receive(receivePacket);

                long receiveTime = System.nanoTime();

                // İlk 8 byte'tan gönderim zamanını al
                ByteBuffer buffer = ByteBuffer.wrap(receivePacket.getData());
                long sendTime = buffer.getLong();

                long delayNs = receiveTime - sendTime;
                double delayMs = delayNs / 1_000_000.0;

                System.out.printf("[%d] Gecikme: %.3f ms (%d ns)%n", i, delayMs, delayNs);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
