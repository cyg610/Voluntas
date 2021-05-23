package com.project.voluntas

import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory

class DetailActivity : AppCompatActivity(),OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var locationManager : LocationManager? = null
    private val REQUEST_CODE_LOCATION : Int = 2
    var currentLocation : String =""
    var latitude : Double?= null
    var longitude : Double?= null

    lateinit var detail_title : TextView
    lateinit var detail_placeNM : TextView
    lateinit var latitudeText :TextView
    lateinit var longitudeText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val geocoder = Geocoder(this)
        detail_title = findViewById<TextView>(R.id.Detail_Title)
        detail_placeNM  = findViewById<TextView>(R.id.Detail_placeNm)
        var detail_target : TextView = findViewById<TextView>(R.id.Detail_target)
        var detail_info1 : TextView = findViewById<TextView>(R.id.Detail_info1)
        var detail_info2 : TextView = findViewById<TextView>(R.id.Detail_info2)
        var detail_date : TextView = findViewById<TextView>(R.id.Detail_date)
        var detail_price : TextView = findViewById<TextView>(R.id.Detail_price)
        var detail_tel : TextView = findViewById<TextView>(R.id.Detail_tel)
        var detail_local : TextView = findViewById<TextView>(R.id.Detail_local)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.MapView) as SupportMapFragment


        latitudeText = findViewById<TextView>(R.id.latitude_text)
        longitudeText = findViewById<TextView>(R.id.longitude_text)

        var data_key = intent.getStringExtra("key")
        val key: String ="1O5TyVjRbo1%2FC5JVf9%2FNZIV2D6FSMXBUZe0MVRTwYQBFnk2GFESxQSZ1zLoJkddQWKRSjJ0y78xRxZt0Zo0S2g%3D%3D"
        val url: String="http://apis.data.go.kr/1383000/YouthActivInfoVolSrvc/getVolProgrmInfo?pageNo=1&numOfRows=10&type=xml&serviceKey="+key+"&key1="+data_key

        val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)
        xml.documentElement.normalize()

        val list: NodeList = xml.getElementsByTagName("item")

        for(i in 0..list.length-1){

            var n: Node = list.item(i)
            if(n.nodeType== Node.ELEMENT_NODE){
                val elem = n as Element
                val map = mutableMapOf<String, String>()

                detail_title.setText("${elem.getElementsByTagName("pgmNm").item(0).textContent}")
                detail_placeNM.setText("${elem.getElementsByTagName("place").item(0).textContent}")
                detail_date.setText("${elem.getElementsByTagName("sdate").item(0).textContent}")
                detail_info1.setText("${elem.getElementsByTagName("info1").item(0).textContent}")
                detail_info2.setText("${elem.getElementsByTagName("info2").item(0).textContent}")
                detail_local.setText("${elem.getElementsByTagName("addr").item(0).textContent}")
                detail_tel.setText("${elem.getElementsByTagName("tel").item(0).textContent}")
                detail_target.setText("${elem.getElementsByTagName("target").item(0).textContent}")
                detail_price.setText("${elem.getElementsByTagName("price").item(0).textContent}")

                val cor = geocoder.getFromLocationName("${elem.getElementsByTagName("addr").item(0).textContent}",1)
                latitudeText.setText("${cor[0].latitude}")
                longitudeText.setText("${cor[0].longitude}")

            }

            mapFragment.getMapAsync(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        latitude = latitudeText.text.toString().toDouble()
        longitude = longitudeText.text.toString().toDouble()
        val LATLNG = LatLng(latitude!!, longitude!!)
        Log.d("LATNG2","  "+latitude+"  "+longitude)

        val markerOptions = MarkerOptions()
            .position(LATLNG)
            .title(detail_placeNM.text.toString())
        mMap.addMarker(markerOptions)

        val cameraPosition = CameraPosition.Builder()
            .target(LATLNG)
            .zoom(15.0f)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap.moveCamera(cameraUpdate)

    }
}
