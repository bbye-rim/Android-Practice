package com.example.navermap

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.naver.maps.geometry.LatLng
import java.lang.reflect.Type

// NaverMap sdk내 객체인 LatLng 을 Deserialize
// (LatLng에 toString 재정의 되어있어 바로 직렬화가 안됨)
class PathDeserializer: JsonDeserializer<Path> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Path {
        val jsonObject = json?.asJsonObject
        val jsonArray = jsonObject?.getAsJsonArray("coordinates")?.asJsonArray ?: JsonArray()
        val latlngList = ArrayList<LatLng>()
        for(i in 0 until jsonArray.size()) {
            val lat = jsonArray[i].asJsonObject.get("lat").asDouble
            val lng = jsonArray[i].asJsonObject.get("lng").asDouble
            val latlng = LatLng(lat, lng)
            latlngList.add(latlng)
        }
        return Path(latlngList)
    }
}