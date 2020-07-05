package br.edu.ifsp.scl.urbandictsdm.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.edu.ifsp.scl.financialmanager.utils.MoneyMask
import br.edu.ifsp.scl.urbandictsdm.R
import br.edu.ifsp.scl.urbandictsdm.viewmodel.UrbanDictionaryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UrbanDictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtValue.addTextChangedListener(MoneyMask.monetario(edtValue))

        viewModel = UrbanDictionaryViewModel(this)
        var list = mutableListOf<String>()

        viewModel.getAvailable().observe(
            this@MainActivity,
            Observer {

                it.currencies.keys.forEach{
                    list.add(it)
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
                sp1.adapter = adapter
                sp2.adapter = adapter
            }
        )

        btnConverter.setOnClickListener {
            val currencies1 = sp1.selectedItem.toString()
            val currencies2 = sp2.selectedItem.toString()
            val value : String = edtValue.text.toString().replace(",","")

            viewModel.converter(currencies1, currencies2, value).observe(
                this@MainActivity,
                Observer {
                    //txtCurrencie.text = it.rates.converted.rate.toString()
                    val converted = it.rates.values.elementAt(0)

                    val formatter: NumberFormat = DecimalFormat("#,###.##")
                    val value: Double = converted.rateForAmount.toDouble()

                    txtValueConverted.text = formatter.format(value)
                    txtCurrencie.text = currencies2
                }
            )
        }

    }

}
