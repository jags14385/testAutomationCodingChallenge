package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Utils {
  private String fileName;

  public Utils() {
    fileName = "env.json";
  }

  public String getValue(String key) {
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      String path = classLoader.getResource(fileName).getPath();
      File file = new File(path);
      JsonNode jsonNode = new ObjectMapper().readTree(file);
      return jsonNode.get(key).asText();
    } catch (IOException e) {
      System.out.println("=== Issue with loading the env.json file ======");
      e.printStackTrace();
    }
    return null;
  }
}
