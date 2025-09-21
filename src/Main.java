import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("src/images/pixil-frame-0.png"));

            int startX, startY;
            do {
                startX = (int) (Math.random() * image.getWidth());
                startY = (int) (Math.random() * image.getHeight());
            } while (image.getRGB(startX, startY) == 0xFF000000); // continua enquanto o pixel for preto

            FloodFill floodFill = new FloodFill(image);

            // Versão com fila
            QueueStructure queue = new QueueStructure();
            floodFill.fillFrom(startX, startY, queue, 100);

            // Versão com pilha
//             StackStructure stack = new StackStructure();
//             floodFill.fillFrom(startX, startY, stack, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}