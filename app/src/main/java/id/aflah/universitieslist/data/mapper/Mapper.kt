package id.aflah.universitieslist.data.mapper

abstract class Mapper<FROM : Any, TO : Any> {
    abstract fun fromResponseToEntity(from :FROM): TO
    fun fromResponsesToEntities(fromList: Collection<FROM>): Collection<TO> {
        val result: MutableCollection<TO> = ArrayList()
        for (from in fromList) {
            val item = fromResponseToEntity(from)
            result.add(item)
        }
        return result
    }
}