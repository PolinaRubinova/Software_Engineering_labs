import com.example.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


class ApplicationTest {

    @Test
    fun test_error1() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("/convert/c_kg/r")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Empty parameter", response.bodyAsText())

        response = client.get("/convert/r/c_kg")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Empty parameter", response.bodyAsText())

        response = client.get("/convert/c_kg/sv")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Empty parameter", response.bodyAsText())

        response = client.get("/convert/sv/r")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Empty parameter", response.bodyAsText())
    }


    @Test
    fun test_error2() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("/convert/c_kg/r?c_kg=abc")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Incorrect parameter", response.bodyAsText())

        response = client.get("/convert/r/c_kg?r=abc")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Incorrect parameter", response.bodyAsText())

        response = client.get("/convert/c_kg/sv?c_kg=abc")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Incorrect parameter", response.bodyAsText())

        response = client.get("/convert/sv/r?sv=abc")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Error! Incorrect parameter", response.bodyAsText())
    }

    @Test
    fun test_correct_answer() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("/convert/c_kg/r?c_kg=17.7")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("17.7 c/kg = 68605.2 r", response.bodyAsText())

        response = client.get("/convert/c_kg/r?c_kg=0")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("0.0 c/kg = 0.0 r", response.bodyAsText())

        response = client.get("/convert/r/c_kg?r=17.7")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("17.7 r = 0.0045666 c/kg", response.bodyAsText())

        response = client.get("/convert/c_kg/sv?c_kg=17.7")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("17.7 c_kg = 601.6680552510414 sv", response.bodyAsText())

        response = client.get("/convert/sv/r?sv=17.7")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("17.7 sv = 2018.2425 r", response.bodyAsText())
    }
}