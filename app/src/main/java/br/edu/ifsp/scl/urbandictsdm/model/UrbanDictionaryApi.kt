package br.edu.ifsp.scl.urbandictsdm.model

object UrbanDictionaryApi {
    val URL_BASE = "https://mashape-community-urban-dictionary.p.rapidapi.com/define"
    val END_POINT = "?term=" // Não é bem um endpoint, mas...
    val X_RAPIDAPI_KEY_FIELD = "x-rapidapi-key"
    val X_RAPIDAPI_KEY_VALUE = "{api-key}"
    val X_RAPIDAPI_HOST_FIELD = "x-rapidapi-host"
    val X_RAPIDAPI_HOST_VALUE = "mashape-community-urban-dictionary.p.rapidapi.com"
}