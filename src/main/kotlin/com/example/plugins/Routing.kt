package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

const val COULOMB_KILOGRAM = "Coulomb/kilogram"

fun Application.configureRouting() {

    routing {
        get("/convert/c-kg") {
            val c_kg = call.parameters[COULOMB_KILOGRAM]
            call.respondText("Hello World!")
        }
    }
}
