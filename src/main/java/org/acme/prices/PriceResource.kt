package org.acme.prices

import org.eclipse.microprofile.reactive.messaging.Channel
import org.jboss.resteasy.annotations.SseElementType
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


/**
 * A simple resource retrieving the in-memory "my-data-stream" and sending the items as server-sent events.
 */
@Path("/prices")
class PriceResource {
    @Inject
    @Channel("my-data-stream")
    lateinit var prices: Publisher<Double>
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    fun stream(): Publisher<Double> {
        return prices
    }
}