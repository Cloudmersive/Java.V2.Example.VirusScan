// Import classes:
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.cloudmersive.virusscan.ApiClient;
import com.cloudmersive.virusscan.ApiException;
import com.cloudmersive.virusscan.Configuration;
import com.cloudmersive.virusscan.api.ScanApi;
import com.cloudmersive.virusscan.model.VirusScanAdvancedResult;

public class Example {
  public static void main(String[] args) throws Exception {
    // --- replace with your API key and input path ---
    String apiKey = System.getenv().getOrDefault("CLOUDMERSIVE_API_KEY", "YOUR API KEY");
    String pathToFile = (args.length > 0) ? args[0] : "C:/path/to/file"; // or /path/to/file on Linux/macOS

    ApiClient defaultClient = Configuration.getDefaultApiClient();

    // Send the Cloudmersive API key on every request via a request interceptor
    // (the openapi-generator "native" library does not generate ApiKeyAuth).
    final String apiKeyValue = apiKey;
    defaultClient.setRequestInterceptor(builder -> builder.header("Apikey", apiKeyValue));

    ScanApi apiInstance = new ScanApi(defaultClient);

    // Read the file fully into memory and wrap it in an InputStream.
    Path inputPath = Paths.get(pathToFile);
    byte[] fileBytes = Files.readAllBytes(inputPath);
    String fileName = inputPath.getFileName().toString();

    // NOTE: For safer defaults, you typically want these set to FALSE.
    Boolean allowExecutables = false;
    Boolean allowInvalidFiles = false;
    Boolean allowScripts = false;
    Boolean allowPasswordProtectedFiles = false;
    Boolean allowMacros = false;
    Boolean allowXmlExternalEntities = false;
    Boolean allowInsecureDeserialization = false;
    Boolean allowHtml = false;
    Boolean allowUnsafeArchives = false;
    Boolean allowOleEmbeddedObject = false;
    Boolean allowUnwantedAction = false;

    // Optional advanced flags
    String options = ""; // e.g. "permitAuthenticodeSignedExecutables"
    String restrictFileTypes = ""; // e.g. ".pdf,.docx,.png"

    try (InputStream inputStream = new ByteArrayInputStream(fileBytes)) {
      VirusScanAdvancedResult result = apiInstance.scanFileAdvanced(
          inputStream,
          fileName,
          allowExecutables,
          allowInvalidFiles,
          allowScripts,
          allowPasswordProtectedFiles,
          allowMacros,
          allowXmlExternalEntities,
          allowInsecureDeserialization,
          allowHtml,
          allowUnsafeArchives,
          allowOleEmbeddedObject,
          allowUnwantedAction,
          options,
          restrictFileTypes
      );
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScanApi#scanFileAdvanced");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
