class Yatzy(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) {

    protected var dice: IntArray = IntArray(5)

    init {
        dice[0] = d1
        dice[1] = d2
        dice[2] = d3
        dice[3] = d4
        dice[4] = d5
    }
    //TODO convert sum of (1,2,3) from companion object to same level of sumOf(4,5,6)

    fun fours(): Int = dice.filter { it == 4 }.sum()

    fun fives(): Int = dice.filter { it == 5 }.sum()

    fun sixes(): Int = dice.filter { it == 6 }.sum()

    companion object {

        fun chance(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) = listOf(d1, d2, d3, d4, d5).sum()

        fun yatzy(vararg dice: Int): Int = if (dice.distinct().size == 1) 50 else 0

        fun ones(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int = listOf(d1, d2, d3, d4, d5).sumOf(1)

        fun twos(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int = listOf(d1, d2, d3, d4, d5).sumOf(2)

        fun threes(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) = listOf(d1, d2, d3, d4, d5).sumOf(3)

        private fun List<Int>.sumOf(num: Int) = filter { it == num }.sum()

        // TODO reduce duplication, try to create fun witch finds pair -> score -> call it from twoPairs

        fun scorePair(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val pairs =
                listOf(d1, d2, d3, d4, d5).toNumberWithOccurrences().keys

            return if (pairs.isEmpty()) 0 else pairs.maxOf { it } * 2

        }

        fun twoPair(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val numberWithOccurrences =
                listOf(d1, d2, d3, d4, d5).toNumberWithOccurrences().keys

            with(numberWithOccurrences) {
                return if (containsTwoPairs()) {
                    sumOf { it * 2 }
                } else {
                    0
                }
            }

        }

        private fun List<Int>.toNumberWithOccurrences(minOccurrence: Int = 2) = groupBy { it }
            .filter { (_, value) -> value.size >= minOccurrence }

        private fun Set<Int>.containsTwoPairs(pairs: Int = 2) = size == pairs

        fun threeOfAKind(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int =
            listOf(d1, d2, d3, d4, d5).sumNthOfKind(3)

        fun fourOfAKind(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int =
            listOf(d1, d2, d3, d4, d5).sumNthOfKind(4)

        private fun List<Int>.sumNthOfKind(num: Int) = groupBy { it }.values.firstOrNull { it.size >= num }?.take(num)
            ?.sum() ?: 0

        fun smallStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) =
            if (listOf(d1, d2, d3, d4, d5).isSmallStraight()) 15 else 0

        fun largeStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int) =
            if (listOf(d1, d2, d3, d4, d5).isLargeStraight()) 20 else 0

        private fun List<Int>.isSmallStraight() = straight() && any { it == 1 }

        private fun List<Int>.isLargeStraight() = straight() && any { it == 6 }

        private fun List<Int>.straight() = distinct().size == size

        fun fullHouse(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {

            val numberWithOccurrences =
                listOf(d1, d2, d3, d4, d5).toNumberWithOccurrences()

            return if (numberWithOccurrences.keys.size == 2) numberWithOccurrences.values.flatten().sum()
            else 0

        }
    }
}


