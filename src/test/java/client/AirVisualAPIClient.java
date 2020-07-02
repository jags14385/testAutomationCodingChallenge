package client;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import utils.Utils;

public class AirVisualAPIClient {

  private final String url;
  private final String apiKey;
  private final Utils utilityObject;

  public AirVisualAPIClient() {
    utilityObject = new Utils();
    this.url = utilityObject.getValue("visualAirApiRootUrl");
    this.apiKey = utilityObject.getValue("visualAirApiKey");
  }

  public AirVisualAPIClient(String apiKey) {
    utilityObject = new Utils();
    this.url = utilityObject.getValue("visualAirApiRootUrl");
    this.apiKey = apiKey;
  }

  public HttpResponse<JsonNode> getStates(String country) {
    String getStatesUrl = this.url + "/states?country=";
    return Unirest.get(String.format("%s%s&key=%s", getStatesUrl, country, this.apiKey)).asJson();
  }

  public HttpResponse<JsonNode> getNearestCity() {
    String nearestCityUrl = this.url + "/nearest_city";
    return Unirest.get(String.format("%s?key=%s", nearestCityUrl, this.apiKey)).asJson();
  }

  public HttpResponse<JsonNode> getSpecifiedCityData(String longitude, String latitude) {
    String specifiedCityUrl =
        this.url + "/nearest_city?key=" + this.apiKey + "&lon=" + longitude + "&lat=" + latitude;
    return Unirest.get(specifiedCityUrl).asJson();
  }
}
