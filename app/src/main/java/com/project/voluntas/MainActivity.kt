package com.project.voluntas

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.voluntas.databinding.ActivityMainBinding
import org.w3c.dom.Element
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

        RecyclerView = findViewById<RecyclerView>(R.id.RecyclerView)

        //네트워크 스레드


        var thread = NetworkThread()
        thread.start()

        data1.add(Data("sdfsdf","","",1))


        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)


    }

    inner class NetworkThread :Thread() {
        @RequiresApi(Build.VERSION_CODES.Q)
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
                     //   Log.d("APITEST","봉사기관: ${organNM}\n")

                        var Add_Data = Data("${organNM}","가나다라","",1)
                        data1.add(Add_Data)

                    }

                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


}




