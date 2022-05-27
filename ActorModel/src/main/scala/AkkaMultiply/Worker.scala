package AkkaMultiply

import ActorMultiply.Worker
import akka.actor.{ActorRef, Props}

object Worker {
  def props(mensaje: Int, listenerActor: ActorRef)
  : Props = Props(new Worker(mensaje, listenerActor))
  final case class WhatToMultiply(matriz1: Array[Int], matriz2: Array[Array[Int]])
  case object ExecuteMultiply
}
