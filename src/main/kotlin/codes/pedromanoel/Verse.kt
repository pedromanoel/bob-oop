package codes.pedromanoel

import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

class NullVerse(writer: Writer) : Verse(-1, writer) {
    override val bottleStringCount get() = throw NotImplementedError()
    override fun takeOneDown() = throw NotImplementedError()
    override fun sing() = Unit
}

class UltimateVerse(writer: Writer) : Verse(0, writer) {
    override val bottleStringCount get() = "No"
    override fun takeOneDown() = "There are no more to pass around "
    override fun nextVerseBottlesOfBeerOnTheWall() = bottlesOfBeerOnTheWall()
}

class PenultimateVerse(writer: Writer) : Verse(1, writer) {
    override val bottlesString: String get() = "bottle"
    override fun takeOneDown() = "Take it down and pass it around "
}

class PrecedingVerse(count: Int, writer: Writer) : Verse(count, writer) {
    override fun takeOneDown() = "Take one down and pass it around "
}

abstract class Verse(private val count: Int, writer: Writer) {
    protected open val bottleStringCount: String
        get() = "$count"
    protected open val bottlesString: String
        get() = "bottles"
    private val countBottles: String
        get() = "$bottleStringCount $bottlesString"
    private val writer = PrintWriter(writer)

    open fun sing() {
        singVerse()
        nextVerse().sing()
    }

    private fun singVerse() {
        writer.run {
            print(bottlesOfBeerOnTheWall())
            println(bottlesOfBeer())
            print(takeOneDown())
            println(nextVerseBottlesOfBeerOnTheWall())
            println()
        }
    }

    protected fun bottlesOfBeerOnTheWall() = "$countBottles of beer on the wall "

    private fun bottlesOfBeer() = "$countBottles of beer "

    abstract fun takeOneDown(): String

    protected open fun nextVerseBottlesOfBeerOnTheWall() = nextVerse()
        .bottlesOfBeerOnTheWall()

    private fun nextVerse() = aVerse(count - 1, writer)

    companion object {
        fun sing(count: Int) = StringWriter()
            .also { aVerse(count, it).sing() }
            .toString()
    }
}

private fun aVerse(count: Int, writer: Writer): Verse = when {
    count > 1 -> PrecedingVerse(count, writer)
    count == 1 -> PenultimateVerse(writer)
    count == 0 -> UltimateVerse(writer)
    else -> NullVerse(writer)
}