package br.edu.ifsp.scl.financialmanager.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat


class MoneyMask {
    companion object {
        fun unmask(ediTxt: EditText): String {
            return ediTxt.text.toString().replace(",",".")
        }

        fun converted(s: String): String {
            val cleanString = s.replace("[R$,.]".toRegex(), "")

            val parsed = java.lang.Double.parseDouble(cleanString)
            val formatted = NumberFormat.getCurrencyInstance().format(parsed / 100)

            return formatted.replace("[R$]".toRegex(), "")
        }

        fun monetario(ediTxt: EditText): TextWatcher {
            return object : TextWatcher {
                private var current = ""

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun afterTextChanged(s: Editable) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.toString() != current) {
                        ediTxt.removeTextChangedListener(this)

                        current = converted(s.toString())
                        ediTxt.setText(current)
                        ediTxt.setSelection(current.length)

                        ediTxt.addTextChangedListener(this)
                    }
                }

            }
        }

    }
}









