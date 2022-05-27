package ActorMultiply

import AkkaMultiply.Listener.{CompletedWork, DoneMsg}
import AkkaMultiply.Worker.{ExecuteMultiply, WhatToMultiply}
import akka.actor.{Actor, ActorRef}

class Worker (mensaje: Int, listenerActor: ActorRef) extends Actor {

  var workerID = mensaje
  var a = Array[Int]()
  var b = Array[Array[Int]]()
  var resultado = Array[Int]()

  def receive = {
    case WhatToMultiply(matriz1, matriz2) =>
      a = matriz1
      b = matriz2
      resultado = Array.ofDim[Int](a.length)
    case ExecuteMultiply =>
      for (i <- 0 until a.length) {
        var temp = 0
        for (j <- 0 until b.length) {

          temp += a(j) * b(j)(i)
        }
        //println(temp)
        resultado(i) = temp
      }
      listenerActor ! DoneMsg(s"Worker #$workerID is done")
      listenerActor ! CompletedWork(mensaje, resultado)
  }
}
