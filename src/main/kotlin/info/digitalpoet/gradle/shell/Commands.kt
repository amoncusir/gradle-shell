package info.digitalpoet.gradle.shell

import java.io.File

/** <!-- Documentation for: info.digitalpoet.gradle.shell.Commands on 3/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
//~ Constants ==========================================================================================================

//~ Functions ==========================================================================================================

fun command(vararg args: String, directory: String? = null, timeout: Long? = null): CommandResult
{
    val command = Command(args.asList())

    directory?.apply { command.directory = File(this) }

    return command(timeout)
}

@Suppress("SpreadOperator")
fun bash(vararg args: String, directory: String? = null, timeout: Long? = null): CommandResult
        = command("/bin/bash", "-c", *args, directory = directory, timeout = timeout)

@Suppress("SpreadOperator")
fun sh(vararg args: String, directory: String? = null, timeout: Long? = null): CommandResult
        = command("/bin/sh", "-c", *args, directory = directory, timeout = timeout)

//~ Extensions =========================================================================================================

//~ Annotations ========================================================================================================

//~ Interfaces =========================================================================================================

//~ Enums ==============================================================================================================

//~ Data Classes =======================================================================================================

//~ Classes ============================================================================================================

//~ Sealed Classes =====================================================================================================

//~ Objects ============================================================================================================
