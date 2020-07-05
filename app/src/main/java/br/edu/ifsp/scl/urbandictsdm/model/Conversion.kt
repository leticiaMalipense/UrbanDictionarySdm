package br.edu.ifsp.scl.urbandictsdm.model

import com.google.gson.annotations.SerializedName

data class Conversion (
    @SerializedName("amount")
    val amount: String,

    @SerializedName("base_currency_code")
    val baseCurrencyCode: String,

    @SerializedName("base_currency_name")
    val baseCurrencyName: String,

    @SerializedName("rates")
    val rates: Map<String, Converted>,

    @SerializedName("status")
    val status: String,

    @SerializedName("updated_date")
    val updatedDate: String
)

data class Converted (
    @SerializedName("currency_name")
    val currencyName: String,

    @SerializedName("rate")
    val rate: String,

    @SerializedName("rate_for_amount")
    val rateForAmount: String
)
