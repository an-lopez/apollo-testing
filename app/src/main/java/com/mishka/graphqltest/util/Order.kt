package com.mishka.graphqltest.util

/**
 * Class to define descending and ascending order types.
 */
sealed class Order {
    object AtoZ: Order()
    object ZtoA: Order()
}