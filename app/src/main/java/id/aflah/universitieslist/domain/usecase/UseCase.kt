package id.aflah.universitieslist.domain.usecase

abstract class UseCase<Response, in Params> {
    protected abstract fun build(params: Params?): Response
    open fun execute(params: Params? = null): Response {
        return build(params)
    }
}
