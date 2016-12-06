import com.sparkbeyond.runtime.util.datastructures.graph.grapht.JGraphTWrapper
import collection.JavaConversions._
import com.sparkbeyond.runtime.graph.MapWrapper
import com.sparkbeyond.runtime.feature.types.USState


object zerocomplexity_graphFunctions{

	def zerocomplexity_adjacentNodes(graph: JGraphTWrapper[Int], vertex: Int) = {
		//println("function_adjacentNodes_was_called")
		val targets = graph.graph.outgoingEdgesOf(vertex).map(graph.graph.getEdgeTarget)
		targets.toSeq
		//graph.graph.getEdge(vertex,vertex)
	}

	def zerocomplexity_lookupMany(seq: Seq[Int], lookup: MapWrapper[Int, USState]) = {
			//println("function_lookupMany_was_called")
			val properties = seq.flatMap(v=>lookup.get(v))
			properties
	}
}
