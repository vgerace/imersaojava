import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

  public static void main(String[] args) throws Exception {
    // Fazer uma conexão HTTP e buscar os top 250 filmes

    // String url = "https://imdb-api.com/API/Top250Movies/";
    String url =
      "https://imdb-api.com/API/MostPopularMovies/" +
      System.getProperty("apiKey");

    URI endereco = URI.create(url);
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(
      request,
      BodyHandlers.ofString()
    );
    String body = response.body();
    System.out.println(body);
    // Extrair os dados que interessam (título, poster, classificação)
    var parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(body);
    System.out.println(listaDeFilmes.size());
    System.out.println(listaDeFilmes.get(0));
    //  Exibir e manipular os dados
    var geradora = new GeradoraDeFigurinhas();
    
    for (Map<String, String> filme : listaDeFilmes) {
      String urlImagem = filme.get("image");
      String titulo = filme.get("title");
      InputStream inputStream = new URL(urlImagem).openStream();

      String nomeArquivo = titulo + ".png";
      
      geradora.cria(inputStream, nomeArquivo);

      System.out.println(
        "\u001b[43m\u001b[1m📽️  Título: 📽️\u001b[m \u001b[1m \u001b[m" +
        "\u001b[1m" +
        filme.get("title")
      );
      System.out.println(
        "\u001b[44m\u001b[1m🖼️  Imagem: \u001b[m \u001b[1m \u001b[m" +
        "\u001b[1m" +
        filme.get("image")
      );
      System.out.println(
        "\u001b[41m\u001b[1m🌟 Classificação: 🌟\u001b[m \u001b[1m \u001b[m" +
        "\u001b[1m" +
        filme.get("imDbRating")
      );
      System.out.println();
    }
  }
}
