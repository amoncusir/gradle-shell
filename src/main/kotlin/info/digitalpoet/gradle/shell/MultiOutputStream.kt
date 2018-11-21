package info.digitalpoet.gradle.shell

import java.io.OutputStream

/** <!-- Documentation for: info.digitalpoet.gradle.shell.MultiOutputStream on 3/09/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class MultiOutputStream(val redirect: Array<out OutputStream>): OutputStream()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    /**
     * Writes the specified byte to this output stream. The general
     * contract for `write` is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument `b`. The 24
     * high-order bits of `b` are ignored.
     *
     *
     * Subclasses of `OutputStream` must provide an
     * implementation for this method.
     *
     * @param      b   the `byte`.
     * @exception  IOException  if an I/O error occurs. In particular,
     * an `IOException` may be thrown if the
     * output stream has been closed.
     */
    override fun write(b: Int)
    {
        for (stream in redirect)
        {
            stream.write(b)
        }
    }

    /**
     * Flushes this output stream and forces any buffered output bytes
     * to be written out. The general contract of `flush` is
     * that calling it is an indication that, if any bytes previously
     * written have been buffered by the implementation of the output
     * stream, such bytes should immediately be written to their
     * intended destination.
     *
     *
     * If the intended destination of this stream is an abstraction provided by
     * the underlying operating system, for example a file, then flushing the
     * stream guarantees only that bytes previously written to the stream are
     * passed to the operating system for writing; it does not guarantee that
     * they are actually written to a physical device such as a disk drive.
     *
     *
     * The `flush` method of `OutputStream` does nothing.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    override fun flush()
    {
        for (stream in redirect)
        {
            stream.flush()
        }
    }

    /**
     * Closes this output stream and releases any system resources
     * associated with this stream. The general contract of `close`
     * is that it closes the output stream. A closed stream cannot perform
     * output operations and cannot be reopened.
     *
     *
     * The `close` method of `OutputStream` does nothing.
     *
     * @exception  IOException  if an I/O error occurs.
     */
    override fun close()
    {
        for (stream in redirect)
        {
            stream.close()
        }
    }

    //~ Operators ======================================================================================================
}
