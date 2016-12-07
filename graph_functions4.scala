import com.sparkbeyond.runtime.util.datastructures.graph.grapht.JGraphTWrapper
import collection.JavaConversions._
import com.sparkbeyond.runtime.graph.MapWrapper
import com.sparkbeyond.runtime.feature.types.USState


object Zerocomplexity_graphFunctions4{
	def zerocomplexity_adjacentNodes(graph: JGraphTWrapper[Int], vertex: Int) = 
		graph.graph.outgoingEdgesOf(vertex).map(graph.graph.getEdgeTarget).toSeq

	def zerocomplexity_lookupManyInt(seq: Seq[Int], lookup: MapWrapper[Int, Int]) = 
		seq.flatMap(lookup.get)
		
	def zerocomplexity_lookupManyDouble(seq: Seq[Int], lookup: MapWrapper[Int, Double]) = 
		seq.flatMap(lookup.get)
	
	def zerocomplexity_lookupManyString(seq: Seq[Int], lookup: MapWrapper[Int, String]) = 
		seq.flatMap(lookup.get)
}
