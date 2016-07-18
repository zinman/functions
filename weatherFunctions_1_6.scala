import com.sparkbeyond.runtime.util.datastructures.LRUCache
import com.sparkbeyond.runtime.feature.types.{LatLong, LatLongDate, TimeSeries, KeyedTimeWindow}
import com.sparkbeyond.runtime.feature.functions.DateHelper
import com.sparkbeyond.runtime.externalsources.weather.Weather

object ExternalWeatherHistoryFunctions {
  	val lru = LRUCache[LatLongDate,Map[String,Double]](20000)

	def temperature(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "TEMP")
	def maxTemperature(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "MAXTEMP")
	def minTemperature(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "MINTEMP")
	def precipitation(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "PRCP")
	def dewPoint(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "DEWP")
	def seaLevelPressure(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "SLP")
	def stationPressure(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "STP")
	def visibility(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "VISIB")
	def windSpeed(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "WDSP")
	def maxWindSpeed(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "MXSPD")
	def gust(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "GUST")
	def snowDepth(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "SNDP")
	def fog(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "FOG_EXISTS")
	def rain(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "RAIN_EXISTS")
	def snow(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "SNOW_EXISTS")
	def hail(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "HAIL_EXISTS")
	def thunder(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "THUNDER_EXISTS")
	def tornado(timeWindow: KeyedTimeWindow[LatLong]) = NOAATimeSeries(timeWindow, "TORNADO_EXISTS")

	private def NOAATimeSeries(timeWindow: KeyedTimeWindow[LatLong], NOAAKey: String): TimeSeries[Double] = {
		import DateHelper._
		import Weather._
		val nDays = DateHelper.diffDays(doubleToDate(timeWindow.startDate), doubleToDate(timeWindow.endDate)).toDouble
		val interval = if (nDays <= 10) 1 else nDays / 5
		val result = TimeSeries.fromDatesAndValues( (0.0 until nDays by interval).map(offset => {
			val currDate = doubleToDate(timeWindow.startDate).plusDays(offset.toInt).toDate
			val w = lru.getOrElse(LatLongDate(timeWindow.key,currDate),contentForLatLongDate(timeWindow.key,currDate).toMap)
			(currDate, w.getOrElse(NOAAKey, Double.NaN))
		}))

		if (timeWindow.relativeTime) result.copy(x = result.x.map(_ - result.x.last))
		else result
	}

}
