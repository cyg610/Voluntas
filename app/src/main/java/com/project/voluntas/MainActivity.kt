package com.project.voluntas

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.lang.Exception
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory


class MainActivity : AppCompatActivity() {

    var VoluntasList = arrayListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ListView = findViewById<ListView>(R.id.ListView)

        //어뎁터설정
        val VoluntasAdapter = MainListAdapter(this, VoluntasList)
        ListView.adapter = VoluntasAdapter

        //네트워크 스레드
        var thread = NetworkThread()
        thread.start()


    }

    inner class NetworkThread :Thread() {
        override fun run() {
            try {

                //URL DATA
                var sido = intent.getStringExtra("SidoKey")
                val key: String ="1O5TyVjRbo1%2FC5JVf9%2FNZIV2D6FSMXBUZe0MVRTwYQBFnk2GFESxQSZ1zLoJkddQWKRSjJ0y78xRxZt0Zo0S2g%3D%3D"
                val url: String="http://apis.data.go.kr/1383000/YouthActivInfoVolSrvc/getVolProgrmList?pageNo=1&numOfRows=10&sido="+sido+"&sdate=20210105&edate=20210501&type=xml&serviceKey="+key

                var data = URL(url)
                var conn = data.openConnection()
                var input = conn.getInputStream()
                var factory = DocumentBuilderFactory.newInstance()
                var builder = factory.newDocumentBuilder()
                var doc = builder.parse(input)
                var root = doc.documentElement
                var item_node_list = root.getElementsByTagName("item")
                for (i in 0 until item_node_list.length){
                    var item_element = item_node_list.item(i) as Element
                    var organNM_list = item_element.getElementsByTagName("organNm")

                    var organNM_node = organNM_list.item(0) as Element
                    var organNM = organNM_node.textContent

                    runOnUiThread{
                        Log.d("APITEST","봉사기관: ${organNM}\n")
                        VoluntasList = arrayListOf<Data>(
                           // Data("${organNM}"," "," "," ",1)
                        )
                    }

                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

}


