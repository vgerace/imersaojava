import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

  public void cria(InputStream inputStream, String nomeArquivo)
    throws Exception {
    // Leitura da imagem
    // BufferedImage imagemOriginal  = ImageIO.read(new File("/home/vgerace/Workspaces/Alura/imersaojava/alura-stickers/entrada/filme.jpg"));
    // InputStream inputStream = new FileInputStream("/home/vgerace/Workspaces/Alura/imersaojava/alura-stickers/entrada/filme.jpg");
    // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,12,128,176_AL_.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // Criar uma nova imagem em memória com transparência e tamanho novo

    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage(
      largura,
      novaAltura,
      BufferedImage.TRANSLUCENT
    );

    // Copiar a imagem original para nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // Configuração da fonte
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);

    // Escrever uma frase na nova imagem
    graphics.drawString("TOPZERA", 100, novaAltura - 100);

    // Escrever a imagem nova em um arquivo
    ImageIO.write(novaImagem, "png", new File("/home/vgerace/Workspaces/Alura/imersaojava/alura-stickers/saida", nomeArquivo));
  }
  // public static void main(String[] args) throws Exception {
  //     var geradora = new GeradoraDeFigurinhas();
  //     geradora.cria();
  // }
}
