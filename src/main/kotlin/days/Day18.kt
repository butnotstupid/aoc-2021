package days

class Day18 : Day(18) {

    override fun partOne(): Any {
        return inputList.map { line ->
            parseNumber(line)
        }.reduce(FishNumber::plus).magnitude
    }

    override fun partTwo(): Any {
        return inputList.flatMap { one ->
            inputList.flatMap { another ->
                if (one != another) {
                    listOf(
                        (parseNumber(one) + parseNumber(another)).magnitude,
                        (parseNumber(another) + parseNumber(one)).magnitude
                    )
                } else emptyList()
            }
        }.maxOrNull()!!
    }

    private fun parseNumber(line: String, parent: FishNumber? = null): FishNumber {
        return line.toIntOrNull()?.let { FishNumber(it, parent) } ?: line.let {
            var balance = 0
            var part = 0
            val parts = listOf(StringBuilder(), StringBuilder())
            for (c in line) {
                when (c) {
                    '[' -> {
                        balance += 1
                        if (balance > 1) parts[part].append(c)
                    }
                    ']' -> {
                        balance -= 1
                        if (balance > 0) parts[part].append(c)
                    }
                    ',' -> {
                        if (balance == 1) part += 1
                        if (balance > 1) parts[part].append(c)
                    }
                    else -> parts[part].append(c)
                }
            }

            val (left, right) = parts.map { it.toString() }

            FishNumber(null, parent).apply {
                this.left = parseNumber(left, this)
                this.right = parseNumber(right, this)
            }
        }
    }

    data class FishNumber(
        var value: Int?,
        var parent: FishNumber?,
        var left: FishNumber? = null,
        var right: FishNumber? = null
    ) {
        val magnitude: Long
            get() =
                if (this.value != null) value!!.toLong()
                else this.left!!.magnitude * 3 + this.right!!.magnitude * 2

        private val firstLeft: FishNumber?
            get() {
                var cur: FishNumber? = this
                while (cur?.parent != null && cur.parent?.left == cur) cur = cur.parent!!
                cur = cur?.parent?.left
                while (cur?.right != null) cur = cur.right

                return cur
            }

        private val firstRight: FishNumber?
            get() {
                var cur: FishNumber? = this
                while (cur?.parent != null && cur.parent?.right == cur)
                    cur = cur.parent!!
                cur = cur?.parent?.right
                while (cur?.left != null) cur = cur.left

                return cur
            }

        operator fun plus(other: FishNumber): FishNumber {
            val result = FishNumber(null, null, this, other)
            this.parent = result
            other.parent = result
            return result.reduce()
        }

        private fun reduce(): FishNumber {
            val number = this
            var reduced: Boolean
            do {
                reduced = if (number.reduceDepth(0)) true else number.reduceValue()
            }
            while (reduced)
            return number
        }

        private fun reduceDepth(depth: Int): Boolean {
            return when {
                value != null -> false
                depth == 4 -> {
                    // explode
                    this.firstLeft?.let { it.value = it.value!! + this.left?.value!! }
                    this.firstRight?.let { it.value = it.value!! + this.right?.value!! }
                    if (this.parent?.left == this) {
                        this.parent!!.left = FishNumber(0, this.parent)
                        true
                    } else {
                        this.parent!!.right = FishNumber(0, this.parent)
                        true
                    }
                }
                else -> {
                    when {
                        this.left?.reduceDepth(depth + 1) ?: false -> true
                        else -> this.right?.reduceDepth(depth + 1) ?: false
                    }
                }
            }
        }

        private fun reduceValue(): Boolean {
            return when {
                value != null && value!! >= 10 -> {
                    // split
                    this.left = FishNumber(value!! / 2, this)
                    this.right = FishNumber((value!! + 1) / 2, this)
                    value = null
                    true
                }
                value != null && value!! < 10 -> false
                else -> {
                    when {
                        this.left?.reduceValue() ?: false -> true
                        else -> this.right?.reduceValue() ?: false
                    }
                }
            }
        }

        override fun toString(): String {
            return when {
                value != null -> value.toString()
                else -> "[$left,$right]"
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FishNumber

            if (value != other.value) return false
            if (left != other.left) return false
            if (right != other.right) return false
            if (parent !== other.parent) return false

            return true
        }

        override fun hashCode(): Int {
            var result = value ?: 0
            result = 31 * result + (parent?.hashCode() ?: 0)
            result = 31 * result + (left?.hashCode() ?: 0)
            result = 31 * result + (right?.hashCode() ?: 0)
            return result
        }
    }
}
