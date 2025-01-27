// Huffman Coding  (instructor-provided file)

import java.io.*;

/**
 * The BitOutputStream and BitInputStream classes provide the ability to
 * write and read individual bits to a file in a compact form.  One minor
 * limitation of this approach is that the resulting file will always have
 * a number of bits that is a multiple of 8.  In effect, whatever bits are
 * output to the file are padded at the end with 0s to make the total
 * number of bits a multiple of 8.
 * 
 * @author Marty Stepp, Stuart Reges, and Owen Astrachan
 */
public class BitOutputStream extends OutputStream {
    public static final boolean DEBUG = false;   // set true for debug printlns
    public static final int BYTE_SIZE = 8;       // digits per byte
    public static final char EOF = (char) 256;   // character for end-of-file
    
    /**
     * Converts the given character into a format that prints well on the screen.
     * For most characters, this just means enclosing it in ' ' marks.
     * For special characters such as \n, replaces them with a \ and an n.
     * Also replaces the special EOF character with the string "EOF".
     * @param ch the character to convert, such as '\n'
     * @return the formatted string, such as "'\\n'"
     */
    public static String toPrintable(char ch) {
        switch (ch) {
            case '\0':                 return "'\\0'";
            case '\n':                 return "'\\n'";
            case '\r':                 return "'\\r'";
            case '\t':                 return "'\\t'";
            case '\f':                 return "'\\f'";
            case BitOutputStream.EOF:  return "EOF";
            default:                   return "'" + ch + "'";
        }
    }


    private OutputStream output;  // actual target to write to
    private boolean open;         // true if still open for writing
    private int digits;           // buffer to build up next byte's digits <= 8
    private int numDigits;        // how many digits are currently in buffer
    private boolean bitMode;      // true if writing bits; false to debug ASCII
    private boolean seenEOF;      // true if this output stream has written EOF
    private String eofEncoding;   // bits to write at end of file to mark EOF
    
    /**
     * Creates a BitOutputStream to send output to the stream in 'bit mode'.
     * Precondition: The given file is legal and can be written.
     * @param output the target output stream to write.
     * @throws NullPointerException if the given output stream is null. 
     */
    public BitOutputStream(OutputStream output) {
        this(output, true);
    }
    
    /**
     * Creates a BitOutputStream to send output to the stream in the given mode.
     * Precondition: The given file is legal and can be written.
     * @param output the target output stream to write.
     * @param bitMode true to write bits at a time; false to write ASCII 
     *                characters (bytes) at a time for debugging.
     * @throws NullPointerException if the given output stream is null. 
     */
    public BitOutputStream(OutputStream output, boolean bitMode) {
        if (output == null) {
            throw new NullPointerException("should not pass a null output stream");
        }
        this.output = output;
        setBitMode(bitMode);
        digits = 0;
        numDigits = 0;
        open = true;
        seenEOF = false;
    }

    /**
     * Closes this output stream for writing and flushes any data to be written.
     * @throws IOException if the file cannot be closed. 
     */
    @Override
    public void close() throws IOException {
        if (open) {
            // add pseudo-EOF character (so BitInputStream knows where EOF is)
            if (eofEncoding != null) {
                writeBits(eofEncoding);
            }
            
            if (numDigits > 0) {
                flush();

                if (!bitMode) {
                    // pad to a multiple of 8 bits to match bit mode
                    for (int i = numDigits; i < BYTE_SIZE; i++) {
                        writeBit(0);
                    }
                }
            }
            
            output.close();
            open = false;
            seenEOF = true;
        }
    }

    /**
     * Flushes the buffer.  If numDigits < BYTE_SIZE, this will 
     * effectively pad the output with extra 0s, so this should
     * be called only when numDigits == BYTE_SIZE or when we are
     * closing the output.
     * @throws IOException if the bits cannot be written. 
     */
    @Override
    public void flush() throws IOException {
        if (inBitMode()) {
            write(digits);
            digits = 0;
            numDigits = 0;
        }
    }
    
    /**
     * Returns true if this output stream has ever written an EOF character;
     * students should not call this method.
     * This is a hack so that a BitInputStream knows when it is out of bits.
     * @return true if EOF has been seen, otherwise false.
     */
    public boolean hasSeenEOF() {
        return seenEOF;
    }
    
    /**
     * Returns whether this stream is in real 'bit mode', writing a bit to the
     * file for each call to writeBit.  The alternative is 'byte mode', where a 
     * full byte (ASCII character) of '0' or '1' is written for each call to 
     * writeBit, to make it easier to debug your program.
     * @return true if in bit mode, false if in byte mode.
     */
    public boolean inBitMode() {
        return bitMode;
    }
    
