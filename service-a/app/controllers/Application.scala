package controllers

import com.google.inject.{Singleton, Inject}
import com.knoldus.servicediscovery.ConsulDiscovery
import play.api._
import play.api.mvc._
import services.PriceCordination
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class Application @Inject()(priceService: PriceCordination, consulDisco: ConsulDiscovery) extends Controller {

  def index = Action.async {
    consulDisco.registerService().map{ data =>
      Ok(s"the registration was = $data")
    }
  }

  def sendPricing = Action.async {
    priceService.compose.map{
      msg =>
        Ok(msg)
    }
  }

}
