// Modified by SignalFx
package datadog.trace.instrumentation.http_url_connection;

import static io.opentracing.log.Fields.ERROR_OBJECT;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.bytebuddy.matcher.ElementMatchers.isMethod;
import static net.bytebuddy.matcher.ElementMatchers.isPublic;
import static net.bytebuddy.matcher.ElementMatchers.named;

import com.google.auto.service.AutoService;
import datadog.trace.agent.tooling.Instrumenter;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@AutoService(Instrumenter.class)
public class UrlInstrumentation extends Instrumenter.Default {

  public static final String COMPONENT = "UrlConnection";

  public UrlInstrumentation() {
    super("urlconnection", "httpurlconnection");
  }

  @Override
  public ElementMatcher<TypeDescription> typeMatcher() {
    return is(URL.class);
  }

  @Override
  public Map<ElementMatcher, String> transformers() {
    final Map<ElementMatcher, String> transformers = new HashMap<>();
    transformers.put(
        isMethod().and(isPublic()).and(named("openConnection")),
        ConnectionErrorAdvice.class.getName());
    return transformers;
  }

  public static class ConnectionErrorAdvice {

    @Advice.OnMethodExit(onThrowable = Throwable.class, suppress = Throwable.class)
    public static void errorSpan(
        @Advice.This final URL url, @Advice.Thrown final Throwable throwable) {
      if (throwable != null) {
        final boolean isTraceRequest = Thread.currentThread().getName().equals("dd-agent-writer");
        if (isTraceRequest) {
          return;
        }

        String protocol = url.getProtocol();
        protocol = protocol != null ? protocol : "url";

        final Scope scope =
            GlobalTracer.get()
                .buildSpan(protocol + ".request")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_CLIENT)
                .withTag(Tags.COMPONENT.getKey(), COMPONENT)
                .startActive(true);

        final Span span = scope.span();
        Tags.HTTP_URL.set(span, url.toString());
        Tags.PEER_PORT.set(span, url.getPort() == -1 ? 80 : url.getPort());
        Tags.PEER_HOSTNAME.set(span, url.getHost());

        Tags.ERROR.set(span, true);
        span.log(Collections.singletonMap(ERROR_OBJECT, throwable));
        scope.close();
      }
    }
  }
}
