package com.example.samplecustomspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.samplecustomspinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Making sample data.
        val spinnerItems = mutableListOf<MySpinnerItem>()
        spinnerItems.add(MySpinnerItem(100, R.drawable.green1, "kazuko midori"))
        spinnerItems.add(MySpinnerItem(200, R.drawable.green2, "futae midori"))
        spinnerItems.add(MySpinnerItem(300, R.drawable.green3, "mituko midori"))

        // Setting spinner.
        val adapter = MySpinnerAdapter(this, spinnerItems)
        binding.spnMySpinner.adapter = adapter

        // Select spinner position.
        val position = adapter.getPositionById(200) ?:0
        binding.spnMySpinner.setSelection(position)

        // When spinner item selected.
        binding.spnMySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(spinner: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                if (spinner == null) return
                val selectedItem = spinner.selectedItem as MySpinnerItem
                var str = ""
                str += "position:$position, id:$id\n"
                str += "selected item id:" + spinner.selectedItemId.toString() + "\n"
                str += "selected item position:" + spinner.selectedItemPosition.toString() + "\n"
                str += "selected item("  + "\n"
                str += "    id:" + selectedItem.id  + "\n"
                str += "    imageId:" + selectedItem.imageId + "\n"
                str += "    name:" + selectedItem.name + "\n"
                str += ")\n"
                binding.txvTest.text = str
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}