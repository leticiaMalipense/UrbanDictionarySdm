package br.edu.ifsp.scl.urbandictsdm.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.scl.urbandictsdm.R
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class MoneyService(val context: Context) {

    private val filaRequisicoesVolley = Volley.newRequestQueue(context)
    private val gson = Gson()

    fun getAvailable(): MutableLiveData<Available> {

        val url = StringBuilder(MoneyApi.URL_BASE)
            .append("${MoneyApi.LIST_AVAILABLE}")
            .toString()

        val respostaRequisicaoLd = MutableLiveData<Available>()
        val requisicao = buildRequest(url, respostaRequisicaoLd)

        filaRequisicoesVolley.add(requisicao)

        return respostaRequisicaoLd
    }

    private fun buildRequest(url: String, respostaRequisicaoLd: MutableLiveData<Available>): JsonObjectRequest {
        return object: JsonObjectRequest(Method.GET, url, null,
            { response: JSONObject? ->
                response?.let{
                    respostaRequisicaoLd.value = gson.fromJson(response.toString(), Available::class.java)
                }
            },
            { error: VolleyError? ->
                Log.e(context.getString(R.string.app_name), error?.message!!)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return buildHeaders()
            }
        }
    }

    fun converter(currencies1: String, currencies2: String, value: String): MutableLiveData<Conversion> {
        val url = StringBuilder(MoneyApi.URL_BASE)
            .append(java.lang.String.format("${MoneyApi.CONVERTER}", currencies1, currencies2, value))
            .toString()

        val respostaRequisicaoLd = MutableLiveData<Conversion>()
        val requisicao = buildRequestConversion(url, respostaRequisicaoLd)

        filaRequisicoesVolley.add(requisicao)

        return respostaRequisicaoLd
    }

    private fun buildRequestConversion(url: String, respostaRequisicaoLd: MutableLiveData<Conversion>): JsonObjectRequest {
        return object: JsonObjectRequest(Method.GET, url, null,
            { response: JSONObject? ->
                response?.let{
                    respostaRequisicaoLd.value = gson.fromJson(response.toString(), Conversion::class.java)
                }
            },
            { error: VolleyError? ->
                Log.e(context.getString(R.string.app_name), error?.message!!)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return buildHeaders()
            }
        }
    }

    private fun buildHeaders(): MutableMap<String, String> {
        return mutableMapOf(
            Pair(
                MoneyApi.X_RAPIDAPI_KEY_FIELD,
                MoneyApi.X_RAPIDAPI_KEY_VALUE
            ),
            Pair(
                MoneyApi.X_RAPIDAPI_HOST_FIELD,
                MoneyApi.X_RAPIDAPI_HOST_VALUE
            )
        )
    }


}