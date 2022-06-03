package lib

interface IConnectivityType {

    fun getConnectionsFor(point: MyPoint, source: MyImage): Set<MyPoint>
}

class Type4Connected : IConnectivityType {
    override fun getConnectionsFor(point: MyPoint, source: MyImage): Set<MyPoint> {
        val connections = hashSetOf<MyPoint>()

        val x = point.x
        val y = point.y

        if (x - 1 >= 0) {
            source.points[x - 1][y].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (x + 1 < source.width) {
            source.points[x + 1][y].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (y - 1 >= 0) {
            source.points[x][y - 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (y + 1 < source.height) {
            source.points[x][y + 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        return connections
    }
}

class Type8Connected : IConnectivityType {
    override fun getConnectionsFor(point: MyPoint, source: MyImage): Set<MyPoint> {
        val connections = hashSetOf<MyPoint>()

        val x = point.x
        val y = point.y

        // right, left, top, bottom
        if (x - 1 >= 0) {
            source.points[x - 1][y].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (x + 1 < source.width) {
            source.points[x + 1][y].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (y - 1 >= 0) {
            source.points[x][y - 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (y + 1 < source.height) {
            source.points[x][y + 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }

        // diagonal
        if (x - 1 >= 0 && y - 1 >= 0) {
            source.points[x - 1][y - 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (x + 1 < source.width && y - 1 >= 0) {
            source.points[x + 1][y - 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (x - 1 >= 0 && y + 1 < source.height) {
            source.points[x - 1][y + 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }
        if (x + 1 < source.width && y + 1 < source.height) {
            source.points[x + 1][y + 1].takeUnless { it.isHole() }?.let { connections.add(it) }
        }

        return connections
    }
}