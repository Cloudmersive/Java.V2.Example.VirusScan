// Import classes:
import java.io.File;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.ApiKeyAuth;
import org.openapitools.client.api.ScanApi;
import org.openapitools.client.models.VirusScanAdvancedResult;

public class Example {
  public static void main(String[] args) {
    // --- replace with your API key and input path ---
    String apiKey = System.getenv().getOrDefault("CLOUDMERSIVE_API_KEY", "YOUR API KEY");
    String pathToFile = (args.length > 0) ? args[0] : "C:/path/to/file"; // or /path/to/file on Linux/macOS

    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.cloudmersive.com");

    // Configure API key authorization: Apikey
    ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
    Apikey.setApiKey(apiKey);
    // Apikey.setApiKeyPrefix("Token"); // uncomment if you need a prefix

    ScanApi apiInstance = new ScanApi(defaultClient);

    File inputFile = new File(pathToFile); // Input file to scan
    String fileName = inputFile.getName(); // Optional override of original file name

    // NOTE: For safer defaults, you typically want these set to FALSE.
    // The sample below mirrors your example signatures (Boolean objects).
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

    try {
      VirusScanAdvancedResult result = apiInstance.scanFileAdvanced(
          inputFile,
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
