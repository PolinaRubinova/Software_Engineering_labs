package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

const val COULOMB_KILOGRAM = "c_kg"
const val ROENTGEN = "r"

fun convert_to_r(c_kg: Double): Double {
    return 3876 * c_kg
}

fun convert_to_c_kg(r: Double): Double {
    return 0.000258 * r
}

fun Application.configureRouting() {

    routing {
        get("/convert/c_kg/r") {
            val c_kg = (call.parameters[COULOMB_KILOGRAM])?.toDouble() ?:0.0
            call.respondText((convert_to_r(c_kg)).toString())
        }

        get("/convert/r/c_kg") {
            val r = (call.parameters[ROENTGEN])?.toDouble() ?:0.0
            call.respondText((convert_to_c_kg(r)).toString())
        }
    }
}
