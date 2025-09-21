import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        //Caminho da imagem de entrada
        String inputPath = args[0];

        //Coordenada inicial
        int startX = Integer.parseInt(args[1]);
        int startY = Integer.parseInt(args[2]);

        //Indica a cor a ser alterada
        int newColor = (int)Long.parseLong(args[3].replace("0x",""), 16);
        String mode = args[4].toLowerCase();

        //Indica se deve salvar cada passo
        int saveEvery = 1;
        if (args.length >= 6) saveEvery = Integer.parseInt(args[5]);

        BufferedImage img = ImageIO.read(new File(inputPath));
        FloodFill ff = new FloodFill(img);

        //Indica se deve usar pilha ou fila
        FillStructure structure;
        if (mode.equals("stack")) structure = new StackStructure();
        else structure = new QueueStructure();

        //Executa o algoritmo
        ff.fill(startX, startY, newColor, structure);
        ImageIO.write(img, "png", new File("output_flood.png"));
        System.out.println("Imagem final salva em output_flood.png");
    }
}
