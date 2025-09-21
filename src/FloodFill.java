import java.awt.image.BufferedImage;
import java.io.File;

public class FloodFill {
    private BufferedImage image;
    private boolean[][] visited;
    private int width, height;

    public FloodFill(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.visited = new boolean[width][height];
    }

    public void fillFrom(int startX, int startY, FillStructure structure, int saveStep) throws Exception {
        structure.add(new int[]{startX, startY});
        int step = 0;

        while (!structure.isEmpty()) {
            int[] actualPoint = structure.remove();

            if (pixelIsValid(actualPoint)) {
                visited[actualPoint[0]][actualPoint[1]] = true;
                fillPixel(actualPoint);
                addNeighbors(actualPoint, structure);
            }

            if (step % saveStep == 0) {
                saveFrame(step / saveStep);
            }

            step++;
        }

        saveFinalImage();
    }

    private boolean pixelIsValid(int[] point) {
        int x = point[0];
        int y = point[1];

        if (x < 0 || x >= width || y < 0 || y >= height) return false;
        if (visited[x][y]) return false;

        int rgb = image.getRGB(x, y);
        return rgb != 0xFF000000 && rgb != 0xFF800080;
    }

    private void addNeighbors(int[] point, FillStructure structure) {
        int x = point[0];
        int y = point[1];

        int[][] neighbors = {
                {x + 1, y}, {x - 1, y},
                {x, y + 1}, {x, y - 1}
        };

        for (int[] n : neighbors) {
            if (pixelIsValid(n)) structure.add(n);
        }
    }

    private void fillPixel(int[] point) {
        image.setRGB(point[0], point[1], 0xFF800080);
    }

    private void saveFrame(int step) throws Exception {
        File outputFile = new File("/Users/pedro/Documents/GitHub/FloodFillAlgorithm/src/frames/frame_" + step + ".png");
        javax.imageio.ImageIO.write(image, "png", outputFile);
    }

    private void saveFinalImage() throws Exception {
        File finalOutput = new File("/Users/pedro/Documents/GitHub/FloodFillAlgorithm/src/frames/frame_final.png");
        javax.imageio.ImageIO.write(image, "png", finalOutput);
        System.out.println("Imagem final salva!");
    }
}