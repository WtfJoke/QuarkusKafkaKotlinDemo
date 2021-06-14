package org.acme.prices

import io.smallrye.mutiny.Multi
import org.eclipse.microprofile.reactive.messaging.Outgoing
import java.time.Duration
import java.util.*
import javax.enterprise.context.ApplicationScoped

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to a Kafka topic (prices). The Kafka configuration is specified in the application configuration.
 */
@ApplicationScoped
class PriceGenerator {
    private  val  random = Random()
    @Outgoing("generated-price")
    fun generate(): Multi<Int> {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(5))
            .onOverflow().drop()
            .map { random.nextInt(100) }
    }
}