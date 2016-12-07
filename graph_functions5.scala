import com.sparkbeyond.runtime.util.datastructures.graph.grapht.JGraphTWrapper
import collection.JavaConversions._
import com.sparkbeyond.runtime.graph.MapWrapper
import com.sparkbeyond.runtime.feature.types.USState
import com.sparkbeyond.runtime.feature.types.Identifier
import scala.collection.GenTraversableOnce


object Zerocomplexity_graphFunctions_7{
	def zerocomplexity_outgoingNodes_7(graph: JGraphTWrapper[Identifier], vertex: Identifier) = 
		graph.graph.outgoingEdgesOf(vertex).map(graph.graph.getEdgeTarget).toSeq

	def zerocomplexity_incomingNodes_7(graph: JGraphTWrapper[Identifier], vertex: Identifier) = 
		graph.graph.incomingEdgesOf(vertex).map(graph.graph.getEdgeSource).toSeq

	
	def zerocomplexity_lookupManyInt_7(seq: Seq[Identifier], lookup: MapWrapper[Identifier, Int]) = 
		seq.flatMap(lookup.get)
		
	def zerocomplexity_lookupManyDouble_7(seq: Seq[Identifier], lookup: MapWrapper[Identifier, Double]) = 
		seq.flatMap(lookup.get)
	
	def zerocomplexity_lookupManyString_7(seq: Seq[Identifier], lookup: MapWrapper[Identifier, String]) = 
		seq.flatMap(lookup.get)
	
	def zerocomplexity_lookupManyState_7(seq: Seq[Identifier], lookup: MapWrapper[Identifier, USState]) = 
		seq.flatMap(lookup.get)
	
	def zerocomplexity_averageInt_7(i: GenTraversableOnce[Int]) = if (i.isEmpty) 0.0
	else {
		var size = 0.0
		val sum = (0l /: i)((sum, next) => {
			size += 1
			sum + next
		})
		sum / size
	}
	
	def zerocomplexity_average_7(i: GenTraversableOnce[Double]) = if (i.isEmpty) 0.0
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
