package com.project.voluntas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MainListAdapter(val context:Context, val VoluntasList:ArrayList<Data>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      val view: View = LayoutInflater.from(context).inflate(R.layout.listview_item, null)

        //view를 listview_item.xml 파일의 각 view와 연결
        val V_Title = view.findViewById<TextView>(R.id.ListView_Item_Title)
        val V_Work = view.findViewById<TextView>(R.id.ListView_Item_Work)
        val V_Date = view.findViewById<TextView>(R.id.ListView_Item_Date)
        val V_Condition = view.findViewById<TextView>(R.id.ListView_Item_Condition)

        //ArrayList<Data>의 변수 데이터를 TextView에 적용
        val Data = VoluntasList[position]
        V_Title.text = Data.title
        V_Work.text = Data.work
        V_Date.text = Data.date
        V_Condition.text = Data.condition

        return view
    }

    override fun getItem(position: Int): Any {
        return VoluntasList[position]
    }

    override fun getItemId(position: Int): Long {
      return 0
    }

    override fun getCount(): Int {
        return VoluntasList.size
    }


}