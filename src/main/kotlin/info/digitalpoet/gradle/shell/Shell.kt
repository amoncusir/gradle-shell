package info.digitalpoet.gradle.shell

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.OutputStream

/** <!-- Documentation for: info.digitalpoet.gradle.shell.Shell on 31/08/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
@Suppress("unused")
open class Shell: DefaultTask()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    var accumulator: MutableList<List<String>> = mutableListOf()

    var cd: String? = null

    var runInsideProject = true

    var interpreter: Array<String> = arrayOf()

    var output: Array<OutputStream> = arrayOf(System.out)

    val results: MutableList<CommandResult> = mutableListOf()

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    fun sh(args: Collection<String>)
    {
        accumulator.add(args.toList())
    }

    fun sh(args: Array<String>)
    {
        accumulator.add(args.toList())
    }

    @TaskAction
    fun runCommands()
    {
        for (script in accumulator)
        {
            results.add(exec(script))
        }
    }

    private fun exec(args: List<String>): CommandResult
    {
        val command = Command(interpreter.plus(args).asList())
        val directory: String? = if (runInsideProject) "${project.projectDir}/${cd ?: ""}"
                        else cd

        directory?.apply { command.directory = File(this) }

        println("Run command: ${args.joinToString(" ")}")

        return command(redirect = output.asList())
    }

    //~ Operators ======================================================================================================
}
