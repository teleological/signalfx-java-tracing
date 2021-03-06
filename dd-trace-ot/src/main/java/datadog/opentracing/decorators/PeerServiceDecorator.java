// Modified by SignalFx
package datadog.opentracing.decorators;

import datadog.opentracing.DDSpanContext;
import io.opentracing.tag.Tags;

public class PeerServiceDecorator extends AbstractDecorator {
  public PeerServiceDecorator() {
    super();
    this.setMatchingTag(Tags.PEER_SERVICE.getKey());
  }

  @Override
  public boolean shouldSetTag(final DDSpanContext context, final String tag, final Object value) {
    // SFx doesn't support per-span service names
    // context.setServiceName(String.valueOf(value));
    return false;
  }
}
