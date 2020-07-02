import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class RandomLoadSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://amazon.com.au")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("RandomLoadSimulation")
    .exec(http("Request--1")
      .get("/"))
    .pause(5)

  setUp(
    scn.inject(rampUsers(1000) during (25 seconds))
    .protocols(httpProtocol)
  )
}