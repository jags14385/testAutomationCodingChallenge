package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import client.AirVisualAPIClient;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DataHandler;

public class AirVisualAPITests {

  private AirVisualAPIClient client;
  private DataHandler handler;

  @BeforeEach
  public void setUp() {
    client = new AirVisualAPIClient();
    handler = new DataHandler();
  }

  @Test
  public void shouldReturnHttpStatusOK() {
    HttpResponse<JsonNode> response = client.getStates("Australia");
    assertEquals(200, response.getStatus());
    assertEquals("OK", response.getStatusText());
  }

  @Test
  public void shouldReturnHttpStatusForbidden() {
    client = new AirVisualAPIClient("Invalid-Api-key");
    HttpResponse<JsonNode> response = client.getStates("Australia");
    assertEquals(403, response.getStatus());
    assertEquals("Forbidden", response.getStatusText());
  }

  @Test
  public void ShouldReturnSixStatesForAustralia() {
    HttpResponse<JsonNode> response = client.getStates("Australia");
    assertEquals(handler.getDataAsArray(response).length(), 6);
    assertEquals(
        "[{\"state\":\"New South Wales\"},"
            + "{\"state\":\"Queensland\"},{\"state\":\"South Australia\"},{\"state\":\"Tasmania\"},"
            + "{\"state\":\"Victoria\"},{\"state\":\"Western Australia\"}]",
        handler.getDataAsArray(response).toString());
  }

  @Test
  public void ShouldReturnDataForTheNearestCity() {
    HttpResponse<JsonNode> response = client.getNearestCity();
    assertEquals("Carlton", handler.getDataAtPath(response, "$['data']['city']"));
    assertEquals(
        "Australia".toLowerCase(),
        handler.getDataAtPath(response, "$['data']['country']").toString().toLowerCase());
  }

  @Test
  public void ShouldReturnNotFoundForInvalidCoordinates() {
    HttpResponse<JsonNode> response = client.getSpecifiedCityData("-90", "-90");
    assertEquals(400, response.getStatus());
    assertEquals("Bad Request", response.getStatusText());
  }

  @Test
  public void ShouldReturnTempForValidCoordinates() {
    HttpResponse<JsonNode> response = client.getSpecifiedCityData("144.97", "-37.8073959");
    assertEquals(200, response.getStatus());
    Object temperature = handler.getDataAtPath(response, "$['data']['current']['weather']['tp']");
    assertEquals("9", temperature.toString());
  }
}
