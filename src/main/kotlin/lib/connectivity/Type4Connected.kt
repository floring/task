package lib.connectivity

import lib.IConnectivityType
import lib.Image
import lib.Point
import kotlin.math.abs

class Type4Connected : IConnectivityType {
    override fun getConnectionsFor(point: Point, source: Image): Set<Point> {
        val connections = hashSetOf<Point>()

        val x = point.x
        val y = point.y

        for (i in -1..1) {
            for (j in -1..1) {
                // skip diagonal and hole neighbors
                if (abs(i) + abs(j) != 2 && !source.points[x + i][y + j].isHole()) {
                    connections.add(source.points[x + i][y + j])
                }
            }
        }
        return connections
    }
}