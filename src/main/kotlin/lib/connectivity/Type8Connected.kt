package lib.connectivity

import lib.IConnectivityType
import lib.Image
import lib.Point

class Type8Connected : IConnectivityType {
    override fun getConnectionsFor(point: Point, source: Image): Set<Point> {
        val connections = hashSetOf<Point>()

        val x = point.x
        val y = point.y

        for (i in -1..1) {
            for (j in -1..1) {
                source.points[x + i][y + j].takeUnless { it.isHole() }?.let { connections.add(it) }
            }
        }
        return connections
    }
}