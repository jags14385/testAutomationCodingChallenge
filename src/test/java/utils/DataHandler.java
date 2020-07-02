package utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class DataHandler {

  public JSONArray getDataAsArray(HttpResponse<JsonNode> response) {
    JSONObject jsonObject = response.getBody().getObject();
    return jsonObject.getJSONArray("data");
  }

  public Object getDataAtPath(HttpResponse<JsonNode> response, String path) {
    DocumentContext jsonContext = JsonPath.parse(response.getBody().toString());
    return jsonContext.read(path);
  }
}
