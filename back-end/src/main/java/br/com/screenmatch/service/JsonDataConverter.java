package br.com.screenmatch.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataConverter implements DataConverter{
  // Classe para converter dados em formato JSON para objetos Java
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T getDataFromJson(String json, Class<T> type) {
    try {
      return mapper.readValue(json, type);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
