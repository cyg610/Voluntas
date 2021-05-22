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
import javax.xml.parsers.DocumentBuilderFactory


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
        val key: String ="1O5TyVjRbo1%2FC5JVf9%2FNZIV2D6FSMXBUZe0MVRTwYQBFnk2GFESxQSZ1zLoJkddQWKRSjJ0y78xRxZt0Zo0S2g%3D%3D"
        val url: String="http://apis.data.go.kr/1383000/YouthActivInfoVolSrvc/getVolProgrmList?pageNo=1&numOfRows=10&sido="+sido+"&sdate=20210105&edate=20210501&type=xml&serviceKey="+key

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


                initData("${elem.getElementsByTagName("organNm").item(0).textContent}","${elem.getElementsByTagName("pgmNm").item(0).textContent}","${elem.getElementsByTagName("sdate").item(0).textContent}",1)

            }

        }

        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun initData(title: String, work: String, date: String, key: Int) {
        adapter.additem(title, work, date, key)
    }


}







