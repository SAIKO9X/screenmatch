package br.com.screenmatch.service;

public interface DataConverter {
  <T> T getDataFromJson(String json, Class<T> type);
}
