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
//
//        if (x - 1 >= 0) {
//            source.points[x - 1][y].takeUnless { it.isHole() }?.let { connections.add(it) }
//        }
//        if (x + 1 < source.width) {
//            source.points[x + 1][y].takeUnless { it.isHole() }?.let { connections.add(it) }
//        }
//        if (y - 1 >= 0) {
//            source.points[x][y - 1].takeUnless { it.isHole() }?.let { connections.add(it) }
//        }
//        if (y + 1 < source.height) {
//            source.points[x][y + 1].takeUnless { it.isHole() }?.let { connections.add(it) }
//        }
        return connections
    }
}