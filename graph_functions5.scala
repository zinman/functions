import com.sparkbeyond.runtime.util.datastructures.graph.grapht.JGraphTWrapper
import collection.JavaConversions._
import com.sparkbeyond.runtime.graph.MapWrapper
import com.sparkbeyond.runtime.feature.types.USState
import com.sparkbeyond.runtime.feature.types.Identifier


object Zerocomplexity_graphFunctions5{
	def zerocomplexity_adjacentNodes(graph: JGraphTWrapper[Identifier], vertex: Identifier) = 
		graph.graph.outgoingEdgesOf(vertex).map(graph.graph.getEdgeTarget).toSeq

	def zerocomplexity_lookupManyInt(seq: Seq[Identifier], lookup: MapWrapper[Identifier, Int]) = 
		seq.flatMap(lookup.get)
		
	def zerocomplexity_lookupManyDouble(seq: Seq[Identifier], lookup: MapWrapper[Identifier, Double]) = 
		seq.flatMap(lookup.get)
	
	def zerocomplexity_lookupManyString(seq: Seq[Identifier], lookup: MapWrapper[Identifier, String]) = 
		seq.flatMap(lookup.get)
	
	def zerocomplexity_averageInt(i: GenTraversableOnce[Int]) = if (i.isEmpty) 0.0
	else {
		var size = 0.0
		val sum = (0l /: i)((sum, next) => {
			size += 1
			sum + next
		})
		sum / size
	}
	
	def zerocomplexity_average(i: GenTraversableOnce[Double]) = if (i.isEmpty) 0.0
	else {
		var size = 0
		var sum = 0.0
		val it = i.toIterator

		while (it.hasNext) {
			val next = it.next()
			size += 1
			sum += next
		}
		sum / size
	}
}
