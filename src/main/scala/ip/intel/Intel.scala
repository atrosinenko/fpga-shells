package ip.intel

import chisel3._
import chisel3.core.{Analog, BlackBox}
import freechips.rocketchip.jtag.Tristate

class IOBUF extends BlackBox {
  val io = IO(new Bundle {
    val datain = Input(Bool())
    val dataout = Output(Bool())
    val oe = Input(Bool())

    val dataio = Analog(1.W)
  })

  override def desiredName: String = "iobuf"
}

object IOBUF {
  def apply(a: Analog, t: Tristate): IOBUF = {
    val res = Module(new IOBUF)
    res.io.datain := t.data
    res.io.oe := t.driven

    a <> res.io.dataio

    res
  }
}