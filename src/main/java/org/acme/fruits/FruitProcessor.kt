package org.acme.fruits

import io.smallrye.reactive.messaging.annotations.Broadcast
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import javax.enterprise.context.ApplicationScoped


/**
 * A bean consuming data from the "fruit-in" Kafka topic and applying some price conversion.
 * The result is pushed to the "fruit-out" stream.
 */
@ApplicationScoped
class FruitProcessor {
    @Incoming("fruit-in")
    @Outgoing("fruit-out")
    @Broadcast
    fun process(fruit: Fruit): Fruit {
        val newPrice = (fruit.price * CONVERSION_RATE).toInt()
        return Fruit(fruit.name, newPrice)
    }

    companion object {
        private const val CONVERSION_RATE = 0.88
    }
}