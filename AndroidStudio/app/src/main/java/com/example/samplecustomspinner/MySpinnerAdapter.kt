package com.example.samplecustomspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.samplecustomspinner.databinding.MySpinnerItemBinding

class MySpinnerAdapter(
    private val context: Context,
    private val spinnerItems: MutableList<MySpinnerItem>
    ) : BaseAdapter()
{

    class ViewHolder(view: View) {
        private val binding = MySpinnerItemBinding.bind(view)
        private val txvId = binding.txvId
        private val imvFace = binding.imvFace
        private val txvName = binding.txvName

        fun bindItem(mySpinnerItem: MySpinnerItem) {
            txvId.text = mySpinnerItem.id.toString()
            imvFace.setImageResource(mySpinnerItem.imageId)
            txvName.text = mySpinnerItem.name
        }
    }

    private val positionById = mutableMapOf<Int, Int>()
    init {
        var i = 0
        for (elm in spinnerItems) {
            positionById[elm.id] = i++
        }
    }

    override fun getCount(): Int {
        return spinnerItems.size
    }

    override fun getItem(position: Int): Any {
        return spinnerItems[position]
    }

    override fun getItemId(position: Int): Long {
        return spinnerItems[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mySpinnerItem = MySpinnerItem(
            spinnerItems[position].id,
            spinnerItems[position].imageId,
            spinnerItems[position].name
        )

        return if (convertView == null) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.my_spinner_item, parent, false)
            val viewHolder = ViewHolder(view)
            viewHolder.bindItem(mySpinnerItem)
            view.tag = viewHolder
            view
        } else {
            val viewHolder = convertView.tag as ViewHolder
            viewHolder.bindItem(mySpinnerItem)
            convertView
        }
    }

    fun getPositionById(id: Int): Int? {
        return positionById[id]
    }
}