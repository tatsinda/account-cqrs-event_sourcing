package com.demo.events


abstract class BaseEvents<T>(
        open val  id: T
)

data class CustomerCreatedEvent(
        override val id:String,
        val name:String,
        val email: String
):BaseEvents<String>(id)


data class CustomerUpdatedEvent(
        override val id:String,
        val name:String,
        val email: String
):BaseEvents<String>(id)

