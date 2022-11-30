package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

const val COULOMB_KILOGRAM = "c_kg"
const val ROENTGEN = "r"
const val SIEVERT = "sv"
const val r_in_sv = 114.025

fun convert_to_r(c_kg: Double): Double {
    return 3876 * c_kg
}

fun convert_to_c_kg(r: Double): Double {
    return 0.000258 * r
}

fun Application.configureRouting() {

    routing {
        get("/convert/c_kg/r") {
            try {
                val c_kg = (call.parameters[COULOMB_KILOGRAM])?.toDouble() ?: call.respondText("Error! Empty parameter")
                call.respondText(c_kg.toString() + " c/kg = " + (convert_to_r(c_kg as Double)).toString() + " r")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }

        get("/convert/r/c_kg") {
            try {
                val r = (call.parameters[ROENTGEN])?.toDouble() ?: call.respondText("Error! Empty parameter")
                call.respondText(r.toString() + " r = " + (convert_to_c_kg(r as Double)).toString() + " c/kg")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }

        get("/convert/c_kg/sv") {
            try {
                val c_kg = (call.parameters[COULOMB_KILOGRAM])?.toDouble() ?: call.respondText("Error! Empty parameter")
                call.respondText(c_kg.toString() + " c_kg = " + (convert_to_r(c_kg as Double) / r_in_sv).toString() + " sv")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }

        get("/convert/sv/r") {
            try {
                val sv = (call.parameters[SIEVERT])?.toDouble() ?: call.respondText("Error! Empty parameter")
                call.respondText(sv.toString() + " sv = " + (sv as Double * r_in_sv).toString() + " r")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }
    }
}