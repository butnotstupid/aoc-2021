package days

class Day16 : Day(16) {

    private val toValueOne = { packet: Packet -> packet.version.toLong() + packet.subPackets.sumOf { it.value!! } }
    private val toOperatorOne = { _: Int -> Iterable<Long>::sum }

    override fun partOne(): Any {
        val reader = inputList.first().flatMap { toBinary(it) }.let { Reader(it) }
        val packet = reader.readPacket(toValueOne, toOperatorOne)
        return packet.value!!
    }

    private val toValueTwo = { packet: Packet -> packet.value!! }
    private val toOperatorTwo = { typeId: Int ->
        mapOf<Int, Iterable<Long>.() -> Long>(
            0 to Iterable<Long>::sum,
            1 to { this.reduce { acc, next -> acc * next } },
            2 to { this.minOrNull()!! },
            3 to { this.maxOrNull()!! },
            5 to { if (this.first() > this.last()) 1 else 0 },
            6 to { if (this.first() < this.last()) 1 else 0 },
            7 to { if (this.first() == this.last()) 1 else 0 }
        ).getValue(typeId)
    }

    override fun partTwo(): Any {
        val reader = inputList.first().flatMap { toBinary(it) }.let { Reader(it) }
        val packet = reader.readPacket(toValueTwo, toOperatorTwo)
        return packet.value!!
    }

    private fun toBinary(c: Char): List<Int> {
        val dec = Character.getNumericValue(c)
        return listOf(8, 4, 2, 1).map { if (dec.and(it) == 0) 0 else 1 }
    }

    class Reader(private val binary: List<Int>) {
        private var pos = 0

        fun readPacket(toValue: (Packet) -> Long, toOperator: (Int) -> Iterable<Long>.() -> Long): Packet {
            val ver = readVersion()
            val type = readTypeId()

            return when (type) {
                4 -> {
                    val packet = Packet(ver, type, readLiteralValue(), emptyList())
                    packet.copy(value = toValue(packet))
                }
                else -> {
                    val subPackets = readSubPackets(toValue, toOperator)
                    val reduce = toOperator(type)
                    val packet = Packet(ver, type, subPackets.map(toValue).reduce(), subPackets)
                    packet.copy(value = toValue(packet))
                }
            }
        }

        private fun readSubPackets(
            toValue: (Packet) -> Long,
            operators: (Int) -> Iterable<Long>.() -> Long
        ): List<Packet> {
            val subPackets = mutableListOf<Packet>()
            when (readBits(1)) {
                0 -> {
                    val totalLength = readBits(15)
                    val subPacketsEnd = pos + totalLength
                    while (pos < subPacketsEnd) {
                        subPackets.add(readPacket(toValue, operators))
                    }
                }
                1 -> {
                    val numOfPackets = readBits(11)
                    repeat(numOfPackets) {
                        subPackets.add(readPacket(toValue, operators))
                    }
                }
            }

            return subPackets
        }

        private fun readVersion(): Int {
            return readBits(3)
        }

        private fun readTypeId(): Int {
            return readBits(3)
        }

        private fun readLiteralValue(): Long {
            var value = 0L
            do {
                val (prefix, part) = readBits(1) to readBits(4)
                value = value * 16 + part
            } while (prefix != 0)

            return value
        }

        private fun readBits(n: Int): Int {
            if (pos + n > binary.size) {
                throw IllegalArgumentException("Can't read $n bits starting from $pos (only ${binary.size - pos} bits left)")
            }

            var sum = 0
            repeat(n) {
                sum = sum * 2 + binary[pos]
                pos += 1
            }

            return sum
        }
    }

    data class Packet(
        val version: Int,
        val type: Int,
        val value: Long? = null,
        val subPackets: List<Packet>
    )
}
