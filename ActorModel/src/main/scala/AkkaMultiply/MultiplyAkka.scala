package AkkaMultiply

import AkkaMultiply.Worker.{ExecuteMultiply, WhatToMultiply}
import akka.actor.{ActorRef, ActorSystem}
import scala.util.Random
import scala.collection.mutable.Map

object MultiplyAkka extends App {

  def generarMatriz(dim: Int): Array[Array[Int]] = {
    val numRand = Random
    val matrix = Array.ofDim[Int](dim, dim)

    for (i <- 0 until dim) {
      for (j <- 0 until dim) {
        matrix(i)(j) = numRand.nextInt(10)
      }
    }
    matrix
  }

  def printMatrix(matriz: Array[Array[Int]]): Unit = {
        for {
          i <- 0 until matriz.length
          j <- 0 until matriz.length
        } println(s"($i)($j) = ${matriz(i)(j)}")
  }

  // set the matrix dimension
  val dimension = 2
  val matriz1 = generarMatriz(dimension)
  val matriz2 = generarMatriz(dimension)

  // allocate memory for resultant matrix
  val matriz3 = Array.ofDim[Int](dimension, dimension)

  // create the container to hold all the actors
  val system: ActorSystem = ActorSystem("MultiplyAkka")

  try {
    val numOfActors = matriz1.length
    var actorRefs = Map[Int, ActorRef]()

    // listener actor will receive messages from all worker actors
    val listener: ActorRef = system.actorOf(Listener.props(matriz1.length), "ListenerActor")

    // generate worker actors
    for (row <- 0 until numOfActors) {
      // map row key -> reference to specific worker
      actorRefs += (row -> system.actorOf(Worker.props(row, listener), s"Worker-$row"))

      // distribute workload and send a trigger message to start
      actorRefs(row) ! WhatToMultiply(matriz1(row), matriz2)
      actorRefs(row) ! ExecuteMultiply
    }
    print("Matxix 1\n")
    printMatrix(matriz1)
    print("Matxix 2\n")
    printMatrix(matriz2)
  } finally {
    system.terminate()
  }
}
