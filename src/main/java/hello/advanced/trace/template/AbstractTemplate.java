package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace logTrace) {
        this.trace = logTrace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;

        }catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 다시 던져야 함
        }
    }

    protected abstract T call();
}
