package info.digitalpoet.gradle.shell

import java.io.File
import java.io.OutputStream

/** <!-- Documentation for: info.digitalpoet.gradle.shell.exec on 31/08/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class Command(val process: ProcessBuilder)
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    var directory: File? = null

    var redirectError: File? = null

    var redirectOutput: File? = null

    //~ Constructors ===================================================================================================

    constructor(command: List<String>): this(ProcessBuilder(command))

    @Suppress("SpreadOperator")
    constructor(vararg args: String): this(args.toList())

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    operator fun invoke(timeout: Long? = null, redirect: List<OutputStream> = listOf()): CommandResult
    {
        directory?.apply { process.directory(this) }
        redirectError?.apply { process.redirectError(this) }
        redirectOutput?.apply { process.redirectInput(this) }

        return if (timeout == null) CommandResult(process.start(), redirect)
        else CommandResult(process.start(), timeout, redirect)
    }

    //~ Operators ======================================================================================================
}
