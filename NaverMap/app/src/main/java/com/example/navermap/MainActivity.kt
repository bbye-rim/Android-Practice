package com.example.navermap

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PolylineOverlay

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    //네이버맵 객체를 전역변수로 선언
    private var nmap: NaverMap? = null
    private lateinit var mapOverlay: PolylineOverlay
    //private lateinit var path: PathOverlay
    private lateinit var startMarker: Marker
    private lateinit var endMarker: Marker

    //읽어올 파일 변수
    private lateinit var airportPath: Path
    private lateinit var officePath: Path
    private lateinit var sheepPath: Path


    private val pathTypeGson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(Path::class.java, PathDeserializer())
            .create()
    }


    /*
    파일 읽어오기

    String으로 파일 읽어오고 Path값으로 반환
    파일명을 파라미터로 받아 asset에서 파일을 읽어서 파싱을 하고 Path 타입으로 반환
    */
    private fun getPathFileFromAsset(fileName: String): Path {
        val assetManager = resources.assets
        val inputStream = assetManager.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return pathTypeGson.fromJson(jsonString, Path::class.java)
    }

    /*
    getPathFileFromAsset에 파라미터로 파일명을 전달.
    Path 타입의 값을 받는다.
    */
    private fun initPathFile() {
        airportPath = getPathFileFromAsset("제주공항.json")
        officePath = getPathFileFromAsset("제주지사.json")
        sheepPath = getPathFileFromAsset("화북포구.json")

        //Log.d("Parsing", "내용확인 :: " + airportPath.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //코드 초기화 순서 생각하기!!
        //코드 초기화 순서에 따라 변수 선언 방법 달라진다.
        initMapView()
        initListener()
        initPathFile()
    }

    //버튼에 대한 리스너 초기화
    fun initListener() {


        //버튼 초기화
        var btn1 = findViewById<Button>(R.id.btn_airport)
        var btn2 = findViewById<Button>(R.id.btn_office)
        var btn3 = findViewById<Button>(R.id.btn_sheep)

        btn1.setOnClickListener {
            //선 그리기
            drawPathOverlay(airportPath)

        }

        btn2.setOnClickListener {
            drawPathOverlay(officePath)
        }

        btn3.setOnClickListener {
            drawPathOverlay(sheepPath)
        }
    }

    //메모리에 쌓이는 오버레이 초기화 -> 메모리 효율적으로 사용하기 위함.
    private fun clearOverlay() {
        mapOverlay.map = null
        startMarker.map = null
        endMarker.map = null
    }

    //선을 그리는 함수
    fun drawPathOverlay(path: Path) {

        //오버레이 초기화
        clearOverlay()

        //경로선
        var pathList = path.coordinates
        if (pathList.size > 1) {  //최소한 2개의 점이 있다면

            //점선으로 경로선 그리기
            mapOverlay.setPattern(10,3)

            //맵 오버레이에 그려지는 좌표
            mapOverlay.coords = pathList
            mapOverlay.map = nmap

            //넘어온 좌표값을 가지고 위치 확인
            startMarker.position = pathList[0]  //배열의 처음. 출발값
            startMarker.icon = OverlayImage.fromResource(R.drawable.icon_yel)
            startMarker.map = nmap

            endMarker.position = pathList[pathList.size-1]  //배열의 마지막. 도착점. 0부터 시작이기 때문에 -1을 해줘야 한다. (오류 많이 나는 부분)
            endMarker.icon = OverlayImage.fromResource(R.drawable.icon_bc)
            endMarker.map = nmap

            //경로선 전체 확인 가능하도록 카메라 위치와 배율 변경
            nmap?.moveCamera(CameraUpdate.fitBounds(mapOverlay.bounds, dpToPx(this, 80f)))
        }



/*        var path = PathOverlay()
        path.coords = listOf(   //경로선으로 연결될 좌표점 리스트
            LatLng(37.57152, 126.97714),
            LatLng(37.56607, 126.98268),
            LatLng(37.56455, 126.97707),
            LatLng(37.55855, 126.97822)
        )

        path.progress = 0.5
        path.color = Color.RED
        path.passedColor = Color.YELLOW
        path.map = nmap


        ---


        mapOverlay.coords = listOf(
            LatLng(33.455705932667584, 126.561377085372124),
            LatLng(33.459883248917606, 126.56142075460035),
            LatLng(33.459883240637346, 126.56090135527154),
            LatLng(33.45895818275228, 126.5508466940866)
        )

        //오버레이 표시
        mapOverlay.map = nmap
        nmap?.moveCamera(CameraUpdate.fitBounds(mapOverlay.bounds))
*/

    }


    private fun dpToPx(context: Context, dp: Float): Int {
        val px = TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
            .toInt()
        return px
    }

    fun initMapView() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }

        mapFragment.getMapAsync(this)
        //초기화
        mapOverlay = PolylineOverlay()
        startMarker = Marker()
        endMarker = Marker()

    }

    //맵이 준비가 되면 실행
    override fun onMapReady(map: NaverMap) {

        nmap = map //초기화할 때 진행이 되기 때문에 맵 사용을 위해 nmap 변수를 사용할 수 있다. 그러기 위해 전역변수로 선언.

        //카메라 시점 이동
        map.moveCamera(
            CameraUpdate.scrollTo(
                LatLng(33.45571983046859, 126.56202635202727)  //제주대학교 좌표
                //위치는 좌표 뿐만 아니라 특정 단어로도 가능. 자세한 것은 가이드라인에서 확인.
            )
        )




    }
}