import com.sparkbeyond.runtime.util.datastructures.graph.grapht.JGraphTWrapper
import collection.JavaConversions._
import com.sparkbeyond.runtime.graph.MapWrapper
import com.sparkbeyond.runtime.feature.types.USState


object Zerocomplexity_graphFunctions{
	def zerocomplexity_adjacentNodes(graph: JGraphTWrapper[Int], vertex: Int) = 
		graph.graph.outgoingEdgesOf(vertex).map(graph.graph.getEdgeTarget).toSeq

	def zerocomplexity_lookupMany(seq: Seq[Int], lookup: MapWrapper[Int, USState]) = 
		seq.flatMap(lookup.get)
}
