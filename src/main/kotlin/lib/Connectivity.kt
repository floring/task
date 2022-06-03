package lib

interface IConnectivityType {

    fun getConnectionsFor(point: MyPoint, source: MyImage): Set<MyPoint>
}

class Type4Connected() : IConnectivityType {
    override fun getConnectionsFor(point: MyPoint, source: MyImage): Set<MyPoint> {
        val connectedPoints = hashSetOf<MyPoint>()

        val x = point.x
        val y = point.y

        if (x - 1 >= 0) {
            connectedPoints.add(source.points[x - 1][y])
        }
        if (x + 1 < source.width) {
            connectedPoints.add(source.points[x + 1][y])
        }
        if (y - 1 >= 0) {
            connectedPoints.add(source.points[x][y - 1])
        }
        if (y + 1 < source.height) {
            connectedPoints.add(source.points[x][y + 1])
        }
        return connectedPoints
    }

}

class Type8Connected() : IConnectivityType {
    override fun getConnectionsFor(point: MyPoint, source: MyImage): Set<MyPoint> {
        val connectedPoints = hashSetOf<MyPoint>()

        val x = point.x
        val y = point.y

        // right, left, top, bottom
        if (x - 1 >= 0) {
            connectedPoints.add(source.points[x - 1][y])
        }
        if (x + 1 < source.width) {
            connectedPoints.add(source.points[x + 1][y])
        }
        if (y - 1 >= 0) {
            connectedPoints.add(source.points[x][y - 1])
        }
        if (y + 1 < source.height) {
            connectedPoints.add(source.points[x][y + 1])
        }

        // diagonal
        if (x - 1 >= 0 && y - 1 >= 0) {
            connectedPoints.add(source.points[x - 1][y - 1])
        }
        if (x + 1 < source.width && y - 1 >= 0) {
            connectedPoints.add(source.points[x + 1][y - 1])
        }
        if (x - 1 >= 0 && y + 1 < source.height) {
            connectedPoints.add(source.points[x - 1][y + 1])
        }
        if (x + 1 < source.width && y + 1 < source.height) {
            connectedPoints.add(source.points[x + 1][y + 1])
        }

        return connectedPoints

    }

}