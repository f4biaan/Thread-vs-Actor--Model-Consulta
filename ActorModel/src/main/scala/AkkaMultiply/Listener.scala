package AkkaMultiply

import ActorMultiply.Listener
import akka.actor.Props

object Listener {
  def props(mensaje: Int): Props = Props(new Listener(mensaje))
  final case class DoneMsg(mensaje: String)
  final case class CompletedWork(fila: Int, work: Array[Int])
}
