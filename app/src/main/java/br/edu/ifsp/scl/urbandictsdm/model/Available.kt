package br.edu.ifsp.scl.urbandictsdm.model

data class Available (
    val currencies: Map<String, String>,
    val status: String
)