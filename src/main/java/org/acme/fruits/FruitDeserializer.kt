package org.acme.fruits

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer


class FruitDeserializer : ObjectMapperDeserializer<Fruit>(Fruit::class.java)