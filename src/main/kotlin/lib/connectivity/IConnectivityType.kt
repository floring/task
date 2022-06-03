package lib

interface IConnectivityType {
    fun getConnectionsFor(point: Point, source: Image): Set<Point>
}