package ActorMultiply

import AkkaMultiply.Listener.{CompletedWork, DoneMsg}
import akka.actor.Actor

import scala.concurrent.duration.DurationLong

class Listener(message: Int) extends Actor {

  val inicioTiempo: Long = System.currentTimeMillis
  var matrizResultado = Array.ofDim[Array[Int]](message)
  var numeroFilas = 0

  def receive = {
    case DoneMsg(message) => println(message + "\n")
    case CompletedWork(row, work) =>
      matrizResultado(row) = work
      numeroFilas += 1
      if (numeroFilas == matrizResultado.length) {
        println("All workers done. Calculation time: %s"
          .format((System.currentTimeMillis - inicioTiempo).millis))
        // se imprime el resultado del producto de matrices
        print("Matriz Total\n")
        for {
          i <- 0 until matrizResultado.length
          j <- 0 until matrizResultado.length
        } println(s"($i)($j) = ${matrizResultado(i)(j)}")
      }
  }

}
