package com.project.voluntas

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.voluntas.databinding.ActivityMainBinding
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.net.URL
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var RecyclerView:RecyclerView
    val data1 = ArrayList<Data>()
    val adapter = MainListAdapter(data1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(android.os.Build.VERSION.SDK_INT >9){
            val policy : StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        RecyclerView = findViewById<RecyclerView>(R.id.RecyclerView)

        var sido = intent.getStringExtra("SidoKey")
        var current_date : Int = Integer.parseInt(SimpleDateFormat("yyyyMMdd").format(Date()))

        val cal = Calendar.getInstance()
        cal.time = Date()
        val df : DateFormat = SimpleDateFormat("yyyyMMdd")
        cal.add(Calendar.DATE, -20)

        Log.d("date","종료날짜 : "+ current_date)
        Log.d("date","시작날짜: ${df.format(cal.time)}")
        val key: String ="1O5TyVjRbo1%2FC5JVf9%2FNZIV2D6FSMXBUZe0MVRTwYQBFnk2GFESxQSZ1zLoJkddQWKRSjJ0y78xRxZt0Zo0S2g%3D%3D"
        val url: String="http://apis.data.go.kr/1383000/YouthActivInfoVolSrvc/getVolProgrmList?pageNo=1&numOfRows=100&sido="+sido+"&sdate=${df.format(cal.time)}&edate="+current_date+"&type=xml&serviceKey="+key

        val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)
        xml.documentElement.normalize()

        val list:NodeList = xml.getElementsByTagName("item")

        for(i in 0..list.length-1){

            var n: Node = list.item(i)
            if(n.nodeType==Node.ELEMENT_NODE){
                val elem = n as Element
                val map = mutableMapOf<String, String>()
                for(j in 0..elem.attributes.length-1){
                    map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
                }


                initData("${elem.getElementsByTagName("organNm").item(0).textContent}","${elem.getElementsByTagName("pgmNm").item(0).textContent}","${elem.getElementsByTagName("sdate").item(0).textContent}","${elem.getElementsByTagName("key1").item(0).textContent}")

            }

        }

        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun initData(title: String, work: String, date: String, key: String) {
        adapter.additem(title, work, date, key)
    }


}







