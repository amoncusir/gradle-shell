package info.digitalpoet.gradle.shell

import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.TimeUnit

/** <!-- Documentation for: info.digitalpoet.gradle.shell.CommandResult on 31/08/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
@Suppress("MemberVisibilityCanBePrivate")
class CommandResult(
    error: InputStream,
    input: InputStream,
    exitCodeProcess: () -> Int,
    redirectInput: List<OutputStream> = listOf(),
    redirectError: List<OutputStream> = listOf()
)
{
    //~ Constants ======================================================================================================

    private val standardStream: OutputStream = object: OutputStream()
    {
        override fun write(b: Int)
        {
            stackedStandardOutput.append(b.toChar())
        }

        override fun write(b: ByteArray)
        {
            stackedStandardOutput.append(b.toString())
        }
    }

    private val errorStream: OutputStream = object: OutputStream()
    {
        override fun write(b: Int)
        {
            stackedStandardError.append(b.toChar())
        }

        override fun write(b: ByteArray)
        {
            stackedStandardError.append(b.toString())
        }
    }

    //~ Values =========================================================================================================

    val exitCode: Int by lazy(exitCodeProcess)

    val output: String
        get() = if (hasSuccess()) getStandardOutput() else getStandardError()

    private val stackedStandardOutput: StringBuffer = StringBuffer()

    private val stackedStandardError: StringBuffer = StringBuffer()

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    @Throws(InterruptedException::class)
    constructor(process: Process, redirect: List<OutputStream> = listOf()):
            this (process.errorStream, process.inputStream, { process.waitFor() }, redirect, redirect)

    @Throws(InterruptedException::class)
    constructor(process: Process, wait: Long, redirect: List<OutputStream> = listOf()):
            this (process.errorStream, process.inputStream,
                  { process.waitFor(wait, TimeUnit.MILLISECONDS); process.exitValue() }, redirect, redirect)

    init
    {
        input.redirect(redirectInput.plus(standardStream))
        error.redirect(redirectError.plus(errorStream))
    }

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    private fun InputStream.redirect(outputs: List<OutputStream>)
    {
        copyTo(MultiOutputStream(outputs.toTypedArray()))
    }

    fun hasSuccess(): Boolean = exitCode == 0

    fun getStandardOutput(): String
    {
        return stackedStandardOutput.toString()
    }

    fun getStandardError(): String
    {
        return stackedStandardError.toString()
    }

    //~ Operators ======================================================================================================
}
