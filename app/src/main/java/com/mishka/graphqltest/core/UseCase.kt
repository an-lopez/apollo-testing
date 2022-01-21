package com.mishka.graphqltest.core

interface UseCase<T> {

    fun invoke(): T
}