    /**
     * Sets whether this stream is in real 'bit mode', writing a bit to the
     * file for each 0 or 1 written in writeBit (as described under inBitMode).
     * Ignores the caller and always uses 'byte' mode if writing to System.out
     * or if the JVM bitstream.bitmode environment variable is set.
     * @param bitMode true to use bit mode, false to use character mode.
     */
    public void setBitMode(boolean bitMode) {
        this.bitMode = bitMode && output != System.out && output != System.err
                && System.getProperty("bitstream.bitmode") == null;
    }
    
    /**
     * Sets this bit output stream to use the given pattern of bits as its
     * encoding for the end of a file (EOF); students should not call this method.
     * @param eofEncoding the EOF encoding to use
     * @throws NullPointerException if the string is null.
     */
    public void setEOFEncoding(String eofEncoding) {
        if (eofEncoding == null) {
            throw new NullPointerException("should not pass a null EOF encoding");
        }
        this.eofEncoding = eofEncoding;
    }

    /**
     * Writes the given byte to output.
     * @param b the byte value to write.
     * @throws IOException if the byte cannot be written. 
     */
    @Override
    public void write(int b) throws IOException {
        if (b == EOF || seenEOF) {
            if (DEBUG) System.out.println("  ** BitOutputStream EOF seen");
            seenEOF = true;
        } else {
            if (DEBUG) System.out.println("  ** BitOutputStream write: " + b + " (" 
                    + toPrintable((char) b) + ")");
            output.write(b);
        }
    }
    
    /**
     * Writes the given array of bytes to output.
     * @param bytes the byte values to write.
     * @throws IOException if the bytes cannot be written. 
     * @throws NullPointerException if bytes is null. 
     */
    @Override
    public void write(byte[] bytes) throws IOException {
        write(bytes, 0, bytes.length);
    }
    
    /**
     * Writes the given byte to output.
     * @param bytes the byte value to write.
     * @param offset the beginning index to use.
     * @param length the number of indexes to write.
     * @throws IOException if the bytes cannot be written. 
     * @throws NullPointerException if bytes is null. 
     */
    @Override
    public void write(byte[] bytes, int offset, int length) throws IOException {
        if (seenEOF) {
            if (DEBUG) System.out.println("  ** BitOutputStream EOF seen");
            seenEOF = true;
        } else {
            if (DEBUG) System.out.println("  ** BitOutputStream write: "
                    + java.util.Arrays.toString(bytes) + ", " + offset + ", "
                    + length);
            output.write(bytes, offset, length);
        }
    }
    
    /**
     * Writes the given bit to output.
     * @param bit the bit value to write.
     * @throws IllegalArgumentException if bit is not 0 or 1.
     * @throws IOException if the bit cannot be written. 
     */
    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1) {
            throw new IllegalArgumentException("Illegal bit value: " + bit);
        }
        
        if (inBitMode()) {
            // pad shifted bit into our digit buffer; flush when we hit 8 digits
            digits += bit << numDigits;
            numDigits++;
            if (DEBUG) System.out.println("  ** BitOutputStream writeBit: " + bit);
            if (numDigits == BYTE_SIZE) {
                flush();
            }
        } else {
            // non-bit mode; just write ASCII character
            numDigits = (numDigits + 1) % 8;
            write((char) (bit + '0'));
        }
    }
    
    /**
     * Writes every bit in the given string of 0s and 1s.
     * @param bits A string entirely of 0s and 1s, such as "01001100110".
     * @throws IllegalArgumentException if the string contains any characters
     *                                  other than '0' or '1'.
     * @throws NullPointerException if the string is null.
     */
    public void writeBits(String bits) throws IOException {
        for (int i = 0; i < bits.length(); i++) {
            char ch = bits.charAt(i);
            if (ch == '0' || ch == 0) {
                writeBit(0);
            } else if (ch == '1' || ch == 1) {
                writeBit(1);
            } else {
                throw new IllegalArgumentException("Illegal bit value '" + ch + 
                        "' at index " + i + " of string \"" + bits + "\"");
            }
        }
    }

//    /**
//     * Writes every character in the given string of 0s and 1s as a byte.
//     * @param bytes A string entirely of 0s and 1s, such as "01001100110".
//     * @throws IllegalArgumentException if the string contains any characters
//     *                                  other than '0' or '1'.
//     */
//    public void writeBytes(String bytes) throws IOException {
//        for (int i = 0; i < bytes.length(); i++) {
//            char ch = bytes.charAt(i);
//            if (ch == '0' || ch == 0) {
//                write('0');
//            } else if (ch == '1' || ch == 1) {
//                write('1');
//            } else {
//                throw new IllegalArgumentException("Illegal bit value '" + ch + 
//                        "' at index " + i + " of string \"" + bytes + "\"");
//            }
//        }
//    }

    /**
     * Runs when the object is garbage collected / program shuts down.
     * Included to ensure that the stream is closed.
     */
    protected void finalize() {
        try {
            close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
