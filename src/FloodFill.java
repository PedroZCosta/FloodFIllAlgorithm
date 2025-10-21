import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FloodFill {

    //A imagem que vai ser modificada, o boolean marca quais pixels já foram processados, LArgura e altura da imagem
    private BufferedImage image;
    private boolean[][] visited;
    private int width, height;

    public FloodFill(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.visited = new boolean[width][height];
    }

    public void fill(int startX, int startY, int newColor, FillStructure structure) throws Exception {

        //Se o ponto inicial estiver fora da imagem ele sai do metodo
        if (startX < 0 || startY < 0 || startX >= width || startY >= height) return;
        int targetColor = image.getRGB(startX, startY); // define a cor a ser pintada da imagem!
        if (targetColor == newColor) return;

        //cria a pasta frames
        Files.createDirectories(Paths.get("frames"));

        //adiciona o ponto inical a pilha ou fila
        structure.add(new int[]{startX, startY});
        int steps = 0;

        //Define limite de frames e pixels
        int maxFrames = 50;
        int saveEvery = Math.max(1, width * height / maxFrames);

        while (!structure.isEmpty()) {
            int[] p = structure.remove();
            if (p == null) continue; // evita o NullPointerException
            int x = p[0], y = p[1];

            //ignora pixels fora da imagem
            if (x < 0 || y < 0 || x >= width || y >= height) continue; // passa para o proximo da estrutura
            if (visited[x][y]) continue;

            int currentColor = image.getRGB(x, y);
            if (currentColor != targetColor) continue;

            // sempre pinta o pixel
            image.setRGB(x, y, newColor);
            visited[x][y] = true;
            steps++;

            // só salva frames intermediários
            if (steps % saveEvery == 0) {
                String fname = String.format("frames/frame_%06d.png", steps);
                ImageIO.write(image, "png", new File(fname));
            }

            // Adiciona os pixels vizinhos a estrutura
            structure.add(new int[]{x+1, y});
            structure.add(new int[]{x-1, y});
            structure.add(new int[]{x, y+1});
            structure.add(new int[]{x, y-1});
        }

        // salva o último frame da pasta frames
        String finalFrame = "frames/frame_final.png";
        ImageIO.write(image, "png", new File(finalFrame));
    }
}
