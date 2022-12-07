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
                call.parameters[COULOMB_KILOGRAM]?.toDouble()?.let {
                    call.respondText(it.toString() + " c/kg = " + (convert_to_r(it)).toString() + " r")
                } ?: call.respondText("Error! Empty parameter")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }

        get("/convert/r/c_kg") {
            try {
                call.parameters[ROENTGEN]?.toDouble()?.let {
                    call.respondText(it.toString() + " r = " + (convert_to_c_kg(it)).toString() + " c/kg")
                } ?: call.respondText("Error! Empty parameter")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }

        get("/convert/c_kg/sv") {
            try {
                call.parameters[COULOMB_KILOGRAM]?.toDouble()?.let {
                    call.respondText(it.toString() + " c_kg = " + (convert_to_r(it) / r_in_sv).toString() + " sv")
                }?: call.respondText("Error! Empty parameter")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }

        get("/convert/sv/r") {
            try {
                call.parameters[SIEVERT]?.toDouble()?.let {
                    call.respondText(it.toString() + " sv = " + (it * r_in_sv).toString() + " r")
                }?: call.respondText("Error! Empty parameter")
            } catch (e: NumberFormatException) {
                call.respondText("Error! Incorrect parameter")
            }
        }
    }
}