package com.mishka.graphqltest.core

import com.mishka.graphqltest.util.Order

interface UseCase<T> {

    fun invoke(order: Order): T
}