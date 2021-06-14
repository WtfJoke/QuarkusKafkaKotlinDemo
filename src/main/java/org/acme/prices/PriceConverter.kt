package org.acme.prices

import io.smallrye.reactive.messaging.annotations.Broadcast
import org.eclipse.microprofile.reactive.messaging.Acknowledgment
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import javax.enterprise.context.ApplicationScoped


/**
 * A bean consuming data from the "prices" Kafka topic and applying some conversion.
 * The result is pushed to the "my-data-stream" stream which is an in-memory stream.
 */
@ApplicationScoped
class PriceConverter {
    @Incoming("prices")
    @Outgoing("my-data-stream")
    @Broadcast
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    fun process(priceInUsd: Int): Double {
        return priceInUsd * CONVERSION_RATE
    }

    companion object {
        private const val CONVERSION_RATE = 0.88
    }
}